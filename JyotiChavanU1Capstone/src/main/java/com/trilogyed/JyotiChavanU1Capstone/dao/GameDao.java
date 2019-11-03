package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.Game;

import java.util.List;

public interface GameDao {

    Game addGame(Game game);

    List<Game> getAllGames();

    Game getGame(int id);

    void updateGame(Game game);

    void deleteGame(int id);

    List<Game> getGameByStudio(String studio);

    List<Game> getGameByESBRRatting(String esrb_rating);

    Game getGameByTitle(String title);





}
