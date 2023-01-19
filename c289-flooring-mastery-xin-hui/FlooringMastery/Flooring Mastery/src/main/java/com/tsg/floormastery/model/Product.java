package com.tsg.floormastery.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
    private String ProductType;
    private BigDecimal CostPerSquareFoot;
    private BigDecimal LaborCostPerSquareFoot;

    public String getProductType() {
        return ProductType;
    }

    public void setProductType(String productType) {
        ProductType = productType;
    }

    public BigDecimal getCostPerSquareFoot() {
        return CostPerSquareFoot;
    }

    public void setCostPerSquareFoot(BigDecimal costPerSquareFoot) {
        CostPerSquareFoot = costPerSquareFoot;
    }

    public BigDecimal getLaborCostPerSquareFoot() {
        return LaborCostPerSquareFoot;
    }

    public void setLaborCostPerSquareFoot(BigDecimal laborCostPerSquareFoot) {
        LaborCostPerSquareFoot = laborCostPerSquareFoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(ProductType, product.ProductType) && Objects.equals(CostPerSquareFoot, product.CostPerSquareFoot) && Objects.equals(LaborCostPerSquareFoot, product.LaborCostPerSquareFoot);
    }

    @Override
    public String toString() {
        return "Product{" +
                "ProductType='" + ProductType + '\'' +
                ", CostPerSquareFoot=" + CostPerSquareFoot +
                ", LaborCostPerSquareFoot=" + LaborCostPerSquareFoot +
                '}';
    }
}
