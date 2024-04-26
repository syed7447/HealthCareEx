package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;
import com.example.demo.exception.AppointmentNotFoundException;
import com.example.demo.service.IAppointmentService;
import com.example.demo.service.IDoctorService;
import com.example.demo.service.ISpecializationService;

@Controller
@RequestMapping("/appointment")
public class AppointmentController {
	@Autowired
	private IAppointmentService service;
	@Autowired
	private IDoctorService docService;
	@Autowired
	private ISpecializationService specService;
	
	private void createDynamicUi(Model model) {
		Map<Long,String> doctor=docService.getDocIdNandNames();
		model.addAttribute("doctors", doctor);
		
	}
	// 1. show register page
	@GetMapping("/register")
	public String showRegister(@RequestParam(required = false)String message,Model model) {
		model.addAttribute("message", message);
		createDynamicUi(model);
		return "AppointmentRegister";	
	}
	
	//2.save on submit
	@PostMapping("/save")
	public String save(@ModelAttribute Appointment app,RedirectAttributes attributes) {
		Long id = service.saveAppointment(app);
		attributes.addAttribute("message", "Appointment ("+id+") is created");
		
		return "redirect:register";
		
	}
	
	//3.display data
	@GetMapping("/all")
	public String display(Model model,@RequestParam(required = false)String message) {
		List<Appointment> list= service.getAllAppointment();
		model.addAttribute("list", list);
		model.addAttribute("message", message);
		return "AppointmentData";
	}
	
	//4.delete by id
	@GetMapping("/delete")
	public String delete(@RequestParam Long id,RedirectAttributes attributes) {
		String message =null;
		try {
			service.removeAppointment(id);
			message="Appointment removed";

		} catch (AppointmentNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			message =e.getMessage();
		}
		attributes.addAttribute("message", message);
		return "redirect:all";
	}
	
	 //5. show edit
	@GetMapping("/edit")
	public String edit(@RequestParam Long id,RedirectAttributes attributes,Model model) {
		
//		return "AppointmentEdit";
		
		String page =null;
		try {
			Appointment app= service.getOneAppointment(id);
			model.addAttribute("Appointment", app);
			createDynamicUi(model);

			page = "AppointmentEdit";

		} catch (AppointmentNotFoundException e) {
			// TODO: handle exception
			e.printStackTrace();
			attributes.addAttribute("message", e.getMessage());
			return "redirect:all";
		}
		return page;
	
		
	}
	
	//6.do update
	@PostMapping("/update")
	public String doUpdate(@ModelAttribute Appointment app,RedirectAttributes attributes) {
		service.updateAppointment(app);
		attributes.addAttribute("message", "Appointment ("+app.getId()+") is updated");
		return "redirect:all";
		
	}
	
	@GetMapping("/view")
	public String viewSlots(Model model,@RequestParam(required = false , defaultValue = "0") Long specId) {
		System.out.println(specId+"specId=======");
		//fetch data for SpecDropDown
		Map<Long,String> specMap = specService.getSpecIdNandName();
		model.addAttribute("specializations",specMap);
		List<Doctor> docList=null;
		if(specId == 0) { //if they did not select any specialization
			docList= docService.getAllDoctor();
		}
		else {
			docList= docService.findDoctorBySpecName(specId);

		}
		model.addAttribute("docList",docList);
		
		return "AppointmentSearch";
	}
	//6.email and mobile validation using ajax
	
	//7. excel export
	

}
