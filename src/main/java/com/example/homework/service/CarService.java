package com.example.homework.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.homework.repository.CarRepository;
import com.example.homework.repository.entity.Car;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Optional<Car> findById(Long id) {
        return carRepository.findById(id);
    }

    public List<Car> findAll(String find, String sort) {
        String[] sorting = sort.split(":");
        if (find.isBlank()) {
            return carRepository.findAll(Sort.by(Sort.Direction.valueOf(sorting[1].toUpperCase()), sorting[0]));
        }
        String search = "%" + find + "%";
        return carRepository.findCarsBySearch(search.toUpperCase(), Sort.by(Sort.Direction.valueOf(sorting[1].toUpperCase()), sorting[0]));
    }
}
