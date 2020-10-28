package com.ste.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ste.inventorymanagement.model.CatalogueComponentDetails;

public interface DetailsRepo extends JpaRepository<CatalogueComponentDetails, Long>{

}
