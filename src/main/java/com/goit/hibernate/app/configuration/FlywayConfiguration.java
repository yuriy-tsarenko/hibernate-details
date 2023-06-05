package com.goit.hibernate.app.configuration;

import com.goit.hibernate.app.util.Constants;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.Location;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class FlywayConfiguration {

    public static Flyway setup() throws IOException {
        return setup(Environment.load());
    }

    public static Flyway setup(Environment environment) throws IOException {

        String url = environment.getProperty(Constants.FLYWAY_CONNECTION_URL);
        String username = environment.getProperty(Constants.FLYWAY_USER);
        String password = environment.getProperty(Constants.FLYWAY_PASSWORD);

        Location migrations = new Location("db/migration");
        Location mixtures = new Location("db/mixture");
        return Flyway.configure()
                .encoding(StandardCharsets.UTF_8)
                .locations(migrations, mixtures)
                .dataSource(url, username, password)
                .loggers(environment.getProperty(Constants.FLYWAY_LOGGER))
                .placeholderReplacement(false)
                .failOnMissingLocations(true)
                .load();
    }
}
