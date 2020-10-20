package com.ste.inventorymanagement.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ste.inventorymanagement.model.Batch;
import com.ste.inventorymanagement.model.Material;
import com.ste.inventorymanagement.model.SmtpMail;
import com.ste.inventorymanagement.repository.BatchRepository;
import com.ste.inventorymanagement.repository.MaterialRepository;
import com.ste.inventorymanagement.services.BatchService;
import com.ste.inventorymanagement.services.MailService;
import com.ste.inventorymanagement.services.MaterialService;

//19-oct-2020
@RestController
@ResponseBody
@RequestMapping("/Material")
public class MaterialController {
	@Autowired
	MaterialRepository materialRepo;

	@Autowired
	BatchRepository batchRepository;

	@Autowired
	MailService mailService;

	@Autowired
	MaterialService materialService;

	@Autowired
	BatchService batchService;

	@GetMapping("/")
	public List<Material> getAllMaterials() {
		List<Material> materials = null;
		materials = materialRepo.findAll();
		return materials;
	}
	
	/*
	 * @GetMapping("/getAllMaterials")
	 * public List<Material> getAllMaterials2() {
	 * List<Material> materials = null;
	 * materials = materialService.getMaterials();
	 * System.out.println(materials.get(0).getBatches());
	 * return materials;
	 * }
	 */

	/*
	 * @GetMapping(path = "/getMaterialsUsingQuery")
	 * public List<Material> getMaterialsUsingQuery() {
	 * return materialRepo.getMaterialDataUsingQyery();
	 * }
	 */
	@GetMapping(path = "/convertMaterialToExcel")
	public void convertMaterialToExcel(HttpServletResponse response) throws IOException {
		materialService.convertMaterialToExcel(response);    
	}
	
	@RequestMapping(value = "/sendemail/{id}")
	public String sendEmail(@PathVariable Long id) throws Exception {
		Material material = this.getMaterialByBatchId(id);
		if(material == null)
			return "material with batch id "+ id+" is not present in table";

		mailService.sendmail(material);
		return "Email sent successfully";
	}

	@GetMapping("/getMaterialByBatchId/{batchId}")
	public Material getMaterialByBatchId(@PathVariable Long batchId) {
		return batchService.getMaterialByBatchIdUsingQuery(batchId);
	}
	
	@GetMapping("/getMaterialsByPagingId/{pageId}")
	public Page<Material> getMaterialsByPagingId(@PathVariable int pageId) {
		Pageable page = PageRequest.of(pageId, materialRepo.PAGE_SIZE);
		Page<Material> material =  materialRepo.findAll(page);
		return material;
	}
	
	@GetMapping("/searchMaterial/{data}")
	public List<Material> searchMaterial(@PathVariable String data) {
		System.out.println(data);
		String pattern = "%"+data+"%";
		List<Material> materials = materialService.searchMaterial(pattern);
		return materials;
	}
	
	@GetMapping("/getMaterialPdf")
	public ResponseEntity<InputStreamResource> getMaterialPdf(HttpServletResponse response) {
		return materialService.getMaterialPdf(response);
		
	}
}
