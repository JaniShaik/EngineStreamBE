package com.ste.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ste.inventorymanagement.model.Batch;

public interface BatchRepository extends JpaRepository<Batch, Long>{

}
