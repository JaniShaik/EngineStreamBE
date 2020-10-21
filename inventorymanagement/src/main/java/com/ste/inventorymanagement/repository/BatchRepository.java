package com.ste.inventorymanagement.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.ste.inventorymanagement.model.Batch;
import com.ste.inventorymanagement.model.Material;

public interface BatchRepository extends JpaRepository<Batch, Long>{
	
	@Query("select b.material from Batch b where b.id = :batchId")
	public Material getMaterialByBatchIdUsingQuery(Long batchId);
	
	@Modifying
	@Transactional
	@Query("update Batch b set b.surplusFlag = 1 where b.ageByMonth > 24")
	public void updateBatchSurplusFlagJob();

}
