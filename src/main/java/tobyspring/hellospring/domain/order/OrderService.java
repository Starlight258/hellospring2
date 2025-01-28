package tobyspring.hellospring.domain.order;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final PlatformTransactionManager transactionManager;

    public OrderService(final OrderRepository orderRepository, final PlatformTransactionManager transactionManager) {
        this.orderRepository = orderRepository;
        this.transactionManager = transactionManager;
    }

    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);

        this.orderRepository.save(order);
        return order;
    }

    public List<Order> createOrders(final List<OrderReq> reqs) {
        return new TransactionTemplate(transactionManager).execute(status ->
                reqs.stream()
                        .map(req -> createOrder(req.no(), req.total()))
                        .toList());
    }
}
