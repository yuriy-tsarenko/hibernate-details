package com.goit.hibernate.app.mapper;

import com.goit.hibernate.app.dto.CustomerDto;
import com.goit.hibernate.app.entity.CustomerEntity;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@NoArgsConstructor(staticName = "instance")
public final class CustomerMapper implements Mapper<CustomerEntity, CustomerDto> {

    private final UserAccountMapper userAccountMapper = UserAccountMapper.instance();
    private final ProductMapper productMapper = ProductMapper.instance();

    @Override
    public CustomerDto mapEntityToDto(CustomerEntity source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        CustomerDto target = new CustomerDto();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setContactName(source.getContactName());
        target.setCountry(source.getCountry());
        target.setAccount(userAccountMapper.mapEntityToDto(source.getAccount()));
        target.setProducts(productMapper.mapEntityToDto(source.getProducts()));
        return target;
    }

    @Override
    public CustomerEntity mapDtoToEntity(CustomerDto source) {
        if (isNull(source)) {
            return null;
        }
        CustomerEntity target = new CustomerEntity();
        target.setId(source.getId());
        target.setName(source.getName());
        target.setContactName(source.getContactName());
        target.setCountry(source.getCountry());
        target.setAccount(userAccountMapper.mapDtoToEntity(source.getAccount()));
        target.setProducts(productMapper.mapDtoToEntity(source.getProducts()));
        return target;
    }
}
