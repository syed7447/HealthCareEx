package com.example.demo.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Doctor;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	@Query("SELECT id,firstName,lastName from Doctor")
	List<Object[]> getDoctorIdNandNames();
}
