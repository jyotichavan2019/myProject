package com.trilogyed.JyotiChavanU1Capstone.controller;

import com.trilogyed.JyotiChavanU1Capstone.exception.NotFoundException;
import com.trilogyed.JyotiChavanU1Capstone.model.Game;
import com.trilogyed.JyotiChavanU1Capstone.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    @Autowired
    ServiceLayer serviceLayer;

    public GameController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Game createGame(@RequestBody Game game) {

        return serviceLayer.saveGame(game);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getAllGames() {
        return serviceLayer.findAllGame();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGame(@PathVariable int id) {

        Game returnVal = serviceLayer.findGame(id);
        if ( returnVal  == null ) {
            throw new NotFoundException("There is no Game with id " + id);
        }

        return returnVal;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Game updateGame(@PathVariable int id, @RequestBody @Valid Game game) {

        if( game.getGame_id() == 0) {
            game.setGame_id(id);
        }

        serviceLayer.updateGame(game);
        return game;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteRsvp(@PathVariable int id) {

        serviceLayer.removeGame(id);
    }

    @GetMapping("/studio/{studio}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByStudio(@PathVariable String studio) {

        List<Game> returnVal = serviceLayer.findGameByStudio(studio);
        if ( returnVal  == null ) {
            throw new NotFoundException("There is no Game with studio " + studio);
        }

        return returnVal;
    }

    @GetMapping("/esrb_rating/{esrb_rating}")
    @ResponseStatus(HttpStatus.OK)
    public List<Game> getGameByEsbrRating(@PathVariable String esrb_rating) {

        List<Game> returnVal = serviceLayer.findGameByEsbrRating(esrb_rating);
        if ( returnVal  == null ) {
            throw new NotFoundException("There is no Game with esrb_rating " + esrb_rating);
        }

        return returnVal;
    }

    @GetMapping("/title/{title}")
    @ResponseStatus(HttpStatus.OK)
    public Game getGameByTitle(@PathVariable String title) {

        Game returnVal = serviceLayer.findGameByTitle(title);
        if ( returnVal  == null ) {
            throw new NotFoundException("There is no Game with title " + title);
        }

        return returnVal;
    }


}
