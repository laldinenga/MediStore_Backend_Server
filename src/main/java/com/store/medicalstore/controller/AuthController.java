package com.store.medicalstore.controller;

import com.store.medicalstore.dto.LoginDto;
import com.store.medicalstore.dto.RegisterDto;
import com.store.medicalstore.models.Roles;
import com.store.medicalstore.models.UserEntity;
import com.store.medicalstore.repository.RoleRepository;
import com.store.medicalstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDto.getEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User Signin Success",HttpStatus.OK);
    }

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,
                          RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        if (userRepository.existsByEmail(registerDto.getEmail())){
            return new ResponseEntity<>("Email is taken", HttpStatus.BAD_REQUEST);
        }
        UserEntity user= new UserEntity();
        user.setFirstname(registerDto.getFirstname());
        user.setLastname(registerDto.getLastname());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Roles role = roleRepository.findByEmail("USER").get();
        user.setRoles(Collections.singletonList(role));

        userRepository.save(user);
        return new ResponseEntity<>("user register success", HttpStatus.OK);
    }
}
