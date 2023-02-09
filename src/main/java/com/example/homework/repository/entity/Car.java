package com.example.homework.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table(name = "car")
public class Car {

    @Id
    private Long id;
    private String make;
    private String model;
    private String numberplate;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "person_id", nullable = false)
    private User person;
}