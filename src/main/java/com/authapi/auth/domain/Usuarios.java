package com.authapi.auth.domain;

import com.authapi.auth.Dto.UsuariosDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "usuarios")
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuarios {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;
    private String email;
    private String senha;


    @ManyToOne
    @JoinColumn(name = "idtypeuser")
    private TypeUser typeUser;


    public Usuarios(String email, String senha, TypeUser typeUser) {

        this.email = email;
        this.senha = senha;
        this.typeUser = typeUser;
    }
}
