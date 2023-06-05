package com.goit.hibernate.app.mapper;

import java.util.List;

public interface Mapper<T, R> {

    R map(T source) throws RuntimeException;

    List<R> map(List<T> source) throws RuntimeException;
}
