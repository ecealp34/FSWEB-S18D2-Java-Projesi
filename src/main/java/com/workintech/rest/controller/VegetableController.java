package com.workintech.rest.controller;

import com.workintech.rest.entity.Fruit;
import com.workintech.rest.entity.Vegetable;
import com.workintech.rest.exceptions.FruitException;
import com.workintech.rest.service.VegetableService;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

public class VegetableController {
    private VegetableService vegetableService;
    @Autowired
    public VegetableController(VegetableService vegetableService) {
        this.vegetableService = vegetableService;
    }

    @GetMapping("/")
    public List<Vegetable> get() {
        return vegetableService.findAll();
    }

    @GetMapping("/{id}")
    public Vegetable getById(@Positive @PathVariable int id) {
        Vegetable vegetable = vegetableService.find(id);
        if(vegetable == null) {
            throw new FruitException("Vegetable with given id is not exist: " + id, HttpStatus.NOT_FOUND);
        }
        return vegetable;
    }

    @GetMapping("/desc")
    public List<Vegetable> getByPriceDesc() {
        return vegetableService.findAllByPriceDesc();
    }

    @PostMapping("/")
    public Vegetable save(@Validated @RequestBody Vegetable vegetable) {
        return vegetableService.save(vegetable);
    }

    @PostMapping("/{name}")
    public List<Vegetable> save(@PathVariable String name) {
        return vegetableService.searchByName(name);
    }

    @DeleteMapping("/{id}")
    public Vegetable delete(@PathVariable int id) {
        Vegetable vegetable = getById(id);
        vegetableService.delete(vegetable);
        return vegetable;
    }
}
