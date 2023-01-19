package com.tsg.floormastery.dao;

import com.tsg.floormastery.model.Product;

import java.util.Map;

public interface ProductDao {
    void loadProduct() throws ProductPersistenceException;

    Map<String, Product> getAllProducts();
}
