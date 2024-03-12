package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Long> {
	@Query("SELECT COUNT(specCode) from Specialization where specCode=:specCode")
	Integer getSpecializationSpecCodeCount(String specCode);
	@Query("SELECT COUNT(specName) from Specialization where specName=:specName")
	Integer getSpecializationSpecNameCount(String specName);
		
	

}
