package com.goit.hibernate.app.mapper;

import java.util.List;

public interface Mapper<E, D> {

    D mapEntityToDto(E source) throws RuntimeException;

    E mapDtoToEntity(D source) throws RuntimeException;

    default List<D> mapEntityToDto(List<E> source) throws RuntimeException {
        return source.stream()
                .map(this::mapEntityToDto)
                .toList();
    }

    default List<E> mapDtoToEntity(List<D> source) throws RuntimeException {
        return source.stream()
                .map(this::mapDtoToEntity)
                .toList();
    }
}
