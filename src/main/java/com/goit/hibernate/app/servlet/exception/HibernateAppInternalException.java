package com.goit.hibernate.app.servlet.exception;

import com.goit.hibernate.app.servlet.HttpCode;

public class HibernateAppInternalException extends WebAppException {
    public HibernateAppInternalException() {
        super();
    }

    public HibernateAppInternalException(String message) {
        super(message);
    }

    public HibernateAppInternalException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getHttpCode() {
        return HttpCode.INTERNAL_ERROR;
    }
}
