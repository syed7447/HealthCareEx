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

import com.example.demo.entity.Doctor;
import com.example.demo.exception.DoctorNotFoundException;
import com.example.demo.service.IDoctorService;

@Controller
@RequestMapping("/doctor")
public class DoctorController {
	@Autowired
	private IDoctorService service;
	// 1. show register page
	@GetMapping("/register")
	public String showRegister(@RequestParam(value = "message",required = false)String message,Model model) {
		model.addAttribute("message", message);
		return "DoctorRegister";
		
	}
	
	//2.save on submit
	@PostMapping("/save")
	public String save(@ModelAttribute Doctor doc,RedirectAttributes attributes) {
		Long id = service.saveDoctor(doc);
		attributes.addAttribute("message", "Doctor ("+id+") is created");
		return "redirect:register";
		
	}
	
	//3.display data
	@GetMapping("/all")
	public String display(Model model,@RequestParam(value = "message",required = false)String message) {
		List<Doctor> list= service.getAllDoctor();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "DoctorData";
		
	}
	
	//4.delete by id
	@GetMapping("/delete")
	public String delete(@RequestParam("id")Long id,RedirectAttributes attributes) {
		String message =null;
		try {
			service.removeDoctor(id);
			message="doctor removed";

		} catch (DoctorNotFoundException e) {
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
		
//		return "DoctorEdit";
		
		String page =null;
		try {
			Doctor doc= service.getOneDoctor(id);
			model.addAttribute("doctor", doc);
			page = "DoctorEdit";

		} catch (DoctorNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			return "redirect:all";
		}
		return page;
	
		
	}
	
	//6.do update
	@PostMapping("/update")
	public String doUpdate(@ModelAttribute Doctor doc,RedirectAttributes attributes) {
		service.updateDoctor(doc);
		attributes.addAttribute("message", "Doctor ("+doc.getId()+") is updated");
		return "redirect:all";
		
	}
	
	//6.email and mobile validation using ajax
	
	//7. excel export
	

}
