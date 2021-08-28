package com.app.emission.EmissionTrack.Repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.app.emission.EmissionTrack.Entity.Emission;


@Repository
public interface EmissionRepo extends JpaRepository<Emission, String>  {


//	List all emission whose product is given input of ProductId	
	@Query("SELECT e FROM Emission e WHERE e.productId = ?1")
	public List<Emission> findByProductId(String productId);
	
//	The day with highest recorded emissions
	@Query("SELECT e.recordedAt from Emission e WHERE e.emission = (SELECT MAX(emission) FROM Emission e WHERE e.productId = ?1)")
	public LocalDate findMaxEmissionDay(String productId);
	
//	An average of emissions per day 
	@Query("SELECT AVG(emission) FROM Emission e WHERE e.productId = ?1")
	public double findAvgEmission(String productId);

//	the total amount of emissions since recording
	@Query("SELECT SUM(emission) FROM Emission e WHERE e.productId = ?1")
	public double totalEmission(String productId);
	
	
}
