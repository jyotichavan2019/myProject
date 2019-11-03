package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TShirtDaoJdbcTemplateImpl implements TShirtDao {

    private JdbcTemplate jdbcTemplate;

    private static final String CREATE_TSHIRT_SQL =
            " insert into t_shirt (size, color, description, price, quantity) values(?, ?, ?, ?, ?)";
    private  static final String GET_TSHIRT_SQL =
            " select * from t_shirt where t_shirt_id = ? " ;
    private static final String DELETE_TSHIRT_SQL =
            " delete from t_shirt where t_shirt_id = ? " ;
    private static final String UPDATE_TSHIRT_SQL =
            " update t_shirt set size = ?, color = ?, description = ?, price = ?, quantity = ?";
    private static final String GET_ALL_TSHIRT_SQL =
            " select * from t_shirt ";
    private static final String GET_TSHIRT_BY_COLOR_SQL =
            " select * from t_shirt where color = ? ";
    private static final String GET_TSHIRT_BY_SIZE_SQL =
            " select * from t_shirt where size = ? ";

    @Autowired
    public TShirtDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public TShirt addTShirt(TShirt tShirt) {

        jdbcTemplate.update(CREATE_TSHIRT_SQL,
                        tShirt.getSize(),
                        tShirt.getColor(),
                        tShirt.getDescription(),
                        tShirt.getPrice(),
                        tShirt.getQuantity());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        tShirt.setT_shirt_id(id);
        return tShirt;

    }

    @Override
    public List<TShirt> getAllTShirt() {
        return jdbcTemplate.query(GET_ALL_TSHIRT_SQL, this::mapRowToTShirt);
    }

    @Override
    public TShirt getTShirt(int t_shirt_id) {
        try{
            return jdbcTemplate.queryForObject(GET_TSHIRT_SQL, this::mapRowToTShirt, t_shirt_id);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no game with id " + t_shirt_id + "; message: " + erdae.getMessage());
            return null;
        }
    }

    @Override
    public void updateTShirt(TShirt tShirt) {
        jdbcTemplate.update(UPDATE_TSHIRT_SQL,
                tShirt.getSize(),
                tShirt.getColor(),
                tShirt.getDescription(),
                tShirt.getPrice(),
                tShirt.getQuantity());

    }

    @Override
    public void deleteTShirt(int t_shirt_id) {
        jdbcTemplate.update(DELETE_TSHIRT_SQL, t_shirt_id);
    }

    @Override
    public List<TShirt> getTShirtByColor(String color) {
        try{
            return jdbcTemplate.query(GET_TSHIRT_BY_COLOR_SQL, this::mapRowToTShirt, color);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no customer with id " + color + "; message: " + erdae.getMessage());
            return null;
        }
    }

    @Override
    public List<TShirt> getTShirtBySize(String size) {
        try{
            return jdbcTemplate.query(GET_TSHIRT_BY_SIZE_SQL, this::mapRowToTShirt, size);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no customer with id " + size + "; message: " + erdae.getMessage());
            return null;
        }
    }

    private TShirt mapRowToTShirt(ResultSet resultSet, int rowNum) throws SQLException {

            TShirt tShirt = new TShirt();
            tShirt.setT_shirt_id(resultSet.getInt("t_shirt_id"));
            tShirt.setSize(resultSet.getString("size"));
            tShirt.setColor(resultSet.getString("color"));
            tShirt.setDescription(resultSet.getString("description"));
            tShirt.setPrice(resultSet.getBigDecimal("price"));
            tShirt.setQuantity(resultSet.getInt("quantity"));

            return tShirt;

    }

}
