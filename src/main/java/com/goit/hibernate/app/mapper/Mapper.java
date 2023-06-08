package com.goit.hibernate.app.mapper;

import java.util.List;
import java.util.stream.Collectors;

public interface Mapper<T, R> {

    R map(T source) throws RuntimeException;

    default List<R> map(List<T> source) throws RuntimeException {
        return source.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
