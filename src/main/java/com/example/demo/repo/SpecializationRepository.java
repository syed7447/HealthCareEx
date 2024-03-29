package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
	@Query("SELECT COUNT(specCode) from Specialization where specCode=:specCode")
	Integer getSpecializationSpecCodeCount(String specCode);
	@Query("SELECT COUNT(specCode) from Specialization where specCode=:specCode AND id!=:id")
	Integer getSpecializationSpecCodeCountForEdit(String specCode,Long id);
	@Query("SELECT COUNT(specName) from Specialization where specName=:specName")
	Integer getSpecializationSpecNameCount(String specName);
	@Query("SELECT COUNT(specName) from Specialization where specName=:specName AND id!=:id")
	Integer getSpecializationSpecNameCountForEdit(String specName,long id);
	@Query("SELECT id,specCode from Specialization")
	List<Object[]> getSpecIdNandName();
		
	

}
