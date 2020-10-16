package com.ste.inventorymanagement.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ste.inventorymanagement.model.Batch;
import com.ste.inventorymanagement.model.Material;
import com.ste.inventorymanagement.repository.BatchRepository;
import com.ste.inventorymanagement.repository.MaterialRepository;
import com.ste.inventorymanagement.services.BatchService;

@RestController
@ResponseBody
@RequestMapping("/Batch")
public class BatchController {

	@Autowired
	BatchService batchService;

	@GetMapping("/")
	public List<Batch> getAllBatchDetails() {
		List<Batch> batches = null;
		batches = batchService.findAll();
		return batches;
	}

	//hello world!
}
