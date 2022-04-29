package com.sg.CarDealership.controller;

import com.sg.CarDealership.dao.MakeDao;
import com.sg.CarDealership.dto.Make;
import com.sg.CarDealership.dto.Model;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Class controller with endpoints for Makes
 */
public class MakesController {
    Set<ConstraintViolation<Make>> violations = new HashSet<>();
    
    @Autowired 
    MakeDao makeDao;
    
    @GetMapping("makes") 
    public String displayMakes(Model model) {
        List<Make> makes = makeDao.getAllMakes();
//        model.addAttribute("makes", makes);
//        model.addAttribute("errors", violations);
        return "makes";
    }

    @PostMapping("addMake")
    public String addMake(String name, String email, Date dateAdded, int carId) {
        Make make = new Make();
        make.setCarId(carId);
        make.setDate(dateAdded);
        make.setEmail(email);
        make.setName(name);
        Validator v = Validation.buildDefaultValidatorFactory().getValidator();
        violations = v.validate(make);
        
        if(violations.isEmpty()) {
            makeDao.addMake(make);
        }
        return "redirect:/makes";
    }
}
