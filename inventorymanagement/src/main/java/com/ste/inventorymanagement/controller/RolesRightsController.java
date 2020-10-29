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
import com.ste.inventorymanagement.model.RolesRights;
import com.ste.inventorymanagement.payLoad.RolesRightsPayload;
import com.ste.inventorymanagement.repository.RolesRightsRepository;
import com.ste.inventorymanagement.services.RolesRightsService;

@RestController
@RequestMapping("/RolesRights")
@ResponseBody
public class RolesRightsController {

	@Autowired
	RolesRightsRepository rolesRightsRepository;

	
	@Autowired
	RolesRightsService rolesRightsService;
	
	@GetMapping("/")
	public List<RolesRights> getAllRolesRights() {
		return rolesRightsRepository.findAll();
	}
	
	@GetMapping("/{Id}")
	public RolesRights getRolesRightsById(@PathVariable(value = "Id") Long Id) {
		RolesRights rolesRights = rolesRightsRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("RolesRights", "Id", Id));
		return rolesRights;
	}
	
	@PostMapping("/")
	public RolesRightsPayload createRolesRights(@Valid @RequestBody RolesRightsPayload rolesRightsPayload) {
		
		RolesRightsPayload savedRolesRights = rolesRightsService.createRolesRights(rolesRightsPayload);
		return (savedRolesRights);
	}
	
	@PutMapping("/")
	public ResponseEntity<RolesRightsPayload> updateRolesRights(@Valid @RequestBody RolesRightsPayload rolesRightsPayload) {
		if(null == rolesRightsPayload) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					"Please pass valid RolesRights data to update.");
		}
		
		RolesRightsPayload updatedPayload = rolesRightsService.updateRolesRights(rolesRightsPayload);
		return ResponseEntity.ok(updatedPayload);
		}
}
