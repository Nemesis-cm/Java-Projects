package com.tsg.floormastery.dao;

import com.tsg.floormastery.model.Product;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Repository
public class ProductDaoFileImpl implements ProductDao{
    private Map<String, Product> allProducts = new HashMap<>();

    private String PRODUCT_FILE = "Data/Products.txt";

    private static final String DELIMITER = ",";

    public ProductDaoFileImpl() throws ProductPersistenceException{
        loadProduct();
    }

    public ProductDaoFileImpl(String fileName) throws ProductPersistenceException{
        this.PRODUCT_FILE = fileName;
        loadProduct();
    }

    @Override
    public Map<String, Product> getAllProducts() {
        return allProducts;
    }

    @Override
    public void loadProduct() throws ProductPersistenceException{
        Scanner scanner;

        try {
            // Create Scanner for reading the file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_FILE)));
        } catch (FileNotFoundException e) {
            throw new ProductPersistenceException(
                    "-_- Could not load product data into memory.", e);
        }
        String currentLine;
        Product currentProduct;
        if (scanner.hasNextLine()) scanner.nextLine();

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallItem(currentLine);
            allProducts.put(currentProduct.getProductType(), currentProduct);
        }
        // close scanner
        scanner.close();
    }

    private Product unmarshallItem(String productAsText){
        String[] itemTokens = productAsText.split(DELIMITER);
        Product product = new Product();
        product.setProductType(itemTokens[0]);
        product.setCostPerSquareFoot(new BigDecimal(itemTokens[1]));
        product.setLaborCostPerSquareFoot(new BigDecimal(itemTokens[2]));

        return product;
    }
}
