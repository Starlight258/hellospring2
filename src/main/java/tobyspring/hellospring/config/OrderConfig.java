package tobyspring.hellospring.config;

import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tobyspring.hellospring.data.JdbcOrderRepository;
import tobyspring.hellospring.domain.order.OrderRepository;
import tobyspring.hellospring.domain.order.OrderService;
import tobyspring.hellospring.domain.order.OrderServiceImpl;

@Configuration
@Import(DataConfig.class)
@EnableTransactionManagement
public class OrderConfig {

    @Bean
    public OrderRepository orderRepository(final DataSource dataSource) {
        return new JdbcOrderRepository(dataSource);
    }

    @Bean
    public OrderService orderService(final DataSourceTransactionManager transactionManager,
                                     final OrderRepository orderRepository) {
        return new OrderServiceImpl(orderRepository);
    }
}
