package com.trilogyed.JyotiChavanU1Capstone.model;

import java.math.BigDecimal;
import java.util.Objects;

public class ProcessingFee {

   private String  product_type ;
   private BigDecimal fee ;

   public ProcessingFee(){}

    public ProcessingFee(String product_type, BigDecimal fee) {
        this.product_type = product_type;
        this.fee = fee;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessingFee that = (ProcessingFee) o;
        return product_type.equals(that.product_type) &&
                Objects.equals(fee, that.fee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_type, fee);
    }

    @Override
    public String toString() {
        return "ProcessingFee{" +
                "product_type='" + product_type + '\'' +
                ", fee=" + fee +
                '}';
    }
}
