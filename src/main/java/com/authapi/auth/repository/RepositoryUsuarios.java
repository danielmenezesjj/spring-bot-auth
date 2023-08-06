package com.authapi.auth.repository;

import com.authapi.auth.domain.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryUsuarios extends JpaRepository<Usuarios, Integer> {
}
