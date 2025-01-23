package tobyspring.hellospring.data;

import tobyspring.hellospring.domain.order.Order;

public class OrderRepository {

    private final TransactionTemplate transactionTemplate;

    public OrderRepository(final TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }

    public void save(final Order order) {
        transactionTemplate.executeWithoutResult(entityManager -> entityManager.persist(order));
    }
}
