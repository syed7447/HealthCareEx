package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Doctor;
import com.example.demo.exception.DoctorNotFoundException;
import com.example.demo.repo.DoctorRepository;
import com.example.demo.service.IDoctorService;
import com.example.demo.util.MyCollectionsUtil;

@Service
public class IDoctorServiceImpl implements IDoctorService{
	@Autowired
	private DoctorRepository repo;

	@Override
	public Long saveDoctor(Doctor doc) {
		// TODO Auto-generated method stub
		return repo.save(doc).getId();
	}

	@Override
	public List<Doctor> getAllDoctor() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public void removeDoctor(Long id) {
		// TODO Auto-generated method stub
		repo.delete(getOneDoctor(id));
		
	}

	@Override
	public Doctor getOneDoctor(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(()-> new DoctorNotFoundException(id+",not exist"));
	}

	@Override
	public void updateDoctor(Doctor doc) {
		// TODO Auto-generated method stub
		if(repo.existsById(doc.getId()))
		repo.save(doc);
		else throw new DoctorNotFoundException(doc.getId()+",not exist");
		
	}

	@Override
	public Map<Long, String> getDocIdNandNames() {
		List<Object[]> list= repo.getDoctorIdNandNames();
		 Map<Long,String> map= MyCollectionsUtil.convertToMapIndex(list);
		return map;
	}

}
