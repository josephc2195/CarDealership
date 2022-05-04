package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Make;
import java.util.List;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Design of an interface defining ... on month day, year
 */
public interface MakeDao {
     public Make addMake(Make make);
     public Make getMakeByName(String makeName);
     public Make getMakeById(int makeId);
     public List<Make> getAllMakes();
}
