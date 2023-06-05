package com.goit.hibernate.app.repository;

import com.goit.hibernate.app.configuration.hibernate.Datasource;
import com.goit.hibernate.app.configuration.hibernate.HibernateAbstractRepository;
import com.goit.hibernate.app.entity.CustomerEntity;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomerEntityRepository extends HibernateAbstractRepository<CustomerEntity, Long> {

    public CustomerEntityRepository(Datasource datasource) {
        super(datasource);
    }
}
