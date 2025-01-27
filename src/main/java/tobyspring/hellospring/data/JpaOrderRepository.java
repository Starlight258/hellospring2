package tobyspring.hellospring.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import tobyspring.hellospring.domain.order.Order;

public class JpaOrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(final Order order) {
        entityManager.persist(order);
    }
}
