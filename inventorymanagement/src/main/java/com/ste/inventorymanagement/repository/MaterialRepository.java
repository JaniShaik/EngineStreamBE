package com.ste.inventorymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ste.inventorymanagement.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long>{

	@Query("select m from Material m inner join m.plant p on p.id = m.id")
	public List<Material> getMaterialDataUsingQyery();
	
}
