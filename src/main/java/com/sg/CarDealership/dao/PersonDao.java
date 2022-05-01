package com.sg.CarDealership.dao;

import java.util.List;
import com.sg.CarDealership.dto.Person;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Design of an interface defining ... on month day, year
 */
public interface PersonDao {
    public Person addPerson(Person person);
    public Person getPersonById(int personId);
    public List<Person> getAllPersons();
}
