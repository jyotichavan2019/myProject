package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.Invoice;

import java.util.List;

public interface InvoiceDao {
    Invoice addInvoice(Invoice invoice);

    List<Invoice> getAllInvoice();

    Invoice getInvoice(int invoice_id);

    void updateInvoice(Invoice invoice);

    void deleteInvoice(int invoice_id);


}
