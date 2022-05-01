package com.sg.CarDealership.controller;

import java.util.List;
import com.sg.CarDealership.dao.CustomerDao;
import com.sg.CarDealership.dto.Customer;
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
 * Design of a class ... on month day, year
 */
@RestController
@RequestMapping("/guildcars.com")
public class CustomerController {
    
    @Autowired
    CustomerDao customerDao;

    @GetMapping("/admin/customers")
    public List<Customer> readAllCustomers() {
        return customerDao.getAllCustomers();
    }
    
    @PostMapping("/addcustomer/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@PathVariable int personId, @RequestBody Customer customer) {//model binding in spring mvc where customer object is injected in the request body
        return customerDao.addCustomer(personId, customer);
    }
}
