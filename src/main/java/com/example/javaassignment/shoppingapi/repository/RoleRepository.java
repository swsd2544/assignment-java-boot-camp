package com.example.javaassignment.shoppingapi.repository;

import com.example.javaassignment.shoppingapi.models.ERole;
import com.example.javaassignment.shoppingapi.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
