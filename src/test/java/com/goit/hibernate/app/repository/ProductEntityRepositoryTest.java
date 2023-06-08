package com.goit.hibernate.app.repository;

import com.goit.hibernate.app.HibernateApplicationTest;
import com.goit.hibernate.app.configuration.hibernate.TransactionalResult;
import com.goit.hibernate.app.entity.CategoryEntity;
import com.goit.hibernate.app.entity.ProductEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.goit.hibernate.app.test.utils.TestUtils.createTestCategory;
import static com.goit.hibernate.app.test.utils.TestUtils.createTestProduct;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class ProductEntityRepositoryTest extends HibernateApplicationTest {

    @Test
    void accountToCustomerRelations() {
        //Given
        ProductEntityRepository productEntityRepository = new ProductEntityRepository(datasource);
        CategoryEntity testCategory = createTestCategory();
        ProductEntity testProduct = createTestProduct();

        //When
        testCategory.setProducts(List.of(testProduct));
        testProduct.setCategories(List.of(testCategory));

        try (TransactionalResult<ProductEntity> result = productEntityRepository.saveTransactional(testProduct)) {
            ProductEntity savedProduct = result
                    .getValue()
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            List<CategoryEntity> categories = savedProduct.getCategories();

            //Then
            assertNotNull(categories);
            assertFalse(categories.isEmpty());
            CategoryEntity savedCategory = categories.get(0);
            assertEquals(savedCategory.getName(), testCategory.getName());
        }
    }
}