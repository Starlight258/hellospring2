package tobyspring.hellospring.config;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import tobyspring.hellospring.data.OrderRepository;
import tobyspring.hellospring.domain.order.Order;

@SpringBootTest
@Import(DataConfig.class)
class DataClientTest {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    @Test
    @DisplayName("DataConfig 빈 등록 테스트")
    void DataConfig_빈_등록_테스트() {
        // Given

        // When

        // Then
        assertThat(entityManager).isNotNull();
    }

    @Test
    @DisplayName("Entity 저장 테스트")
    @Transactional
    void Entity_저장_테스트() {
        // Given
        Order order = new Order("100", BigDecimal.TEN);

        // When & Then
        Assertions.assertThatCode(() -> {
            orderRepository.save(order);
        }).doesNotThrowAnyException();
    }
}
