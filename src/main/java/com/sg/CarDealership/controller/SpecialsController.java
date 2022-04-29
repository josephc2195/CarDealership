package com.sg.CarDealership.controller;

import com.sg.CarDealership.dao.SpecialsDao;
import com.sg.CarDealership.dto.Specials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Class controller with endpoints for Specials
 */
public class SpecialsController {
    @Autowired
    SpecialsDao specialDao;
    
    @PostMapping("addSpecial") 
    public String addSpecial(String title, String description) {
        Specials special = new Specials();
        special.setTitle(title);
        special.setDescription(description);
        return "redirect:/specials";
    }
}
