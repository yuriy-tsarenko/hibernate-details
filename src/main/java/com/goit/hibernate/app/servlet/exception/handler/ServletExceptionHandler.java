package com.goit.hibernate.app.servlet.exception.handler;

import com.goit.hibernate.app.servlet.HttpCode;
import com.goit.hibernate.app.servlet.exception.HibernateAppInternalException;
import com.goit.hibernate.app.servlet.exception.WebAppException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;

import static java.util.Objects.isNull;

@Slf4j
@Builder
public final class ServletExceptionHandler {
    private HttpServletResponse servletResponse;
    private CheckedAction action;

    public void doAction() {
        try {
            checkAction();
            checkResponse();
            action.perform();
        } catch (Exception e) {
            handleException(servletResponse, e);
        } finally {
            servletResponse = null;
            action = null;
        }
    }

    private void checkAction() {
        if (isNull(action)) {
            throw new HibernateAppInternalException("""
                    action shouldn't be performed with because it's a 'null' value,
                    or twice at the same time"""
            );
        }
    }

    private void checkResponse() {
        if (isNull(servletResponse)) {
            throw new HibernateAppInternalException("""
                    action shouldn't be performed with a nullable response,
                    or twice at the same time"""
            );
        }
    }

    private void handleException(HttpServletResponse response, Exception e) throws RuntimeException {
        try {
            int code = HttpCode.INTERNAL_ERROR;
            String message = isNull(e.getMessage())
                    ? "Something went wrong"
                    : e.getMessage();

            if (e instanceof WebAppException) {
                code = ((WebAppException) e).getHttpCode();
            }

            response.setStatus(code);
            response.setContentType("application/json");
            response.getWriter().write(String.format("{\"message\": \"%s\"}", message));
            response.getWriter().close();
            log.error(message, e);
        } catch (Exception exception) {
            log.error("Exception handling failed", e);
            throw new RuntimeException(exception);
        }
    }
}
