package com.ste.inventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ste.inventorymanagement.model.Plant;
import com.ste.inventorymanagement.repository.PlantRepository;

@RestController
@ResponseBody
@RequestMapping("/Plant")
public class PlantController {

	@Autowired
	PlantRepository plantRepository;
	
	@GetMapping("/")
	public List<Plant> getAllPlantDetails() {
		return plantRepository.findAll();
	}
}
