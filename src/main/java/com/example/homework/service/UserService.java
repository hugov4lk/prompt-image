package com.example.homework.service;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.homework.exception.NotFoundException;
import com.example.homework.repository.UserRepository;
import com.example.homework.repository.entity.User;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("No data found with requested id."));
    }

    public List<User> findAll(String find, String sortValue, Sort.Direction sortOrder) {
        if (find == null) {
            return userRepository.findAll(Sort.by(sortOrder, sortValue));
        }
        return userRepository.findAll(convertToValidSearch(find), Sort.by(sortOrder, sortValue));
    }

    private static String convertToValidSearch(String find) {
        return "%" + find.toUpperCase() + "%";
    }
}