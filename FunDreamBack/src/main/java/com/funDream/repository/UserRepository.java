/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.repository;

import com.funDream.entity.Idea;
import com.funDream.entity.User;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
    Optional<User> findByEmail(String email);    
    boolean existsByEmail(String email);
    
    
}
