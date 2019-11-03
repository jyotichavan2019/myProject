package com.trilogyed.JyotiChavanU1Capstone.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.JyotiChavanU1Capstone.model.TShirt;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@WebMvcTest(TShirtController.class)
public class TShirtControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceLayer serviceLayer;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() {

    }

    @Test
    public void getTShirtByIdShouldReturnTShirtWithIdJson() throws Exception {

        TShirt tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Black");
        tShirt.setDescription("Pure Cotton");
        tShirt.setPrice(BigDecimal.valueOf(19.99));
        tShirt.setQuantity(2);
        tShirt.setT_shirt_id(3);

        String outputJson = mapper.writeValueAsString(tShirt);
        when(serviceLayer.findTShirt(3)).thenReturn(tShirt);

        this.mockMvc.perform(get("/tShirts/id/3"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void createTShirtShouldReturnCreatedTShirt() throws Exception {
        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("L");
        tShirt1.setColor("Black");
        tShirt1.setDescription("Pure Cotton");
        tShirt1.setPrice(BigDecimal.valueOf(19.99));
        tShirt1.setQuantity(2);
        String inputJson = mapper.writeValueAsString(tShirt1);

        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("L");
        tShirt2.setColor("Black");
        tShirt2.setDescription("Pure Cotton");
        tShirt2.setPrice(BigDecimal.valueOf(19.99));
        tShirt2.setQuantity(2);
        tShirt2.setT_shirt_id(4);

        String outputJson = mapper.writeValueAsString(tShirt2);

        when(serviceLayer.saveTShirt(tShirt1)).thenReturn(tShirt2);

        this.mockMvc.perform(post("/tShirts")
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void getAllTShirtsShouldReturnAListOfTShirts() throws Exception {

        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("L");
        tShirt1.setColor("Black");
        tShirt1.setDescription("Pure Cotton");
        tShirt1.setPrice(BigDecimal.valueOf(19.99));
        tShirt1.setQuantity(2);

        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("M");
        tShirt2.setColor("Res");
        tShirt2.setDescription("Pure Cotton");
        tShirt2.setPrice(BigDecimal.valueOf(19.99));
        tShirt2.setQuantity(4);

        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(tShirt1);
        tShirtList.add(tShirt2);

        List<TShirt> outPutList =  new ArrayList<>();
        outPutList.addAll(tShirtList);

        when(serviceLayer.findAllTShirt()).thenReturn(outPutList);
        String outputJson = mapper.writeValueAsString(outPutList);

        this.mockMvc.perform(get("/tShirts"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void updateTShirtsShouldReturnAnUpdatedTShirts() throws Exception {
        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("L");
        tShirt2.setColor("Black");
        tShirt2.setDescription("Pure Cotton");
        tShirt2.setPrice(BigDecimal.valueOf(19.99));
        tShirt2.setQuantity(2);
        tShirt2.setT_shirt_id(4);

        String inputJson = mapper.writeValueAsString(tShirt2);
        String outputJson = mapper.writeValueAsString(tShirt2);

        this.mockMvc.perform(put("/tShirts/" + tShirt2.getT_shirt_id())
                .content(inputJson)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(outputJson));
    }

    @Test
    public void deleteTShirtIsOkNoContentReturned() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.delete("/tShirts/1"))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void getTShirtByColorShouldReturnTShirtWithColorJson() throws Exception {

        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("L");
        tShirt2.setColor("Black");
        tShirt2.setDescription("Pure Cotton");
        tShirt2.setPrice(BigDecimal.valueOf(19.99));
        tShirt2.setQuantity(2);
        tShirt2.setT_shirt_id(4);

        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(tShirt2);

        List<TShirt> outPutList =  new ArrayList<>();
        outPutList.addAll(tShirtList);

        String outputJson = mapper.writeValueAsString(outPutList);
        when(serviceLayer.findTShirtByColor("Black")).thenReturn(outPutList);

        this.mockMvc.perform(get("/tShirts/color/Black"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }

    @Test
    public void getTShirtBySizeShouldReturnTShirtWithSizeJson() throws Exception {

        TShirt tShirt2 = new TShirt();
        tShirt2.setSize("L");
        tShirt2.setColor("Black");
        tShirt2.setDescription("Pure Cotton");
        tShirt2.setPrice(BigDecimal.valueOf(19.99));
        tShirt2.setQuantity(2);
        tShirt2.setT_shirt_id(4);

        List<TShirt> tShirtList = new ArrayList<>();
        tShirtList.add(tShirt2);

        List<TShirt> outPutList =  new ArrayList<>();
        outPutList.addAll(tShirtList);

        String outputJson = mapper.writeValueAsString(outPutList);
        when(serviceLayer.findTShirtBySize("L")).thenReturn(outPutList);

        this.mockMvc.perform(get("/tShirts/size/L"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputJson));

    }


}