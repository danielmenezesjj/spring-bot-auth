package com.authapi.auth.domain;

import com.authapi.auth.Dto.UsuariosDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity(name = "usuarios")
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuarios  implements UserDetails {

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

    public void updateUser(UsuariosDTO data) {
        if(data.email() != null){
            this.email = data.email();
        }
        if(data.senha() != null){
            this.senha = data.senha();
        }
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
