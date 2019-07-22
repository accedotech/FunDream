/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service.implementation;

import com.funDream.dto.UserDTO;
import com.funDream.entity.User;
import com.funDream.repository.UserRepository;
import com.funDream.service.UserService;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@Service 
public class UserServiceImpl implements UserService{
    
    @Autowired
    UserRepository userRepository;
    
    @Autowired
    ModelMapper modelMapper;

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

    @Override
    public ResponseEntity searchUserByEmail(String email) {
        
        Optional<User> user =  this.userRepository.findByEmail(email);
        
        if(user == null || !user.isPresent()){
            return new ResponseEntity("No se encontro ningun usuario registrado en el sistema con este correo: "+email, HttpStatus.NOT_FOUND);
        }        
        return new ResponseEntity( this.modelMapper.map(user.get(), UserDTO.class)  , HttpStatus.OK);
    }
    

}
