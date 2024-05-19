package com.example.ex8crudoperations.cars.services;

import com.example.ex8crudoperations.cars.entities.Car;
import com.example.ex8crudoperations.cars.repositories.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    public Car create(Car car) {
        return carRepository.save(car);
    }

    public Page<Car> getAll(Integer page, Integer size) {
        if ((page == null && size == null)) {
            return Page.empty();
        }
        Sort sort = Sort.by(Sort.Direction.ASC, "modelName");
        Pageable pageable = PageRequest.of(page, size, sort);
        return carRepository.findAll(pageable);
    }

    public Optional<Car> getBy(long id) {
        if (!carRepository.existsById(id)) {
            return Optional.empty();
        }
        return carRepository.findById(id);
    }

    public Car updateTypeBy(long id, String type) {
        if (!carRepository.existsById(id)) {
            return null;
        }
        Car car = carRepository.getReferenceById(id);
        car.setType(type);
        return carRepository.save(car);
    }

    public void deleteBy(long id) throws ClassNotFoundException {
        if (!carRepository.existsById(id)) {
            throw new ClassNotFoundException();
        }
        carRepository.deleteById(id);
    }

    public void deleteAll() {
        carRepository.deleteAll();
    }
}
