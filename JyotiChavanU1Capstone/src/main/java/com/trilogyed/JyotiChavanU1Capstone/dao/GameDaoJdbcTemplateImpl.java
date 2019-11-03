package com.trilogyed.JyotiChavanU1Capstone.dao;


import com.trilogyed.JyotiChavanU1Capstone.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class GameDaoJdbcTemplateImpl implements GameDao {

    private JdbcTemplate jdbcTemplate;

    private static final String CREATE_GAME_SQL =
            " insert into game (title, esrb_rating, description, price, studio, quantity) values(?, ?, ?, ?, ?, ?)";
    private  static final String GET_GAME_SQL =
            " select * from Game where game_id = ? " ;
    private static final String DELETE_GAME_SQL =
            " delete from game where game_id = ? " ;
    private static final String UPDATE_GAME_SQL =
            " update game set title = ?, esrb_rating = ?, description = ?, price = ?, studio = ?, quantity = ?";
    private static final String GET_ALL_GAME_SQL =
            " select * from game ";
    private static final String GET_GAME_BY_STUDIO_SQL =
            " select * from Game where studio = ? ";
    private static final String GET_GAME_BY_ESBR_RATING_SQL =
            " select * from Game where esrb_rating = ? ";
    private static final String GET_GAME_BY_TITLE_SQL =
            " select * from Game where title = ? ";



    @Autowired
    public GameDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Game addGame(Game game) {
        jdbcTemplate.update(CREATE_GAME_SQL,
                game.getTitle(),
                game.getEsrb_rating(),
                game.getDescription(),
                game.getPrice(),
                game.getStudio(),
                game.getQuantity());

        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        game.setGame_id(id);
        return game;
    }

    @Override
    public List<Game> getAllGames() {
        return jdbcTemplate.query(GET_ALL_GAME_SQL, this::mapRowToGame);
    }

    @Override
    public Game getGame(int id) {
        try{
            return jdbcTemplate.queryForObject(GET_GAME_SQL, this::mapRowToGame, id);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no game with id " + id + "; message: " + erdae.getMessage());
            return null;
        }
    }


    @Override
    public void updateGame(Game game) {
        jdbcTemplate.update(UPDATE_GAME_SQL,
                            game.getTitle(),
                            game.getEsrb_rating(),
                            game.getDescription(),
                            game.getPrice(),
                            game.getStudio(),
                            game.getQuantity());

    }

    @Override
    public void deleteGame(int game_id) {
        jdbcTemplate.update(DELETE_GAME_SQL, game_id);
    }

    @Override
    public List<Game> getGameByStudio(String studio) {
        try{
            return jdbcTemplate.query(GET_GAME_BY_STUDIO_SQL, this::mapRowToGame, studio);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no customer with id " + studio + "; message: " + erdae.getMessage());
            return null;
        }
    }

    @Override
    public List<Game> getGameByESBRRatting(String esrb_rating) {
        try{
            return jdbcTemplate.query(GET_GAME_BY_ESBR_RATING_SQL, this::mapRowToGame, esrb_rating);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no customer with id " + esrb_rating + "; message: " + erdae.getMessage());
            return null;
        }
    }

    @Override
    public Game getGameByTitle(String title) {
        try{
            return jdbcTemplate.queryForObject(GET_GAME_BY_TITLE_SQL, this::mapRowToGame, title);
        }catch(EmptyResultDataAccessException erdae){
            System.out.println("There is no customer with id " + title + "; message: " + erdae.getMessage());
            return null;
        }
    }


    private Game mapRowToGame(ResultSet resultSet, int rowNum) throws SQLException {
            Game game = new Game();
            game.setGame_id(resultSet.getInt("game_id"));
            game.setTitle(resultSet.getString("title"));
            game.setEsrb_rating(resultSet.getString("esrb_rating"));
            game.setDescription(resultSet.getString("description"));
            game.setPrice(resultSet.getBigDecimal("price"));
            game.setStudio(resultSet.getString("studio"));
            game.setQuantity(resultSet.getInt("quantity"));
        return game;
    }
}
