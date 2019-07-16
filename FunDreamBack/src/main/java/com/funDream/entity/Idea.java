/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "idea")
public class Idea implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String name;
          
    
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

    @NotNull
    @ManyToMany
    @JoinTable(name = "rel_idea_categories", joinColumns = @JoinColumn(name = "idea_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Categories> categories = new HashSet<>();
                
}
