package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.Game;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GameDaoTest {

        @Autowired
        GameDao gameDao;

        private Game game;

    @Before
    public void setUp() throws Exception{
        List<Game> games = gameDao.getAllGames();
        for (Game game : games) {
            gameDao.deleteGame(game.getGame_id());
        }

    }

    @Test
    public void addGetAndDeleteGame(){
        Game gameToInsert = new Game();
        gameToInsert.setTitle(" Call of Duty");
        gameToInsert.setEsrb_rating("M17+");
        gameToInsert.setDescription("Blood and Gore and Intense Violence");
        gameToInsert.setPrice(BigDecimal.valueOf(9.99));
        gameToInsert.setStudio("ABCD");
        gameToInsert.setQuantity(1);

        Game gameAfterInsert = gameDao.addGame(gameToInsert);

        Game gameIGot = gameDao.getGame(gameAfterInsert.getGame_id());

        assertEquals(gameIGot, gameAfterInsert );

        gameDao.deleteGame(gameAfterInsert.getGame_id());

        gameIGot = gameDao.getGame(gameAfterInsert.getGame_id());

        assertNull( gameIGot);

    }

    @Test
    public void getAllGames(){

        Game gameToInsert1 = new Game();
        gameToInsert1.setTitle(" Call of Duty");
        gameToInsert1.setEsrb_rating("M17+");
        gameToInsert1.setDescription("Blood and Gore and Intense Violence");
        gameToInsert1.setPrice(BigDecimal.valueOf(9.99));
        gameToInsert1.setStudio("ABCD");
        gameToInsert1.setQuantity(1);

        Game gameToInsert2 = new Game();
        gameToInsert2.setTitle(" Star Wars");
        gameToInsert2.setEsrb_rating("TEEN");
        gameToInsert2.setDescription("Fantasy Violence & Mild Language");
        gameToInsert2.setPrice(BigDecimal.valueOf(9.99));
        gameToInsert2.setStudio("ABCD");
        gameToInsert2.setQuantity(2);

        gameDao.addGame(gameToInsert1);
        gameDao.addGame(gameToInsert2);

        List<Game> games = gameDao.getAllGames();

        assertEquals(2, games.size() );

    }
    @Test
    public void updateGame(){

        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);

        game = gameDao.addGame(game);

        game.setTitle("Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(19.99));
        game.setStudio("ABCD");
        game.setQuantity(2);

        gameDao.updateGame(game);

        Game game1 = gameDao.getGame(game.getGame_id());

        assertEquals(game1 , game);

    }

    @Test
    public void shouldGetGameByStudio(){

        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);

        game = gameDao.addGame(game);

        List<Game> game1 = gameDao.getGameByStudio("ABCD");

        assertEquals(1 , game1.size());


    }

    @Test
    public void shouldGetGameByEsbrRating(){

        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);

        game = gameDao.addGame(game);

        List<Game> game1 = gameDao.getGameByESBRRatting("M17+");

        assertEquals(1 , game1.size());

    }
    @Test
    public void shouldGetGameByTitle(){
        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);

        game = gameDao.addGame(game);

        Game game1 = gameDao.getGameByTitle("Call of Duty");

        assertEquals(game1 , game);

    }





}