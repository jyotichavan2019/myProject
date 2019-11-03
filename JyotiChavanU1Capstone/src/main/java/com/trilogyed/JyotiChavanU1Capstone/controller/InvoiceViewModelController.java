package com.trilogyed.JyotiChavanU1Capstone.controller;

import com.trilogyed.JyotiChavanU1Capstone.model.Invoice;
import com.trilogyed.JyotiChavanU1Capstone.model.PurchasingItem;
import com.trilogyed.JyotiChavanU1Capstone.service.InvoiceViewModelServiceLayer;
import com.trilogyed.JyotiChavanU1Capstone.viewModel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

@RestController

public class InvoiceViewModelController {

    @Autowired
    InvoiceViewModelServiceLayer invoiceViewModelServiceLayer;

    @RequestMapping(value = "/purchasingItem", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Invoice createInvoice(@RequestBody InvoiceViewModel purchasingItem) {
        return invoiceViewModelServiceLayer.saveInvoice(purchasingItem);
    }








}
