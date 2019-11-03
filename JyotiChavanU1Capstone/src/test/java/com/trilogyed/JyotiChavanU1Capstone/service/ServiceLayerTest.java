package com.trilogyed.JyotiChavanU1Capstone.service;

//import static org.junit.Assert.*;

import com.trilogyed.JyotiChavanU1Capstone.dao.*;
import com.trilogyed.JyotiChavanU1Capstone.model.Console;
import com.trilogyed.JyotiChavanU1Capstone.model.Game;
import com.trilogyed.JyotiChavanU1Capstone.model.TShirt;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    private ServiceLayer serviceLayer;
    private GameDao gameDao;
    private ConsoleDao consoleDao;
    private TShirtDao tShirtDao;

    @Before
    public void setUp() throws Exception {

        setUpGameDaoMock();
        setUpConsoleDaoMock();
        setUpTShirtDaoMock();

        serviceLayer = new ServiceLayer(gameDao, consoleDao, tShirtDao);
    }

    @Test
    public void saveFindGame() {
        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);

        game =serviceLayer.saveGame(game);
        Game fromService =serviceLayer.findGame(game.getGame_id());
        assertEquals(fromService, game);
    }

    @Test
    public void findGamebyStudio() {
        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);

        game =serviceLayer.saveGame(game);
        List<Game> fromService =serviceLayer.findGameByStudio(game.getStudio());

        assertEquals(1, fromService.size());
    }

    @Test
    public void findGamebyesrbRating() {
        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);

        game =serviceLayer.saveGame(game);
        List<Game> fromService =serviceLayer.findGameByEsbrRating(game.getEsrb_rating());

        assertEquals(1, fromService.size());
    }

    @Test
    public void findGameByTitle() {
        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);

        game =serviceLayer.saveGame(game);
        Game fromService =serviceLayer.findGameByTitle(game.getTitle());

        assertEquals(game, fromService);
    }

    @Test
    public void findAllGames() {
        Game game1 = new Game();
        game1.setTitle("Call of Duty");
        game1.setEsrb_rating("M17+");
        game1.setDescription("Blood and Gore and Intense Violence");
        game1.setPrice(BigDecimal.valueOf(9.99));
        game1.setStudio("ABCD");
        game1.setQuantity(1);

        serviceLayer.saveGame(game1);
        List<Game> gameList = new ArrayList<>();
        gameList.add(game1);
        serviceLayer.findAllGame();
        assertEquals(1, gameList.size());

    }


    @Test
    public void removeGame() {
        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);

        serviceLayer.saveGame(game);
        serviceLayer.removeGame(game.getGame_id());

        Game deletedGame = serviceLayer.findGame(game.getGame_id()) ;

        assertEquals(null, deletedGame);
    }

    @Test
    public void saveFindTShirt() {

        TShirt tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Black");
        tShirt.setDescription("Pure Cotton");
        tShirt.setPrice(BigDecimal.valueOf(19.99));
        tShirt.setQuantity(2);

        tShirt =serviceLayer.saveTShirt(tShirt);
        TShirt fromService =serviceLayer.findTShirt(tShirt.getT_shirt_id());
        assertEquals(tShirt, fromService);
    }

    @Test
    public void removeTshirt() {
        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("L");
        tShirt1.setColor("Black");
        tShirt1.setDescription("Pure Cotton");
        tShirt1.setPrice(BigDecimal.valueOf(19.99));
        tShirt1.setQuantity(2);


        serviceLayer.saveTShirt(tShirt1);

        serviceLayer.removeTShirt(tShirt1.getT_shirt_id());

        TShirt deletedTShirt = serviceLayer.findTShirt(tShirt1.getT_shirt_id()) ;

        assertEquals(null, deletedTShirt);
    }

    @Test
    public void findTShirtByColor() {
        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("L");
        tShirt1.setColor("Black");
        tShirt1.setDescription("Pure Cotton");
        tShirt1.setPrice(BigDecimal.valueOf(19.99));
        tShirt1.setQuantity(2);

        tShirt1 =serviceLayer.saveTShirt(tShirt1);

        List<TShirt> fromService =serviceLayer.findTShirtByColor(tShirt1.getColor());

        assertEquals(1, fromService.size());
    }

    @Test
    public void findTShirtBySize() {
        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("L");
        tShirt1.setColor("Black");
        tShirt1.setDescription("Pure Cotton");
        tShirt1.setPrice(BigDecimal.valueOf(19.99));
        tShirt1.setQuantity(2);

        tShirt1 =serviceLayer.saveTShirt(tShirt1);
        List<TShirt> fromService =serviceLayer.findTShirtBySize(tShirt1.getSize());

        assertEquals(1, fromService.size());
    }

    @Test
    public void findAllTShirts() {
        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("L");
        tShirt1.setColor("Black");
        tShirt1.setDescription("Pure Cotton");
        tShirt1.setPrice(BigDecimal.valueOf(19.99));
        tShirt1.setQuantity(2);

        serviceLayer.saveTShirt(tShirt1);
        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(tShirt1);
        serviceLayer.findAllTShirt();

        assertEquals(1, tShirtList.size());
    }

    @Test
    public void findAndSaveConsoleById() {
        Console console1 = new Console();
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("NINTENDO");
        console1.setMemory_amount("32GB");
        console1.setProcessor("Tegra processor");
        console1.setPrice(BigDecimal.valueOf(299.99));
        console1.setQuantity(1);

        console1 =serviceLayer.saveConsole(console1);
        Console fromService =serviceLayer.findConsole(console1.getConsole_id());
        assertEquals(fromService, console1);

    }

    @Test
    public void findConsoleByManufacturer() {
        Console console1 = new Console();
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("NINTENDO");
        console1.setMemory_amount("32GB");
        console1.setProcessor("Tegra processor");
        console1.setPrice(BigDecimal.valueOf(299.99));
        console1.setQuantity(1);

        console1 =serviceLayer.saveConsole(console1);
        List<Console>consoleList = serviceLayer.findGameByManufacturer(console1.getManufacturer());

        assertEquals(1, consoleList.size());

    }

    @Test
    public void removeConsole() {
        Console console1 = new Console();
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("NINTENDO");
        console1.setMemory_amount("32GB");
        console1.setProcessor("Tegra processor");
        console1.setPrice(BigDecimal.valueOf(299.99));
        console1.setQuantity(1);

        serviceLayer.saveConsole(console1);
        serviceLayer.removeTShirt(console1.getConsole_id());
        Console deletedConsole = serviceLayer.findConsole(console1.getConsole_id()) ;
        assertEquals(null, deletedConsole);
    }

    @Test
    public void shouldFindAllConsoles() {

        Console console1 = new Console();
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("NINTENDO");
        console1.setMemory_amount("32GB");
        console1.setProcessor("Tegra processor");
        console1.setPrice(BigDecimal.valueOf(299.99));
        console1.setQuantity(1);

        serviceLayer.saveConsole(console1);

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console1);
        serviceLayer.findAllConsole();

        assertEquals(1, consoleList.size());
    }

    @Test
    public void saveFindConsole() {
        Console console1 = new Console();
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("NINTENDO");
        console1.setMemory_amount("32GB");
        console1.setProcessor("Tegra processor");
        console1.setPrice(BigDecimal.valueOf(299.99));
        console1.setQuantity(1);

        console1 =serviceLayer.saveConsole(console1);
        Console fromService =serviceLayer.findConsole(console1.getConsole_id());
        assertEquals(fromService, console1);
    }

    private void setUpGameDaoMock() {
        gameDao = mock(GameDaoJdbcTemplateImpl.class);
        Game outPutGame = new Game();
        outPutGame.setGame_id(1);
        outPutGame.setTitle("Call of Duty");
        outPutGame.setEsrb_rating("M17+");
        outPutGame.setDescription("Blood and Gore and Intense Violence");
        outPutGame.setPrice(BigDecimal.valueOf(9.99));
        outPutGame.setStudio("ABCD");
        outPutGame.setQuantity(1);

        Game inputGame = new Game();
        inputGame.setTitle("Call of Duty");
        inputGame.setEsrb_rating("M17+");
        inputGame.setDescription("Blood and Gore and Intense Violence");
        inputGame.setPrice(BigDecimal.valueOf(9.99));
        inputGame.setStudio("ABCD");
        inputGame.setQuantity(1);



        List<Game> gameList = new ArrayList<>();
        gameList.add(outPutGame);
        doReturn(outPutGame).when(gameDao).addGame(inputGame);
        doReturn(outPutGame).when(gameDao).getGame(1);
        doReturn(gameList).when(gameDao).getAllGames();
        doReturn(gameList).when(gameDao).getGameByStudio("ABCD");
        doReturn(gameList).when(gameDao).getGameByESBRRatting("M17+");
        doReturn(outPutGame).when(gameDao).getGameByTitle("Call of Duty");



    }

    private void setUpConsoleDaoMock() {
        consoleDao = mock(ConsoleDaoJdbcTemplateImpl.class);
        Console console1 = new Console();
        console1.setConsole_id(11);
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("NINTENDO");
        console1.setMemory_amount("32GB");
        console1.setProcessor("Tegra processor");
        console1.setPrice(BigDecimal.valueOf(299.99));
        console1.setQuantity(1);

        Console inputConsole = new Console();
        inputConsole.setModel("Nintendo Switch");
        inputConsole.setManufacturer("NINTENDO");
        inputConsole.setMemory_amount("32GB");
        inputConsole.setProcessor("Tegra processor");
        inputConsole.setPrice(BigDecimal.valueOf(299.99));
        inputConsole.setQuantity(1);
        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console1);

        doReturn(console1).when(consoleDao).addConsole(inputConsole);
        doReturn(console1).when(consoleDao).getConsole(11);
        doReturn(consoleList).when(consoleDao).getAllConsole();
        doReturn(consoleList).when(consoleDao).getConsoleByManufacturer("NINTENDO");

    }

    private void setUpTShirtDaoMock() {
        tShirtDao = mock(TShirtDaoJdbcTemplateImpl.class);
        TShirt outPutTShirt= new TShirt();
        outPutTShirt.setT_shirt_id(11);
        outPutTShirt.setSize("L");
        outPutTShirt.setColor("Black");
        outPutTShirt.setDescription("Pure Cotton");
        outPutTShirt.setPrice(BigDecimal.valueOf(19.99));
        outPutTShirt.setQuantity(2);

        TShirt inputTShirt= new TShirt();
        inputTShirt.setSize("L");
        inputTShirt.setColor("Black");
        inputTShirt.setDescription("Pure Cotton");
        inputTShirt.setPrice(BigDecimal.valueOf(19.99));
        inputTShirt.setQuantity(2);

        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(outPutTShirt);

        doReturn(outPutTShirt).when(tShirtDao).addTShirt(inputTShirt);

        doReturn(tShirtList).when(tShirtDao).getAllTShirt();
        doReturn(outPutTShirt).when(tShirtDao).getTShirt(11);
        doReturn(tShirtList).when(tShirtDao).getTShirtByColor("Black");
        doReturn(tShirtList).when(tShirtDao).getTShirtBySize("L");

    }
}