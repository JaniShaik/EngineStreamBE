package com.ste.inventorymanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ste.inventorymanagement.model.Plant;

public interface PlantRepository extends JpaRepository<Plant, Long>{

}
