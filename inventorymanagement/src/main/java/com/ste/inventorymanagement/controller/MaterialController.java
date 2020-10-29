package com.ste.inventorymanagement.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ste.inventorymanagement.model.Material;
import com.ste.inventorymanagement.repository.BatchRepository;
import com.ste.inventorymanagement.repository.MaterialRepository;
import com.ste.inventorymanagement.services.BatchService;
import com.ste.inventorymanagement.services.MailService;
import com.ste.inventorymanagement.services.MaterialOpenPdfService;
import com.ste.inventorymanagement.services.MaterialService;

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
		materials = materialService.getMaterials();
		return materials;
	}
	
	@GetMapping("/plantandengine")
	public List<Map<String, Object>> getAllMaterialDetailsWithEngineAndPlant() {
		List<Material> materialList = materialRepository.findAll();
		List<Map<String, Object>> materials = new ArrayList<Map<String,  Object>>();
		for(Material material:materialList) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("id", material.getId());
			map.put("materialNumber", material.getMaterialNumber());
			map.put("materialDescription", material.getMaterialDescription());
			map.put("lastPOUnitPrice", material.getLastPOUnitPrice());
			map.put("last1stYearIssueQuantity", material.getLast1stYearIssueQuantity());
			map.put("last2ndYearIssueQuantity", material.getLast2ndYearIssueQuantity());
			map.put("last3rdYearIssueQuantity", material.getLast3rdYearIssueQuantity());
			map.put("batches", material.getBatches());
			map.put("plantName", material.getPlant().getPlantName());
			map.put("plantCode", material.getPlant().getPlantCode());
			map.put("engineType", material.getEngine().getEngineType());
            map.put("engineTypeDescription", material.getEngine().getEngineTypeDescription());
            materials.add(map);
		}
		return materials;
	}
	
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
		Pageable page = PageRequest.of(pageId, materialRepository.PAGE_SIZE);
		Page<Material> material =  materialRepository.findAll(page);
		return material;
	}
	
	@GetMapping("/searchMaterial/")
	public List<Material> searchMaterial(@RequestParam("data") String data, @RequestParam("pageSize") int pageSize, @RequestParam("pageIndex") int pageIndex) {
		System.out.println(data);
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		String pattern = "%"+data+"%";
		List<Material> materials = materialService.searchMaterial(pattern, pageable,false);
		return materials;
	}
	
	@GetMapping("/materials/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws Exception {
        response.setContentType("application/pdf");
         
        String headerKey = "Content-Disposition";
        String headerValue = "inline; filename=materials" + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        List<Material> materials = materialService.getMaterials();
         
        MaterialOpenPdfService exporter = new MaterialOpenPdfService(materials);
        exporter.export(response);
         
    }
}
