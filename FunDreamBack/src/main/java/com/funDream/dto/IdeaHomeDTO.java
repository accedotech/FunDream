/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.dto;

import com.funDream.entity.Categories;
import com.funDream.entity.User;
import java.util.List;
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
public class IdeaHomeDTO {
         
    private Long id;     
    private String name;              
    private String objective;                   
    private String country;            
    private String description;        
    private String principalImage;                
    private List<User> entrepreneurs;                  
    private List<Categories> categories;
    
    private Double fundRaised;
    private Double progress;
}
