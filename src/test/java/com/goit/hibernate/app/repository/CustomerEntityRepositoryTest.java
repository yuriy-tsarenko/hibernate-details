package com.goit.hibernate.app.repository;

import com.goit.hibernate.app.HibernateApplicationTest;
import com.goit.hibernate.app.configuration.hibernate.Datasource;
import com.goit.hibernate.app.entity.CustomerEntity;
import com.goit.hibernate.app.test.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerEntityRepositoryTest extends HibernateApplicationTest {

    @Test
    @DisplayName("Check if customers exists when findAll() invoked")
    void findAll() {
        //Given
        final int expectedSize = 3;
        Datasource datasource = new Datasource(environment);
        CustomerEntityRepository repository = new CustomerEntityRepository(datasource);

        //When
        List<CustomerEntity> all = repository.findAll();

        //Then
        assertFalse(all.isEmpty());
        assertEquals(expectedSize, all.size());
    }

    @Test
    void findById() {
        //Given
        final long testId;
        Datasource datasource = new Datasource(environment);
        CustomerEntityRepository repository = new CustomerEntityRepository(datasource);
        List<CustomerEntity> all = repository.findAll();
        testId = all.get(0).getId();

        //When
        CustomerEntity customer = repository.findById(testId);

        //Then
        assertNotNull(customer);
        assertEquals(testId, customer.getId());
    }

    @Test
    void save() {
        //Given
        Datasource datasource = new Datasource(environment);
        CustomerEntityRepository repository = new CustomerEntityRepository(datasource);
        CustomerEntity testCustomer = TestUtils.createTestCustomer();

        //When
        CustomerEntity savedCustomer = repository.save(testCustomer);
        CustomerEntity byId = repository.findById(savedCustomer.getId());

        //Then
        assertNotNull(savedCustomer);
        assertEquals(testCustomer.getId(), byId.getId());
    }

    @Test
    void delete() {
        //Given
        int expectedDeletedCount = 1;
        Datasource datasource = new Datasource(environment);
        CustomerEntityRepository repository = new CustomerEntityRepository(datasource);

        //When
        List<CustomerEntity> all = repository.findAll();
        CustomerEntity entity = all.get(0);
        int deleteCount = repository.delete(entity);

        //Then
        assertEquals(expectedDeletedCount, deleteCount);
    }
}
