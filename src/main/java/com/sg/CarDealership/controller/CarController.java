package com.sg.CarDealership.controller;

import java.util.List;
import com.sg.CarDealership.dao.CarDao;
import com.sg.CarDealership.dto.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Class controller with endpoints for User
 */
@RestController
@RequestMapping("/guildcars.com")
public class CarController {
    
    private final CarDao carDao;
    
    @Autowired
    public CarController(CarDao carDao) {
        this.carDao = carDao;
    }
    
    @GetMapping("/admin/vehicles")
    public List<Car> readAllCars() {
        return carDao.getAllCars();
    }
    
    @PostMapping("/admin/addvehicle/{modelId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Car createCar(@PathVariable int modelId, @RequestBody Car car) {//model binding in spring mvc where todo object is injected in the request body
        return carDao.addCar(modelId, car);
    }
    
    /* We use 2 params to follow the convention that every URL that operates on an existing resource uses the form "admin/editvehicle/{id}"*/
    @PutMapping("admin/editvehicle/{modelId}")
    public ResponseEntity updateCar(@PathVariable int modelId, @RequestBody Car car){//ResponseEntity is type-less
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (modelId != car.getId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (carDao.updateCar(car)) {
            response = new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return response;
    }
}
