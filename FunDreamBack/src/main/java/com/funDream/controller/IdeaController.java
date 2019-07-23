/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.controller;

import com.funDream.service.implementation.IdeaServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api/idea")
public class IdeaController {

    @Autowired
    private IdeaServiceImpl ideaService;
    
    @PostMapping()
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity saveIdea( @RequestParam ("file") MultipartFile[] files, 
                                    @RequestParam ("type") String[] type, 
                                    @RequestParam ("idea") String json){
        
        return this.ideaService.saveIdea(files, type, json);
    }

    @GetMapping()
    public ResponseEntity getAllIdeas(){
        return this.ideaService.getAllIdeas();
    }
}
