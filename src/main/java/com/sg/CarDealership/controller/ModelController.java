package com.sg.CarDealership.controller;

import com.sg.CarDealership.dao.ModelDao;
import com.sg.CarDealership.dto.Model;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Class controller with endpoints for Model
 */
public class ModelController {
    @Autowired
    ModelDao modelDao;
    
    @PostMapping("addModel") 
    public String addModel(String name, String email, Date dateAdded, int carId) {
        Model model = new Model();
        model.setName(name);
        model.setDate(dateAdded);
        model.setEmail(email);
        model.setId(carId);
        return "redirect:/models";
    }

}
