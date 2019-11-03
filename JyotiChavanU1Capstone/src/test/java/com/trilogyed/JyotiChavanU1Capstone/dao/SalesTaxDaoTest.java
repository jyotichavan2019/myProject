package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.SalesTax;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class SalesTaxDaoTest {
    @Autowired
    SalesTaxDao salesTaxDao;

    private SalesTax salesTax;
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getRateByState(){
        SalesTax salesTax = new SalesTax();
        salesTax.setState("AL");
        salesTax.setRate(BigDecimal.valueOf(0.05));

        SalesTax outSaleTax = salesTaxDao.getRateByState("AL");

        assertEquals(outSaleTax , salesTax);
    }





}