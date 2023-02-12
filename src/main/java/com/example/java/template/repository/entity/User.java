package com.example.java.template.repository.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;

@Entity
@Getter
@Table(name = "person")
public class User {

    @Id
    private Long id;
    private String name;
}
