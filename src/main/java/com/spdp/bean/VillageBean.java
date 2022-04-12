package com.spdp.bean;

import java.util.Date;

public class VillageBean {

	private Long villageId;
	
	private String villageName;
	
	private String lgdCode;
	
	private String resolutionNumber;
	
	private String notificationDate;
	
	private Long districtId;
	
	private Long subdivisionId;
	
	private Long blocktId;
	
	private Long gpId;
	
	private Long tehsilId;
	
	private Long riCircleId;
	
	private String createdBy;
	
	private Date createdOn;
	
	private String updatedBy;
	
	private Date updatedOn;
	
	private String activeStatus;

	private String distName;
	
	private String subName;
	
	private String blockName;
	
	private String GPName;
	
	private String tehsilName;
	
	private String riCircleName;
	
	
	
	public String getGPName() {
		return GPName;
	}

	public void setGPName(String gPName) {
		GPName = gPName;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getRiCircleName() {
		return riCircleName;
	}

	public void setRiCircleName(String riCircleName) {
		this.riCircleName = riCircleName;
	}

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

	public String getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}


	public Long getSubdivisionId() {
		return subdivisionId;
	}

	public void setSubdivisionId(Long subdivisionId) {
		this.subdivisionId = subdivisionId;
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

	public Long getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
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

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getSubName() {
		return subName;
	}

	public void setSubName(String subName) {
		this.subName = subName;
	}
	
	
	

	

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	@Override
	public String toString() {
		return "VillageBean [villageId=" + villageId + ", villageName=" + villageName + ", lgdCode=" + lgdCode
				+ ", resolutionNumber=" + resolutionNumber + ", notificationDate=" + notificationDate + ", districtId="
				+ districtId + ", subDivisionId=" + subdivisionId + ", blocktId=" + blocktId + ", gpId=" + gpId
				+ ", tehsilId=" + tehsilId + ", riCircleId=" + riCircleId + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + ", activeStatus=" + activeStatus
				+ ", distName=" + distName + ", subName=" + subName + ", getVillageId()=" + getVillageId()
				+ ", getVillageName()=" + getVillageName() + ", getLgdCode()=" + getLgdCode()
				+ ", getResolutionNumber()=" + getResolutionNumber() + ", getNotificationDate()="
				+ getNotificationDate() + ", getDistrictId()=" + getDistrictId() + ", getSubDivisionId()="
				+ getSubdivisionId() + ", getBlocktId()=" + getBlocktId() + ", getGpId()=" + getGpId()
				+ ", getTehsilId()=" + getTehsilId() + ", getRiCircleId()=" + getRiCircleId() + ", getCreatedBy()="
				+ getCreatedBy() + ", getCreatedOn()=" + getCreatedOn() + ", getUpdatedBy()=" + getUpdatedBy()
				+ ", getUpdatedOn()=" + getUpdatedOn() + ", getActiveStatus()=" + getActiveStatus() + ", getDistName()="
				+ getDistName() + ", getSubName()=" + getSubName() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}
	
	
}
