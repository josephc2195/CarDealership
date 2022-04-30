package com.sg.CarDealership.controller;

import static com.fasterxml.jackson.databind.util.ClassUtil.name;
import com.sg.CarDealership.dao.MakeDao;
import com.sg.CarDealership.dto.Make;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Class controller with endpoints for Makes
 */
@RestController
@RequestMapping("/guildcars.com")
public class MakeController {
    Set<ConstraintViolation<Make>> violations = new HashSet<>();
    
    @Autowired 
    MakeDao makeDao;
    
    @GetMapping("/admin/makes") 
    public List<Make> readAllMakes() {
        return makeDao.getAllMakes();
    }

    @PostMapping("/admin/addmake")
    @ResponseStatus(HttpStatus.CREATED)
    public Make createMake(@RequestBody Make make) {
        return makeDao.addMake(make);
    }
}
