package com.authapi.auth.Dto;

import com.authapi.auth.domain.TypeUser;

public record UsuariosDTO(String email, String senha, Integer typeUser) {
}
