package com.ste.inventorymanagement.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ste.inventorymanagement.repository.BatchRepository;

@Service
public class BatchService {

	@Autowired
	BatchRepository batchRepository;
	
}
