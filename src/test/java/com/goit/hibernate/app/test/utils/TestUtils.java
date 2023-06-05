package com.goit.hibernate.app.test.utils;

import com.goit.hibernate.app.entity.CustomerEntity;

public final class TestUtils {

    public static CustomerEntity createTestCustomer() {
        CustomerEntity customer = new CustomerEntity();
        customer.setName("John Doe");
        customer.setCountry("Ukraine");
        customer.setContactName("John Doe1");

        return customer;
    }
}
