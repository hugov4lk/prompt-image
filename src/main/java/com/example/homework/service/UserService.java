package com.example.homework.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.homework.repository.CarRepository;
import com.example.homework.repository.UserRepository;
import com.example.homework.repository.entity.Car;
import com.example.homework.repository.entity.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final CarRepository carRepository;

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> findAll(String find, String sort) {
        String[] sorting = sort.split(":");
        if (find.isBlank()) {
            return userRepository.findAll(Sort.by(Sort.Direction.valueOf(sorting[1].toUpperCase()), sorting[0]));
        }
        String search = "%" + find + "%";
        return userRepository.findAll(search.toUpperCase(), Sort.by(Sort.Direction.valueOf(sorting[1].toUpperCase()), sorting[0]));
    }

    public List<Car> findUserCars(Long id) {
        return carRepository.findByPersonId(id);
    }
}