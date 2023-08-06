package com.authapi.auth.repository;

import com.authapi.auth.domain.TypeUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RepositoryTypeUser extends JpaRepository<TypeUser, Integer> {

    @Query("SELECT t FROM typeUser t WHERE t.id_typeuser = :id_typeuser")
    TypeUser findUmTypeUser(Integer id_typeuser);
}
