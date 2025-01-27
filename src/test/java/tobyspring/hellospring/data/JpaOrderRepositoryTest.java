package tobyspring.hellospring.data;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tobyspring.hellospring.config.OrderConfig;
import tobyspring.hellospring.domain.order.Order;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = OrderConfig.class)
@Transactional
class JpaOrderRepositoryTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private JpaOrderRepository jpaOrderRepository;

    @Test
    void 엔티티를_저장한다() {
        // Given
        Order order = new Order("100", BigDecimal.TEN);

        // When & Then
        Assertions.assertThatCode(() -> {
            jpaOrderRepository.save(order);
        }).doesNotThrowAnyException();
    }

    @Test
    void 같은_id를_가진_엔티티를_저장하면_예외가_발생한다() {
        // Given
        Order order = new Order("100", BigDecimal.TEN);
        jpaOrderRepository.save(order);

        // When & Then
        assertThatThrownBy(() -> {
            Order order2 = new Order("100", BigDecimal.TEN);
            jpaOrderRepository.save(order2);
            entityManager.flush();
        }).isInstanceOf(ConstraintViolationException.class);
    }
}
