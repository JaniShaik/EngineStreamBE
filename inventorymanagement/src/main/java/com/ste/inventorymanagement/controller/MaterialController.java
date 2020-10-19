package com.ste.inventorymanagement.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
	MaterialRepository materialRepository;

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
		materials = materialRepository.findAll();
		return materials;
	}

	@GetMapping(path = "/convertMaterialToExcel")
	public void convertMaterialToExcel(HttpServletResponse response) throws IOException {
		materialService.convertMaterialToExcel(response);    
	}
	@GetMapping(path = "/getMaterialsUsingQuery")
	public List<Material> getMaterialsUsingQuery() {
		return materialRepository.getMaterialDataUsingQyery();
	}

	@RequestMapping(value = "/sendemail/{id}")
	public String sendEmail(@PathVariable Long id) throws Exception {
		Material material = this.getMaterialByBatchId(id);
		if(material == null)
			return "material with batch id "+ id+" is not present in table";

		mailService.sendmail(material);
		return "Email sent successfully";
	}
	/*
	 * @GetMapping("/getMaterialByBatchId/{id}")
	 * public Optional<Material> getMaterialByBatchId(@PathVariable int id) {
	 * return materialService.getMaterialByBatchId(id);
	 * }
	 */

	@GetMapping("/getMaterialByBatchId/{batchId}")
	public Material getMaterialByBatchId(@PathVariable Long batchId) {
		return batchService.getMaterialByBatchIdUsingQuery(batchId);
	}



	@PostMapping(value="/sendEmailAttachement")
	public String sendEmailAttachement(@RequestBody SmtpMail smtpEmail) throws Exception {
		mailService.sendBatchEmail(smtpEmail);
		return "mail sent successfully";
	}

}
