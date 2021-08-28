package com.app.emission.EmissionTrack.Entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name="emissiontbl")
public class Emission {
	    @Id
	    @Column(name="id")
		private String id;
		
	    @Column(name="productId")
		private String productId;
		
		@Column(name="emission")
		private float emission;
	
		@Column(name="recorded_at", nullable=false, updatable=false)
		@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy")
		private LocalDate recordedAt;


		public Emission() {
			super();
		}


		public Emission(String id, String productId, float emission, LocalDate recordedAt) {
			super();
			this.id = id;
			this.productId = productId;
			this.emission = emission;
			this.recordedAt = recordedAt;
		}


		public String getId() {
			return id;
		}


		public void setId(String id) {
			this.id = id;
		}


		public String getProductId() {
			return productId;
		}


		public void setProductId(String productId) {
			this.productId = productId;
		}


		public float getEmission() {
			return emission;
		}


		public void setEmission(float emission) {
			this.emission = emission;
		}


		public LocalDate getRecordedAt() {
			return recordedAt;
		}


		public void setRecordedAt(LocalDate recordedAt) {
			this.recordedAt = recordedAt;
		}


		@Override
		public String toString() {
			return "Emission [ id=" + id + ", productId=" + productId + ",  emission=" + emission + ", recordedAt="
					+ recordedAt + "]";
		}
		
		
		
}
