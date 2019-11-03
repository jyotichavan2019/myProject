package com.trilogyed.JyotiChavanU1Capstone.controller;

import com.trilogyed.JyotiChavanU1Capstone.exception.NotFoundException;
import com.trilogyed.JyotiChavanU1Capstone.model.TShirt;
import com.trilogyed.JyotiChavanU1Capstone.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/tShirts")
public class TShirtController {

    @Autowired
    ServiceLayer serviceLayer;

    public TShirtController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TShirt createTShirt(@RequestBody TShirt tShirt) {

        return serviceLayer.saveTShirt(tShirt);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getAllTShirt() {

        return serviceLayer.findAllTShirt();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirt getTShirt(@PathVariable int id) {

        TShirt returnVal = serviceLayer.findTShirt(id);
        if ( returnVal  == null ) {
            throw new NotFoundException("There is no TShirt with id " + id);
        }

        return returnVal;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TShirt updateTShirt(@PathVariable int id, @RequestBody @Valid TShirt tShirt) {

        if( tShirt.getT_shirt_id() == 0) {
            tShirt.setT_shirt_id(id);
        }

        serviceLayer.updateTShirt(tShirt);
        return tShirt;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void removeTShirt(@PathVariable int id) {

        serviceLayer.removeTShirt(id);
    }

    @GetMapping("/color/{color}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtByColor(@PathVariable String color) {

        List<TShirt> returnVal = serviceLayer.findTShirtByColor(color);
        if ( returnVal  == null ) {
            throw new NotFoundException("There is no TShirt with color " + color);
        }

        return returnVal;
    }

    @GetMapping("/size/{size}")
    @ResponseStatus(HttpStatus.OK)
    public List<TShirt> getTShirtBySize(@PathVariable String size) {

       List<TShirt>  returnVal = serviceLayer.findTShirtBySize(size);
        if ( returnVal  == null ) {
            throw new NotFoundException("There is no TShirt with size " + size);
        }

        return returnVal;
    }





}
