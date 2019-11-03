package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.Console;
import com.trilogyed.JyotiChavanU1Capstone.model.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.relational.core.sql.In;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    InvoiceDao invoiceDao;

    private Invoice invoice;

    @Before
    public void setUp() throws Exception {

        List<Invoice> invoices = invoiceDao.getAllInvoice();
        for (Invoice invoice : invoices) {
            invoiceDao.deleteInvoice(invoice.getInvoice_id());
        }
    }


    @Test
    public void addGetAndDeleteInvoice() {
        Invoice invoiceToAdd = new Invoice();
        invoiceToAdd.setName("Jyoti Chavan");
        invoiceToAdd.setStreet("75 Krstin Cr.");
        invoiceToAdd.setCity("Schaumburg");
        invoiceToAdd.setState("IL");
        invoiceToAdd.setZipcode("60195");
        invoiceToAdd.setItem_type("Game");
        invoiceToAdd.setItem_id(1);
        invoiceToAdd.setQuantity(1);
        invoiceToAdd.setUnit_price(BigDecimal.valueOf(20.00));
        invoiceToAdd.setSubtotal(BigDecimal.valueOf(20.00));
        invoiceToAdd.setTax(BigDecimal.valueOf(1.00));
        invoiceToAdd.setProcessing_fee(BigDecimal.valueOf(1.49));
        invoiceToAdd.setTotal(BigDecimal.valueOf(22.49));


        Invoice InvoiceAfterAddingToDB = invoiceDao.addInvoice(invoiceToAdd);

        Invoice invoiceIGot = invoiceDao.getInvoice(InvoiceAfterAddingToDB.getInvoice_id());

        assertEquals(invoiceIGot, InvoiceAfterAddingToDB );

        invoiceDao.deleteInvoice(InvoiceAfterAddingToDB.getInvoice_id());

        invoiceIGot = invoiceDao.getInvoice(InvoiceAfterAddingToDB.getInvoice_id());

        assertNull( invoiceIGot);


    }

    @Test
    public void getAllInvoice() {
        Invoice invoice1 = new Invoice();
        invoice1.setName("Jyoti Chavan");
        invoice1.setStreet("75 Krstin Cr.");
        invoice1.setCity("Schaumburg");
        invoice1.setState("IL");
        invoice1.setZipcode("60195");
        invoice1.setItem_type("Game");
        invoice1.setItem_id(1);
        invoice1.setQuantity(1);
        invoice1.setUnit_price(BigDecimal.valueOf(20.00));
        invoice1.setSubtotal(BigDecimal.valueOf(20.00));
        invoice1.setTax(BigDecimal.valueOf(1.00));
        invoice1.setProcessing_fee(BigDecimal.valueOf(1.49));
        invoice1.setTotal(BigDecimal.valueOf(22.49));

        Invoice invoice2 = new Invoice();
        invoice2.setName("Yuvaan Zanzane");
        invoice2.setStreet("75 Krstin Cr.");
        invoice2.setCity("Schaumburg");
        invoice2.setState("IL");
        invoice2.setZipcode("60195");
        invoice2.setItem_type("Game");
        invoice2.setItem_id(1);
        invoice2.setQuantity(1);
        invoice2.setUnit_price(BigDecimal.valueOf(20.00));
        invoice2.setSubtotal(BigDecimal.valueOf(20.00));
        invoice2.setTax(BigDecimal.valueOf(1.00));
        invoice2.setProcessing_fee(BigDecimal.valueOf(1.49));
        invoice2.setTotal(BigDecimal.valueOf(22.49));

        invoiceDao.addInvoice(invoice1);
        invoiceDao.addInvoice(invoice2);

        List<Invoice> invoiceList = invoiceDao.getAllInvoice();
        assertEquals(2,invoiceList.size());

    }


    @Test
    public void updateInvoice() {


        Invoice invoice1 = new Invoice();
        invoice1.setName("Jyoti Chavan");
        invoice1.setStreet("75 Krstin Cr.");
        invoice1.setCity("Schaumburg");
        invoice1.setState("IL");
        invoice1.setZipcode("60195");
        invoice1.setItem_type("Game");
        invoice1.setItem_id(1);
        invoice1.setQuantity(1);
        invoice1.setUnit_price(BigDecimal.valueOf(20.00));
        invoice1.setSubtotal(BigDecimal.valueOf(20.00));
        invoice1.setTax(BigDecimal.valueOf(1.00));
        invoice1.setProcessing_fee(BigDecimal.valueOf(1.49));
        invoice1.setTotal(BigDecimal.valueOf(22.49));

        invoiceDao.addInvoice(invoice1);

        invoice1.setName("Amit Zanzane");
        invoice1.setStreet("75 Krstin Cr.");
        invoice1.setCity("Schaumburg");
        invoice1.setState("IL");
        invoice1.setZipcode("60193");
        invoice1.setItem_type("Game");
        invoice1.setItem_id(1);
        invoice1.setQuantity(1);
        invoice1.setUnit_price(BigDecimal.valueOf(20.00));
        invoice1.setSubtotal(BigDecimal.valueOf(20.00));
        invoice1.setTax(BigDecimal.valueOf(1.00));
        invoice1.setProcessing_fee(BigDecimal.valueOf(1.49));
        invoice1.setTotal(BigDecimal.valueOf(22.49));

        invoiceDao.updateInvoice(invoice1);

        Invoice invoice2 = invoiceDao.getInvoice(invoice1.getInvoice_id());
        assertEquals(invoice2 , invoice1);

    }


}