package com.sg.CarDealership.controller;

import com.sg.CarDealership.dao.UserDao;
import java.util.List;
import com.sg.CarDealership.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Class controller with endpoints for User
 */
@RestController
@RequestMapping("/guildcars.com")
public class UserController {
    
    @Autowired
    UserDao userDao;

    @GetMapping("/admin/users")
    public List<User> readAllUsers() {
        return userDao.getAllUsers();
    }
    
    @PostMapping("/admin/adduser/{personId}")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@PathVariable int personId, @RequestBody User user) {//model binding in spring mvc where user object is injected in the request body
        return userDao.addUser(personId, user);
    }
    
   /* We use 2 params to follow the convention that every URL that operates on an existing resource uses the form "admin/edituser/{personId}"*/
    @PutMapping("admin/edituser/{personId}")
    public ResponseEntity updateUser(@PathVariable int personId, @RequestBody User user){//ResponseEntity is type-less
        ResponseEntity response = new ResponseEntity(HttpStatus.NOT_FOUND);
        if (personId != user.getId()) {
            response = new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        } else if (userDao.updateUser(user)) {
            response = new ResponseEntity(HttpStatus.ACCEPTED);
        }
        return response;
    }
}
