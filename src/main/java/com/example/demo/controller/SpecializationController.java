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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Specialization;
import com.example.demo.exception.SpecializationNotFoundException;
import com.example.demo.service.ISpecializationService;
import com.example.demo.view.SpecializationExcelView;

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

		Long id = service.saveSpecialization(specialization);
		String msg = "Record (" + id + ") Created";
		model.addAttribute("message", msg);

		return "SpecializationRegister";
	}

	@GetMapping("/all")
	public String viewAll(Model model, @RequestParam(value = "message", required = false) String message) {
		List<Specialization> list = service.getAllSpecializatiion();
		model.addAttribute("list", list);
		model.addAttribute("message", message);

		return "SpecializationData";

	}

	@GetMapping("/delete")
	public String deleteData(@RequestParam Long id, RedirectAttributes attributes) {
		try {
			service.removeSpecialization(id);
			attributes.addAttribute("message", "Record (" + id + ") Deleted");

		} catch (SpecializationNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
		}

		return "redirect:all";
	}

	@GetMapping("/edit")
	public String showEditPage(@RequestParam Long id, Model model,RedirectAttributes attributes) {
		String page = null;
		try {
			Specialization spec = service.getOneSpecialization(id);
			model.addAttribute("specialization", spec);
			page="SpecializationEdit";
		} catch (SpecializationNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			attributes.addAttribute("message",e.getMessage());
			page="redirect:all";
			
		}
	

		return page;
	}

	@PostMapping("/update")
	public String updateSpecialization(@ModelAttribute Specialization spec, RedirectAttributes attributes) {
		service.updateSpecialization(spec);
		attributes.addAttribute("message", "Record (" + spec.getId() + ") is updated");
		return "redirect:all";

	}

	@GetMapping("/checkCode")
	public @ResponseBody String validateSpecCode(@RequestParam String code,@RequestParam Long id) {
		String message = "";
		if (id==0 && service.isSpecCodeExist(code)) { //register check
			message = code + ", already exist";
		}
		else if (id!=0 && service.isSpecCodeExistForEdit(code,id)) { //register check
			message = code + ", already exist";
		}
		return message; // this is not viewName this is a message
	}

	@GetMapping("/checkName")
	public @ResponseBody String validateSpecName(@RequestParam String name,@RequestParam long id) {
		String message = "";
		if (id==0 && service.isSpecNameExist(name)) {
			message = name + ", already exist";
		}
		else if (id!=0 && service.isSpecNameExistForEdit(name,id)) {
			message = name + ", already exist";
		}
		return message; // this is not viewName this is a message
	}
	//export data to excel file
	@GetMapping("/excel")
	public ModelAndView exportToExcel() {
		ModelAndView m= new ModelAndView();
		m.setView(new SpecializationExcelView());
		//read data from db
		List<Specialization> list= service.getAllSpecializatiion();
		//send to excelimpl class
		m.addObject("list",list);
		return m;
		
	}

}
