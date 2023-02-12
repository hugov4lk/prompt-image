package com.example.java.template.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import com.example.java.template.repository.entity.Car;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarControllerIntegrationTest extends AbstractControllerIntegrationTest {

    @Override
    protected String getPath() {
        return "/cars";
    }

    @Test
    @Sql("/db/controller/car/create-data.sql")
    @Sql(scripts = "/db/controller/car/cleanup-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getCar_success() {
        ResponseEntity<Car> response = restTemplate.exchange("/cars/1",
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<>() {
                });

        assertNotNull(response.getBody());
        assertEquals(1, response.getBody().getId());
        assertEquals("Lada", response.getBody().getMake());
    }

    @Test
    @Sql("/db/controller/car/create-data.sql")
    @Sql(scripts = "/db/controller/car/cleanup-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getCars_findBySearchAndSortByModelDesc_success() {
        ResponseEntity<List<Car>> response = restTemplate.exchange("/cars?find=Bmw&sort=model:desc",
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<>() {
                });

        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("BMW", response.getBody().get(0).getMake());
        assertEquals("760", response.getBody().get(0).getModel());
        assertEquals("BMW", response.getBody().get(1).getMake());
        assertEquals("740", response.getBody().get(1).getModel());
    }

    @Test
    @Sql("/db/controller/car/create-data.sql")
    @Sql(scripts = "/db/controller/car/cleanup-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getCars_findBySearchAndSortByModelAsc_success() {
        ResponseEntity<List<Car>> response = restTemplate.exchange("/cars?find=Bmw&sort=model:asc",
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<>() {
                });

        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("BMW", response.getBody().get(0).getMake());
        assertEquals("740", response.getBody().get(0).getModel());
        assertEquals("BMW", response.getBody().get(1).getMake());
        assertEquals("760", response.getBody().get(1).getModel());
    }

    @Test
    @Sql("/db/controller/car/create-data.sql")
    @Sql(scripts = "/db/controller/car/cleanup-data.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    void getUserCars_success() {
        ResponseEntity<List<Car>> response = restTemplate.exchange("/users/1/cars",
                HttpMethod.GET,
                new HttpEntity<>(new HttpHeaders()),
                new ParameterizedTypeReference<>() {
                });

        assertNotNull(response.getBody());
        assertEquals(2, response.getBody().size());
        assertEquals("Lada", response.getBody().get(0).getMake());
        assertEquals("Kia", response.getBody().get(1).getMake());
    }
}
