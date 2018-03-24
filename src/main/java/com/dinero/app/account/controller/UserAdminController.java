package com.dinero.app.account.controller;

import com.dinero.app.account.dto.UserByEmail;
import com.dinero.app.account.entity.User;
import com.dinero.app.account.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
public class UserAdminController
{
    @Autowired
    UserService userService;
    
    @GetMapping
    public ResponseEntity<List<User>> getUsers()
    {
        return userService.getUsers();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable int id)
    {
        return userService.getUserById(id);
    }
    
    @PostMapping
    public ResponseEntity getUserByEmail(@RequestBody UserByEmail email)
    {
        return userService.getUserByEmail(email.getEmail());
    }

}
