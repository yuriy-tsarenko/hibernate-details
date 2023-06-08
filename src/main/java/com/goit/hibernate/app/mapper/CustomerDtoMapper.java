package com.goit.hibernate.app.mapper;

import com.goit.hibernate.app.dto.CustomerDto;
import com.goit.hibernate.app.entity.CustomerEntity;
import lombok.NoArgsConstructor;

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
}
