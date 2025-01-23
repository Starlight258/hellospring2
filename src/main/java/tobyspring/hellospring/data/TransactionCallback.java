package tobyspring.hellospring.data;

import jakarta.persistence.EntityManager;

@FunctionalInterface
public interface TransactionCallback<T> {

    T execute(EntityManager em);
}
