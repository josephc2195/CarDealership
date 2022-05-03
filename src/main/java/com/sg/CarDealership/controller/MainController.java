/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.sg.CarDealership.controller;

import com.sg.CarDealership.dao.CarDao;
import com.sg.CarDealership.dao.SpecialsDao;
import com.sg.CarDealership.dto.Car;
import com.sg.CarDealership.dto.Specials;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author westo
 */
@Controller
@RequestMapping("guildcars.com/")
public class MainController {
    
    private final CarDao carDao;
    private final SpecialsDao specialDao;
    @Autowired
    public MainController(CarDao carDao, SpecialsDao specialDao) {
        this.carDao = carDao;
        this.specialDao = specialDao;
    }
    @GetMapping("index")
    public String getFeaturedCars(Model model) {
        List<Car> cars = carDao.getFeaturedCars();
        model.addAttribute("cars", cars);
        return "index";
    }
    @GetMapping("inventoryNew")
    public String getNewInventory(Model model){
        List<Car> cars = carDao.getNewCars();
        model.addAttribute("cars", cars);
        return "inventoryNew";
    }
    @GetMapping("inventoryUsed")
    public String getUsedInventory(Model model){
        List<Car> cars = carDao.getUsedCars();
        model.addAttribute("cars", cars);
        return "inventoryUsed";
    }
    @GetMapping("carDetails")
    public String getCarDetails(@RequestParam(value="carId", required = false) int carId, Model model){
        Car car = carDao.getCarById(carId);
        model.addAttribute("car", car);
        return "carDetails";
    }
    @GetMapping("specials")
    public String getSpecials(Model model){
        List<Specials> specials = specialDao.getAllSpecials();
        model.addAttribute("specials", specials);
        return "specials";
    }
    @GetMapping("contact")
    public String setContact(Model model){
        List<Car> cars = carDao.getUsedCars();
        model.addAttribute("cars", cars);
        return "contact";
    }
    @GetMapping("specialsDetails")
    public String setSpecials(Model model){
        List<Specials> specials = specialDao.getAllSpecials();
        model.addAttribute("specials", specials);
        return "specialsDetails";
    }
    @GetMapping("salesIndex")
    public String getSalesIndex(){
        return "salesIndex";
    }
    @GetMapping("addCar")
    public String addCar(){
        return "addCar";
    }
    @GetMapping("addUser")
    public String addUser(){
        return "addUser";
    }
    @GetMapping("car")
    public String showCar(){
        return "car";
    }
    @GetMapping("changePassword")
    public String changePassword(){
        return "changePassword";
    }
    @GetMapping("editCar")
    public String editCar(){
        return "editCar";
    }
    @GetMapping("editUser")
    public String editUser(){
        return "editUser";
    }
    @GetMapping("inventoryReport")
    public String getInventoryReport(){
        return "inventoryReport";
    }
    @GetMapping("make")
    public String getMake(){
        return "make";
    }
    @GetMapping("model")
    public String getModel(){
        return "model";
    }
    @GetMapping("report")
    public String getReports(){
        return "report";
    }
    @GetMapping("salesRecords")
    public String getSalesRecords(){
        return "salesRecords";
    }
    @GetMapping("salesReport")
    public String getSalesReport(){
        return "salesReport";
    }
    @GetMapping("users")
    public String getUser(){
        return "users";
    }
}
