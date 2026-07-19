package com.ecommerce.repos;

import com.ecommerce.entities.Role;
import com.ecommerce.enums.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository
        extends JpaRepository<Role, Long> {

    Optional<Role> findByRoleName(RoleType roleName);

}