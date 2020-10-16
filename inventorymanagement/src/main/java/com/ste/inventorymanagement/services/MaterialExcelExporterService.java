package com.ste.inventorymanagement.services;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ste.inventorymanagement.model.Batch;
import com.ste.inventorymanagement.model.Material;

public class MaterialExcelExporterService {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	private List<Material> materials;
	int rowCount = 1;
	int headerRow = 0;

	public MaterialExcelExporterService(List<Material> materials) {
		this.materials = materials;
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Materialss");
	}


	private void writeMaterialHeaderLine() {
		
		int column = 0;
		Row row = sheet.createRow(headerRow);

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(14);
		style.setFont(font);

		createCell(row, column++, "id", style);
		createCell(row, column++, "materialNumber", style);
		createCell(row, column++, "materialDescription", style);
		createCell(row, column++, "lastPOUnitPrice", style);
		createCell(row, column++, "last1stYearIssueQuantity", style);
		
		createCell(row, column++, "last2ndYearIssueQuantity", style);
		createCell(row, column++, "last3rdYearIssueQuantity", style);

	}
	
	
	

	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if (value instanceof Integer) {
			cell.setCellValue((Integer) value);
		} else if (value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}else {
			if(value != null)
				cell.setCellValue( value.toString());
			else
				cell.setCellValue("null");
		}
		cell.setCellStyle(style);
	}

	private void writeMaterialDataLines(Material material) {

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(12);
		style.setFont(font);

		Row row = sheet.createRow(rowCount);
		int columnCount = 0;

		createCell(row, columnCount++, material.getId(), style);
		createCell(row, columnCount++, material.getMaterialNumber(), style);
		createCell(row, columnCount++, material.getMaterialDescription(), style);
		createCell(row, columnCount++, material.getLastPOUnitPrice(), style);
		createCell(row, columnCount++, material.getLast1stYearIssueQuantity(), style);
		createCell(row, columnCount++, material.getLast2ndYearIssueQuantity(), style);
		createCell(row, columnCount++, material.getLast3rdYearIssueQuantity(), style);
		
		headerRow = ++rowCount;
		rowCount++;

	}
	
	private void writeBatchHeaderLine() {

		Row row = sheet.createRow(headerRow);
		int column = 1;

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setBold(true);
		font.setFontHeight(14);
		style.setFont(font);

		createCell(row, column++, "Serial No", style);
		createCell(row, column++, "bizUnit", style);
		
		createCell(row, column++, "bizUnitDescription", style);
		createCell(row, column++, "batchNo", style);
		createCell(row, column++, "qiBatchNo", style);
		createCell(row, column++, "storageLocation", style);
		createCell(row, column++, "storageBin", style);
		createCell(row, column++, "lastReceiptDate", style);
		createCell(row, column++, "ageByDay", style);
		createCell(row, column++, "ageByMonth", style);
		createCell(row, column++, "quantity", style);
		createCell(row, column++, "uom", style);
		createCell(row, column++, "vendorName", style);
		createCell(row, column++, "reasonPurchaseDescription", style);
		createCell(row, column++, "valueInUSD", style);
		createCell(row, column++, "nbvInUSD", style);
		createCell(row, column++, "totalNBVUSD", style);
		createCell(row, column++, "tsn", style);
		createCell(row, column++, "csn", style);
		createCell(row, column++, "condition", style);
		createCell(row, column++, "materialSerialNumber", style);
		createCell(row, column++, "materialCharacteristic", style);
		

	}
	private void writeBatchDataLines(List<Batch> batches) {

		CellStyle style = workbook.createCellStyle();
		XSSFFont font = workbook.createFont();
		font.setFontHeight(12);
		style.setFont(font);
		int sno = 1;

		
		int columnCount = 1;
		for(Batch batch : batches) {
			Row row = sheet.createRow(rowCount++);
			createCell(row, columnCount++,sno++, style);
			createCell(row, columnCount++, batch.getBizUnit(), style);
			createCell(row, columnCount++, batch.getBizUnitDescription(), style);
			createCell(row, columnCount++, batch.getBatchNo(), style);
			createCell(row, columnCount++, batch.getQiBatchNo(), style);
			createCell(row, columnCount++, batch.getStorageLocation(), style);
			createCell(row, columnCount++, batch.getStorageBin(), style);
			createCell(row, columnCount++, batch.getLastReceiptDate(), style);
			createCell(row, columnCount++, batch.getAgeByDay(), style);
			createCell(row, columnCount++, batch.getAgeByMonth(), style);
			createCell(row, columnCount++, batch.getQuantity(), style);
			createCell(row, columnCount++, batch.getUom(), style);
			createCell(row, columnCount++, batch.getVendorName(), style);
			createCell(row, columnCount++, batch.getReasonPurchaseDescription(), style);
			createCell(row, columnCount++, batch.getValueInUSD(), style);
			createCell(row, columnCount++, batch.getNbvInUSD(), style);
			createCell(row, columnCount++, batch.getTotalNBVUSD(), style);
			createCell(row, columnCount++, batch.getTsn(), style);
			createCell(row, columnCount++, batch.getCsn(), style);
			createCell(row, columnCount++, batch.getCondition(), style);
			createCell(row, columnCount++, batch.getMaterialSerialNumber(), style);
			createCell(row, columnCount++, batch.getMaterialCharacteristic(), style);
			columnCount = 1;
			
		}
		
		headerRow = ++rowCount;
		rowCount++;

	}

	public void export(HttpServletResponse response) throws IOException {
		
		for(Material material : materials) {
			writeMaterialHeaderLine();
			writeMaterialDataLines(material);
			writeBatchHeaderLine();
			writeBatchDataLines(material.getBatches());
			
		}
		

		ServletOutputStream outputStream = response.getOutputStream();
		workbook.write(outputStream);
		workbook.close();

		outputStream.close();

	}
}