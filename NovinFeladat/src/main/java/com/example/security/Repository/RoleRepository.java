package com.example.security.Repository;


import com.example.security.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
    Role findRoleById(Long id);
}
