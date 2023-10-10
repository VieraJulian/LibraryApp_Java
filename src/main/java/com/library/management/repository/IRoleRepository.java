package com.library.management.repository;

import com.library.management.model.ERole;
import com.library.management.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(ERole role);
}