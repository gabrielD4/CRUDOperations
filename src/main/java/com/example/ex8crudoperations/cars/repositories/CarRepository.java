package com.example.ex8crudoperations.cars.repositories;

import com.example.ex8crudoperations.cars.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
}
