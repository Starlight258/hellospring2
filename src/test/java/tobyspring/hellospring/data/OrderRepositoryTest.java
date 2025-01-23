package tobyspring.hellospring.data;

import java.math.BigDecimal;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import tobyspring.hellospring.config.DataConfig;
import tobyspring.hellospring.domain.order.Order;

class OrderRepositoryTest {

    private OrderRepository orderRepository;

    @BeforeEach
    void setUp() {
        BeanFactory beanFactory = new AnnotationConfigApplicationContext(DataConfig.class);
        this.orderRepository = beanFactory.getBean(OrderRepository.class);
    }

    @Test
    void save() {
        // Given
        Order order = new Order("100", BigDecimal.TEN);

        // When & Then
        Assertions.assertThatCode(() -> {
            orderRepository.save(order);
        }).doesNotThrowAnyException();
    }
}
