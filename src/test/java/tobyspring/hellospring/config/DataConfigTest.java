package tobyspring.hellospring.config;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.math.BigDecimal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.domain.order.Order;

class DataConfigTest {

    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
    }

    @Test
    @DisplayName("DataConfig 빈 등록 테스트")
    void DataConfig_빈_등록_테스트() {
        // Given

        // When
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        // Then
        assertThat(emf).isNotNull();
    }

    @Test
    @DisplayName("Entity 저장 테스트")
    void Entity_저장_테스트() {
        // Given

        // When
        Order savedOrder = executeTransaction((entityManager) -> {
            Order order = new Order("100", BigDecimal.TEN);
            entityManager.persist(order);
            return order;
        });

        // Then
        assertThat(savedOrder).extracting("no", "total")
                .containsExactly("100", BigDecimal.TEN);
    }

    private <T> T executeTransaction(TransactionCallback<T> callback) {
        EntityManagerFactory entityManagerFactory = beanFactory.getBean(EntityManagerFactory.class);
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();

        try {
            transaction.begin();
            T result = callback.execute(entityManager);
            transaction.commit();
            return result;
        } catch (Exception e) {
            transaction.rollback();
            throw e;
        } finally {
            entityManager.close();
        }
    }
}
