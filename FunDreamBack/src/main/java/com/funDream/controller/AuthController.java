/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.funDream.controller;

import com.funDream.dto.JwtDTO;
import com.funDream.dto.LoginUser;
import com.funDream.dto.Menssage;
import com.funDream.dto.NewUser;
import com.funDream.entity.Role;
import com.funDream.entity.User;
import com.funDream.enums.RoleName;
import com.funDream.security.jwt.JwtProvider;
import com.funDream.service.implementation.RoleServiceImpl;
import com.funDream.service.implementation.UserServiceImpl;
import java.util.HashSet;
import java.util.Set;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author felipe
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
     @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserServiceImpl userService;

    @Autowired
    RoleServiceImpl roleService;

    @Autowired
    JwtProvider jwtProvider;
    
     @PostMapping("/new")
    public ResponseEntity<?> newUser (@Valid @RequestBody NewUser newUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Menssage("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);        
        if(userService.existsByEmail(newUser.getEmail()))
            return new ResponseEntity(new Menssage("ese email ya existe"), HttpStatus.BAD_REQUEST);
        User user = new User(newUser.getFirstName(), newUser.getLastName(), newUser.getEmail(), passwordEncoder.encode(newUser.getPassword()), newUser.getCountry(), newUser.getBirthdate(), newUser.getImage());
        Set<String> rolesStr = newUser.getRoles();
        Set<Role> roles = new HashSet<>();
        for (String role : rolesStr) {
            switch (role) {
                case "admin":
                    Role roleAdmin = roleService.getByRoleName(RoleName.ROLE_ADMIN).get();
                    roles.add(roleAdmin);
                    break;
                default:
                    Role roleUser = roleService.getByRoleName(RoleName.ROLE_USER).get();
                    roles.add(roleUser);
            }
        }
        user.setRoles(roles);
        userService.save(user);
        return new ResponseEntity(new Menssage("usuario guardado"), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<JwtDTO> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult){
        if(bindingResult.hasErrors())
            return new ResponseEntity(new Menssage("campos vacíos o email inválido"), HttpStatus.BAD_REQUEST);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginUser.getEmail(), loginUser.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }
}

