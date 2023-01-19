package com.tsg.floormastery.dao;

import com.tsg.floormastery.model.Tax;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TaxDaoFileImplTest {

    private static TaxDao dao;
    private final static String TAX_FILE = "Data/Taxes.txt";

    @BeforeAll
    static void init() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.scan("com.tsg.floormastery");
        context.refresh();

        dao = context.getBean(TaxDao.class, TAX_FILE);
    }

    @Test
    void loadTax() throws TaxPersistenceException{
        dao.loadTax();
        Map<String, Tax> allTaxes = dao.getAllTaxes();

        assertNotNull(allTaxes);
        assertEquals(4, allTaxes.size());

//        WA,Washington,9.25
        Tax tax = new Tax();
        tax.setStateAbbreviation("WA");
        tax.setStateName("Washington");
        tax.setTaxRate(new BigDecimal("9.25"));

        assertTrue(allTaxes.containsValue(tax));

    }
}