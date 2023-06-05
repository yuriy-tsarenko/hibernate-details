package com.goit.hibernate.app.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

import static java.util.Objects.isNull;

//@WebFilter(value = "/api/*")
public class AuthFilter extends HttpFilter {
    private static final String DEFAULT_FILE_NAME = "application.properties";

    private static final String AUTH_HEADER = "Authorization";

    private String token;

    @Override
    public void init() {
//        try {
//            Properties properties = new Properties();
//            properties.load(FlywayConfigurations.class.getClassLoader().getResourceAsStream(DEFAULT_FILE_NAME));
//            token = properties.getProperty(Constants.TOKEN);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String auth = req.getHeader(AUTH_HEADER);
        if (isNull(auth)) {
            sendNotAuthorized(res);
        } else if (auth.equals(token)) {
            chain.doFilter(req, res);
        } else {
            sendNotAuthorized(res);
        }
    }

    private static void sendNotAuthorized(HttpServletResponse res) throws IOException {
        final int unauthorized = 401;
        res.setStatus(unauthorized);
        res.setContentType("application/json");
        res.getWriter().write("{\"Error\": \"Not authorized\"}");
        res.getWriter().close();
    }
}
