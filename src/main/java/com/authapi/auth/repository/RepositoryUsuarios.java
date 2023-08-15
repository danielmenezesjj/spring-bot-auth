package com.authapi.auth.repository;

import com.authapi.auth.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface RepositoryUsuarios extends JpaRepository<Usuarios, Integer> {
    UserDetails findByEmail(String username);
}
