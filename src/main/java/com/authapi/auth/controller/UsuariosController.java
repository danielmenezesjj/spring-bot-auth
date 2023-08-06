package com.authapi.auth.controller;


import com.authapi.auth.Dto.UsuariosDTO;
import com.authapi.auth.domain.TypeUser;
import com.authapi.auth.domain.Usuarios;
import com.authapi.auth.repository.RepositoryTypeUser;
import com.authapi.auth.repository.RepositoryUsuarios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
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


    @GetMapping("/{id_usuario}")
    public ResponseEntity getOneUser(@PathVariable Integer id_usuario){
        Optional<Usuarios> optionalUsuarios = repositoryUsuarios.findById(id_usuario);
        if(optionalUsuarios.isPresent()) return ResponseEntity.ok(optionalUsuarios.get());
        return ResponseEntity.notFound().build();
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

    @PutMapping("/{id_usuario}")
    @Transactional
    public ResponseEntity putUser(@RequestBody UsuariosDTO data, @PathVariable Integer id_usuario){
        var user = repositoryUsuarios.getReferenceById(id_usuario);
        user.updateUser(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id_usuario}")
    public ResponseEntity deleteUser(@PathVariable Integer id_usuario){
        Optional<Usuarios> optionalUsuarios = repositoryUsuarios.findById(id_usuario);
        if(optionalUsuarios.isPresent()){
            Usuarios usuarios = optionalUsuarios.get();
            repositoryUsuarios.delete(usuarios);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

}
