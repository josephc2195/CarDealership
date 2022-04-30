package com.sg.CarDealership.controller;

import com.sg.CarDealership.dto.User;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Ronald Gedeon; email: gedemarcel0002@hotmail.com;  
 * gitRepo: https://github.com/gedegithub/C223-JavaDev.git 
 * Class controller with endpoints for User
 */
public class UserController {

    @PostMapping("addUser")
    public String addUser(String role, String username, String pw) {
        User user = new User();
        user.setRole(role);
        user.setUsername(username);
        user.setPassword(pw);
        
        
        return "redirect:/users";
    }
    
    @PostMapping("editUser")
    public void updateUser() {
        
    }
}
