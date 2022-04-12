package com.spdp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TBL_POLICE_STATION_MASTER")
public class policeStationEntity {
	
	
	@Id
	@GenericGenerator(name="policeStInc" , strategy="increment")
	@GeneratedValue(generator="policeStInc")	
	@Column(name="POLICE_STATION_ID")
	private Long policeStationId;
	
	@Column(name="POLICE_STATION_NAME")
	private String policeStationName;
	
	@Column(name="POLICE_STATION_CATEGORY")
	private Long policeStationCatg;
	
	@Column(name="NOTIFICATION_NO")
	private String notiNo;
	
	@Column(name="NOTIFICATION_DATE")
	private Date notiDate;
	
	@Column(name="TYPE_OF_POLICE_STATION")
	private Long typeOfPs;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="CREATED_BY")
	private Long createdBy;
	
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name="UPDATED_BY")
	private Long updatedBy;
	
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	@Column(name="DIST_ID")
	private Long distId;
	
	@Column(name="BLOCK_ID")
	private Long blockId;
	
	
	

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public Long getDistId() {
		return distId;
	}

	public void setDistId(Long distId) {
		this.distId = distId;
	}

	public Long getPoliceStationId() {
		return policeStationId;
	}

	public void setPoliceStationId(Long policeStationId) {
		this.policeStationId = policeStationId;
	}

	public String getPoliceStationName() {
		return policeStationName;
	}

	public void setPoliceStationName(String policeStationName) {
		this.policeStationName = policeStationName;
	}

	public Long getPoliceStationCatg() {
		return policeStationCatg;
	}

	public void setPoliceStationCatg(Long policeStationCatg) {
		this.policeStationCatg = policeStationCatg;
	}

	public String getNotiNo() {
		return notiNo;
	}

	public void setNotiNo(String notiNo) {
		this.notiNo = notiNo;
	}

	public Date getNotiDate() {
		return notiDate;
	}

	public void setNotiDate(Date notiDate) {
		this.notiDate = notiDate;
	}

	public Long getTypeOfPs() {
		return typeOfPs;
	}

	public void setTypeOfPs(Long typeOfPs) {
		this.typeOfPs = typeOfPs;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	
	

}
