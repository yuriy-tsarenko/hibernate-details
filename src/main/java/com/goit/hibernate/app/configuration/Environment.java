package com.goit.hibernate.app.configuration;

import java.util.Optional;
import java.util.Properties;

import static com.goit.hibernate.app.util.Constants.DEFAULT_APP_FILE_NAME;


public class Environment {

    private final Properties properties;

    public Environment(Properties properties) {
        this.properties = properties;
    }

    public static Environment load() {
        try {
            Properties properties = new Properties();
            properties.load(Environment.class.getClassLoader().getResourceAsStream(DEFAULT_APP_FILE_NAME));
            return new Environment(properties);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Object getObject(String key) {
        return properties.get(key);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Optional<String> getPropertyOptional(String key) {
        return Optional.ofNullable(properties.getProperty(key));
    }
}
