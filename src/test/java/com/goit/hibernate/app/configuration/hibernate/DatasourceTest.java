package com.goit.hibernate.app.configuration.hibernate;

import com.goit.hibernate.app.HibernateApplicationTest;
import org.hibernate.Session;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DatasourceTest extends HibernateApplicationTest {

    @Test
    void openSession() {
        //Given
        Datasource datasource = new Datasource(environment);

        //When
        Session session = datasource.openSession();

        //Then
        Assertions.assertTrue(session.isOpen());

        session.close();
    }
}
