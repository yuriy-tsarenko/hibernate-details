package com.goit.hibernate.app.servlet.exception;

import com.goit.hibernate.app.servlet.HttpCode;

public class HibernateAppNotFoundException extends WebAppException {
    public HibernateAppNotFoundException() {
        super();
    }

    public HibernateAppNotFoundException(String message) {
        super(message);
    }

    public HibernateAppNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getHttpCode() {
        return HttpCode.NOT_FOUND;
    }
}
