/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funDream.entity.Document;
import com.funDream.entity.Idea;
import com.funDream.repository.IdeaRepository;
import com.funDream.service.IdeaService;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@Service
public class IdeaServiceImpl implements IdeaService{
    
    @Autowired
    private IdeaRepository ideaRepository;
    
    @Autowired
    private DocumentServiceImpl documentService;
    
    @Autowired
    private ObjectMapper Objectmapper;
    
    @Override
    public ResponseEntity saveIdea(MultipartFile[] files, String[] type, String json){
        
        Idea idea;
        String mensagge;
        
        //Convert Json to Idea object 
        try {
            idea = this.Objectmapper.readValue(json, Idea.class);             
        } catch (IOException ex) {
            mensagge = "Error en el servidor, la idea no puedo ser procesada.";
            return new ResponseEntity(mensagge, HttpStatus.NOT_ACCEPTABLE);   
        }
        
        
        Idea repetIdea = this.ideaRepository.findByName(idea.getName());
        if(repetIdea != null){
            mensagge = "En el sistema ya se encuentra una idea registrada con ese nombre: " + idea.getName();
            return new ResponseEntity(mensagge,  HttpStatus.NOT_ACCEPTABLE);
        }
        
        //Save the files  
        List<Document> documents= this.documentService.uploadFiles(files, type);        
        if(documents == null || documents.isEmpty()){
            mensagge = "Error en el servidor, Los archivos no han podido ser guardados.";
            return new ResponseEntity(mensagge,  HttpStatus.NOT_ACCEPTABLE);
        }
        
        idea.setDocuments(documents);
        idea.setState("created");
        idea.setCreatedAt(new Date());
        for(Document document: documents){
            if(document.getType().equals("main")){
                idea.setPrincipalImage(document.getNameFile());
            }
        }
        
        Idea createdIdea = this.ideaRepository.save(idea);
        
        if(createdIdea == null){
            mensagge = "Error en la base de datos, La idea no pudo ser guardada.";
            return new ResponseEntity(mensagge,  HttpStatus.NOT_ACCEPTABLE);
        }
        
        mensagge = "La idea fue creada correctamente.";
        return new ResponseEntity( new Gson().toJson(mensagge),  HttpStatus.OK);
    }

    
    @Override
    public ResponseEntity getAllIdeas() {
        
        List<Idea> ideasList = this.ideaRepository.findAll();
        
        if(ideasList == null || ideasList.isEmpty()){
            return new ResponseEntity("No se encontraron ideas disponibles.", HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity(ideasList, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getIdeaByName(String nameIdea) {
        Idea idea = this.ideaRepository.findByName(nameIdea);
        
        if(idea == null){
            return new ResponseEntity("No se encontro ninguna idea con el nombre de: " + nameIdea,  HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(idea,  HttpStatus.OK);
    }
    
    
    
    
}
