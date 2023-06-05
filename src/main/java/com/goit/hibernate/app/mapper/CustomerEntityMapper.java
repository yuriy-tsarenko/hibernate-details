package com.goit.hibernate.app.mapper;

import com.goit.hibernate.app.dto.CustomerDto;
import com.goit.hibernate.app.entity.CustomerEntity;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(staticName = "instance")
public class CustomerEntityMapper implements Mapper<CustomerEntity, CustomerDto> {

    @Override
    public CustomerDto map(CustomerEntity entity) throws RuntimeException {
        return CustomerDto.of(entity.getId(), entity.getName(), entity.getContactName(), entity.getCountry());
    }

    @Override
    public List<CustomerDto> map(List<CustomerEntity> source) throws RuntimeException {
        return source.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
