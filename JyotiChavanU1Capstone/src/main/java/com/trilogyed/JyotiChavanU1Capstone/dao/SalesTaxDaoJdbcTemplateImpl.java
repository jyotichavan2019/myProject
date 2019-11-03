package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.SalesTax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SalesTaxDaoJdbcTemplateImpl implements SalesTaxDao {


    private JdbcTemplate jdbcTemplate;
    private static final String GET_RATE_BY_STATE_SQL =
            " select * from sales_tax_rate where state = ? ";



    @Autowired
    public SalesTaxDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public SalesTax getRateByState(String state) {
        try{
            return jdbcTemplate.queryForObject(GET_RATE_BY_STATE_SQL, this::mapRowToSalesTax, state);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no customer with id " + state + "; message: " + erdae.getMessage());
            return null;
        }
    }

    private SalesTax mapRowToSalesTax(ResultSet resultSet, int rowNum) throws SQLException {

        SalesTax salesTax = new SalesTax();
        salesTax.setState(resultSet.getString("state"));
        salesTax.setRate(resultSet.getBigDecimal("rate"));
        return salesTax;
    }

}
