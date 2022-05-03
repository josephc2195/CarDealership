package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Car;
import java.util.List;

/**
 * @author Weston Gavin, Joseph Chica && Ronald Gedeon; 
 * gitRepo: https://github.com/josephc2195/CarDealership.git 
 * Interface with all mandatory behavior methods available for CarDao
 */
public interface CarDao {
    public Car getCarById(int carId);
    public List<Car> getAllCars();
    public List<Car> getFeaturedCars();
    public List<Car> getUsedCars();
    public List<Car> getNewCars();
//    public List<Car> getCarByMake(String make);
//    public List<Car> getCarByModel(String model);
//    public List<Car>getCarByYears(int min, int max);
//    public List<Car>getCarByPrices(int min, int max);
    public Car addCar(int modelId, Car car);
    public boolean updateCar(Car car);
}
