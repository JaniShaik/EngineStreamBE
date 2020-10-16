package com.ste.inventorymanagement.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ste.inventorymanagement.model.Material;
import com.ste.inventorymanagement.repository.MaterialRepository;

@Service
public class MaterialService {
	
	@Autowired
	MaterialRepository materialRepository;

	private List<Material> getMaterials() {
		return materialRepository.findAll();
	}
	
	public void convertMaterialToExcel(HttpServletResponse response) throws IOException {
			response.setContentType("application/octet-stream");
	         
	        String headerKey = "Content-Disposition";
	        String headerValue = "attachment; filename=materials" + ".xlsx";
	        response.setHeader(headerKey, headerValue);
	         
	        List<Material> materials = this.getMaterials();
	         
	        MaterialExcelExporterService excelExporter = new MaterialExcelExporterService(materials);
	         
	        excelExporter.export(response);    
	}

	public List<Material> getMaterialDataUsingQyery() {
		return materialRepository.getMaterialDataUsingQyery();
	}

	public Optional<Material> getMaterialByBatchId(int id) {
		Optional<Material> material = materialRepository.findById((long) id);
		return material;
	}
	
}
