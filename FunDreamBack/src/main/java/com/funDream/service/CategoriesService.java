/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service;

import org.springframework.http.ResponseEntity;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */
public interface CategoriesService {        
    
    ResponseEntity<?> getAllCategories();
}
