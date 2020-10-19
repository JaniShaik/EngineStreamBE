package com.ste.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ste.inventorymanagement.model.Plant;

public interface MaterialRepository extends JpaRepository<Plant, Long>{

}
