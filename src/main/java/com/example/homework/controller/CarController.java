package com.example.homework.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.homework.repository.entity.Car;
import com.example.homework.service.CarService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("/{id}")
    public ResponseEntity<Car> getCar(@PathVariable long id) {
        Optional<Car> car = carService.findById(id);
        return new ResponseEntity<>(car.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Car>> getCars(@RequestParam(required = false, defaultValue = "") String find,
                                             @RequestParam(required = false, defaultValue = "make:asc") String sort) {
        List<Car> cars = carService.findAll(find, sort);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
