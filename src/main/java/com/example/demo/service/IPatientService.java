package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Patient;

public interface IPatientService {
	public Long savePatient(Patient pat);
	
	public List<Patient> getAllPatient();
	
	public void removePatient(Long id);

	public Patient getOnePatient(Long id);

	public void updatePatient(Patient pat);
}
