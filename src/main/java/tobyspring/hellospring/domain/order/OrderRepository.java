package tobyspring.hellospring.domain.order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class OrderRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(final Order order) {
        entityManager.persist(order);
    }
}
