package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.SalesTax;

public interface SalesTaxDao {
    SalesTax getRateByState(String state);
}
