package com.tarikukebede.userManagement.Repositories;

import com.tarikukebede.userManagement.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
