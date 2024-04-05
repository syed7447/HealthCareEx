package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Appointment;
import com.example.demo.entity.Doctor;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

}
