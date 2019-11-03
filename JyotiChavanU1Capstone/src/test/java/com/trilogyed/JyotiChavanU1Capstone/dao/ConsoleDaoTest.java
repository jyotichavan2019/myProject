package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.Console;
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
public class ConsoleDaoTest {

    @Autowired
    ConsoleDao consoleDao;

    private Console console;

    @Before
    public void setUp() throws Exception {

        List<Console> consoles = consoleDao.getAllConsole();
        for (Console console : consoles) {
            consoleDao.deleteConsole(console.getConsole_id());
        }
    }

    @Test
    public void addGetAndDeleteConsole(){
         Console consoleToAdd = new Console();
         consoleToAdd.setModel("Nintendo Switch");
         consoleToAdd.setManufacturer("NINTENDO");
         consoleToAdd.setMemory_amount("32GB");
         consoleToAdd.setProcessor("Tegra processor");
         consoleToAdd.setPrice(BigDecimal.valueOf(259.99));
         consoleToAdd.setQuantity(1);

         Console consoleAfterAddingToDB = consoleDao.addConsole(consoleToAdd);

         Console consoleIGot = consoleDao.getConsole(consoleAfterAddingToDB.getConsole_id());

         assertEquals(consoleIGot, consoleAfterAddingToDB );

         consoleDao.deleteConsole(consoleAfterAddingToDB.getConsole_id());

         consoleIGot = consoleDao.getConsole(consoleAfterAddingToDB.getConsole_id());

         assertNull( consoleIGot);



    }


    @Test
    public void getAllConsole() {

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

        consoleDao.addConsole(console1);
        consoleDao.addConsole(console2);

        List<Console> consoleList= consoleDao.getAllConsole();

        assertEquals(2,consoleList.size());

    }

    @Test
    public void updateConsole() {

        Console console1 = new Console();
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("NINTENDO");
        console1.setMemory_amount("32GB");
        console1.setProcessor("Tegra processor");
        console1.setPrice(BigDecimal.valueOf(299.99));
        console1.setQuantity(1);
        consoleDao.addConsole(console1);

        console1.setModel("Nintendo Switch");
        console1.setManufacturer("NINTENDO");
        console1.setMemory_amount("16GB");
        console1.setProcessor("Tegra processor");
        console1.setPrice(BigDecimal.valueOf(240.99));
        console1.setQuantity(1);

        consoleDao.updateConsole(console1);

        Console console2 = consoleDao.getConsole(console1.getConsole_id());

        assertEquals(console2 ,console1);

    }


    @Test
    public void getConsoleByManufacturer() {

        Console console1 = new Console();
        console1.setModel("Nintendo Switch");
        console1.setManufacturer("NINTENDO");
        console1.setMemory_amount("32GB");
        console1.setProcessor("Tegra processor");
        console1.setPrice(BigDecimal.valueOf(299.99));
        console1.setQuantity(1);
        consoleDao.addConsole(console1);

        List<Console> console2 = consoleDao.getConsoleByManufacturer("NINTENDO");

        assertEquals(1 ,console2.size());

    }
}