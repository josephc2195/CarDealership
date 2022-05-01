package com.sg.CarDealership.dao;

import java.util.List;
import com.sg.CarDealership.dto.Sales;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Design of an interface defining ... on month day, year
 */
public interface SalesDao {
    public Sales addSales(int carId, int customerId, Sales sales);
    public List<Sales> getAllSales();
}
