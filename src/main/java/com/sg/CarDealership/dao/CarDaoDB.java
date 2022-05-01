package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.Car;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.sg.CarDealership.dao.ModelDaoDB.ModelMapper;
import com.sg.CarDealership.dto.Model;
import org.springframework.dao.EmptyResultDataAccessException;
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
    
    @Autowired
    ModelDaoDB modelDao;
    
    @Override
    public Car getCarById(int carId) {
        final String SELECT_CARID = "SELECT * FROM car WHERE id = ?";

        // car id not exist
        try {
            Car car = jdbc.queryForObject(SELECT_CARID, new CarMapper(), carId);
            Model model = getModelForCar(car);
            car.setModel(model); // populate model field in Car class
            model.setMake(modelDao.getMakeForModel(model));
            return car;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    @Override
    public List<Car> getAllCars() {
        final String SELECT_ALL_CARS = "SELECT * FROM car";
        List<Car> cars = jdbc.query(SELECT_ALL_CARS, new CarMapper());
        
        for(Car car: cars){
            Model model = getModelForCar(car);
            model.setMake(modelDao.getMakeForModel(model));
            car.setModel(model); // populate model field in Car class
            
        }
        return cars;
    }

    // fetch and populate the Make field in Car class
    public Model getModelForCar(Car car) {
        final String SELECT_MODEL_FOR_CAR = "SELECT mo.* FROM model mo "
                + "JOIN car c ON mo.id = c.modelId WHERE c.id = ?";
        return jdbc.queryForObject(SELECT_MODEL_FOR_CAR, new ModelMapper(), car.getId());
    }
   
    @Override
    public Car addCar(int modelId, Car car) {
        final String INSERT_CAR = "INSERT INTO car(year, type, bodyStyle, interior, "
                + "color, mileage, transmission, vin, msrp, salesPrice, description, "
                + "picture, available, featured, modelId) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
        
        /*       if (modelId == 0) {
            throw new InvalidRequestParametersException("Invalid parameters - Missing  modelId");
*/         
        // check if model id exists.
//        Model model = modelDao.getModelById(modelId);
//        if (model == null) {
//            System.out.println("Make doesn't exists"); // throw new MakeNotFoundException(modelId);
//        }
        
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
                car.getAvailable(),
                car.getFeatured(),
                modelId);

        int newId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        car.setId(newId);
        return car;
    }

    @Override
    public boolean updateCar(Car car) {
        final String UPDATE_CAR = "UPDATE car SET year = ?, type = ?, bodyStyle = ?, interior = ?, "
                + "color = ?, mileage = ?, transmission = ?, vin = ?, msrp = ?, salesPrice = ?, description = ?, "
                + "picture = ?, available = ?, featured = ? WHERE id = ?";
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
                car.getFeatured(),
                car.getId()) > 0;
    }

    // Specific field to Car class
    public static final class CarMapper implements RowMapper<Car> {

        @Override
        public Car mapRow(ResultSet rs, int index) throws SQLException {
            Car car = new Car();
            car.setId(rs.getInt("id"));
            car.setYear(rs.getInt("year"));
            car.setType(rs.getString("type"));
            car.setBodyStyle(rs.getString("bodyStyle"));
            car.setInterior(rs.getString("interior"));
            car.setColor(rs.getString("color"));
            car.setMileage(rs.getInt("mileage"));
            car.setTransmission(rs.getString("transmission"));
            car.setVin(rs.getString("vin"));
            car.setMsrp(rs.getDouble("msrp"));
            car.setSalesPrice(rs.getDouble("salesPrice"));
            car.setDescription(rs.getString("description"));
            car.setPicture(rs.getString("picture"));
            car.setAvailable(rs.getInt("available"));
            car.setFeatured(rs.getInt("featured"));
            return car;
        }
    }
}
