package com.wlt.wla.auth.repository;

import com.wlt.wla.auth.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long>{
}
