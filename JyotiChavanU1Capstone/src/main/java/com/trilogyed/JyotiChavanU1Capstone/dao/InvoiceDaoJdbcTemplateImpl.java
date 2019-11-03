package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImpl implements InvoiceDao {
    private JdbcTemplate jdbcTemplate;

    private static final String CREATE_INVOICE_SQL =
            " insert into invoice (name, street, city, state, zipcode, item_type, item_id, unit_price, quantity, subtotal,tax,processing_fee, total) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private  static final String GET_INVOICE_SQL =
            " select * from invoice where invoice_id = ? " ;
    private static final String DELETE_INVOICE_SQL =
            " delete from invoice where invoice_id = ? " ;
    private static final String UPDATE_INVOICE_SQL =
            " update invoice set name = ? , street = ? , city = ? , state = ? , zipcode = ? , item_type = ? , item_id = ? , unit_price = ? , quantity = ? , subtotal = ? ,tax = ? ,processing_fee = ?, total = ?";
    private static final String GET_ALL_INVOICE_SQL =
            " select * from invoice ";


    @Autowired
    public InvoiceDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Invoice addInvoice(Invoice invoice) {
        jdbcTemplate.update(CREATE_INVOICE_SQL,
                invoice.getName(),
                invoice.getStreet(),
                invoice.getCity(),
                invoice.getState(),
                invoice.getZipcode(),
                invoice.getItem_type(),
                invoice.getItem_id(),
                invoice.getUnit_price(),
                invoice.getQuantity(),
                invoice.getSubtotal(),
                invoice.getTax(),
                invoice.getProcessing_fee(),
                invoice.getTotal());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoice.setInvoice_id(id);
        return invoice;

    }

    @Override
    public List<Invoice> getAllInvoice() {
        return jdbcTemplate.query(GET_ALL_INVOICE_SQL, this::mapRowToInvoice);
    }

    @Override
    public Invoice getInvoice(int invoice_id) {
        try{
            return jdbcTemplate.queryForObject(GET_INVOICE_SQL, this::mapRowToInvoice, invoice_id);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no game with id " + invoice_id + "; message: " + erdae.getMessage());
            return null;
        }
    }

    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL,
                invoice.getName(),
                invoice.getStreet(),
                invoice.getCity(),
                invoice.getState(),
                invoice.getZipcode(),
                invoice.getItem_type(),
                invoice.getItem_id(),
                invoice.getUnit_price(),
                invoice.getQuantity(),
                invoice.getSubtotal(),
                invoice.getTax(),
                invoice.getProcessing_fee(),
                invoice.getTotal());

    }

    @Override
    public void deleteInvoice(int invoice_id) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, invoice_id);
    }


    private Invoice mapRowToInvoice(ResultSet resultSet, int rowNum) throws SQLException{
        Invoice invoice = new Invoice();
        invoice.setInvoice_id(resultSet.getInt("invoice_id"));
        invoice.setName(resultSet.getString("name"));
        invoice.setStreet(resultSet.getString("street"));
        invoice.setCity(resultSet.getString("city"));
        invoice.setState(resultSet.getString("state"));
        invoice.setZipcode(resultSet.getString("zipcode"));
        invoice.setItem_type(resultSet.getString("item_type"));
        invoice.setItem_id(resultSet.getInt("item_id"));
        invoice.setUnit_price(resultSet.getBigDecimal("unit_price"));
        invoice.setQuantity(resultSet.getInt("quantity"));
        invoice.setSubtotal(resultSet.getBigDecimal("subtotal"));
        invoice.setTax(resultSet.getBigDecimal("tax"));
        invoice.setProcessing_fee(resultSet.getBigDecimal("processing_fee"));
        invoice.setTotal(resultSet.getBigDecimal("total"));

        return invoice;
    }
}

