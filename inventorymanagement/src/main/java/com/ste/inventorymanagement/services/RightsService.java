package com.ste.inventorymanagement.services;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.ste.inventorymanagement.model.Rights;
import com.ste.inventorymanagement.payLoad.RightsPayload;
import com.ste.inventorymanagement.repository.RightsRepository;

@Service
public class RightsService {

	private Logger logger = LogManager.getLogger(RolesService.class);
	
	@Autowired
	RightsRepository rightsRepository;
	
	public RightsPayload createRights(RightsPayload rightsPayload) {
		
		Rights rights = new Rights();
		rights.setName(rightsPayload.getName());
		rights.setCode(rightsPayload.getCode());
		rights.setUrl(rightsPayload.getUrl());
		rights.setOtherUrl(rightsPayload.getOtherUrl());
		rights.setParent(rightsPayload.getParent());
		rights.setType(rightsPayload.getType());
		rights.setPosition(rightsPayload.getPosition());
		try {
			rights = rightsRepository.save(rights);
		} catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not create Rights");
		}
		rightsPayload=mapRightsToRightsPayload(rightsPayload, rights);

		return rightsPayload;
	}
	
	public RightsPayload updateRights(RightsPayload rightsPayload) {
		Optional<Rights> optRights = rightsRepository.findById(rightsPayload.getId());
		Rights rights = optRights.get();
		rights.setName(rightsPayload.getName());
		rights.setCode(rightsPayload.getCode());
		rights.setUrl(rightsPayload.getUrl());
		rights.setOtherUrl(rightsPayload.getOtherUrl());
		rights.setParent(rightsPayload.getParent());
		rights.setType(rightsPayload.getType());
		rights.setPosition(rightsPayload.getPosition());
		try {
			rights = rightsRepository.save(rights);
		} catch(Exception ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Could not update rights");
		}
		rightsPayload=mapRightsToRightsPayload(rightsPayload, rights);
		return rightsPayload;
	}
	
	private RightsPayload mapRightsToRightsPayload(RightsPayload rightsPayload, Rights rights) {
		rightsPayload.setId(rights.getId());
		rightsPayload.setName(rights.getName());
		rightsPayload.setCode(rights.getCode());
		rightsPayload.setUrl(rights.getUrl());
		rightsPayload.setOtherUrl(rights.getOtherUrl());
		rightsPayload.setParent(rights.getParent());
		rightsPayload.setType(rights.getType());
		rightsPayload.setPosition(rights.getPosition());
		rightsPayload.setStatus(rights.getStatus());
		rightsPayload.setCreatedAt(rights.getCreatedAt());
		rightsPayload.setCreatedBy(rights.getCreatedBy());
		rightsPayload.setUpdatedAt(rights.getUpdatedAt());
		rightsPayload.setUpdatedBy(rights.getUpdatedBy());

		return rightsPayload;
	}
}
