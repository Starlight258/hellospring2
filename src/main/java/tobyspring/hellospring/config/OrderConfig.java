package tobyspring.hellospring.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.PlatformTransactionManager;
import tobyspring.hellospring.data.JdbcOrderRepository;
import tobyspring.hellospring.domain.order.OrderRepository;
import tobyspring.hellospring.domain.order.OrderService;

@Configuration
@Import(DataConfig.class)
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(final DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(final OrderRepository orderRepository,
                                     final PlatformTransactionManager transactionManager) {
        return new OrderService(orderRepository, transactionManager);
    }
}
