package tobyspring.hellospring.config;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.domain.order.Order;
import tobyspring.hellospring.domain.order.OrderRepository;

@ExtendWith(SpringExtension.class)
@Import(OrderConfig.class)
class OrderClientTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("Entity 저장 테스트")
    @Transactional
    void Entity_저장_테스트() {
        // Given
        Order order = new Order("100", BigDecimal.TEN);
        order.setId(1L);

        // When & Then
        Assertions.assertThatCode(() -> {
            orderRepository.save(order);
        }).doesNotThrowAnyException();
    }
}
