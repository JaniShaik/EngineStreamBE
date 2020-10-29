package com.ste.inventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ste.inventorymanagement.model.Engine;
import com.ste.inventorymanagement.repository.EngineRepository;

@RestController
@ResponseBody
@RequestMapping("/Engine")
public class EngineController {

	@Autowired
	EngineRepository engineRepository;
	
	@GetMapping("/")
	public List<Engine> getAllEngineDetails() {
		return engineRepository.findAll();
	}
}
