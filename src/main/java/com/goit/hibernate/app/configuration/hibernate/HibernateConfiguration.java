package com.goit.hibernate.app.configuration.hibernate;

import com.goit.hibernate.app.configuration.Environment;
import jakarta.persistence.Entity;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.Optional;
import java.util.Set;

import static com.goit.hibernate.app.util.Constants.BASE_PACKAGE;

@Slf4j
public class HibernateConfiguration {

    public static Configuration setup(Environment environment, Class<?>... classesToRegister) {
        Configuration configuration = new Configuration();
        String driverProperty = environment.getProperty(AvailableSettings.DRIVER);
        String urlProperty = environment.getProperty(AvailableSettings.URL);
        String showSqlProperty = environment.getProperty(AvailableSettings.SHOW_SQL);
        Optional<String> dialectProperty = environment.getPropertyOptional(AvailableSettings.DIALECT);
        Optional<String> userProperty = environment.getPropertyOptional(AvailableSettings.USER);
        Optional<String> passwordProperty = environment.getPropertyOptional(AvailableSettings.PASS);

        configuration.setProperty(AvailableSettings.DRIVER, driverProperty);
        configuration.setProperty(AvailableSettings.URL, urlProperty);
        configuration.setProperty(AvailableSettings.SHOW_SQL, showSqlProperty);

        dialectProperty.ifPresent(dialect -> configuration.setProperty(AvailableSettings.DIALECT, dialect));
        userProperty.ifPresent(userName -> configuration.setProperty(AvailableSettings.USER, userName));
        passwordProperty.ifPresent(password -> configuration.setProperty(AvailableSettings.PASS, password));


        Reflections reflections = environment.getPropertyOptional(BASE_PACKAGE)
                .map(Reflections::new)
                .orElse(new Reflections());
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(Entity.class);
        log.info("Found:[{}] entities", classes.size());
        if (classesToRegister.length == 0) {
            classes.forEach(configuration::addAnnotatedClass);
        } else {
            Arrays.stream(classesToRegister).forEach(configuration::addAnnotatedClass);
        }
        return configuration;
    }
}
