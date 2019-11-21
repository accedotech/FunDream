/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service.implementation;

import com.funDream.entity.Transaction;
import com.funDream.repository.TransactionRepository;
import com.funDream.service.TransactionService;
import com.google.gson.Gson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 *
 * @author felipe
 */


@Service 
public class TransactionServiceImpl implements TransactionService{
    
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public ResponseEntity<?> save(Transaction transaction) {
        
        String mensaje;        
        Transaction transactionCreada =  this.transactionRepository.save(transaction);
        
        if(transactionCreada == null){
                mensaje = "Error en el servidor, la trasacción no pudo ser compeltada.";
                return new ResponseEntity (mensaje, HttpStatus.NOT_ACCEPTABLE);
        }
        
        mensaje = "La transacción se complento correctamente.";
        return new ResponseEntity (new Gson().toJson(mensaje), HttpStatus.OK);
    }

    
    @Override
    public ResponseEntity<?> getAllTransaction() {
        
        List<Transaction> transacciones = this.transactionRepository.findAll();
        
        if(transacciones == null || transacciones.isEmpty()){
            String mensaje = "No se encontraron transacciones en el sistema.";
            return new ResponseEntity (mensaje, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity (transacciones, HttpStatus.OK);
    }

    
    @Override
    public ResponseEntity<?> getAllTransactionByUser(String idUser) {
        
        List<Transaction> transacciones = this.transactionRepository.findByUser(idUser);
        
        if(transacciones == null || transacciones.isEmpty()){
            String mensaje = "No se encontraron transacciones en el sistema.";
            return new ResponseEntity (mensaje, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity (transacciones, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getAllTransactionByIdea(String idIdea) {
        List<Transaction> transacciones = this.transactionRepository.findByIdea(idIdea);
        
        if(transacciones == null || transacciones.isEmpty()){
            String mensaje = "No se encontraron transacciones en el sistema.";
            return new ResponseEntity (mensaje, HttpStatus.NOT_FOUND);
        }
        
        return new ResponseEntity (transacciones, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> getTransactionById(String idTransaction) {
        
        Transaction transaction = this.transactionRepository.findById(idTransaction);
        
        if(transaction == null){
            String mensaje = "No se encontró la transacción en el sistema.";
            return new ResponseEntity (mensaje, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity (transaction, HttpStatus.OK);
    }
    
    
    
    
}
