package com.dinero.app.account.service;

import com.dinero.app.account.dto.UserRegisterOut;
import com.dinero.app.account.entity.User;
import com.dinero.app.account.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@Service
@Slf4j
public class UserService
{
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    PasswordEncoder encoder;
    
    public ResponseEntity<List<User>> getUsers()
    {
        final List<User> listOfUsers = userRepository.findAll();
        
        return listOfUsers.isEmpty() ? ResponseEntity.noContent().build()
                                     : ResponseEntity.ok(listOfUsers);
    }
    
    public ResponseEntity getUserById(int userId)
    {
        final Optional<User> obtainedUser = userRepository.findById(userId);
    
        return obtainedUser.isPresent() ? ResponseEntity.ok(obtainedUser)
                                        : ResponseEntity.noContent().build();
    }
    
    public ResponseEntity getUserByEmail(String email)
    {
        final Optional<User> obtainedUser = userRepository.findByEmail(email);
    
        return obtainedUser.isPresent() ? ResponseEntity.ok(obtainedUser)
                                        : ResponseEntity.noContent().build();
    }
    
    public ResponseEntity<UserRegisterOut> addUser(User user)
    {
        user.setPassword(encoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        
        return ResponseEntity.status(CREATED)
                             .body(new UserRegisterOut(savedUser.getEmail(), savedUser.getUsername()));
    }

    public String loginUser(String login, String password) {
        JWT jwt = new JWT();
        String token = null;
        if (userRepository.checkUserPass(login, password)){

             token = jwt.createJWT(login,password);
        }

            return token;


    }
}
