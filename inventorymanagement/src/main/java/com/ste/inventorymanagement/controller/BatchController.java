package com.ste.inventorymanagement.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.ste.inventorymanagement.model.Batch;
import com.ste.inventorymanagement.model.SurplusFlagHistory;
import com.ste.inventorymanagement.payLoad.BatchPayLoad;
import com.ste.inventorymanagement.repository.BatchRepository;
import com.ste.inventorymanagement.repository.SurplusFlagHistoryRepository;
import com.ste.inventorymanagement.services.BatchService;

@RestController
@ResponseBody
@RequestMapping("/Batch")
public class BatchController {

	@Autowired
	BatchRepository batchRepository;
	
	@Autowired
	BatchService batchService;
	
	@Autowired
	SurplusFlagHistoryRepository surplusFlagHistoryRepository;
	
	@GetMapping("/")
	public List<Batch> getAllBatchDetails() {
		List<Batch> batches = null;
		batches = batchService.findAll();
		return batches;
	}
	
	@PutMapping("/flag")
	public ResponseEntity<Batch> changeSurplusFlag(@RequestBody String payload) {
			JSONObject json = new JSONObject(payload);
			Long batchId = json.getLong("id");
		    Optional<Batch> optBatch = batchRepository.findById(batchId);
		    if(!optBatch.isPresent()) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid id");
		    }
		    Batch batch = optBatch.get();
			batch.setSurplusFlag(!batch.isSurplusFlag());
		    batchRepository.save(batch);
		    SurplusFlagHistory surplusFlagHistory= new SurplusFlagHistory();
		    surplusFlagHistory.setBatch(batch);
		    surplusFlagHistory.setFlagUpdated(batch.isSurplusFlag());
		    surplusFlagHistory.setReason(json.getString("reason"));
		    surplusFlagHistory.setUpdatedAt(new Date(System.currentTimeMillis()));
		    surplusFlagHistory.setUpdatedBy(json.getLong("employeeId"));
		    surplusFlagHistoryRepository.save(surplusFlagHistory);
		    return ResponseEntity.ok(batch);
	}
	
	/*
	 * @GetMapping("/getEnabledSurplusFlagBatchRecordss")
	 * public List<Map<String, Object>>
	 * getAllBatchDetailsWithMaterialName(@RequestParam("pageSize") int
	 * pageSize, @RequestParam("pageIndex") int pageIndex) {
	 * System.out.println("getAllBatchDetailsWithMaterialName is called");
	 * 
	 * List<Map<String, Object>> mapList = new ArrayList<Map<String,Object>>();
	 * Pageable pageable = PageRequest.of(pageIndex, pageSize);
	 * List<Batch> batches =
	 * batchService.getEnabledSurplusFlagBatchRecords(pageable,false);
	 * 
	 * for(Batch batch : batches) {
	 * Map<String,Object> map = new LinkedHashMap<String, Object>();
	 * 
	 * map.put("id", batch.getId());
	 * map.put("bizUnit", batch.getBizUnit());
	 * map.put("bizUnitDescription",batch.getBizUnitDescription());
	 * map.put("batchNo", batch.getBatchNo());
	 * map.put("qiBatchNo", batch.getQiBatchNo());
	 * map.put("storageLocation", batch.getStorageLocation());
	 * map.put("storageBin", batch.getStorageBin());
	 * map.put("lastReceiptDate", batch.getLastReceiptDate());
	 * map.put("ageByDay", batch.getAgeByDay());
	 * map.put("ageByMonth", batch.getAgeByMonth());
	 * map.put("quantity", batch.getQuantity());
	 * map.put("uom", batch.getUom());
	 * map.put("vendorName", batch.getVendorName());
	 * map.put("reasonPurchaseDescription",batch.getReasonPurchaseDescription());
	 * map.put("valueInUSD", batch.getValueInUSD());
	 * map.put("nbvInUSD", batch.getNbvInUSD());
	 * map.put("totalNBVUSD", batch.getTotalNBVUSD());
	 * map.put("tsn", batch.getTsn());
	 * map.put("csn", batch.getCsn());
	 * map.put("condition", batch.getCondition());
	 * map.put("materialSerialNumber", batch.getMaterialSerialNumber());
	 * map.put("materialCharacteristic", batch.getMaterialCharacteristic());
	 * map.put("surplusFlag", batch.isSurplusFlag());
	 * map.put("materialDescription", batch.getMaterial() != null ?
	 * batch.getMaterial().getMaterialDescription() : null);
	 * 
	 * mapList.add(map);
	 * }
	 * return mapList;
	 * }
	 */
	@GetMapping("/getEnabledSurplusFlagBatchRecords")
	public List<BatchPayLoad> getEnabledSurplusFlagBatchRecords(@RequestParam("pageSize") int pageSize, @RequestParam("pageIndex") int pageIndex) throws Exception {
		List<Batch> batches = null;
		List<BatchPayLoad> batchPayloads = new ArrayList<BatchPayLoad>();
		BatchPayLoad batchPayload;
		Pageable pageable = PageRequest.of(pageIndex, pageSize);
		batches = batchService.getEnabledSurplusFlagBatchRecords(pageable,false);
		for(Batch b : batches) {
			batchPayload = new BatchPayLoad();
			BeanUtils.copyProperties(batchPayload, b);
			
			if(b.getMaterial() != null && b.getMaterial().getMaterialDescription() != null)
				batchPayload.setMaterialDescription((b.getMaterial().getMaterialDescription()));
			
			batchPayloads.add(batchPayload);
		}
		return batchPayloads;
		
	}
}
