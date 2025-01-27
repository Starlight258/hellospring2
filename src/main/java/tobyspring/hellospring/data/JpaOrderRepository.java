package tobyspring.hellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tobyspring.hellospring.domain.order.Order;
import tobyspring.hellospring.domain.order.OrderRepository;

public class JpaOrderRepository implements OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(final Order order) {
        entityManager.persist(order);
    }
}
