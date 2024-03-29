/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "user")
public class User implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    @Column(name = "first_name")
    private String firstName;
    
    @NotNull
    @Column(name = "last_name")
    private String lastName;
    
    @NotNull
    @Column(unique = true)
    private String email;
    
    @NotNull
    private String password;
    
    @NotNull
    private String country;
    
    
    private Date birthdate;
    
    private String image;
    
    @NotNull
    @ManyToMany
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> roles;    
    
    
    
    @ManyToMany(mappedBy = "entrepreneurs")        
    @JsonIgnore
    private List<Idea> ideas;
    
    @OneToMany(mappedBy = "user")
    private List<Transaction> transaction;  

        
}
