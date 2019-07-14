/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service.implementation;

import com.funDream.entity.Categories;
import com.funDream.repository.CategoriesRepository;
import com.funDream.service.CategoriesService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@Service
public class CategoriesServiceImpl implements CategoriesService{

    @Autowired
    private CategoriesRepository categoriesRepository;
    
    @Override
    public ResponseEntity<?> getAllCategories() {
        
        List<Categories> listCategories =  this.categoriesRepository.findAll();
        
        if(listCategories == null || listCategories.isEmpty()){
            return new ResponseEntity ("No hay categorias disponibles en el sistema", HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity (listCategories, HttpStatus.OK);
    }
    
}
