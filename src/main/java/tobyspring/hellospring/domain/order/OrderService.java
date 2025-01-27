package tobyspring.hellospring.domain.order;

import java.math.BigDecimal;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionTemplate;
import tobyspring.hellospring.data.JpaOrderRepository;

@Service
public class OrderService {

    private final JpaOrderRepository jpaOrderRepository;
    private final JpaTransactionManager transactionManager;

    public OrderService(final JpaOrderRepository jpaOrderRepository, final JpaTransactionManager transactionManager) {
        this.jpaOrderRepository = jpaOrderRepository;
        this.transactionManager = transactionManager;
    }

    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);

        return new TransactionTemplate(transactionManager).execute(status -> {
            this.jpaOrderRepository.save(order);
            return order;
        });
    }
}
