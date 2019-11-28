/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service.implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.funDream.dto.CompleteIdeaDTO;
import com.funDream.dto.IdeaHomeDTO;
import com.funDream.entity.Document;
import com.funDream.entity.Idea;
import com.funDream.entity.Transaction;
import com.funDream.repository.IdeaRepository;
import com.funDream.repository.TransactionRepository;
import com.funDream.service.IdeaService;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    private TransactionRepository transactionRepository;
    
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
        
        
        for(Document document: documents){
            document.setIdea(createdIdea);
        }
        
        List<Document> createdDocuemnts = this.documentService.saveDocumentsList(documents);        
        
        if(createdDocuemnts ==  null || createdDocuemnts.isEmpty()){
        mensagge = "La idea fue creada, pero los documentos no pudieron ser guardados.";
        return new ResponseEntity( mensagge,  HttpStatus.NOT_ACCEPTABLE);    
        }
        
        mensagge = "La idea fue creada correctamente.";
        return new ResponseEntity( new Gson().toJson(mensagge),  HttpStatus.OK);
    }

    
    @Override
    public ResponseEntity getAllIdeasForHome() {
        
        List<Idea> ideasList = this.ideaRepository.findAll();        
        List<IdeaHomeDTO> ideasForHome = new ArrayList<>();
        List<Transaction> transactions;
        Double fundRaised;
        Double progress;
        IdeaHomeDTO ideaHomeDTO;
        
        if(ideasList == null || ideasList.isEmpty()){
            return new ResponseEntity("No se encontraron ideas disponibles.", HttpStatus.NOT_FOUND);
        }
        
        for(Idea idea: ideasList){
           
            transactions = this.transactionRepository.findByIdea_id(idea.getId());
            fundRaised = 0.0;
            progress = 0.0;
            
            if(transactions != null && !transactions.isEmpty()){
                for(Transaction transaction: transactions){
                    fundRaised =  fundRaised + transaction.getValue();
                }                
            }
                 
            progress = (fundRaised * 100) / Integer.parseInt(idea.getObjective());                        
            
            ideaHomeDTO =  new IdeaHomeDTO();
            
            ideaHomeDTO.setId(idea.getId());
            ideaHomeDTO.setName(idea.getName());
            ideaHomeDTO.setObjective(idea.getObjective());
            ideaHomeDTO.setPrincipalImage(idea.getPrincipalImage());
            ideaHomeDTO.setDescription(idea.getDescription());
            ideaHomeDTO.setCountry(idea.getCountry());
            ideaHomeDTO.setCategories(idea.getCategories());
            ideaHomeDTO.setEntrepreneurs(idea.getEntrepreneurs());
            ideaHomeDTO.setFundRaised(fundRaised);
            ideaHomeDTO.setProgress(progress);
            
            ideasForHome.add(ideaHomeDTO);
            
        }       
        
        return new ResponseEntity(ideasForHome, HttpStatus.OK);
    }
    
    
    @Override
    public ResponseEntity getCompleteIdeaById(Long id){
        
        Optional<Idea> idea = this.ideaRepository.findById(id);
        Double fundRaised;
        Double progress;
        CompleteIdeaDTO completeIdeaDTO = new CompleteIdeaDTO();
        
        if(idea ==  null || !idea.isPresent()){
            return new ResponseEntity("La idea no fue encontrada en el sistema.", HttpStatus.NOT_FOUND);
        }
        
        List<Transaction> transactions = this.transactionRepository.findByIdea_id(id);
            fundRaised = 0.0;
            progress = 0.0;
            
            if(transactions != null && !transactions.isEmpty()){
                for(Transaction transaction: transactions){
                    fundRaised =  fundRaised + transaction.getValue();
                }                
            }
                 
            progress = (fundRaised * 100) / Integer.parseInt(idea.get().getObjective());                        
                        
            
            completeIdeaDTO.setId(idea.get().getId());
            completeIdeaDTO.setName(idea.get().getName());
            completeIdeaDTO.setObjective(idea.get().getObjective());
            completeIdeaDTO.setExplanation(idea.get().getExplanation());
            completeIdeaDTO.setContact(idea.get().getContact());
            completeIdeaDTO.setCountry(idea.get().getCountry());
            completeIdeaDTO.setState(idea.get().getState());
            completeIdeaDTO.setCreatedAt(idea.get().getCreatedAt());
            completeIdeaDTO.setCampaignFinishedDate(idea.get().getCampaignFinishedDate());
            completeIdeaDTO.setDescription(idea.get().getDescription());  
            completeIdeaDTO.setVideo(idea.get().getVideo());
            completeIdeaDTO.setPrincipalImage(idea.get().getPrincipalImage());                      
            completeIdeaDTO.setEntrepreneurs(idea.get().getEntrepreneurs());
            completeIdeaDTO.setDocuments(idea.get().getDocuments());
            completeIdeaDTO.setCategories(idea.get().getCategories());
            
                        
            completeIdeaDTO.setFundRaised(fundRaised);
            completeIdeaDTO.setProgress(progress);
            completeIdeaDTO.setSuports(transactions.size());
            
            return new ResponseEntity(completeIdeaDTO, HttpStatus.OK);
    }
    
    @Override
    public ResponseEntity getIdeaById(Long id) {
        
        Optional<Idea> idea =  this.ideaRepository.findById(id);
        
        if(idea == null || !idea.isPresent()){
            return new ResponseEntity("La idea no fue encontrada en el sistema.", HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity(idea.get(), HttpStatus.OK);
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
