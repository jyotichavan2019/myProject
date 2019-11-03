package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.TShirt;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TShirtDaoTest {
    @Autowired
    TShirtDao tShirtDao;

    private TShirt tShirt;



    @Before
    public void setUp() throws Exception {
        List<TShirt> tShirts = tShirtDao.getAllTShirt();
        for (TShirt tShirt : tShirts) {
            tShirtDao.deleteTShirt(tShirt.getT_shirt_id());
        }
    }

    @Test
    public void addGetAndDeleteTShirt(){

        TShirt tShirt = new TShirt();
        tShirt.setSize("L");
        tShirt.setColor("Black");
        tShirt.setDescription("Pure Cotton");
        tShirt.setPrice(BigDecimal.valueOf(19.99));
        tShirt.setQuantity(2);
        TShirt tShirtToAddInDb = tShirtDao.addTShirt(tShirt);

        TShirt tShirt1 = tShirtDao.getTShirt(tShirtToAddInDb.getT_shirt_id());

        assertEquals(tShirt1, tShirtToAddInDb);

        tShirtDao.deleteTShirt(tShirtToAddInDb.getT_shirt_id());

        TShirt tShirtIGot = tShirtDao.getTShirt(tShirtToAddInDb.getT_shirt_id());

        assertNull(tShirtIGot);

    }
    @Test
    public void getAllTShirt() {

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

        tShirtDao.addTShirt(tShirt1);
        tShirtDao.addTShirt(tShirt2);

        List<TShirt> tShirtList = tShirtDao.getAllTShirt();

        assertEquals(2, tShirtList.size());


    }

    @Test
    public void updateTShirt() {

        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("L");
        tShirt1.setColor("Black");
        tShirt1.setDescription("Pure Cotton");
        tShirt1.setPrice(BigDecimal.valueOf(19.99));
        tShirt1.setQuantity(2);

        tShirtDao.addTShirt(tShirt1);


        tShirt1.setSize("L");
        tShirt1.setColor("Red");
        tShirt1.setDescription("Pure Cotton");
        tShirt1.setPrice(BigDecimal.valueOf(19.99));
        tShirt1.setQuantity(1);

        tShirtDao.updateTShirt(tShirt1);

        TShirt tShirtIGot = tShirtDao.getTShirt(tShirt1.getT_shirt_id());

        assertEquals(tShirtIGot, tShirt1);


    }

    @Test
    public void getTShirtByColor() {
        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("L");
        tShirt1.setColor("Black");
        tShirt1.setDescription("Pure Cotton");
        tShirt1.setPrice(BigDecimal.valueOf(19.99));
        tShirt1.setQuantity(2);

        tShirtDao.addTShirt(tShirt1);

        List<TShirt> tShirtIExpect = tShirtDao.getTShirtByColor("Black");

        assertEquals(1 , tShirtIExpect.size());


    }

    @Test
    public void getTShirtBySize() {

        TShirt tShirt1 = new TShirt();
        tShirt1.setSize("L");
        tShirt1.setColor("Black");
        tShirt1.setDescription("Pure Cotton");
        tShirt1.setPrice(BigDecimal.valueOf(19.99));
        tShirt1.setQuantity(2);

        tShirtDao.addTShirt(tShirt1);

        List<TShirt> tShirtIExpect = tShirtDao.getTShirtBySize("L");

        assertEquals(1 , tShirtIExpect.size());
    }
}