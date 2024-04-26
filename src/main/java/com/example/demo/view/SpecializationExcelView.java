package com.example.demo.view;


import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import com.example.demo.entity.Specialization;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SpecializationExcelView extends AbstractXlsView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//define your own excel file name
		response.addHeader("content-Disposition", "attachment;specialization=SPec.xlsx");
		//read data given by controller
		@SuppressWarnings("unchecked")
		List<Specialization> list= (List<Specialization>) model.get("list");
		
		//create one sheet
		Sheet sheet = workbook.createSheet("Specialization");
		
		//create row#0 as header
		setHead(sheet);
		
		//create row#1 onwards list data
		setBody(sheet,list);

	}

	private void setBody(Sheet sheet, List<Specialization> list) {
		// TODO Auto-generated method stub
		int rowNum=1;
		for(Specialization spec:list) {
			Row row= sheet.createRow(rowNum++);
			row.createCell(0).setCellValue(spec.getSpecId());
			row.createCell(1).setCellValue(spec.getSpecCode());
			row.createCell(2).setCellValue(spec.getSpecName());
			row.createCell(3).setCellValue(spec.getSpecNote());
		}
		
	}

	private void setHead(Sheet sheet) {
		// TODO Auto-generated method stub
		Row row= sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("CODE");
		row.createCell(2).setCellValue("NAME");
		row.createCell(3).setCellValue("NOTE");
	}

}
