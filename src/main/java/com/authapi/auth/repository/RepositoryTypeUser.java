package com.authapi.auth.repository;

import com.authapi.auth.domain.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryTypeUser extends JpaRepository<TypeUser, Integer> {
}
