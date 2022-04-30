package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Specials;
import java.util.List;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Design of an interface defining ... on month day, year
 */
public interface SpecialsDao {
    public Specials addSpecial(Specials special);
    public List<Specials> getAllSpecials();
}
