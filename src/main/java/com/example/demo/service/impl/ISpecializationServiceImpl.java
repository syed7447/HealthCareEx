package com.example.demo.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Specialization;
import com.example.demo.exception.SpecializationNotFoundException;
import com.example.demo.repo.SpecializationRepository;
import com.example.demo.service.ISpecializationService;
import com.example.demo.util.MyCollectionsUtil;
@Service
public class ISpecializationServiceImpl implements ISpecializationService {
	@Autowired
	private SpecializationRepository repo;

	@Override
	public Long saveSpecialization(Specialization spec) {
		// TODO Auto-generated method stub
		return repo.save(spec).getSpecId();
	}

	@Override
	public List<Specialization> getAllSpecializatiion() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public void removeSpecialization(Long id) {
		// TODO Auto-generated method stub
		repo.delete(getOneSpecialization(id));

	}

	@Override
	public Specialization getOneSpecialization(Long id) {
		// TODO Auto-generated method stub
		Optional<Specialization> optional = repo.findById(id);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			throw new SpecializationNotFoundException(id+" Not Found");
		}
	}

	@Override
	public void updateSpecialization(Specialization spec) {
		// TODO Auto-generated method stub
		repo.save(spec);

	}

	@Override
	public boolean isSpecCodeExist(String specCode) {
		// TODO Auto-generated method stub
//		Integer count=repo.getSpecializationSpecCodeCount(specCode);
//		boolean exist = count > 0 ? true:false;
//		return exist;
		return repo.getSpecializationSpecCodeCount(specCode)>0;
	}

	@Override
	public boolean isSpecNameExist(String specName) {
		// TODO Auto-generated method stub
		return repo.getSpecializationSpecNameCount(specName)>0;
	}

	@Override
	public boolean isSpecCodeExistForEdit(String specCode, long id) {
		// TODO Auto-generated method stub
		return repo.getSpecializationSpecCodeCountForEdit(specCode,id)>0;
	}

	@Override
	public boolean isSpecNameExistForEdit(String specName, long id) {
		// TODO Auto-generated method stub
		return repo.getSpecializationSpecNameCountForEdit(specName,id)>0;
	}

	@Override
	public Map<Long, String> getSpecIdNandName() {
		// TODO Auto-generated method stub
		List<Object[]> list= repo.getSpecIdNandName();
		 Map<Long,String> map= MyCollectionsUtil.convertToMap(list);
		return map;
	}

}
