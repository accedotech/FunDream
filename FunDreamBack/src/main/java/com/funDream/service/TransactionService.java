/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service;

import com.funDream.entity.Transaction;
import org.springframework.http.ResponseEntity;

/**
 *
 * @author felipe
 */
public interface TransactionService {
 
    
    ResponseEntity<?> save(Transaction transaction);
    
    ResponseEntity<?> getAllTransaction();
    
    ResponseEntity<?> getAllTransactionByUser(Long idUser);
    
    ResponseEntity<?> getAllTransactionByIdea(Long idIdea);
    
    ResponseEntity<?> getTransactionById(Long idTransaction);
}
