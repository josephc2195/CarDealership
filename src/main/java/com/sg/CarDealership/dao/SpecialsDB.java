package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Specials;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Design of a class ... on month day, year
 */
public class SpecialsDB implements SpecialsDao{
    
    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public Specials addSpecial(Specials special) {
        final String CMD = "INSERT INTO specials(title, description) " + 
                "VALUES(?, ?)";
        jdbc.update(CMD, special.getTitle(), special.getDescription());
        
        int currId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        special.setId(currId);
        
        return special;

    }

}
