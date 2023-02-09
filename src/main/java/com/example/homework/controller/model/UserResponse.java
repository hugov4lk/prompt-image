package com.example.homework.controller.model;

import java.util.Set;

import com.example.homework.repository.entity.Car;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

    private Long id;
    private String name;
    private Set<Car> cars;
}
