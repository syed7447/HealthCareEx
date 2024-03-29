package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
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
	
	/*
	 * @Column(name ="image") private String photos;
	 */
	
	@Column(name ="img")
	private String imgLoc;
	
	/*
	 * @Transient private String photosImagePath; public String getPhotoImagePath()
	 * { if(photos == null || id==null) return null; else return "/user-photos/"+ id
	 * +"/" + photos; }
	 */
	
//	has-A relation
	@ManyToOne
	@JoinColumn(name="spec_id_fk_col")
	private Specialization specialization;

}
