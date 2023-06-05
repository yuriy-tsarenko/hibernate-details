package com.goit.hibernate.app.servlet;

import com.goit.hibernate.app.configuration.Environment;
import com.goit.hibernate.app.configuration.FlywayConfiguration;
import com.goit.hibernate.app.configuration.LoggingConfiguration;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import lombok.extern.slf4j.Slf4j;

import static com.goit.hibernate.app.util.Constants.APP_ENV;

@Slf4j
public class ApplicationInitServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) {
        try {
            Environment environment = Environment.load();
            log.info("Started configuration init");
            LoggingConfiguration.setup(environment);
            FlywayConfiguration.setup(environment).migrate();
            config.getServletContext().setAttribute(APP_ENV, environment);
            log.info("Configuration init finished");
        } catch (Exception e) {
            log.error("Configuration init failed", e);
            throw new RuntimeException(e);
        }
    }
}
