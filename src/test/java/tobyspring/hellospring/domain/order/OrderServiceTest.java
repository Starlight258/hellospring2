package tobyspring.hellospring.domain.order;

import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
