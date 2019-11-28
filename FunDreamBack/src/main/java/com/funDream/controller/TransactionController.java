/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.controller;

import com.funDream.entity.Transaction;
import com.funDream.service.implementation.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author felipe
 */

@RestController
@RequestMapping("${api.base.url}/transaction")
public class TransactionController {
    
    @Autowired
    private TransactionServiceImpl transactionService;
    
    
    
    @PostMapping
    public ResponseEntity save(@RequestBody Transaction transaction) {
        return this.transactionService.save(transaction);
    }

    
    
    @GetMapping("/get-all")
    public ResponseEntity<?> getAllTransaction() {        
        return this.transactionService.getAllTransaction();
    }

    
    @GetMapping("/get-all-by-user/{idUser}")
    public ResponseEntity<?> getAllTransactionByUser(@PathVariable ("idUser") Long idUser) {
        return this.transactionService.getAllTransactionByUser(idUser);
    }

    
    @GetMapping("/get-all-by-idea/{idIdea}")
    public ResponseEntity<?> getAllTransactionByIdea(@PathVariable ("idIdea") Long idIdea) {
        return this.transactionService.getAllTransactionByIdea(idIdea);
    }

    @GetMapping("/get-all-by-id/{idTransaction}")
    public ResponseEntity<?> getTransactionById(@PathVariable("idTransaction") Long idTransaction) {
        return this.transactionService.getTransactionById(idTransaction);
       
    }
            
    
}
