package com.example.ex8crudoperations.cars.controllers;

import com.example.ex8crudoperations.cars.entities.Car;
import com.example.ex8crudoperations.cars.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/cars")
public class CarController {

    @Autowired
    private CarService carService;

    @PostMapping
    public Car create(@RequestBody Car car) {
        return carService.create(car);
    }

    @GetMapping
    public Page<Car> loadAll(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size) {
        return carService.getAll(page, size);
    }

    @GetMapping("/{id}")
    public Optional<Car> loadBy(@PathVariable long id) {
        return carService.getBy(id);
    }

    @PutMapping("/{id}")
    public Car updateBy(@PathVariable long id, @RequestParam String type) {
       return carService.updateTypeBy(id, type);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBy(@PathVariable long id) {
        try {
            carService.deleteBy(id);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        } catch (ClassNotFoundException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @DeleteMapping
    public void deleteAll() {
        carService.deleteAll();
    }
}
