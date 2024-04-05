package com.example.demo.entity;

import java.sql.Date;
import java.util.Set;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "appointment_tab")
public class Appointment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "appt_id_col")
	private long id;

//	has-A relation
	@ManyToOne
	@JoinColumn(name="doc_id_fk_col")
	private Doctor doctor;

	@Column(name = "appt_date_col")
	@DateTimeFormat(iso = ISO.DATE)
	@Temporal(TemporalType.DATE)
	private java.util.Date apptDate;
	
	@Column(name="appt_slots_col")
	private String noOfSlots;
	
	@Column(name="appt_amt_col")
	private Double fee;
	
	@Column(name="appt_details_col")
	private String details;
	
	
}
