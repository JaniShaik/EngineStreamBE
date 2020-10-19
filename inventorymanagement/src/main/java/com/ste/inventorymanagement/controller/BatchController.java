package com.ste.inventorymanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ste.inventorymanagement.model.Batch;
import com.ste.inventorymanagement.repository.BatchRepository;

@RestController
@ResponseBody
@RequestMapping("/Batch")
public class BatchController {

	@Autowired
	BatchRepository batchRepository;
	
	
	@GetMapping("/")
	public List<Batch> getAllBatchDetails() {
		return batchRepository.findAll();
	}
}
