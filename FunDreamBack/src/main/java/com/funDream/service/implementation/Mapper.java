/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service.implementation;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */
@Configuration
public class Mapper {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}

