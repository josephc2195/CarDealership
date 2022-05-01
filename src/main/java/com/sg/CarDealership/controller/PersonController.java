package com.sg.CarDealership.controller;

import java.util.List;
import com.sg.CarDealership.dao.PersonDao;
import com.sg.CarDealership.dto.Person;
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
 * Design of a class ... on month day, year
 */
@RestController
@RequestMapping("/guildcars.com")
public class PersonController {
    
    @Autowired
    PersonDao personDao;

    @GetMapping("/admin/persons")
    public List<Person> readAllPersons() {
        return personDao.getAllPersons();
    }
    
    @PostMapping("/addperson")
    @ResponseStatus(HttpStatus.CREATED)
    public Person createCustomer(@RequestBody Person person) {//model binding in spring mvc where customer object is injected in the request body
        return personDao.addPerson(person);
    }
}
