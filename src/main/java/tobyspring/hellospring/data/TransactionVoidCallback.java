package tobyspring.hellospring.data;

import jakarta.persistence.EntityManager;

@FunctionalInterface
public interface TransactionVoidCallback {

    void execute(EntityManager em);
}
