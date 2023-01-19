package com.tsg.floormastery.dao;

import com.tsg.floormastery.model.Tax;

import java.util.Map;

public interface TaxDao {
    void loadTax() throws TaxPersistenceException;

    Map<String, Tax> getAllTaxes();

}
