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
import com.ste.inventorymanagement.model.Roles;
import com.ste.inventorymanagement.payLoad.RolePayload;
import com.ste.inventorymanagement.repository.RolesRepository;
import com.ste.inventorymanagement.services.RolesService;

@RestController
@RequestMapping("/Roles")
@ResponseBody
public class RolesController {
	
	@Autowired
	RolesRepository rolesRepository;
	
	@Autowired
	RolesService rolesService;
	
	@GetMapping("/")
	public List<Roles> getAllRoles() {
		return rolesRepository.findAll();
	}
	
	@GetMapping("/{Id}")
	public Roles getRolesById(@PathVariable(value = "Id") Long Id) {
		Roles role = rolesRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Roles", "Id", Id));
		return role;
	}
	
	@PostMapping("/")
	public RolePayload createRole(@Valid @RequestBody RolePayload rolePayload) {
		
		RolePayload savedRole = rolesService.createRole(rolePayload);
		return (savedRole);
	}
	
	@PutMapping("/")
	public ResponseEntity<RolePayload> updateRole(@Valid @RequestBody RolePayload rolePayload) {
		if(null == rolePayload) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
					"Please pass valid Role data to update.");
		}
		
		RolePayload updatedPayload = rolesService.updateRole(rolePayload);
		return ResponseEntity.ok(updatedPayload);
		}
	}
