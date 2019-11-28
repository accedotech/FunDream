/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.dto;

import com.funDream.entity.Categories;
import com.funDream.entity.Document;
import com.funDream.entity.Transaction;
import com.funDream.entity.User;
import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author felipe
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CompleteIdeaDTO {
            
    private Long id;
    private String name;
    private String objective;
    private String explanation;
    private String contact;
    private String country;
    private String state;
    private Date createdAt;
    private String description;
    private String video;
    private String principalImage;
    private List<User> entrepreneurs;
    private List<Document> documents;    
    private List<Categories> categories;
    
    private Double fundRaised;
    private Double progress;
    private int suports;
}
