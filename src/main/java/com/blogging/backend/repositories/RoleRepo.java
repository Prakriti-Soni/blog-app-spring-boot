package com.blogging.backend.repositories;

import com.blogging.backend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<Role, Integer > {
}
