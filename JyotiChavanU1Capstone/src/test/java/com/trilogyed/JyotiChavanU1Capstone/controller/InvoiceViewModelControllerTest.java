package com.trilogyed.JyotiChavanU1Capstone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.JyotiChavanU1Capstone.model.Invoice;
import com.trilogyed.JyotiChavanU1Capstone.service.InvoiceViewModelServiceLayer;
import com.trilogyed.JyotiChavanU1Capstone.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.relational.core.sql.In;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(InvoiceViewModelController.class)
public class InvoiceViewModelControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InvoiceViewModelServiceLayer invoiceViewModelServiceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {
    }


    @Test
    public void purchasingItemShouldReturnCreatedInvoice() throws Exception {
        Invoice invoice = new Invoice();
        invoice.setName();
        invoice.setStreet();
    }

}