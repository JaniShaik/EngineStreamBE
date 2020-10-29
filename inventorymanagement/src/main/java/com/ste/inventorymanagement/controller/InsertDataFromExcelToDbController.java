package com.ste.inventorymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ste.inventorymanagement.services.ReadExcelSheetService;

@Controller
@RequestMapping("/insert")
public class InsertDataFromExcelToDbController {
	
	@Autowired
	ReadExcelSheetService readExcelSheetService;
	
	@PostMapping("/insertDataFromExcelToDb")
	@ResponseBody
	public String insertDataFromExcelToDb() throws Exception {
		readExcelSheetService.ReadExcel();
		return "data is inserted successfully";
	}

}
