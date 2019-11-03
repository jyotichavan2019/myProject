package com.trilogyed.JyotiChavanU1Capstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class SalesTax {

    private  String state ;
    private BigDecimal rate ;

    public SalesTax(){}

    public SalesTax(String state, BigDecimal rate) {
        this.state = state;
        this.rate = rate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SalesTax salesTax = (SalesTax) o;
        return state.equals(salesTax.state) &&
                rate.equals(salesTax.rate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, rate);
    }

    @Override
    public String toString() {
        return "SalesTax{" +
                "state='" + state + '\'' +
                ", rate=" + rate +
                '}';
    }
}
