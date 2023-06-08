package com.goit.hibernate.app.repository;

import com.goit.hibernate.app.configuration.hibernate.Datasource;
import com.goit.hibernate.app.configuration.hibernate.HibernateAbstractRepository;
import com.goit.hibernate.app.entity.ProductEntity;

public class ProductEntityRepository extends HibernateAbstractRepository<ProductEntity, Long> {
    public ProductEntityRepository(Datasource datasource) {
        super(datasource);
    }
}
