package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Patient;
import com.example.demo.exception.PatientNotFoundException;
import com.example.demo.service.IPatientService;

@Controller
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private IPatientService service;
	

	// 1. show register page
	@GetMapping("/register")
	public String showRegister(@RequestParam(value = "message",required = false)String message,Model model) {
		model.addAttribute("message", message);
		return "PatientRegister";
		
	}
	
	//2.save on submit
	@PostMapping("/save")
	public String save(@ModelAttribute Patient patient,RedirectAttributes attributes) {
		Long id = service.savePatient(patient);
		attributes.addAttribute("message", "Patient ("+id+") is created");
		
		return "redirect:register";
		
	}
	
	//3.display data
	@GetMapping("/all")
	public String display(Model model,@RequestParam(value = "message",required = false)String message) {
		List<Patient> list= service.getAllPatient();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "PatientData";
	}
	
	//4.delete by id
	@GetMapping("/delete")
	public String delete(@RequestParam("id")Long id,RedirectAttributes attributes) {
		String message =null;
		try {
			service.removePatient(id);
			message="Patient removed";

		} catch (PatientNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			message =e.getMessage();
		}
		attributes.addAttribute("message", message);
		return "redirect:all";
	}
	
	 //5. show edit
	@GetMapping("/edit")
	public String edit(@RequestParam("id")Long id,RedirectAttributes attributes,Model model) {
		
//		return "PatientEdit";
		
		String page =null;
		try {
			Patient patient= service.getOnePatient(id);
			model.addAttribute("Patient", patient);

			page = "PatientEdit";

		} catch (PatientNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			return "redirect:all";
		}
		return page;
	
		
	}
	
	//6.do update
	@PostMapping("/update")
	public String doUpdate(@ModelAttribute Patient patient,RedirectAttributes attributes) {
		service.updatePatient(patient);
		attributes.addAttribute("message", "Patient ("+patient.getId()+") is updated");
		return "redirect:all";
		
	}
	
	//6.email and mobile validation using ajax
	
	//7. excel export
	

}
