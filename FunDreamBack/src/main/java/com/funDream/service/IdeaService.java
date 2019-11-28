/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */
public interface IdeaService {
    
    ResponseEntity saveIdea(MultipartFile[] files, String[] type, String json);
    
    ResponseEntity getAllIdeasForHome();
    
    ResponseEntity getCompleteIdeaById(Long id);
    
    ResponseEntity getIdeaByName(String nameIdea);
}
