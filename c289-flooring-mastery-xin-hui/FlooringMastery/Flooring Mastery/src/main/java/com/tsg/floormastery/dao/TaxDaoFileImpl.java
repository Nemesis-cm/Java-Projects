package com.tsg.floormastery.dao;

import com.tsg.floormastery.model.Tax;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Repository
@Profile({"prod"})
public class TaxDaoFileImpl implements TaxDao{

    private Map<String, Tax> allTaxes = new HashMap<>();

    private String TAX_FILE = "Data/Taxes.txt";

    private static final String DELIMITER = ",";

    public TaxDaoFileImpl() throws TaxPersistenceException{
        loadTax();
    }

    public TaxDaoFileImpl(String fileName) throws TaxPersistenceException{
        this.TAX_FILE = fileName;
        loadTax();
    }

    @Override
    public Map<String, Tax> getAllTaxes() {
        return allTaxes;
    }

    @Override
    public void loadTax() throws TaxPersistenceException{
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(TAX_FILE)));
        } catch (FileNotFoundException e) {
            throw new TaxPersistenceException(
                    "-_- Could not load tax data into memory.", e);
        }
        String currentLine;
        Tax currentTax;
        if (scanner.hasNextLine()) scanner.nextLine();

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTax = unmarshallItem(currentLine);
            allTaxes.put(currentTax.getStateAbbreviation(), currentTax);
        }
        // close scanner
        scanner.close();
    }

    private Tax unmarshallItem(String taxAsText){
//        State,StateName,TaxRate
//        TX,Texas,4.45
//        WA,Washington,9.25
//        KY,Kentucky,6.00
//        CA,Calfornia,25.00
        String[] itemTokens = taxAsText.split(DELIMITER);
        Tax tax = new Tax();
        tax.setStateAbbreviation(itemTokens[0]);
        tax.setStateName(itemTokens[1]);
        tax.setTaxRate(new BigDecimal(itemTokens[2]));

        return tax;
    }


}
