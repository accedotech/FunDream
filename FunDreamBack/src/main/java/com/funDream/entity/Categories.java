/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */


@Entity 
@Table(name = "categories")
public class Categories {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String name;
    
    @NotNull
    private String description;
    
    @NotNull
    @ManyToMany(mappedBy = "categories")
    private Set<Idea> ideas  = new HashSet<>();

    public Categories() {
    }

    public Categories(String name, String description) {
        this.name = name;
        this.description = description;
    }
  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Idea> getIdeas() {
        return ideas;
    }

    public void setIdeas(Set<Idea> ideas) {
        this.ideas = ideas;
    }
    
    
}
