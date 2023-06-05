package com.goit.hibernate.app;

import com.goit.hibernate.app.configuration.Environment;
import com.goit.hibernate.app.configuration.LoggingConfiguration;
import org.junit.jupiter.api.BeforeEach;

public class HibernateApplicationTest {

    protected Environment environment;

    @BeforeEach
    public void setup() {
        environment = Environment.load();
        LoggingConfiguration.setup(environment);
    }
}
