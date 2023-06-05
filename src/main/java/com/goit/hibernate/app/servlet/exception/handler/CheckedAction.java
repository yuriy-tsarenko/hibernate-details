package com.goit.hibernate.app.servlet.exception.handler;

@FunctionalInterface
public interface CheckedAction {

    void perform() throws Exception;
}
