package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Car;
import com.sg.CarDealership.dto.Make;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.sg.CarDealership.dao.MakeDaoDB.MakeMapper;
import org.springframework.stereotype.Repository;
/**
 * @author Weston Gavin, Joseph Chica && Ronald Gedeon; 
 * gitRepo: https://github.com/josephc2195/CarDealership.git 
 * Implementation of CarDao Interface
 */
@Repository
public class CarDaoDB implements CarDao {

    @Autowired
    JdbcTemplate jdbc;

    @Override
    public List<Car> getAllCars() {
        final String SELECT_ALL_CARS = "SELECT * FROM car";
        List<Car> cars = jdbc.query(SELECT_ALL_CARS, new CarMapper());
        
        for(Car car: cars){
            getMakeForCar(car);
        }
        return cars;
    }

    // fetch and populate the Make field in Car class
    private Make getMakeForCar(Car car) {
        final String SELECT_MAKE_FOR_CAR = "SELECT m.* FROM make m "
                + "JOIN car c ON m.id = c.makeId WHERE c.id = ?";
        return jdbc.queryForObject(SELECT_MAKE_FOR_CAR, new MakeMapper(), car.getId());
    }
    
    /*
    @Override
    public List<Car> getCarByMake(String make){
       try {
            final String SELECT_CAR_BY_MAKE = "SELECT * FROM car WHERE make = ?";
            List<Car> cars = jdbc.query(SELECT_CAR_BY_MAKE, new CarMapper(), make);
            return cars;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        } 
    }
    
    @Override
    public List<Car> getCarByModel(String model){
        try {
            final String SELECT_CAR_BY_MODEL = "SELECT * FROM car WHERE model = ?";
            List<Car> cars = jdbc.query(SELECT_CAR_BY_MODEL, new CarMapper(), model);
            return cars;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        } 
    }

    @Override
    public List<Car> getCarByYears(int min, int max) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Car> getCarByPrices(int min, int max) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

     */
    @Override
    public Car addCar(Car car) {
        final String INSERT_CAR = "INSERT INTO car(year, type, bodyStyle, interior, "
                + "color, mileage, transmission, vin, msrp, salesPrice, description, "
                + "picture, available) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
        jdbc.update(INSERT_CAR,
                car.getYear(),
                car.getType(),
                car.getBodyStyle(),
                car.getInterior(),
                car.getColor(),
                car.getMileage(),
                car.getTransmission(),
                car.getVin(),
                car.getMsrp(),
                car.getSalesPrice(),
                car.getDescription(),
                car.getPicture(),
                car.getAvailable());

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        car.setId(newId);
        return car;
    }

    @Override
    public boolean updateCar(Car car) {
        final String UPDATE_CAR = "UPDATE car SET year = ?, type = ?, bodyStyle = ?, interior = ?, "
                + "color = ?, mileage = ?, transmission = ?, vin = ?, msrp = ?, salesPrice = ?, description = ?, "
                + "picture = ?, available = ? WHERE id = ?";
        return jdbc.update(UPDATE_CAR,
                car.getYear(),
                car.getType(),
                car.getBodyStyle(),
                car.getInterior(),
                car.getColor(),
                car.getMileage(),
                car.getTransmission(),
                car.getVin(),
                car.getMsrp(),
                car.getSalesPrice(),
                car.getDescription(),
                car.getPicture(),
                car.getAvailable(),
                car.getId()) > 0;
    }

    // Specific field to Car class
    public static final class CarMapper implements RowMapper<Car> {

        @Override
        public Car mapRow(ResultSet rs, int index) throws SQLException {
            Car car = new Car();
            car.setId(rs.getInt("id"));
            car.setYear(rs.getInt("year"));
            car.setMileage(rs.getInt("mileage"));
            car.setType(rs.getString("type"));
            car.setBodyStyle(rs.getString("bodyStyle"));
            car.setInterior(rs.getString("interior"));
            car.setColor(rs.getString("color"));
            car.setTransmission(rs.getString("transmission"));
            car.setVin(rs.getString("vin"));
            car.setDescription(rs.getString("description"));
            car.setPicture(rs.getString("picture"));
            car.setMsrp(rs.getDouble("msrp"));
            car.setSalesPrice(rs.getDouble("salesPrice"));
            car.setAvailable(rs.getInt("available"));
            return car;
        }
    }
}
