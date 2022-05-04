package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.AggregateSoldCar;
import java.util.List;
import com.sg.CarDealership.dto.User;

/**
 * @author Weston Gavin, Joseph Chica && Ronald Gedeon; 
 * gitRepo: https://github.com/josephc2195/CarDealership.git 
 * Interface with all mandatory behavior methods available for UserDao
 */
public interface UserDao {
    public User addUser(int personId, User user);
    public List<User> getAllUsers();
    public boolean updateUser(User user);
    public User getUserById(int id);
    public int userSellCar(User user, int carId);
    public List<AggregateSoldCar> getSoldCars();

}
