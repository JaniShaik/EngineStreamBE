package com.ste.inventorymanagement.services;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ste.inventorymanagement.model.Material;
import com.ste.inventorymanagement.repository.MaterialRepository;

@Service
public class MaterialService {
	
	@Autowired
	MaterialRepository materialRepo;
	
	

	public List<Material> getMaterials() {
		return materialRepo.findAll();
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
		return materialRepo.getMaterialDataUsingQyery();
	}

	public Optional<Material> getMaterialByBatchId(int id) {
		Optional<Material> material = materialRepo.findById((long) id);
		return material;
	}


	public List<Material> searchMaterial(String pattern) {
		/*
		 * String query = "select m "
		 * + "from Material m ";
		 * if(data != "null") {
		 * query +=
		 * "where m.materialNumber like :data or "
		 * + "m.materialDescription like :data or "
		 * + "m.lastPOUnitPrice like :data or "
		 * + "m.last1stYearIssueQuantity like :data or "
		 * + "m.last2ndYearIssueQuantity like :data or "
		 * + "m.last3rdYearIssueQuantity like :data ";
		 * }
		 */
		
		List<Material> materials = materialRepo.searchMaterial(pattern);
		System.out.println(pattern);
		return materials;
	}

	public ResponseEntity<InputStreamResource> getMaterialPdf(HttpServletResponse response) {
		response.setContentType("application/octet-stream");
        
        String headerKey = "Content-Disposition";
        String headerValue = "inline; filename=MaterialsReport.pdf";
        response.setHeader(headerKey, headerValue);
		
		MaterialPdfService materialPdfService = new MaterialPdfService();
		List<Material> materials = this.getMaterials();
		ByteArrayInputStream bis = materialPdfService.getMaterialPdf(materials);
		
		return ResponseEntity
                .ok()
                //.headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
	}
	
}
