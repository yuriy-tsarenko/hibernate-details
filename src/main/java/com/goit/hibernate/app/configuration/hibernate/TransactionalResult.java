package com.goit.hibernate.app.configuration.hibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Optional;

public class TransactionalResult<T> implements AutoCloseable {
    private final Session session;
    private final Transaction transaction;
    private final T value;

    private TransactionalResult(Session session,
                                Transaction transaction,
                                T value) {
        this.session = session;
        this.transaction = transaction;
        this.value = value;
    }

    public static <T> TransactionalResult<T> of(Session session,
                                                Transaction transaction,
                                                T value) {
        return new TransactionalResult<>(session, transaction, value);
    }

    public Optional<T> getValue() {
        return Optional.ofNullable(value);
    }

    @Override
    public void close() {
        if (transaction.isActive()) {
            transaction.commit();
        }
        if (session.isOpen()) {
            session.close();
        }
    }
}
