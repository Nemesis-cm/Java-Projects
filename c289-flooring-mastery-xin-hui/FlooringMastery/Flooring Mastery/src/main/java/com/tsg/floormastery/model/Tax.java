package com.tsg.floormastery.model;

import java.math.BigDecimal;
import java.util.Objects;

public class Tax {
    private String StateAbbreviation;
    private String StateName;
    private BigDecimal TaxRate;

    public String getStateAbbreviation() {
        return StateAbbreviation;
    }

    public void setStateAbbreviation(String stateAbbreviation) {
        StateAbbreviation = stateAbbreviation;
    }

    public String getStateName() {
        return StateName;
    }

    public void setStateName(String stateName) {
        StateName = stateName;
    }

    public BigDecimal getTaxRate() {
        return TaxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        TaxRate = taxRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tax tax = (Tax) o;
        return Objects.equals(StateAbbreviation, tax.StateAbbreviation) && Objects.equals(StateName, tax.StateName) && Objects.equals(TaxRate, tax.TaxRate);
    }

    @Override
    public String toString() {
        return "Tax{" +
                "StateAbbreviation='" + StateAbbreviation + '\'' +
                ", StateName='" + StateName + '\'' +
                ", TaxRate=" + TaxRate +
                '}';
    }
}
