package com.ste.inventorymanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ste.inventorymanagement.services.ReadExcelSheet;

@Controller
@RequestMapping("/insert")
public class InsertDataFromExcelToDbController {
	
	@Autowired
	ReadExcelSheet r;
	
	@PostMapping("/insertDataFromExcelToDb")
	public void insertDataFromExcelToDb() throws Exception {
		r.ReadExcel();

	}

}
