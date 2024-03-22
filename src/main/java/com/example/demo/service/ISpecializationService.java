package com.example.demo.service;

import java.util.List;
import java.util.Map;

import com.example.demo.entity.Specialization;

public interface ISpecializationService {
	public Long saveSpecialization(Specialization spec);
	public List<Specialization> getAllSpecializatiion();
	public void removeSpecialization(Long id);
	public Specialization getOneSpecialization(Long id);
	public void updateSpecialization(Specialization spec);
	public boolean isSpecCodeExist(String specCode);
	public boolean isSpecCodeExistForEdit(String specCode,long id);
	public boolean isSpecNameExist(String specName);
	public boolean isSpecNameExistForEdit(String specName,long id);
	Map<Long,String> getSpecIdNandName();

}
