package com.goit.hibernate.app.mapper;

import com.goit.hibernate.app.dto.UserAccountDto;
import com.goit.hibernate.app.entity.UserAccountEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor(staticName = "instance")
public final class UserAccountDtoMapper implements Mapper<UserAccountDto, UserAccountEntity> {

    @Override
    public UserAccountEntity map(UserAccountDto source) {
        UserAccountEntity target = new UserAccountEntity();
        target.setPassword(source.getPassword());
        target.setUsername(source.getUsername());
        return target;
    }

}
