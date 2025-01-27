package tobyspring.hellospring.domain.order;

import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.config.OrderConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Test
    void createOrder() {
        // Given

        // When
        Order savedOrder = orderService.createOrder("0100", BigDecimal.TEN);

        // Then
        Assertions.assertThat(savedOrder).extracting("no", "total")
                .containsExactly("0100", BigDecimal.TEN);
    }
}
