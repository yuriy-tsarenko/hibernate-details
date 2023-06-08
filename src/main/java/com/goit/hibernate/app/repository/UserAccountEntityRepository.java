package com.goit.hibernate.app.repository;

import com.goit.hibernate.app.configuration.hibernate.Datasource;
import com.goit.hibernate.app.configuration.hibernate.HibernateAbstractRepository;
import com.goit.hibernate.app.entity.CustomerEntity;
import com.goit.hibernate.app.entity.UserAccountEntity;

public class UserAccountEntityRepository extends HibernateAbstractRepository<UserAccountEntity, CustomerEntity> {
    public UserAccountEntityRepository(Datasource datasource) {
        super(datasource);
    }
}
