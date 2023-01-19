package com.tsg.floormastery.dao;

import com.tsg.floormastery.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ProductDaoFileImplTest {
    private ProductDao dao;
    private String PRODUCT_FILE = "Data/ProductsTest.txt";

    @BeforeEach
    void setUp() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.tsg.floormastery");
        context.refresh();

        dao = context.getBean(ProductDao.class, PRODUCT_FILE);
    }
    @Test
    void loadProduct() throws ProductPersistenceException{
        dao.loadProduct();
        Map<String, Product> allProducts = dao.getAllProducts();

        assertNotNull(allProducts);
        assertEquals(4, allProducts.size());

//        Carpet,2.25,2.10
        Product product = new Product();
        product.setProductType("Carpet");
        product.setCostPerSquareFoot(new BigDecimal("2.25"));
        product.setLaborCostPerSquareFoot(new BigDecimal("2.10"));

        assertTrue(allProducts.containsValue(product));

    }
}
