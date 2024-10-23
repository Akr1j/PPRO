package com.example.auta.service;

import com.example.auta.model.Driver;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface DriverService {

    ArrayList<Driver> getAllDrivers();

    Driver getDriversById(int id);

    void deleteDriver(int id);

    void saveDriver(Driver Driver);
}