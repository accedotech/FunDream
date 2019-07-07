/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.entity;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@Entity
@Table(name = "idea")
public class Idea {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String name;
    
    @NotNull
    private String category;
    
    @NotNull
    private String objective;
    
    @NotNull
    private String explanation;
    
    @NotNull
    private String contact;
    
    @NotNull
    private String country;
    
    @NotNull
    private String state;
    
    @NotNull
    @Column(name = "principal_image")
    private String principalImage;
    
    @NotNull
    @ManyToMany(mappedBy = "ideas")
    private Set<User> entrepreneurs;
    
    @NotNull    
    @OneToMany(mappedBy = "idea", cascade = CascadeType.ALL)
    private Set<Document> documents  = new HashSet<>();

    public Idea(String name, String category, String objective, String explanation, String contact, String country, String state, String principalImage, Set<User> entrepreneurs) {
        this.name = name;
        this.category = category;
        this.objective = objective;
        this.explanation = explanation;
        this.contact = contact;
        this.country = country;
        this.state = state;
        this.principalImage = principalImage;
        this.entrepreneurs = entrepreneurs;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getObjective() {
        return objective;
    }

    public void setObjective(String objective) {
        this.objective = objective;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPrincipalImage() {
        return principalImage;
    }

    public void setPrincipalImage(String principalImage) {
        this.principalImage = principalImage;
    }

    public Set<User> getEntrepreneurs() {
        return entrepreneurs;
    }

    public void setEntrepreneurs(Set<User> entrepreneurs) {
        this.entrepreneurs = entrepreneurs;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }
    
    
}
