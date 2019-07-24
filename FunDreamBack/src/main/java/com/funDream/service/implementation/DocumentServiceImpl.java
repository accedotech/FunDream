/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service.implementation;

import com.funDream.entity.Document;
import com.funDream.repository.DocumentRepository;
import com.funDream.service.DocumentService;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@Service
public class DocumentServiceImpl implements DocumentService{
   
    
    @Autowired
    private DocumentRepository documentRepository;
    
    private final String uri = "/home/felipe/Applications/FunDream/filesApplication/ideas";    
    
    @Override
    public List<Document> uploadFiles(MultipartFile[] files, String[] type){
        
        String nameFile; 
        Path pathFile;           
        List<Document> documentList = new ArrayList<>();                
        
        if(files.length > 0){
            for(int i = 0; i < files.length; i++) {
            
                if(!files[i].isEmpty()){

                    nameFile = UUID.randomUUID().toString()+ "_" +files[i].getOriginalFilename().replace(" ", "_");
                    pathFile = Paths.get(uri).resolve(nameFile).toAbsolutePath();

                    try {
                        Files.copy(files[i].getInputStream(), pathFile);
                    } 
                    catch (IOException ex) {
                        System.out.println(ex.toString());
                          return null;
                    } 
                    
                    documentList.add(  this.documentRepository.save(new Document(pathFile.toString(), nameFile,  type[i])));                
                }
                else{
                    return null;
                }
            }
        }
        else{
            return null; 
        }        
                
        return documentList;
    }
    
    
    @Override
     public ResponseEntity getFile(String nameFile) {
        
        HttpHeaders header = new HttpHeaders();
        Resource file = null;
        Path pathFile;
        pathFile = Paths.get(uri).resolve(nameFile).toAbsolutePath();
        
        try{
              file  = new UrlResource(pathFile.toUri());   
          }catch(MalformedURLException e){
              e.printStackTrace();
          }
        
        if(!file.exists() && file.isReadable()){
              return  new ResponseEntity("Error no se peude cargar el archivo: "+ nameFile, HttpStatus.NOT_ACCEPTABLE);
          }
                    
        header.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"");
        return new ResponseEntity(file, header, HttpStatus.OK);
    }
}
