package com.authapi.auth.controller;


import com.authapi.auth.Dto.UsuariosDTO;
import com.authapi.auth.domain.TypeUser;
import com.authapi.auth.domain.Usuarios;
import com.authapi.auth.repository.RepositoryTypeUser;
import com.authapi.auth.repository.RepositoryUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/usuarios")
@RestController
public class UsuariosController {

    @Autowired
    private RepositoryUsuarios repositoryUsuarios;


    @Autowired
    private RepositoryTypeUser repositoryTypeUser;


    @GetMapping
    public ResponseEntity getAllUsers(){
        var allUser = repositoryUsuarios.findAll();
        return ResponseEntity.ok(allUser);
    }

    @PostMapping
    public ResponseEntity postUser(@RequestBody UsuariosDTO data){
        var typeUser = repositoryTypeUser.getReferenceById(data.typeUser());
        Optional<TypeUser> optionalTypeUser = repositoryTypeUser.findById(typeUser.getId_typeuser());
        if(optionalTypeUser.isPresent()){
            Usuarios newUser = new Usuarios(data.email(), data.senha(), typeUser);
            repositoryUsuarios.save(newUser);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
