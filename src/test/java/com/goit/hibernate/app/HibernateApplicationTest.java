package com.goit.hibernate.app;

import com.goit.hibernate.app.configuration.Environment;
import com.goit.hibernate.app.configuration.LoggingConfiguration;
import com.goit.hibernate.app.configuration.hibernate.Datasource;
import org.junit.jupiter.api.BeforeEach;

public class HibernateApplicationTest {

    protected Environment environment;
    protected Datasource datasource;

    @BeforeEach
    public void setup() {
        environment = Environment.load();
        LoggingConfiguration.setup(environment);
        datasource = new Datasource(environment);
    }
}
