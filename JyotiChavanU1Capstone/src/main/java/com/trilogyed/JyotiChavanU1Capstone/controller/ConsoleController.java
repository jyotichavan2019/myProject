package com.trilogyed.JyotiChavanU1Capstone.controller;

import com.trilogyed.JyotiChavanU1Capstone.exception.NotFoundException;
import com.trilogyed.JyotiChavanU1Capstone.model.Console;
import com.trilogyed.JyotiChavanU1Capstone.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/consoles")
public class ConsoleController {

    @Autowired
    ServiceLayer serviceLayer;

    public ConsoleController(ServiceLayer serviceLayer) {
        this.serviceLayer = serviceLayer;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Console createConsole(@RequestBody Console console1) {

        return serviceLayer.saveConsole(console1);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Console> getAllConsole() {

        return serviceLayer.findAllConsole();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console getConsole(@PathVariable int id) {

        Console returnVal = serviceLayer.findConsole(id);
        if ( returnVal  == null ) {
            throw new NotFoundException("There is no Console with id " + id);
        }

        return returnVal;
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Console updateConsole(@PathVariable int id, @RequestBody @Valid Console console) {

        if( console.getConsole_id() == 0) {
            console.setConsole_id(id);
        }

        serviceLayer.updateConsole(console);
        return console;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteConsole(@PathVariable int id) {

        serviceLayer.removeGame(id);
    }

    @GetMapping("/manufacturer/{manufacturer}")
    @ResponseStatus(HttpStatus.OK)
    public List<Console>  getConsoleByManufacturer(@PathVariable String manufacturer) {

        List<Console> returnVal = serviceLayer.findGameByManufacturer(manufacturer);
        if ( returnVal  == null ) {
            throw new NotFoundException("There is no Console with manufacturer " + manufacturer);
        }

        return returnVal;
    }




}
