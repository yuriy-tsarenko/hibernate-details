package com.goit.hibernate.app.repository;

import com.goit.hibernate.app.configuration.hibernate.Datasource;
import com.goit.hibernate.app.entity.CustomerEntity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public class CustomerEntityRepository {

    private final Datasource datasource;

    public CustomerEntityRepository(Datasource datasource) {
        this.datasource = datasource;
    }

    public List<CustomerEntity> findAll() {
        return dbCall(session -> session
                .createQuery("select c from CustomerEntity c", CustomerEntity.class)
                .getResultList());
    }

    public CustomerEntity findById(Long id) {
        return dbCall(session -> {
            String queryString = "select c from CustomerEntity c where c.id=:id";
            Query<CustomerEntity> query = session.createQuery(queryString, CustomerEntity.class);
            query.setParameter("id", id);
            CustomerEntity result;
            try {
                result = query.getSingleResult();
            } catch (Exception e) {
                log.warn("no results found", e);
                result = null;
            }
            return result;
        });
    }

    public CustomerEntity save(CustomerEntity entity) {
        dbVoidCall(session -> persist(entity, session));
        return entity;
    }

    public int delete(CustomerEntity entity) {
        Long id = entity.getId();
        return deleteById(id);
    }

    public int deleteById(Long id) {
        return dbCall(session -> {
            String queryString = "delete from CustomerEntity c where c.id=:id";
            MutationQuery mutationQuery = session.createMutationQuery(queryString);
            mutationQuery.setParameter("id", id);
            return mutationQuery.executeUpdate();
        });
    }

    private CustomerEntity persist(CustomerEntity entity, Session session) {
        CustomerEntity saved = session.merge(entity);
        entity.setId(saved.getId());
        return entity;
    }

    private <R> R dbCall(Function<Session, R> function) {
        try (Session session = datasource.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        } catch (Exception e) {
            log.error("db execution failed", e);
            throw new RuntimeException(e);
        }
    }

    private void dbVoidCall(Consumer<Session> function) {
        try (Session session = datasource.openSession()) {
            Transaction transaction = session.beginTransaction();
            function.accept(session);
            transaction.commit();
        } catch (Exception e) {
            log.error("db execution failed", e);
            throw new RuntimeException(e);
        }
    }
}
