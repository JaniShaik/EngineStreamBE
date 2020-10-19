package com.ste.inventorymanagement.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.ste.inventorymanagement.model.Material;

public interface MaterialRepository extends JpaRepository<Material, Long>,PagingAndSortingRepository<Material, Long>{

	final int PAGE_SIZE = 20;
	
	@Query("select m from Material m inner join m.plant p on p.id = m.id")
	public List<Material> getMaterialDataUsingQyery();
	
	@Query("select m "
			+ "from Material m inner join m.batches b where "
			+ "m.materialNumber like :data or "
			+ "m.materialDescription like :data or "
			+ "m.lastPOUnitPrice like :data or "
			+ "m.last1stYearIssueQuantity like :data or "
			+ "m.last2ndYearIssueQuantity like :data or "
			+ "m.last3rdYearIssueQuantity like :data or "
			+ "b.bizUnit like :data or "
			+ "b.bizUnitDescription like :data or "
			+ "b.qiBatchNo like :data or "
			+ "b.storageLocation like :data or "
			+ "b.storageBin like :data")
	public List<Material> searchMaterial(String data);
	
}
