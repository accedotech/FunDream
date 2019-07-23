/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.controller;

import com.funDream.service.implementation.DocumentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 * 
 */

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/document")
public class DocumentController {
    
    @Autowired
    private DocumentServiceImpl documentService;
    
    @GetMapping("/show-image/{nameFile}")    
    public ResponseEntity getFile(@PathVariable("nameFile") String nameFile){        
        return this.documentService.getFile(nameFile);
    }
}
