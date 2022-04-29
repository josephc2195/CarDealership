package com.sg.CarDealership.dao;

import com.sg.CarDealership.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author Weston Gavin, Joseph Chica && Ronald Gedeon; 
 * gitRepo: https://github.com/josephc2195/CarDealership.git 
 * Implementation of UserDao Interface
 */
public class UserDaoDB implements UserDao{

    @Autowired
    JdbcTemplate jdbc;
    
    @Override
    public User addUser(User user) {
        final String CMD = "INSERT INTO user(role, username, pw) "
                + "VALUES(?, ?, ?)";
        jdbc.update(CMD, user.getRole(), user.getUsername(), user.getPw());
        int currId = jdbc.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);
        user.setId(currId);
        return user;
    }

    @Override
    public void updateUser(User user) {
        final String CMD = "UPDATE user SET role = ?, username = ?, " 
                + "pw = ? WHERE id = ?";
        jdbc.update(CMD, user.getRole(), user.getUsername(), user.getPw(), user.getId());
    }

}
