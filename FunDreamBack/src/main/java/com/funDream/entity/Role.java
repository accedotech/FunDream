/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.entity;

import com.funDream.enums.RoleName;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "role")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Enumerated(EnumType.STRING)
    @NotNull
    private RoleName roleName;

    public Role(@NotNull RoleName roleName) {
        this.roleName = roleName;
    }        
}
