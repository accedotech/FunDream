/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author felipe
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Transaction")
public class Transaction implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull    
    private Double value;
    
                
    // Transaccion Debito
    private String numeroTarjeta;        
    private String nombreTitular;        
    private Date fechaVnecimiento;        
    private String nroVerificacion;
    
    
    // Transaccion Debito
    private String banco;        
    private String tipoCliente;        
    private String tipoIdentificacion;        
    private String numeroIdentificacion;        
    private String numeroCuenta;
    
    
    @NotNull    
    private Date createdAt;
    
    @NotNull    
    private String transactionType;       
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "idea_id", referencedColumnName = "id")
    private Idea idea;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;         
}
