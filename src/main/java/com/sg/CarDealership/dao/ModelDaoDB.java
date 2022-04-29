package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Design of a class ... on month day, year
 */
public class ModelDaoDB implements ModelDao{
    
    @Autowired
    JdbcTemplate jdbc;

    @Override
    public Model addModel(Model model) {
        final String CMD = "INSERT INTO model(`name`, email, date, make) " +
                "VALUES(?,?,?,?)";
        
        jdbc.update(CMD, model.getName(), model.getEmail(), model.getDate(), model.getMake());
        int currId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        model.setId(currId);
        return model;
    }

}
