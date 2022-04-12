package com.spdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TBL_RICIRCLE_VILLAGE_MAPPING")
public class RiCircleVillageMapping {
	
	@Id
	@GenericGenerator(name="riVillMap" , strategy="increment")
	@GeneratedValue(generator="riVillMap")	
	@Column(name="VILLAGE_MAPPING_ID")
	private Long riVillMapId;
	
	@Column(name="RI_CIRCLE_ID")
	private Long riCircleId;
	
	@Column(name="VILLAGE_ID")
	private Long villageId;
	
	@Column(name="RI_CIRCLE_PRIMARY_ID")
	private Long riCirclePK;
	
	@Column(name="STATUS")
	private String status;
	
	
	
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getRiCirclePK() {
		return riCirclePK;
	}

	public void setRiCirclePK(Long riCirclePK) {
		this.riCirclePK = riCirclePK;
	}

	public Long getRiVillMapId() {
		return riVillMapId;
	}

	public void setRiVillMapId(Long riVillMapId) {
		this.riVillMapId = riVillMapId;
	}

	public Long getRiCircleId() {
		return riCircleId;
	}

	public void setRiCircleId(Long riCircleId) {
		this.riCircleId = riCircleId;
	}

	public Long getVillageId() {
		return villageId;
	}

	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}
	
	

}
