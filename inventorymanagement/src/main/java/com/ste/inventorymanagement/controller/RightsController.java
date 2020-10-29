package com.ste.inventorymanagement.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ste.inventorymanagement.exceptions.ResourceNotFoundException;
import com.ste.inventorymanagement.model.Rights;
import com.ste.inventorymanagement.payLoad.RightsPayload;
import com.ste.inventorymanagement.repository.RightsRepository;
import com.ste.inventorymanagement.services.RightsService;

@RestController
@RequestMapping("/Rights")
@ResponseBody
public class RightsController {

	@Autowired
	RightsRepository rightsRepository;
	
	@Autowired
	RightsService rightsService;
	
	@GetMapping("/")
	public List<Rights> getAllRights() {
		return rightsRepository.findAll();
	}
	
	@GetMapping("/{Id}")
	public Rights getRightsById(@PathVariable(value = "Id") Long Id) {
		Rights rights = rightsRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Rights", "Id", Id));
		return rights;
	}
	
	@PostMapping("/")
	public RightsPayload createRights(@Valid @RequestBody RightsPayload rightsPayload) {
		
		RightsPayload savedRights = rightsService.createRights(rightsPayload);
		return (savedRights);
	}
	
	@PutMapping("/")
	public ResponseEntity<RightsPayload> updateRights(@Valid @RequestBody RightsPayload rightsPayload) {
		if(null == rightsPayload) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					"Please pass valid Rights data to update.");
		}
		
		RightsPayload updatedPayload = rightsService.updateRights(rightsPayload);
		return ResponseEntity.ok(updatedPayload);
		}
}
