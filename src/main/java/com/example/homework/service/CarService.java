package com.example.homework.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.homework.exception.NotFoundException;
import com.example.homework.repository.CarRepository;
import com.example.homework.repository.entity.Car;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    public Car findById(Long id) {
        return carRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No data found with requested id."));
    }

    public List<Car> findByPersonId(Long id) {
        return carRepository.findByPersonId(id);
    }

    public List<Car> findAll(String find, String sortValue, Sort.Direction sortOrder) {
        if (find == null) {
            return carRepository.findAll(Sort.by(sortOrder, sortValue));
        }
        return carRepository.findAll(convertToValidSearch(find), Sort.by(sortOrder, sortValue));
    }

    private static String convertToValidSearch(String find) {
        return "%" + find.toUpperCase() + "%";
    }
}
