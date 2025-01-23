package tobyspring.hellospring.config;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManagerFactory;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.domain.order.Order;
import tobyspring.hellospring.data.TransactionTemplate;

class DataConfigTest {

    private EntityManagerFactory entityManagerFactory;

    @BeforeEach
    void setUp() {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        this.entityManagerFactory = beanFactory.getBean(EntityManagerFactory.class);
    }

    @Test
    @DisplayName("DataConfig 빈 등록 테스트")
    void DataConfig_빈_등록_테스트() {
        // Given

        // When

        // Then
        assertThat(entityManagerFactory).isNotNull();
    }

    @Test
    @DisplayName("Entity 저장 테스트")
    void Entity_저장_테스트() {
        // Given
        TransactionTemplate transactionTemplate = new TransactionTemplate(entityManagerFactory);

        // When
        Order savedOrder = transactionTemplate.execute((entityManager) -> {
            Order order = new Order("100", BigDecimal.TEN);
            entityManager.persist(order);
            return order;
        });

        // Then
        assertThat(savedOrder).extracting("no", "total")
                .containsExactly("100", BigDecimal.TEN);
    }
}
