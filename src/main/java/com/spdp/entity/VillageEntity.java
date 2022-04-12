package com.spdp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TBL_VILLAGE_MASTER")

@NamedQueries({
    @NamedQuery(name = "villageDetails", query = "from  VillageEntity"),
    
    @NamedQuery(name = "deleteByVillageId",
    query = "from VillageEntity where villageId=:villageId"),
    
    @NamedQuery(name = "updateByVillageId",
    query = "from  VillageEntity where villageId=:villageId"),
    
})

public class VillageEntity {

	@GenericGenerator(name="villageInc" , strategy="increment")
	@GeneratedValue(generator="villageInc")	
	@Id
	@Column(name="VILLAGE_ID")
	private Long villageId;
	
	@Column(name="VILLAGE_NAME")
	private String villageName;
	
	@Column(name="LGD_CODE")
	private String lgdCode;
	
	@Column(name="RESOLUTION_NO")
	private String resolutionNumber;
	
	@Column(name="NOTIFICATION_DATE")
	private Date notificationDate;
	
	@Column(name="DISTRICT_ID")
	private Long districtId;
	
	@Column(name="SUB_DIVISION_ID")
	private Long subDivisionId;
	
	@Column(name="BLOCK_ID")
	private Long blocktId;
	
	@Column(name="GP_ID")
	private Long gpId;
	
	@Column(name="TEHSIL_ID")
	private Long tehsiltId;
	
	@Column(name="RI_CIRCLE")
	private Long riCircleId;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="UPDATED_ON")
	private Date updatedOn;
	
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;

	public Long getVillageId() {
		return villageId;
	}

	public void setVillageId(Long villageId) {
		this.villageId = villageId;
	}

	public String getVillageName() {
		return villageName;
	}

	public void setVillageName(String villageName) {
		this.villageName = villageName;
	}

	public String getLgdCode() {
		return lgdCode;
	}

	public void setLgdCode(String lgdCode) {
		this.lgdCode = lgdCode;
	}

	public String getResolutionNumber() {
		return resolutionNumber;
	}

	public void setResolutionNumber(String resolutionNumber) {
		this.resolutionNumber = resolutionNumber;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getSubDivisionId() {
		return subDivisionId;
	}

	public void setSubDivisionId(Long subDivisionId) {
		this.subDivisionId = subDivisionId;
	}

	public Long getBlocktId() {
		return blocktId;
	}

	public void setBlocktId(Long blocktId) {
		this.blocktId = blocktId;
	}

	public Long getGpId() {
		return gpId;
	}

	public void setGpId(Long gpId) {
		this.gpId = gpId;
	}

	

	public Long getTehsiltId() {
		return tehsiltId;
	}

	public void setTehsiltId(Long tehsiltId) {
		this.tehsiltId = tehsiltId;
	}

	public Long getRiCircleId() {
		return riCircleId;
	}

	public void setRiCircleId(Long riCircleId) {
		this.riCircleId = riCircleId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	
	
}
