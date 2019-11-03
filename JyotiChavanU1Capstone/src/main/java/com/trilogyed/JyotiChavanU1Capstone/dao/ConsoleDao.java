package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.Console;

import java.util.List;

public interface ConsoleDao {


    Console addConsole(Console console);

    List<Console> getAllConsole();

    Console getConsole(int id);

    void updateConsole(Console game);

    void deleteConsole(int id);

    List<Console> getConsoleByManufacturer(String manufacturer);

}
