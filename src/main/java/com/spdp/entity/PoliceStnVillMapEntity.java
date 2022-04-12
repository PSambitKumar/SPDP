package com.spdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TBL_POLICE_STA_VILLAGE_MAPPING")
public class PoliceStnVillMapEntity {
	
	
	@Id
	@GenericGenerator(name="policeStMapInc" , strategy="increment")
	@GeneratedValue(generator="policeStMapInc")	
	@Column(name="POLICE_STATION_VILLAGE_ID")
	private Long policeStVillMapId;
	
	@Column(name="POLICE_STATION_ID")
	private Long policeStationId;
	
	@Column(name="VILLAGE_ID")
	private Long villageId;
	
	@Column(name="STATUS")
	private String status;

	public Long getPoliceStVillMapId() {
		return policeStVillMapId;
	}

	public void setPoliceStVillMapId(Long policeStVillMapId) {
		this.policeStVillMapId = policeStVillMapId;
	}

	public Long getPoliceStationId() {
		return policeStationId;
	}

	public void setPoliceStationId(Long policeStationId) {
		this.policeStationId = policeStationId;
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
