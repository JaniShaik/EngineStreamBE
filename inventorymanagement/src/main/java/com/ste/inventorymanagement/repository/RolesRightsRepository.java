package com.ste.inventorymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ste.inventorymanagement.model.Rights;
import com.ste.inventorymanagement.model.RolesRights;

public interface RolesRightsRepository extends JpaRepository<RolesRights, Long>{

	@Query("SELECT u.right FROM RolesRights u where u.role.id = :id")
	public List<Rights> getAllRightsForTheRole(Long id);
}
