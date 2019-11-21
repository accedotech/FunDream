/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.controller;

import com.funDream.service.implementation.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */


@RestController
@RequestMapping("${api.base.url}/user")
public class UserController {
    
    @Autowired
    private UserServiceImpl userServiceImpl;
    
    @GetMapping("/search-by-email/{email}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity searchUserByEmail(@PathVariable("email") String email) {        
        return this.userServiceImpl.searchUserByEmail(email);
    }
    
   
}
