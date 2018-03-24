package com.dinero.app.account.controller;

import com.dinero.app.account.entity.User;
import com.dinero.app.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController
{
    @Autowired
    UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity createUser(@RequestBody User user)
    {
        return userService.addUser(user);
    }

    @PostMapping("/login")
    public String loginUser(@RequestBody String login, @RequestBody String password) {
        return userService.loginUser(login,password);
    }


}
