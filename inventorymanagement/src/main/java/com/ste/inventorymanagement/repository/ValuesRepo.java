package com.ste.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ste.inventorymanagement.model.CatalogueComponentValues;
import com.ste.inventorymanagement.model.Engine;

public interface ValuesRepo extends JpaRepository<CatalogueComponentValues, Long>{

}
