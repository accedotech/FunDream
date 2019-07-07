/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service.implementation;

import com.funDream.entity.Role;
import com.funDream.enums.RoleName;
import com.funDream.repository.RoleRepository;
import com.funDream.service.RoleService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@Service
@Transactional
public class RoleServiceImpl implements RoleService{
    
    @Autowired
    RoleRepository roleRepository;

    @Override
    public Optional<Role> getByRoleName(RoleName roleName){
        return roleRepository.findByRoleName(roleName);
    }
}
