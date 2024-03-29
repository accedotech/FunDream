/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.repository;

import com.funDream.entity.Role;
import com.funDream.enums.RoleName;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    
    Optional<Role> findByRoleName(RoleName roleName);
}
