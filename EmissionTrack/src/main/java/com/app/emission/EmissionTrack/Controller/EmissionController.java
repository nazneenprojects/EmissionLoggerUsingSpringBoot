package com.app.emission.EmissionTrack.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.emission.EmissionTrack.Entity.Emission;
import com.app.emission.EmissionTrack.Error.ResourceNotFoundException;
import com.app.emission.EmissionTrack.service.EmissionService;

@RestController
@RequestMapping("/api")
public class EmissionController {

	@Autowired
	EmissionService emissionService;

	//To Create Emission Records 
	@PostMapping("/emissions")
	public List<Emission> addEmissionsInfo(@RequestBody List<Emission> emission) {
		try {
			return emissionService.addEmissionsInfo(emission);
		} catch (Exception e) {
			throw new  ResourceNotFoundException( "Please provide valid emission values");
		}
	}

	//To Get All Emission Records
	@GetMapping("/stats") 
	public ResponseEntity<List<Emission>> getAllEmission() {
		try {
			List<Emission> sample = emissionService.getEmissionInfo();

			if (sample.isEmpty()) {

				throw new  ResourceNotFoundException("Emission data not available");
			}
			return new ResponseEntity<>(sample, HttpStatus.OK);
		} catch (Exception e) {
			throw new  ResourceNotFoundException("Emission data not available due to internal Error");
		}


	}

	//To Get All Emission Records based on 'productId'
	@GetMapping("/stats/{productId}")
	public ResponseEntity<String> getEmissionStats(@PathVariable String productId) {
		try {
			return emissionService.getEmissionStats(productId);
		} catch (Exception exc) {
			throw new ResourceNotFoundException( productId + " Not found");
		}
	} 



}
