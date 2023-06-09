package com.goit.hibernate.app.configuration.hibernate.util;

import com.goit.hibernate.app.configuration.hibernate.Datasource;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.function.Function;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DbUtils {

    public static <R> R dbCall(Datasource datasource, Function<Session, R> function) {
        return dbCall(datasource, function, true);
    }

    public static <R> R dbCall(Datasource datasource, Function<Session, R> function, boolean autocommit) {
        try {
            Session session;
            Transaction transaction;
            R result;
            if (datasource.hasActiveSession() && datasource.currentSession().getTransaction().isActive()) {
                log.warn("Observed externally controlled session, it won't be closed at the repository level");
                session = datasource.currentSession();
                result = function.apply(session);
            } else {
                session = datasource.openSession();
                transaction = session.beginTransaction();
                result = function.apply(session);
                if (autocommit) {
                    transaction.commit();
                    session.close();
                }
            }
            return result;
        } catch (Exception e) {
            log.error("db execution failed", e);
            throw new RuntimeException(e);
        }
    }
}
