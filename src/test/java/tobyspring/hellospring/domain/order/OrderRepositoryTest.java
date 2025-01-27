package tobyspring.hellospring.domain.order;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.config.OrderConfig;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
@Transactional
class OrderRepositoryTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void 엔티티를_저장한다() {
        // Given
        Order order = new Order("100", BigDecimal.TEN);

        // When & Then
        Assertions.assertThatCode(() -> {
            orderRepository.save(order);
        }).doesNotThrowAnyException();
    }

    @Test
    void 같은_id를_가진_엔티티를_저장하면_예외가_발생한다() {
        // Given
        Order order = new Order("100", BigDecimal.TEN);
        orderRepository.save(order);

        // When & Then
        assertThatThrownBy(() -> {
            Order order2 = new Order("100", BigDecimal.TEN);
            orderRepository.save(order2);
        }).isInstanceOf(DuplicateKeyException.class);
    }

    @Test
    void 각각_다른_id를_가진_엔티티를_저장한다() {
        // Given
        Order order = new Order("100", BigDecimal.TEN);
        orderRepository.save(order);

        // When
        Order order2 = new Order("101", BigDecimal.TEN);
        orderRepository.save(order2);

        // Then
        assertAll(
                () -> assertThat(order.getId()).isOne(),
                () -> assertThat(order2.getId()).isEqualTo(51)
        );
    }
}
