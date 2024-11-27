package com.example.auta.controller;

import com.example.auta.model.Car;
import com.example.auta.service.CarService;
import com.example.auta.service.DriverService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/cars")
public class CarController {

    private DriverService driverService;
    private CarService carService;

    @Autowired
    public CarController(CarService carService, DriverService driverService) {
        this.carService = carService;
        this.driverService = driverService;
    }

    @GetMapping("/")
    public String list(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "car_list";
    }

    @GetMapping("/detail/{id}")
    public String detail(Model model, @PathVariable long id) {

        Car car = carService.getCarsById(id);
        if (car != null) {
            model.addAttribute("car", car);
            return "car_detail";
        }
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable long id) {
        carService.deleteCar(id);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String edit(Model model, @PathVariable long id) {

        Car car = carService.getCarsById(id);
        if (car != null) {
            model.addAttribute("car", car);
            model.addAttribute("edit", true);
            model.addAttribute("drivers", driverService.getAllDrivers());
            return "car_edit";
        }
        return "redirect:/";
    }

    @GetMapping("/create")
    public String createCar(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("edit", false);
        model.addAttribute("drivers", driverService.getAllDrivers());
        return "car_edit";
    }

    @PostMapping("/save")
    public String saveCar(@Valid @ModelAttribute Car car, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("edit", true);
            return "car_edit";
        }
        carService.saveCar(car);
        return "redirect:/";
    }
}
