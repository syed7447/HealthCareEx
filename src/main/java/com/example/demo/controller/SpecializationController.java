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

import com.example.demo.entity.Specialization;
import com.example.demo.service.ISpecializationService;

@Controller
@RequestMapping("/spec")
public class SpecializationController {
	@Autowired
	private ISpecializationService service;
	
	@GetMapping("/register")
	public String displayRegister() {
		return "SpecializationRegister";
	}
	@PostMapping("/save")
	public String saveForm(@ModelAttribute Specialization specialization, Model model) {
		
		Long id= service.saveSpecialization(specialization);
		String msg = "Record ("+id+") Created";
		model.addAttribute("message",msg);
		
		return "SpecializationRegister";
	}
	@GetMapping("/all")
	public String viewAll(Model model) {
		List<Specialization> list= service.getAllSpecializatiion();
		model.addAttribute("list",list);
		
		return "SpecializationData";
		
	}
	@GetMapping("/delete")
	public String deleteData(@RequestParam Long id) {
		service.removeSpecialization(id);
		
		return "redirect:all";
	}

}
