package com.task.SchoolLaptopFixServis.repositories;

import com.task.SchoolLaptopFixServis.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Optional<Role> findRoleByAuthority(String authority);
}
