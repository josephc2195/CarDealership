package com.sg.CarDealership.controller;

import java.util.List;
import com.sg.CarDealership.dao.SpecialsDao;
import com.sg.CarDealership.dto.Specials;
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
 * Class controller with endpoints for Specials
 */
@RestController
@RequestMapping("/guildcars.com")
public class SpecialsController {
    
    @Autowired
    SpecialsDao specialDao;
    
    @PostMapping("/admin/addspecial")
    @ResponseStatus(HttpStatus.CREATED)
    public Specials createSpecial(@RequestBody Specials special) {
        return specialDao.addSpecial(special);
    }
    
    @GetMapping("/admin/specials")
    public List<Specials> readAllSpecials() {
        return specialDao.getAllSpecials();
    }
}
