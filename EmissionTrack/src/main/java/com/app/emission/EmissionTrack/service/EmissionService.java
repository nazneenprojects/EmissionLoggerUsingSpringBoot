package com.app.emission.EmissionTrack.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.emission.EmissionTrack.Entity.Emission;
import com.app.emission.EmissionTrack.Repository.EmissionRepo;



@Service
public class EmissionService {

	@Autowired
	private EmissionRepo emissionRepo;


	public List<Emission> addEmissionsInfo(List<Emission> emission) {
		return emissionRepo.saveAll(emission);
	}


	public List<Emission> getEmissionInfo() {
		return emissionRepo.findAll();
	}

	public ResponseEntity<String> getEmissionStats(String productId) {
		double avg =  emissionRepo.findAvgEmission(productId);
		double sum = emissionRepo.totalEmission(productId);
		LocalDate maxEmissionDay = emissionRepo.findMaxEmissionDay(productId);

		String output = "| Highest recorded emissions Day: "+ maxEmissionDay.toString()+ "|--------------|"+
				"| Average of emissions per day: "+ avg + "  co2tonnes" + "|--------------|"+
				"| Total amount of emissions since recording: "+ sum+"  co2tonnes";

		return new ResponseEntity<>(output, HttpStatus.OK);

	}




}
