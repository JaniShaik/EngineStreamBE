package com.ste.inventorymanagement.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ste.inventorymanagement.model.RolesRights;
import com.ste.inventorymanagement.payLoad.RolesRightsPayload;
import com.ste.inventorymanagement.repository.RightsRepository;
import com.ste.inventorymanagement.repository.RolesRepository;
import com.ste.inventorymanagement.repository.RolesRightsRepository;


@Service
public class RolesRightsService {

private Logger logger = LogManager.getLogger(RolesService.class);
	
	@Autowired
	RolesRightsRepository rolesRightsRepository;
	
	@Autowired
	RolesRepository rolesRepository;
	
	@Autowired
	RightsRepository rightsRepository;
	
	public RolesRightsPayload createRolesRights(RolesRightsPayload rolesRightsPayload) {
		
		RolesRights rolesRights = new RolesRights();
		rolesRights = mapRolesRightsPayloadToRolesRights(rolesRights, rolesRightsPayload);
		try {
			rolesRights = rolesRightsRepository.save(rolesRights);
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Could not create RolesRights");
		}
		rolesRightsPayload = mapRolesRightsToRolesRightsPayload(rolesRightsPayload, rolesRights);

		return rolesRightsPayload;
	}
	
	private RolesRights mapRolesRightsPayloadToRolesRights(RolesRights rolesRights, RolesRightsPayload rolesRightsPayload) {
		if(null != rolesRightsPayload.getRoleId() && (0L != rolesRightsPayload.getRoleId())) {
			rolesRights.setRole(rolesRepository.findById(rolesRightsPayload.getRoleId()).get());
		}
		else {
			rolesRights.setRole(null);
		}
		if(null != rolesRightsPayload.getRightId() && (0L != rolesRightsPayload.getRightId())) {
		    rolesRights.setRight(rightsRepository.findById(rolesRightsPayload.getRightId()).get());
		}
		else {
			rolesRights.setRight(null);
		}
		return rolesRights;
	}
	
	public RolesRightsPayload updateRolesRights(RolesRightsPayload rolesRightsPayload) {
		Optional<RolesRights> optRolesRights = rolesRightsRepository.findById(rolesRightsPayload.getId());
		RolesRights rolesRights = optRolesRights.get();
		rolesRights = mapRolesRightsPayloadToRolesRights(rolesRights, rolesRightsPayload);
		try {
			rolesRights = rolesRightsRepository.save(rolesRights);
		}catch (Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
					"Could not update RolesRights");
		}
		rolesRightsPayload = mapRolesRightsToRolesRightsPayload(rolesRightsPayload, rolesRights);
		return rolesRightsPayload;
	}
	private RolesRightsPayload mapRolesRightsToRolesRightsPayload(RolesRightsPayload rolesRightsPayload, RolesRights rolesRights) {
		rolesRightsPayload.setId(rolesRights.getId());
		rolesRightsPayload.setRoleId(rolesRights.getRole().getId());
		rolesRightsPayload.setRightId(rolesRights.getRight().getId());
		rolesRightsPayload.setStatus(rolesRights.getStatus());
		rolesRightsPayload.setCreatedAt(rolesRights.getCreatedAt());
		rolesRightsPayload.setCreatedBy(rolesRights.getCreatedBy());
		rolesRightsPayload.setUpdatedAt(rolesRights.getUpdatedAt());
		rolesRightsPayload.setUpdatedBy(rolesRights.getUpdatedBy());
		return rolesRightsPayload;
	}
}
