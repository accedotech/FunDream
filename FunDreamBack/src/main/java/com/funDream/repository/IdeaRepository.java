/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.repository;

import com.funDream.entity.Idea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */
@Repository
public interface IdeaRepository extends JpaRepository<Idea, Long>{
    
}
