package com.goit.hibernate.app.configuration.hibernate;

import jakarta.persistence.Id;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.MutationQuery;
import org.hibernate.query.Query;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.lang.String.format;
import static java.lang.String.join;

@Slf4j
public abstract class HibernateAbstractRepository<T, ID> {
    private final Datasource datasource;
    private final Class<T> entityType;
    private final Field idField;
    private final String selectTemplate;
    private final String deleteTemplate;
    private final String whereTemplate;

    @SuppressWarnings("unchecked")
    public HibernateAbstractRepository(Datasource datasource) {
        this.datasource = datasource;
        this.entityType = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        String entityName = entityType.getSimpleName();
        selectTemplate = format("select c from %s c", entityName);
        deleteTemplate = format("delete from %s c", entityName);
        idField = Arrays.stream(entityType.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow();
        idField.setAccessible(true);
        String name = idField.getName();
        whereTemplate = format("where c.%s=:id", name);
    }

    public List<T> findAll() {
        return dbCall(session -> session
                .createQuery(selectTemplate, entityType)
                .getResultList());
    }

    public T findById(ID id) {
        return dbCall(session -> {
            String queryString = join(" ", selectTemplate, whereTemplate);
            Query<T> query = session.createQuery(queryString, entityType);
            query.setParameter("id", id);
            T result;
            try {
                result = query.getSingleResult();
            } catch (Exception e) {
                log.warn("no results found", e);
                result = null;
            }
            return result;
        });
    }

    public T save(T entity) {
        dbVoidCall(session -> save(entity, session));
        return entity;
    }

    @SuppressWarnings("unchecked")
    public int delete(T entity) {
        try {
            ID id = (ID) idField.get(entity);
            return deleteById(id);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteById(ID id) {
        return dbCall(session -> {
            String queryString = join(" ", deleteTemplate, whereTemplate);
            MutationQuery mutationQuery = session.createMutationQuery(queryString);
            mutationQuery.setParameter("id", id);
            return mutationQuery.executeUpdate();
        });
    }

    @SuppressWarnings("unchecked")
    private T save(T entity, Session session) {
        try {
            T saved = session.merge(entityType.getSimpleName(), entity);
            idField.set(entity, (ID) idField.get(saved));
            return entity;
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
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
