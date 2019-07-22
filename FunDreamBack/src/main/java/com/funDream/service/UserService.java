/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service;

import com.funDream.entity.User;
import java.util.Optional;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */
public interface UserService {
    
    Optional<User> getByEmail(String email);
    
    boolean existsByEmail(String email);
    
    void save(User user);
    
    ResponseEntity<?> searchUserByEmail(String email);
}
