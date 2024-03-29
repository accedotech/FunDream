/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.validation.constraints.Size;
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
    @Column(unique = true)
    private String name;
          
    
    private String objective;
    
    @NotNull
    @Size(min = 1, max = 15000)
    private String explanation;        
    
    @NotNull
    private String contact;

    @NotNull
    private String country;
    
    @NotNull
    private String state;
    
    private Date createdAt;
    
    @NotNull
    private Date campaignFinishedDate;
    
    @Size(max = 500)
    private String description;
    
    private String video;
    
    @NotNull
    @Column(name = "principal_image")
    private String principalImage;
        
    
    @NotNull    
    @ManyToMany()    
    @JoinTable(name = "rel_user_idea", joinColumns = @JoinColumn(name = "idea_id"), inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<User> entrepreneurs;
          
    
    @OneToMany(mappedBy = "idea")
    private List<Document> documents;  
    
    
    @OneToMany(mappedBy = "idea")
    private List<Transaction> transaction;  

    @NotNull    
    @ManyToMany
    @JoinTable(name = "rel_idea_categories", joinColumns = @JoinColumn(name = "idea_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private List<Categories> categories;
                
}
