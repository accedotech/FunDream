/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.controller;

import com.funDream.service.implementation.CategoriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/categories")
public class CategoriesController {
    
    @Autowired
    private CategoriesServiceImpl categoriesService;
    
    @GetMapping()
    public ResponseEntity getAllCategories(){
        return this.categoriesService.getAllCategories();
    }
}
