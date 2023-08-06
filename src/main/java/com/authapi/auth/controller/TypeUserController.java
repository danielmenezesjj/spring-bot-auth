package com.authapi.auth.controller;


import com.authapi.auth.Dto.TypeUserDTO;
import com.authapi.auth.domain.TypeUser;
import com.authapi.auth.repository.RepositoryTypeUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;


@RestController
@RequestMapping("typeuser")
public class TypeUserController {

    @Autowired
    private RepositoryTypeUser repositoryTypeUser;

    @GetMapping
    public ResponseEntity getTypeUser(){
        var alltypesUser = repositoryTypeUser.findAll();
        return ResponseEntity.ok(alltypesUser);
    }

    @GetMapping("/{id_typeuser}")
    public ResponseEntity getOneTypeUser(@PathVariable Integer id_typeuser){
        var oneTypeUser = repositoryTypeUser.findUmTypeUser(id_typeuser);
        return ResponseEntity.ok(oneTypeUser);
    }

    @PostMapping
    @Transactional
    public ResponseEntity postTypeUser(@RequestBody TypeUserDTO data, UriComponentsBuilder uriComponentsBuilder){
        TypeUser newtypeUser = new TypeUser(data);
        repositoryTypeUser.save(newtypeUser);
        URI uri = uriComponentsBuilder.path("/typeuser/${id}").buildAndExpand(newtypeUser.getId_typeuser()).toUri();
        return ResponseEntity.created(uri).body(new TypeUser(data));
    }

    @PutMapping("/{id_typeuser}")
    @Transactional
    public ResponseEntity putTypeUser(@RequestBody TypeUserDTO data, @PathVariable Integer id_typeuser){
        var typeuser = repositoryTypeUser.getReferenceById(id_typeuser);
        typeuser.updateType(data);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id_typeuser}")
    public ResponseEntity deleteTypeUser(@PathVariable Integer id_typeuser){
        Optional<TypeUser> optionalTypeUser = repositoryTypeUser.findById(id_typeuser);
        if(optionalTypeUser.isPresent()){
            TypeUser typeUser = optionalTypeUser.get();
            repositoryTypeUser.delete(typeUser);
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }



}
