package com.example.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Appointment;
import com.example.demo.exception.AppointmentNotFoundException;
import com.example.demo.repo.AppointmentRepository;
import com.example.demo.service.IAppointmentService;

@Service
public class IAppointmentServiceImpl implements IAppointmentService{
	@Autowired
	private AppointmentRepository repo;

	@Override
	public Long saveAppointment(Appointment app) {
		// TODO Auto-generated method stub
		return repo.save(app).getId();
	}

	@Override
	public List<Appointment> getAllAppointment() {
		// TODO Auto-generated method stub
		return repo.findAll();
	}

	@Override
	public void removeAppointment(Long id) {
		// TODO Auto-generated method stub
		repo.delete(getOneAppointment(id));
		
	}

	@Override
	public Appointment getOneAppointment(Long id) {
		// TODO Auto-generated method stub
		return repo.findById(id).orElseThrow(()-> new AppointmentNotFoundException(id+",not exist"));
	}

	@Override
	public void updateAppointment(Appointment app) {
		// TODO Auto-generated method stub
		if(repo.existsById(app.getId()))
		repo.save(app);
		else throw new AppointmentNotFoundException(app.getId()+",not exist");
		
	}

}
