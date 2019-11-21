/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.repository;

import com.funDream.entity.Transaction;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author felipe
 */

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{      
    
    List<Transaction> findByUser(String idUser);
    
    List<Transaction> findByIdea(String idIdea);
    
    Transaction findById (String idTransaction);
}
