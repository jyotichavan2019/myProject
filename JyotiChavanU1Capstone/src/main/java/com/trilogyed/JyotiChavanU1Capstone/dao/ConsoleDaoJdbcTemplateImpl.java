package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
@Repository
public class ConsoleDaoJdbcTemplateImpl implements ConsoleDao{

    private JdbcTemplate jdbcTemplate;
    private static final String CREATE_CONSOLE_SQL =
            " insert into console (model, manufacturer, memory_amount, processor, price, quantity) values(?, ?, ?, ?, ?, ?)";
    private  static final String GET_CONSOLE_SQL =
            " select * from console where console_id = ? " ;
    private static final String DELETE_CONSOLE_SQL =
            " delete from console where console_id = ? " ;
    private static final String UPDATE_CONSOLE_SQL =
            " update console set model = ?, manufacturer = ?, memory_amount = ?, processor = ?, price = ?, quantity = ?";
    private static final String GET_ALL_CONSOLE_SQL =
            " select * from console ";
    private static final String GET_GAME_BY_MANUFACTURER_SQL =
            " select * from console where manufacturer = ? ";




    @Autowired
    public ConsoleDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Console addConsole(Console console) {
        jdbcTemplate.update(CREATE_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemory_amount(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity()
                );


        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        console.setConsole_id(id);
        return console;
    }

    @Override
    public List<Console> getAllConsole() {
        return jdbcTemplate.query(GET_ALL_CONSOLE_SQL, this::mapRowToConsole);
    }

    @Override
    public Console getConsole(int console_id) {
        try{
            return jdbcTemplate.queryForObject(GET_CONSOLE_SQL, this::mapRowToConsole, console_id);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no console with id " + console_id + "; message: " + erdae.getMessage());
            return null;
        }
    }

    @Override
    public void updateConsole(Console console) {
        jdbcTemplate.update(UPDATE_CONSOLE_SQL,
                console.getModel(),
                console.getManufacturer(),
                console.getMemory_amount(),
                console.getProcessor(),
                console.getPrice(),
                console.getQuantity());
    }

    @Override
    public void deleteConsole(int console_id) {
        jdbcTemplate.update(DELETE_CONSOLE_SQL, console_id);
    }

    @Override
    public List<Console> getConsoleByManufacturer(String manufacturer) {


        try{
            return jdbcTemplate.query(GET_GAME_BY_MANUFACTURER_SQL, this::mapRowToConsole, manufacturer);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no customer with id " + manufacturer + "; message: " + erdae.getMessage());
            return null;
        }
    }

    private Console mapRowToConsole(ResultSet resultSet, int rowNum) throws SQLException {
        Console console = new Console();
        console.setConsole_id(resultSet.getInt("console_id"));
        console.setModel(resultSet.getString("model"));
        console.setManufacturer(resultSet.getString("manufacturer"));
        console.setMemory_amount(resultSet.getString("memory_amount"));
        console.setProcessor(resultSet.getString("processor"));
        console.setPrice(resultSet.getBigDecimal("price"));
        console.setQuantity(resultSet.getInt("quantity"));

        return console;

    }

    }
