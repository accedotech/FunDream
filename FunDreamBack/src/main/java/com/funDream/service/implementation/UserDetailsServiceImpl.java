/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.service.implementation;

import com.funDream.entity.User;
import com.funDream.repository.UserRepository;
import com.funDream.security.MainUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;    

/**
 *
 * @author Felipe Giraldo Leon / github: felipegleon
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    
    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
            User user = this.userRepository.findByEmail(email).get();
            return MainUser.build(user);
    }
    
}
