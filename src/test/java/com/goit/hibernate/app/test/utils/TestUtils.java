package com.goit.hibernate.app.test.utils;

import com.goit.hibernate.app.entity.CategoryEntity;
import com.goit.hibernate.app.entity.CustomerEntity;
import com.goit.hibernate.app.entity.ProductEntity;
import com.goit.hibernate.app.entity.UserAccountEntity;

import java.math.BigDecimal;

public final class TestUtils {

    public static CustomerEntity createTestCustomer() {
        CustomerEntity customer = new CustomerEntity();
        customer.setName("John Doe");
        customer.setCountry("Ukraine");
        customer.setContactName("John Doe1");
        return customer;
    }

    public static UserAccountEntity createTestAccount() {
        UserAccountEntity accountEntity = new UserAccountEntity();
        accountEntity.setUsername("test@gmail.com");
        accountEntity.setPassword("12345");
        return accountEntity;
    }

    public static CategoryEntity createTestCategory() {
        CategoryEntity categoryEntity = new CategoryEntity();
        categoryEntity.setName("Laptops");
        categoryEntity.setDescription("New popular laptops");
        return categoryEntity;
    }

    public static ProductEntity createTestProduct() {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setPrice(BigDecimal.ONE);
        productEntity.setName("Test Laptop");
        return productEntity;
    }
}
