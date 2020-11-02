package com.ste.inventorymanagement;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ste.inventorymanagement.controller.InsertDataFromExcelToDbController;
import com.ste.inventorymanagement.repository.BatchRepository;
import com.ste.inventorymanagement.repository.MaterialRepository;

@Component
public class ScheduedJobs {

	@Autowired
	BatchRepository batchRepo;
	
	@Autowired
	InsertDataFromExcelToDbController insertDataFromExcelToDbController;
	//@Scheduled(cron = "0 * 9 * * ?") 
	// @Scheduled(fixedRate = 1000)
	// @Scheduled(fixedDelay = 1000, initialDelay = 3000)
	@Scheduled(cron = "0 0 23 * * ?")
	   public void cronJobSch() {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	      Date now = new Date();
	      String strDate = sdf.format(now);
	      System.out.println("Java cron job expression:: " + strDate);
	      batchRepo.updateBatchSurplusFlagJob();
	   }
	
	@Scheduled(cron = "0 35 19 * * ?")
	   public void cronJobInsertDataFromExcelToDbDetailsAndValues() throws Exception {
	      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	      Date now = new Date();
	      String strDate = sdf.format(now);
	      System.out.println("cronJob InsertDataFromExcelToDb Details And Values:: " + strDate);
	      insertDataFromExcelToDbController.insertDataFromExcelToDb();
	   }
	
}
