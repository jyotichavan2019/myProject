package com.trilogyed.JyotiChavanU1Capstone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.JyotiChavanU1Capstone.model.Game;
import com.trilogyed.JyotiChavanU1Capstone.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(GameController.class)
public class GameControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

    }

    @Test
    public void getGameByIdShouldReturnGameWithIdJson() throws Exception {

        Game game = new Game();
        game.setTitle(" Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);
        game.setGame_id(12);

        String outputJson = mapper.writeValueAsString(game);
        when(serviceLayer.findGame(12)).thenReturn(game);

        this.mockMvc.perform(get("/games/id/12"))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(content().json(outputJson));

    }


//    @Test
//    public void getGameThatDoesNotExistReturns404() throws Exception{
//
//
//    }

    @Test
    public void createGameShouldReturnCreatedGame() throws Exception {
        Game inputGame = new Game();
        inputGame.setTitle("Call of Duty");
        inputGame.setEsrb_rating("M17+");
        inputGame.setDescription("Blood and Gore and Intense Violence");
        inputGame.setPrice(BigDecimal.valueOf(9.99));
        inputGame.setStudio("ABCD");
        inputGame.setQuantity(1);

        String inputJson = mapper.writeValueAsString(inputGame);

        Game outputGame = new Game();
        outputGame.setTitle("Call of Duty");
        outputGame.setEsrb_rating("M17+");
        outputGame.setDescription("Blood and Gore and Intense Violence");
        outputGame.setPrice(BigDecimal.valueOf(9.99));
        outputGame.setStudio("ABCD");
        outputGame.setQuantity(1);
        outputGame.setGame_id(6);

        String outputJson = mapper.writeValueAsString(outputGame);

        when(serviceLayer.saveGame(inputGame)).thenReturn(outputGame);

        this.mockMvc.perform(post("/games")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
                ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getAllGamesShouldReturnAListOfGames() throws Exception {

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

        List<Game> gameList = new ArrayList<>();
        gameList.add(gameToInsert1);
        gameList.add(gameToInsert2);

        List<Game> outPutList =  new ArrayList<>();
        outPutList.addAll(gameList);

        when(serviceLayer.findAllGame()).thenReturn(outPutList);
        String outputJson = mapper.writeValueAsString(outPutList);

        this.mockMvc.perform(get("/games"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void updateGamesShouldReturnAnUpdatedGames() throws Exception {
        Game game = new Game();
        game.setTitle("Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);
        game.setGame_id(6);

        String inputJson = mapper.writeValueAsString(game);
        String outputJson = mapper.writeValueAsString(game);

        this.mockMvc.perform(put("/games/" + game.getGame_id())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void deleteGamesIsOkNoContentReturned() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/games/6"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void getGameByStudioShouldReturnGameWithStudioJson() throws Exception {

        Game game = new Game();
        game.setTitle(" Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);
        game.setGame_id(12);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);

        List<Game> outPutList =  new ArrayList<>();
        outPutList.addAll(gameList);

        String outputJson = mapper.writeValueAsString(gameList);
        when(serviceLayer.findGameByStudio("ABCD")).thenReturn(outPutList);

        this.mockMvc.perform(get("/games/studio/ABCD"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void getGameByEsbrRatingShouldReturnGameWithEsbrRatingJson() throws Exception {

        Game game = new Game();
        game.setTitle(" Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);
        game.setGame_id(12);

        List<Game> gameList = new ArrayList<>();
        gameList.add(game);

        List<Game> outPutList =  new ArrayList<>();
        outPutList.addAll(gameList);


        String outputJson = mapper.writeValueAsString(gameList);
        when(serviceLayer.findGameByEsbrRating("M17+")).thenReturn(outPutList);

        this.mockMvc.perform(get("/games/esrb_rating/M17+"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void getGameByTitleShouldReturnGameWithTitleJson() throws Exception {

        Game game = new Game();
        game.setTitle(" Call of Duty");
        game.setEsrb_rating("M17+");
        game.setDescription("Blood and Gore and Intense Violence");
        game.setPrice(BigDecimal.valueOf(9.99));
        game.setStudio("ABCD");
        game.setQuantity(1);
        game.setGame_id(12);


        String outputJson = mapper.writeValueAsString(game);
        when(serviceLayer.findGameByTitle("Call of Duty")).thenReturn(game);

        this.mockMvc.perform(get("/games/title/Call of Duty"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }


}
