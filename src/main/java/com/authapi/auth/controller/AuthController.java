package com.authapi.auth.controller;


import com.authapi.auth.Dto.AuthLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {


    @Autowired
    private AuthenticationManager manager;


    @PostMapping
    @Transactional
    public ResponseEntity auth(@RequestBody AuthLoginDTO dados){
        var token = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = manager.authenticate(token);
        return ResponseEntity.ok().build();
    }







}
