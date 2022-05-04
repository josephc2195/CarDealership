package com.sg.CarDealership.dao;

import com.sg.CarDealership.dao.PersonDaoDB.PersonMapper;
import com.sg.CarDealership.dto.AggregateSoldCar;
import com.sg.CarDealership.dto.Person;
import com.sg.CarDealership.dto.User;
import java.util.List;
import java.sql.SQLException;
import java.sql.ResultSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.dao.EmptyResultDataAccessException;

/**
 * @author Weston Gavin, Joseph Chica && Ronald Gedeon; 
 * gitRepo: https://github.com/josephc2195/CarDealership.git 
 * Implementation of UserDao Interface
 */
@Repository
public class UserDaoDB implements UserDao{

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public User addUser(int personId, User user) {
        final String INSERT_USER = "INSERT INTO user(role, username, password, personId) "
                + "VALUES(?, ?, ?, ?)";
        jdbc.update(INSERT_USER, 
                user.getRole(), 
                user.getUsername(), 
                user.getPassword(),
                personId);
        int currId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setId(currId);
        return user;
    }

    @Override
    public boolean updateUser(User user) {
        final String UPDATE_CAR = "UPDATE user SET role = ?, username = ?, " 
                + "password = ? WHERE id = ?";
        return jdbc.update(UPDATE_CAR, 
                user.getRole(), 
                user.getUsername(), 
                user.getPassword(), 
                user.getId()) > 0;
    }

    @Override
    public List<User> getAllUsers() {
        final String SELECT_ALL_USERS = "SELECT * FROM user";
        List<User> users = jdbc.query(SELECT_ALL_USERS, new UserMapper());
        
        for(User user: users){
            Person person = getPersonForUser(user);
            user.setPerson(person); // populate person field in User class
        }
        return users;
    }
    
    @Override
    public User getUserById(int id) {
        try {
            final String CMD = "SELECT * FROM user WHERE id = ?";
            User user = jdbc.queryForObject(CMD, new UserMapper(), id);
            user.setPerson(getPersonForUser(user));
            return user;
        } catch(DataAccessException ex) {
            return null;
        }
    }
    
    // fetch and populate the Person field in User class
    private Person getPersonForUser(User user) {
        final String SELECT_PERSON_FOR_USER = "SELECT p.* FROM person p "
                + "JOIN user u ON p.id = u.personId WHERE u.id = ?";
        return jdbc.queryForObject(SELECT_PERSON_FOR_USER, new PersonMapper(), user.getId());
    }

    @Override
    public int userSellCar(User user, int carId) {
        final String SELL_CAR = "UPDATE user SET carId = ? " 
                + "WHERE id = ?";
        
        return jdbc.update(SELL_CAR, 
                carId,  
                user.getId());
    }

    @Override
    public List<AggregateSoldCar> getSoldCars() {
        final String SELECT_SOLD_CARS = "SELECT * from aggregateSoldCar";
        // car id not exist
        try {
            List<AggregateSoldCar> sodlCars = jdbc.query(SELECT_SOLD_CARS, new AggregateSoldCarMapper());
            return sodlCars;
        } catch (EmptyResultDataAccessException ex) {
            return null;
        }
    }

    // Specific field to User class
    public static final class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int index) throws SQLException {
            User user = new User();
            user.setId(rs.getInt("id"));
            user.setRole(rs.getString("role"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            
            return user;
        }
    }
    
    // Specific field to AggregateSoldCar class
    public static final class AggregateSoldCarMapper implements RowMapper<AggregateSoldCar> {

        @Override
        public AggregateSoldCar mapRow(ResultSet rs, int index) throws SQLException {
            AggregateSoldCar aggregateSoldCar = new AggregateSoldCar();

            aggregateSoldCar.setUser(rs.getString("user"));
            aggregateSoldCar.setTotalSales(rs.getInt("totalSales"));
            aggregateSoldCar.setTotalVehicles(rs.getInt("totalVehicles"));

            return aggregateSoldCar;
        }
    }
}
