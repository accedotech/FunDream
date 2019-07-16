/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.dto;

import javax.validation.constraints.NotBlank;
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
public class LoginUser {
    
    @NotBlank
    private String email;

    @NotBlank
    private String password;
  
    
}
