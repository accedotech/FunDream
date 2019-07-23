/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service;

import com.funDream.entity.Document;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */
public interface DocumentService {
    
    List<Document> uploadFiles(MultipartFile[] files, String[] type);
    
    ResponseEntity getFile(String nameFile);
}
