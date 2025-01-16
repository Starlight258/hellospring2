package tobyspring.hellospring.config;

import static org.assertj.core.api.Assertions.assertThat;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import java.math.BigDecimal;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.domain.order.Order;

class DataConfigTest {

    @Test
    @DisplayName("DataConfig 빈 등록 테스트")
    void DataConfig_빈_등록_테스트() {
        // Given
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);

        // When
        EntityManagerFactory emf = beanFactory.getBean(EntityManagerFactory.class);

        // Then
        assertThat(emf).isNotNull();
    }

    @Test
    @DisplayName("Entity 저장 테스트")
    void Entity_저장_테스트() {
        // Given
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        EntityManagerFactory entityManagerFactory = beanFactory.getBean(EntityManagerFactory.class);
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        // When
        Order order = new Order("100", BigDecimal.TEN);
        entityManager.persist(order);

        // Then
        assertThat(order).extracting("no", "total")
                .containsExactly("100", BigDecimal.TEN);

        transaction.commit();
        entityManager.close();
    }
}
