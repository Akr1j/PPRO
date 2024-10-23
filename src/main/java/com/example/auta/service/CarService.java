package com.example.auta.service;

import com.example.auta.model.Car;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface CarService {

    ArrayList<Car> getAllCars();

    Car getCarsById(int id);

    void deleteCar(int id);

    void saveCar(Car car);
}