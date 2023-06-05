package com.goit.hibernate.app.servlet.exception;

public abstract class WebAppException extends RuntimeException {

    public WebAppException() {
    }

    public WebAppException(String message) {
        super(message);
    }

    public WebAppException(String message, Throwable cause) {
        super(message, cause);
    }

    public WebAppException(Throwable cause) {
        super(cause);
    }

    public abstract int getHttpCode();
}
