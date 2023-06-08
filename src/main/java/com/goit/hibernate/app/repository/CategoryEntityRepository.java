package com.goit.hibernate.app.repository;

import com.goit.hibernate.app.configuration.hibernate.Datasource;
import com.goit.hibernate.app.configuration.hibernate.HibernateAbstractRepository;
import com.goit.hibernate.app.entity.CategoryEntity;

public class CategoryEntityRepository extends HibernateAbstractRepository<CategoryEntity, Long> {
    public CategoryEntityRepository(Datasource datasource) {
        super(datasource);
    }
}
