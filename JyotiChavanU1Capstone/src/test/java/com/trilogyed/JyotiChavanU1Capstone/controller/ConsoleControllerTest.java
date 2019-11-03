package com.trilogyed.JyotiChavanU1Capstone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.JyotiChavanU1Capstone.model.Console;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ConsoleController.class)
public class ConsoleControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

    }


    @Test
    public void getConsoleByIdShouldReturnConsoleWithIdJson() throws Exception {

        Console consoleToAdd = new Console();
        consoleToAdd.setModel("Nintendo Switch");
        consoleToAdd.setManufacturer("NINTENDO");
        consoleToAdd.setMemory_amount("32GB");
        consoleToAdd.setProcessor("Tegra processor");
        consoleToAdd.setPrice(BigDecimal.valueOf(259.99));
        consoleToAdd.setQuantity(1);
        consoleToAdd.setConsole_id(3);


        String outputJson = mapper.writeValueAsString(consoleToAdd);
        when(serviceLayer.findConsole(3)).thenReturn(consoleToAdd);

        this.mockMvc.perform(get("/consoles/id/3"))
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
    public void createConsoleShouldReturnCreatedConsole() throws Exception {
        Console console1 = new Console();
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("NINTENDO");
        console1.setMemory_amount("32GB");
        console1.setProcessor("Tegra processor");
        console1.setPrice(BigDecimal.valueOf(299.99));
        console1.setQuantity(1);

        String inputJson = mapper.writeValueAsString(console1);

        Console outputConsole = new Console();
        outputConsole.setModel("Nintendo Switch");
        outputConsole.setManufacturer("NINTENDO");
        outputConsole.setMemory_amount("32GB");
        outputConsole.setProcessor("Tegra processor");
        outputConsole.setPrice(BigDecimal.valueOf(299.99));
        outputConsole.setQuantity(1);
        outputConsole.setConsole_id(4);

        String outputJson = mapper.writeValueAsString(outputConsole);

        when(serviceLayer.saveConsole(console1)).thenReturn(outputConsole);

        this.mockMvc.perform(post("/consoles")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void getAllConsolesShouldReturnAListOfConsoles() throws Exception {


        Console console1 = new Console();
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("NINTENDO");
        console1.setMemory_amount("32GB");
        console1.setProcessor("Tegra processor");
        console1.setPrice(BigDecimal.valueOf(299.99));
        console1.setQuantity(1);

        Console console2 = new Console();
        console2.setModel("Nintendo Switch");
        console2.setManufacturer("NINTENDO");
        console2.setMemory_amount("16GB");
        console2.setProcessor("Tegra processor");
        console2.setPrice(BigDecimal.valueOf(259.99));
        console2.setQuantity(1);

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console1);
        consoleList.add(console2);

        List<Console> outPutList =  new ArrayList<>();
        outPutList.addAll(consoleList);

        when(serviceLayer.findAllConsole()).thenReturn(outPutList);
        String outputJson = mapper.writeValueAsString(outPutList);

        this.mockMvc.perform(get("/consoles"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void updateConsoleShouldReturnAnUpdatedConsoles() throws Exception {
        Console console1 = new Console();
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("NINTENDO");
        console1.setMemory_amount("32GB");
        console1.setProcessor("Tegra processor");
        console1.setPrice(BigDecimal.valueOf(299.99));
        console1.setQuantity(1);
        console1.setConsole_id(6);

        String inputJson = mapper.writeValueAsString(console1);
        String outputJson = mapper.writeValueAsString(console1);

        this.mockMvc.perform(put("/consoles/" + console1.getConsole_id())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void deleteGamesIsOkNoContentReturned() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/consoles/6"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void getConsoleByStudioShouldReturnConsoleWithManufacturerJson() throws Exception {

        Console console1 = new Console();
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("NINTENDO");
        console1.setMemory_amount("32GB");
        console1.setProcessor("Tegra processor");
        console1.setPrice(BigDecimal.valueOf(299.99));
        console1.setQuantity(1);
        console1.setConsole_id(6);

        List<Console> consoleList = new ArrayList<>();
        consoleList.add(console1);

        List<Console> outPutList =  new ArrayList<>();
        outPutList.addAll(consoleList);

        String outputJson = mapper.writeValueAsString(consoleList);
        when(serviceLayer.findGameByManufacturer( "NINTENDO")).thenReturn(outPutList);

        this.mockMvc.perform(get("/consoles/manufacturer/NINTENDO"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

















}