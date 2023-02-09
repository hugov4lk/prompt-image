package com.example.homework.controller;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.homework.repository.entity.Car;
import com.example.homework.repository.entity.User;
import com.example.homework.service.UserService;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final ModelMapper modelMapper;
    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable Long id) {
        Optional<User> user = userService.findById(id);
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers(@RequestParam(required = false, defaultValue = "") String find,
                                               @RequestParam(required = false, defaultValue = "name:asc") String sort) {
        List<User> users = userService.findAll(find, sort);
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}/cars")
    public ResponseEntity<List<Car>> getUserCars(@PathVariable Long id) {
        List<Car> cars = userService.findUserCars(id);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

}
