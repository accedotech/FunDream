/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service;

import com.funDream.entity.Role;
import com.funDream.enums.RoleName;
import java.util.Optional;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */
public interface RoleService {
    
    Optional<Role> getByRoleName(RoleName roleName);
}
