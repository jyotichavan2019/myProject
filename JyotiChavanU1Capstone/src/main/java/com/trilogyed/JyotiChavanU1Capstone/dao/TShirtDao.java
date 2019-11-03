package com.trilogyed.JyotiChavanU1Capstone.dao;

import com.trilogyed.JyotiChavanU1Capstone.model.TShirt;

import java.util.List;

public interface TShirtDao {

    TShirt addTShirt(TShirt game);

    List<TShirt> getAllTShirt();

    TShirt getTShirt(int id);

    void updateTShirt(TShirt game);

    void deleteTShirt(int id);

    List<TShirt> getTShirtByColor(String color);

    List<TShirt> getTShirtBySize(String size);
}
