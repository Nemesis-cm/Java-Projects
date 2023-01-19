package com.tsg.floormastery.dao;

import com.tsg.floormastery.model.Tax;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
@Profile({"test"})
public class TaxDaoStub implements TaxDao {

    private Map<String, Tax> allTaxes = new HashMap<>();

    public TaxDaoStub() {
        Tax tax = new Tax();
        tax.setStateAbbreviation("TX");
        tax.setStateName("Texas");
        tax.setTaxRate(new BigDecimal("4.45"));
        allTaxes.put(tax.getStateAbbreviation(), tax);
        tax = new Tax();
        tax.setStateAbbreviation("WA");
        tax.setStateName("Washington");
        tax.setTaxRate(new BigDecimal("9.25"));
        allTaxes.put(tax.getStateAbbreviation(), tax);
        tax = new Tax();
        tax.setStateAbbreviation("KY");
        tax.setStateName("Kentucky");
        tax.setTaxRate(new BigDecimal("6.00"));
        allTaxes.put(tax.getStateAbbreviation(), tax);
        tax = new Tax();
        tax.setStateAbbreviation("CA");
        tax.setStateName("Calfornia");
        tax.setTaxRate(new BigDecimal("25.00"));
        allTaxes.put(tax.getStateAbbreviation(), tax);
    }

    @Override
    public void loadTax() throws TaxPersistenceException {

    }

    @Override
    public Map<String, Tax> getAllTaxes() {
        return allTaxes;
    }
}
