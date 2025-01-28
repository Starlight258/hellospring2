package tobyspring.hellospring.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.math.BigDecimal;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.config.OrderConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private DataSource dataSource;

    @Test
    void 성공_주문생성() {
        // Given

        // When
        Order savedOrder = orderService.createOrder("0100", BigDecimal.TEN);

        // Then
        assertThat(savedOrder).extracting("no", "total")
                .containsExactly("0100", BigDecimal.TEN);
    }

    @Test
    void 성공_여러주문생성() {
        // Given
        List<OrderReq> orderReqs = List.of(
                new OrderReq("0200", BigDecimal.ONE),
                new OrderReq("0201", BigDecimal.TWO)
        );

        // When
        List<Order> orders = orderService.createOrders(orderReqs);

        // Then
        assertAll(
                () -> assertThat(orders).hasSize(2),
                () -> orders.forEach(order -> assertThat(order.getId()).isGreaterThan(0))
        );
    }

    @Test
    void 실패_여러주문생성_트랜잭션내중복key() {
        // Given
        List<OrderReq> orderReqs = List.of(
                new OrderReq("0300", BigDecimal.ONE),
                new OrderReq("0300", BigDecimal.TWO)
        );

        // When & Then
        Assertions.assertAll(
                () -> assertThatThrownBy(() -> orderService.createOrders(orderReqs))
                        .isInstanceOf(DataIntegrityViolationException.class),
                () -> assertThat(getCount("0300")).isEqualTo(0)
        );
    }

    private Long getCount(final String key) {
        JdbcClient client = JdbcClient.create(dataSource);
        return client.sql("select count(*) from orders where no = " + key)
                .query(Long.class)
                .single();
    }
}
