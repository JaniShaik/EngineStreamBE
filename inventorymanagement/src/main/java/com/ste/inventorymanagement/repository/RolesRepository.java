package com.ste.inventorymanagement.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ste.inventorymanagement.model.Roles;

public interface RolesRepository extends JpaRepository<Roles, Long> {
}
