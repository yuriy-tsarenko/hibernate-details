package com.goit.hibernate.app.mapper;

import com.goit.hibernate.app.dto.CustomerDto;
import com.goit.hibernate.app.entity.CustomerEntity;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@NoArgsConstructor(staticName = "instance")
public final class CustomerDtoMapper implements Mapper<CustomerDto, CustomerEntity> {

    @Override
    public CustomerEntity map(CustomerDto source) {
        CustomerEntity target = new CustomerEntity();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setContactName(source.getContactName());
        target.setCountry(source.getCountry());
        return target;
    }

    @Override
    public List<CustomerEntity> map(List<CustomerDto> source) throws RuntimeException {
        return source.stream()
                .map(this::map)
                .collect(Collectors.toList());
    }
}
