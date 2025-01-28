package tobyspring.hellospring.domain.order;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(String no, BigDecimal total) {
        Order order = new Order(no, total);

        this.orderRepository.save(order);
        return order;
    }

    @Override
    public List<Order> createOrders(final List<OrderReq> reqs) {
        return reqs.stream()
                .map(req -> createOrder(req.no(), req.total()))
                .toList();
    }
}
