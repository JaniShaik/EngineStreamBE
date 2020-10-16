package com.ste.inventorymanagement.services;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ste.inventorymanagement.model.Batch;
import com.ste.inventorymanagement.model.Material;
import com.ste.inventorymanagement.repository.BatchRepository;

@Service
public class BatchService {

	@Autowired
	BatchRepository batchRepository;

	public List<Batch> findAll() {
		return batchRepository.findAll();
	}

	public Material getMaterialByBatchIdUsingQuery(Long batchId) {
		batchRepository.getMaterialByBatchIdUsingQuery(batchId);
		return null;
	}
	
	
	
}
