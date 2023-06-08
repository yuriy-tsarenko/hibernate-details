package com.goit.hibernate.app.configuration.hibernate;

import com.goit.hibernate.app.configuration.Environment;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.classloading.internal.ClassLoaderServiceImpl;
import org.hibernate.boot.registry.classloading.spi.ClassLoaderService;
import org.hibernate.cfg.Configuration;

import java.util.Objects;

public class Datasource {

    private final Configuration configuration;
    private final SessionFactory sessionFactory;
    private Session session;

    public Datasource(Environment environment, Class<?>... entities) {
        this.configuration = HibernateConfiguration.setup(environment, entities);
        this.sessionFactory = createSessionFactory();
    }

    public Session openSession() {
        if (Objects.nonNull(session) && session.isOpen()) {
            session.flush();
            session.close();
        }
        session = sessionFactory.openSession();
        return session;
    }

    public Session currentSession() {
        return session;
    }

    public boolean hasActiveSession() {
        return Objects.nonNull(session) && session.isOpen();
    }

    private SessionFactory createSessionFactory() {
        final ClassLoaderService classLoaderService = new ClassLoaderServiceImpl(getClass().getClassLoader());
        return configuration.buildSessionFactory(
                new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties())
                        .addService(ClassLoaderService.class, classLoaderService)
                        .build()
        );
    }
}
