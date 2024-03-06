package com.example.demo.specialization;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import com.example.demo.entity.Specialization;
import com.example.demo.repo.SpecializationRepository;

@DataJpaTest(showSql = true) //activate data JPA
@AutoConfigureTestDatabase(replace = Replace.NONE) //user our own properties file as input to create datasource
@Rollback(false) //after unit testing data will be deleted rollback=true
@TestMethodOrder(OrderAnnotation.class) //execute test  cases in order

public class SpecializationRepositoryTest {
	@Autowired
	private SpecializationRepository repo;
	
	
	/**
	 * Test save operation
	 **/
//	@Disabled
	@Test
	@Order(1)
	public void testSpecCreate() {
		Specialization spec = new Specialization(0, "CRDLS", "Cardiologist", "A cardiologist is a doctor who specializes in diagnosing, treating, and preventing diseases of the heart and blood vessels");
		 spec = repo.save(spec);
		 assertNotNull(spec.getId(),"spec not created");
	}
	/**
	 * Test display all operation
	 */
	@Test
	@Order(2)
	public void testSpecFetchAll() {
		List list = repo.findAll();
		assertNotNull(list);
		if(list.size()<0) {
			fail("No data exist in Database");
		}
		
	}
	}


