/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "document")
public class Document implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private String link;
    
    private String nameFile;
        
    @NotNull
    private String type;
    
    @ManyToOne
    @JoinColumn(name = "idea_id", referencedColumnName = "id")
    private Idea idea;

    public Document(String link, String nameFile, String type) {        
        this.link = link;
        this.nameFile = nameFile;
        this.type = type;
    }
        
}
