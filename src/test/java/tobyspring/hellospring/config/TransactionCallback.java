package tobyspring.hellospring.config;

import jakarta.persistence.EntityManager;

public interface TransactionCallback<T> {

    T execute(EntityManager em);
}
