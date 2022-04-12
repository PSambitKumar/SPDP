package com.spdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TBL_GP_VILLAGE_MAPPING")
public class GPVillageMapEntity {
	
	
	@Id
	@GenericGenerator(name="gpVillMapInc" , strategy="increment")
	@GeneratedValue(generator="gpVillMapInc")	
	@Column(name="GP_VILLAGE_ID")
	private Long gpVillId;
	
	@Column(name="GP_ID")
	private Long gpId;
	
	@Column(name="VILLAGE_ID")
	private Long villageId;

	@Column(name="STATUS")
	private String status;

	public Long getGpVillId() {
		return gpVillId;
	}

	public void setGpVillId(Long gpVillId) {
		this.gpVillId = gpVillId;
	}

	public Long getGpId() {
		return gpId;
	}

	public void setGpId(Long gpId) {
		this.gpId = gpId;
	}

	public Long getVillageId() {
		return villageId;
	}

	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
