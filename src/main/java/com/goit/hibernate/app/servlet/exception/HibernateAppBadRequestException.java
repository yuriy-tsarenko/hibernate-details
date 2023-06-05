package com.goit.hibernate.app.servlet.exception;

import com.goit.hibernate.app.servlet.HttpCode;

public class HibernateAppBadRequestException extends WebAppException {

    public HibernateAppBadRequestException() {
        super();
    }

    public HibernateAppBadRequestException(String message) {
        super(message);
    }

    public HibernateAppBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    @Override
    public int getHttpCode() {
        return HttpCode.BAD_REQUEST;
    }
}
