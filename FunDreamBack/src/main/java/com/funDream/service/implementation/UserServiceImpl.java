/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service.implementation;

import com.funDream.entity.User;
import com.funDream.repository.UserRepository;
import com.funDream.service.UserService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@Service 
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserRepository userRepository;

    @Override
    public Optional<User> getByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
    
    
    @Override
    public void save(User user){
        userRepository.save(user);
    }
}
