package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.ProcessingFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProcessingFeeDaoJdbcTemplateImpl implements ProcessingFeeDao {


    private JdbcTemplate jdbcTemplate;
    private static final String GET_PROCESSINGFEE_BY_PRODUCTTYPE_SQL =
            " select * from processing_fee where product_type = ? ";

    @Autowired
    public ProcessingFeeDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public long getProcessingFeeByProductType(String product_type) {

        try{
            return jdbcTemplate.queryForObject(GET_PROCESSINGFEE_BY_PRODUCTTYPE_SQL, this::mapRowToProcessingFee, product_type);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no customer with id " + product_type + "; message: " + erdae.getMessage());
            return null;
        }
    }

    private ProcessingFee mapRowToProcessingFee(ResultSet resultSet, int rowNum) throws SQLException {

        ProcessingFee processingFee = new ProcessingFee();
        processingFee.setProduct_type(resultSet.getString("product_type"));
        processingFee.setFee(resultSet.getBigDecimal("fee"));
        return processingFee;
    }

}
