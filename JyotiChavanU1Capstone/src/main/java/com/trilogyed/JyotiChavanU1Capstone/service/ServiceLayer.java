package com.trilogyed.JyotiChavanU1Capstone.service;

import com.trilogyed.JyotiChavanU1Capstone.dao.*;
import com.trilogyed.JyotiChavanU1Capstone.model.Console;
import com.trilogyed.JyotiChavanU1Capstone.model.Game;
import com.trilogyed.JyotiChavanU1Capstone.model.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {
    private GameDao gameDao;
    private ConsoleDao consoleDao;
    private TShirtDao tshirtDao;


    @Autowired
    public ServiceLayer(GameDao gameDao, ConsoleDao consoleDao, TShirtDao tshirtDao) {
        this.consoleDao = consoleDao;
        this.gameDao = gameDao;
        this.tshirtDao = tshirtDao;
    }

    // API for Game

    public Game saveGame(Game game) {
        Game game1 = new Game();
        game1.setTitle(game.getTitle());
        game1.setEsrb_rating(game.getEsrb_rating());
        game1.setDescription(game.getDescription());
        game1.setPrice(game.getPrice());
        game1.setStudio(game.getStudio());
        game1.setQuantity(game.getQuantity());
        game1 =  gameDao.addGame(game1);
        game1.setGame_id(game1.getGame_id());
        return game1;
    }

    public Game findGame(int game_id) {
        Game game =  gameDao.getGame(game_id);
        if(game == null)
            return null;
        else
            return game;
    }

    public List<Game> findAllGame() {
        return gameDao.getAllGames();
    }

    public void updateGame(Game game) {
        Game game1 = new Game();
        game1.setGame_id(game1.getGame_id());
        game1.setTitle(game.getTitle());
        game1.setEsrb_rating(game.getEsrb_rating());
        game1.setDescription(game.getDescription());
        game1.setPrice(game.getPrice());
        game1.setStudio(game.getStudio());
        game1.setQuantity(game.getQuantity());
        gameDao.updateGame(game1);
    }

    public void removeGame(int game_id) {
        gameDao.deleteGame(game_id);
    }

    public List<Game> findGameByStudio(String studio) {
        List<Game> gamesByStudio =  gameDao.getGameByStudio(studio);
        if(gamesByStudio == null)
            return null;
        else
            return gamesByStudio;
    }

    public List<Game> findGameByEsbrRating(String esrb_rating) {
        return gameDao.getGameByESBRRatting(esrb_rating);
    }

    public Game findGameByTitle(String title) {
        return gameDao.getGameByTitle(title);
    }


    // API for Console

    public Console saveConsole(Console console) {
        Console console1 = new Console();
        console1.setModel(console.getModel());
        console1.setManufacturer(console.getManufacturer());
        console1.setMemory_amount(console.getMemory_amount());
        console1.setProcessor(console.getProcessor());
        console1.setPrice(console.getPrice());
        console1.setQuantity(console.getQuantity());
        console1 =  consoleDao.addConsole(console1);
       // console1.setConsole_id(console1.getConsole_id());
        return console1;

    }

    public Console findConsole(int console_id) {
        Console console =  consoleDao.getConsole(console_id);
        if(console == null)
            return null;
        else
            return console;
    }

    public List<Console> findAllConsole() {
        return consoleDao.getAllConsole();
    }

    public void updateConsole(Console console) {
        Console console1 = new Console();
        console1.setConsole_id(console1.getConsole_id());
        console1.setModel(console.getModel());
        console1.setManufacturer(console.getManufacturer());
        console1.setMemory_amount(console.getMemory_amount());
        console1.setProcessor(console.getProcessor());
        console1.setPrice(console.getPrice());
        console1.setQuantity(console.getQuantity());
        consoleDao.updateConsole(console1);
    }

    public void removeConsole(int console_id) {
        consoleDao.deleteConsole(console_id);

    }
    public List<Console> findGameByManufacturer(String manufacturer) {
        List<Console> consoleByManufacturer =  consoleDao.getConsoleByManufacturer(manufacturer);
        if(consoleByManufacturer == null)
            return null;
        else
            return consoleByManufacturer;
    }


    // API for TShirt

    public TShirt saveTShirt(TShirt tShirt) {
        TShirt tShirt1 = new TShirt();
        tShirt1.setSize(tShirt.getSize());
        tShirt1.setDescription(tShirt.getDescription());
        tShirt1.setColor(tShirt.getColor());
        tShirt1.setPrice(tShirt.getPrice());
        tShirt1.setQuantity(tShirt.getQuantity());
        tShirt1 =  tshirtDao.addTShirt(tShirt1);
        tShirt1.setT_shirt_id(tShirt1.getT_shirt_id());
        return tShirt1;
    }

    public TShirt findTShirt(int tShirt_id) {
        TShirt tShirt =  tshirtDao.getTShirt(tShirt_id);
        if(tShirt == null)
            return null;
        else
            return tShirt;
    }

    public List<TShirt> findAllTShirt() {
        return tshirtDao.getAllTShirt();
    }

    public void updateTShirt(TShirt tShirt) {
        TShirt tShirt1 = new TShirt();
        tShirt1.setT_shirt_id(tShirt1.getT_shirt_id());
        tShirt1.setSize(tShirt1.getSize());
        tShirt1.setDescription(tShirt1.getDescription());
        tShirt1.setColor(tShirt1.getColor());
        tShirt1.setPrice(tShirt1.getPrice());
        tShirt1.setQuantity(tShirt1.getQuantity());
        tshirtDao.updateTShirt(tShirt1);
    }

    public void removeTShirt(int tShirt_id) {
        tshirtDao.deleteTShirt(tShirt_id);
    }

    public List<TShirt> findTShirtByColor(String color) {
        List<TShirt> tShirtsByColor = tshirtDao.getTShirtByColor(color);
        if(tShirtsByColor == null)
            return null;
        else
            return tShirtsByColor;
    }


    public List<TShirt> findTShirtBySize(String size) {
        List<TShirt> tShirtsbySize = tshirtDao.getTShirtBySize(size);
        if(tShirtsbySize == null)
            return null;
        else
            return tShirtsbySize;
    }
}