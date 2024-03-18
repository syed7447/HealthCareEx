package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="doctor_tab")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="doc_id_col")
	private Long id;
	
	@Column(name="doc_fn_col")
	private String firstName;
	
	@Column(name="doc_ln_col")
	private String lastName;
	
	@Column(name="doc_email_col")
	private String email;
	
	@Column(name="doc_addr_col")
	private String address;
	
	@Column(name="doc_mob_col")
	private String mobile;
	
	@Column(name="doc_gen_col")
	private String gender;
	
	@Column(name="doc_note_col")
	private String note;
	

}
