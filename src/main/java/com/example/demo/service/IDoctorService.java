package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.Doctor;

public interface IDoctorService {
	public Long saveDoctor(Doctor doc);
	
	public List<Doctor> getAllDoctor();
	
	public void removeDoctor(Long id);

	public Doctor getOneDoctor(Long id);

	public void updateDoctor(Doctor doc);
	
	Map<Long,String> getDocIdNandNames();

}
