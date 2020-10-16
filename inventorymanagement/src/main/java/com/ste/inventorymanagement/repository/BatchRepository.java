package com.ste.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ste.inventorymanagement.model.Batch;
import com.ste.inventorymanagement.model.Material;

public interface BatchRepository extends JpaRepository<Batch, Long>{
	
	@Query("select b.material from Batch b where b.id = :batchId")
	public Material getMaterialByBatchIdUsingQuery(Long batchId);

}
