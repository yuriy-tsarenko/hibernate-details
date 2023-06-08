package com.goit.hibernate.app.dto;

import com.goit.hibernate.app.entity.CustomerEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UserAccountDto {

    private Long id;
    private String username;
    private String password;
    private CustomerEntity customer;
}
