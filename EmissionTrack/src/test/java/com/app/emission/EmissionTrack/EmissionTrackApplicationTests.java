package com.app.emission.EmissionTrack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Order;


import com.app.emission.EmissionTrack.Entity.Emission;
import com.app.emission.EmissionTrack.Repository.EmissionRepo;


@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class EmissionTrackApplicationTests {

	@Autowired
	EmissionRepo emissionRepo;
	
	LocalDate date = LocalDate.now();
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	String text = date.format(formatter);
	LocalDate parsedDate = LocalDate.parse(text, formatter);

	@Test
	@Order(1)
	public void testCreate () {
		Emission e = new Emission();
		e.setId("P4");
		e.setProductId("Low-Duty-Truck");
		e.setEmission(9);
		e.setRecordedAt(parsedDate);
		
		emissionRepo.save(e);
		assertNotNull(emissionRepo.findById("P4").get());
	}
	
	
	
	
	@Test
	@Order(2)
	public void testGet () {
		
		Emission emission = emissionRepo.findById("P4").get();
		assertEquals("Low-Duty-Truck", emission.getProductId());
	}
	
	@Test
	@Order(3)
	public void testRead () {
		
		List<Emission> emission = emissionRepo.findByProductId("Low-Duty-Truck");
		assertEquals("P4", emission.get(0).getId());
	}
	
	
	@Test
	@Order(4)
	public void testCreateinBulk() {
		Emission e1 = new Emission();
		e1.setId("P1");
		e1.setProductId("Heay-Duty-Truck");
		e1.setEmission(7);
		e1.setRecordedAt(parsedDate);
		
		Emission e2 = new Emission();
		e2.setId("P2");
		e2.setProductId("Heay-Duty-Truck");
		e2.setEmission(6);
		e2.setRecordedAt(parsedDate);
		
		Emission e3 = new Emission();
		e3.setId("P3");
		e3.setProductId("Heay-Duty-Truck");
		e3.setEmission(9);
		e3.setRecordedAt(parsedDate);
		
		
		Emission e4 = new Emission();
		e4.setId("P4");
		e4.setProductId("Low-Duty-Truck");
		e4.setEmission(0);
		e4.setRecordedAt(parsedDate);
		

		List<Emission> data = new ArrayList<Emission>();
		data.add(e1);
		data.add(e2);
		data.add(e3);
		data.add(e4);
		emissionRepo.saveAll(data);
		assertNotNull(emissionRepo.count());
	}
	
	
	@Test
	@Order(5)
	public void testGetHighestEmissionDay () {
		LocalDate maxEmissionDay = emissionRepo.findMaxEmissionDay("Heay-Duty-Truck");
		assertEquals(parsedDate, maxEmissionDay);
	}
	
	@Test
	@Order(6)
	public void testGetAvergeEmissionPerDay () {
		double avg = emissionRepo.findAvgEmission("Heay-Duty-Truck");
		assertEquals(7.333333333333333, avg);
	}
	
	
	@Test
	@Order(7)
	public void testGetTotalEmission () {
		double sum = emissionRepo.totalEmission("Heay-Duty-Truck");
		assertEquals(22.0, sum);
		
	}
	
	
	
}


