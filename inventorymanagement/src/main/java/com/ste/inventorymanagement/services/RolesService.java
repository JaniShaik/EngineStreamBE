package com.ste.inventorymanagement.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ste.inventorymanagement.model.Roles;
import com.ste.inventorymanagement.payLoad.RolePayload;
import com.ste.inventorymanagement.repository.RolesRepository;

@Service
public class RolesService {

	private Logger logger = LogManager.getLogger(RolesService.class);
	
	@Autowired
	RolesRepository rolesRepository;
	
	public RolePayload createRole(RolePayload rolePayload) {
		Roles role = new Roles();
		role.setName(rolePayload.getRoleName());
		try {
			role = rolesRepository.save(role);
		} catch (Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Could not create Role");
		}
		rolePayload=mapRoleToRolePayload(rolePayload, role);

		return rolePayload;
	}
	
	public RolePayload updateRole(RolePayload rolePayload) {
		Optional<Roles> optRole = rolesRepository.findById(rolePayload.getId());
		Roles role = optRole.get();
		role.setName(rolePayload.getRoleName());
		try {
			role = rolesRepository.save(role);
		} catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not update role");
		}
		rolePayload=mapRoleToRolePayload(rolePayload, role);
		return rolePayload;
	}
	
	private RolePayload mapRoleToRolePayload(RolePayload rolePayload, Roles role) {
		rolePayload.setId(role.getId());
		rolePayload.setRoleName(role.getName());
		
		return rolePayload;
	}
}
