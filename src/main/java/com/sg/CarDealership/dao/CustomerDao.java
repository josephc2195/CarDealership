package com.sg.CarDealership.dao;

import java.util.List;
import com.sg.CarDealership.dto.Customer;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Design of an interface defining ... on month day, year
 */
public interface CustomerDao {
    public Customer addCustomer(int personId, Customer customer);
    public List<Customer> getAllCustomers();
}
