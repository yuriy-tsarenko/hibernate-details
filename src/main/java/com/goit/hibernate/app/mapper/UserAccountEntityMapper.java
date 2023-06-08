package com.goit.hibernate.app.mapper;

import com.goit.hibernate.app.dto.UserAccountDto;
import com.goit.hibernate.app.entity.UserAccountEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "instance")
public class UserAccountEntityMapper implements Mapper<UserAccountEntity, UserAccountDto> {

    @Override
    public UserAccountDto map(UserAccountEntity source) throws RuntimeException {
        UserAccountDto target = new UserAccountDto();
        target.setPassword(source.getPassword());
        target.setUsername(source.getUsername());
        return target;
    }
}
