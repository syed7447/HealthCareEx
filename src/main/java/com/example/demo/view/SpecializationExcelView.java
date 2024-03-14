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
		
		//read data given by controller
		List<Specialization> list= (List<Specialization>) model.get("list");
		
		//create one sheet
		Sheet sheet = workbook.createSheet("Specialization");
		
		//create row#0 as header
		setHead(sheet);
		//create row#1 onwards list data

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
