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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "patient_tab")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pat_id_col")
	private long id;

	@Column(name = "pat_firstName_col")
	private String firstName;
	
	@Column(name = "pat_lastName_col")
	private String lastName;
	
	@Column(name = "pat_gen_col")
	private String gender;
	
	@Column(name = "pat_mob_col")
	private String mobile;
	
	@Column(name = "pat_dob_col")
	@DateTimeFormat(iso = ISO.DATE)
	private Date dateOfBirth;
	
	@Column(name = "pat_maritStatus_col")
	private String maritalStatus;
	
	@Column(name = "pat_currAddr_col")
	private String presentAddr;
	
	@Column(name = "pat_PermAddr_col")
	private String commAddr;
	
	@ElementCollection
	@CollectionTable(name = "pat_medi_hist_tab",
	joinColumns = @JoinColumn(name="pat_medi_hist_id_fk"))
	@Column(name = "pat_medi_hist_col")
	private Set<String> mediHistory;
	
	@Column(name = "pat_ifOther_col")
	private String ifOther;
	
	@Column(name = "pat_note_col")
	private String note;

	
	

	
	
}
