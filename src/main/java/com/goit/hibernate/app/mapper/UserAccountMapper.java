package com.goit.hibernate.app.mapper;

import com.goit.hibernate.app.dto.UserAccountDto;
import com.goit.hibernate.app.entity.UserAccountEntity;
import lombok.NoArgsConstructor;

import static java.util.Objects.isNull;

@NoArgsConstructor(staticName = "instance")
public class UserAccountMapper implements Mapper<UserAccountEntity, UserAccountDto> {

    private final CustomerMapper customerMapper = CustomerMapper.instance();

    @Override
    public UserAccountDto mapEntityToDto(UserAccountEntity source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        UserAccountDto target = new UserAccountDto();
        target.setPassword(source.getPassword());
        target.setUsername(source.getUsername());
        target.setCustomer(customerMapper.mapEntityToDto(source.getCustomer()));
        return target;
    }

    @Override
    public UserAccountEntity mapDtoToEntity(UserAccountDto source) throws RuntimeException {
        if (isNull(source)) {
            return null;
        }
        UserAccountEntity target = new UserAccountEntity();
        target.setPassword(source.getPassword());
        target.setUsername(source.getUsername());
        target.setCustomer(customerMapper.mapDtoToEntity(source.getCustomer()));
        return target;
    }
}
