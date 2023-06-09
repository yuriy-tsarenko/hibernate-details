package com.goit.hibernate.app.repository;

import com.goit.hibernate.app.HibernateApplicationTest;
import com.goit.hibernate.app.configuration.hibernate.TransactionalResult;
import com.goit.hibernate.app.entity.CustomerEntity;
import com.goit.hibernate.app.entity.ProductEntity;
import com.goit.hibernate.app.entity.UserAccountEntity;
import com.goit.hibernate.app.test.utils.TestUtils;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.goit.hibernate.app.test.utils.TestUtils.createTestAccount;
import static com.goit.hibernate.app.test.utils.TestUtils.createTestCustomer;
import static com.goit.hibernate.app.test.utils.TestUtils.createTestProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CustomerEntityRepositoryTest extends HibernateApplicationTest {

    @Test
    @DisplayName("Check if customers exists when findAll() invoked")
    void findAll() {
        //Given
        final int expectedSize = 3;

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

        CustomerEntityRepository repository = new CustomerEntityRepository(datasource);

        //When
        List<CustomerEntity> all = repository.findAll();
        CustomerEntity entity = all.get(0);
        int deleteCount = repository.delete(entity);

        //Then
        assertEquals(expectedDeletedCount, deleteCount);
    }

    @Test
    void accountToCustomerRelations() {
        //Given
        CustomerEntityRepository customerEntityRepository = new CustomerEntityRepository(datasource);
        UserAccountEntityRepository userAccountEntityRepository = new UserAccountEntityRepository(datasource);

        CustomerEntity testCustomer = createTestCustomer();
        UserAccountEntity testAccount = createTestAccount();

        //When
        testAccount.setCustomer(testCustomer);
        testCustomer.setAccount(testAccount);

        CustomerEntity savedCustomer = customerEntityRepository.save(testCustomer);
        CustomerEntity loadedByIdCustomer = customerEntityRepository.findById(savedCustomer.getId());
        UserAccountEntity loadedByIdAccount = userAccountEntityRepository.findById(savedCustomer);

        //Then
        assertNotNull(loadedByIdCustomer);
        assertNotNull(loadedByIdAccount);
        assertNotNull(loadedByIdCustomer.getAccount());
        assertNotNull(loadedByIdAccount.getCustomer());

        assertEquals(testCustomer.getAccount().getUsername(), loadedByIdCustomer.getAccount().getUsername());
        assertEquals(testCustomer.getAccount().getPassword(), loadedByIdCustomer.getAccount().getPassword());

        assertEquals(testCustomer.getAccount().getUsername(), loadedByIdAccount.getUsername());
        assertEquals(testCustomer.getAccount().getUsername(), loadedByIdAccount.getUsername());
    }

    @Test
    void productToCustomerRelations() {
        //Given
        CustomerEntityRepository customerEntityRepository = new CustomerEntityRepository(datasource);
        CustomerEntity testCustomer = createTestCustomer();
        ProductEntity testProduct = createTestProduct();

        //When
        testCustomer.setProducts(List.of(testProduct));
        testProduct.setCustomer(testCustomer);

        try (TransactionalResult<CustomerEntity> result = customerEntityRepository.saveTransactional(testCustomer)) {
            CustomerEntity savedCustomer = result.getValue()
                    .orElseThrow(() -> new RuntimeException("Customer not found"));
            List<ProductEntity> savedCustomerProducts = savedCustomer.getProducts();

            //Then
            assertNotNull(savedCustomerProducts);
            assertFalse(savedCustomerProducts.isEmpty());
            ProductEntity savedProduct = savedCustomerProducts.get(0);
            assertEquals(savedProduct.getName(), testProduct.getName());
        }
    }
}
