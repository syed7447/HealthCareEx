package com.example.demo.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Specialization;
import com.example.demo.repo.SpecializationRepository;
import com.example.demo.service.ISpecializationService;
@Service
public class ISpecializationServiceImpl implements ISpecializationService {
	@Autowired
	private SpecializationRepository repo;

	@Override
	public Long saveSpecialization(Specialization spec) {
		// TODO Auto-generated method stub
		return repo.save(spec).getId();
	}

	@Override
	public List<Specialization> getAllSpecializatiion() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		// TODO Auto-generated method stub
		repo.deleteById(id);

	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		// TODO Auto-generated method stub
		Optional<Specialization> optional = repo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			return null;
		}
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		// TODO Auto-generated method stub
		repo.save(spec);

	}

}
