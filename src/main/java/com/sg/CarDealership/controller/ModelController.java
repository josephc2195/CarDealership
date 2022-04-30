package com.sg.CarDealership.controller;

import com.sg.CarDealership.dao.ModelDao;
import com.sg.CarDealership.dto.Model;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Class controller with endpoints for Model
 */
@RestController
@RequestMapping("/guildcars.com")
public class ModelController {
    
    @Autowired
    ModelDao modelDao;
    
    @PostMapping("/admin/addmodel/{makeId}") 
    @ResponseStatus(HttpStatus.CREATED)
    public Model createModel(@PathVariable int makeId, @RequestBody Model model) { // custom makeIdModel
        return modelDao.addModel(makeId, model);
    }

    @GetMapping("/admin/models")
    public List<Model> readAllModels() {
        return modelDao.getAllModels();
    }
}
