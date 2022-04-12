package com.spdp.bean;

import java.util.Date;
import java.util.List;

public class TehsilMasterBean {

	private Long tehsiltId;

	private String tehsilName;

	private String notificationNum;

	private String notificationDate;

	private Date updatedOn;

	private Date createdOn;

	private String updatedBy;

	private String createdBy;

	private String deleteStatus;

	private Long subDivisionId;

	private Long districtId;

	private String distName;

	private String subName;
	
	private String tehsilNames;
	
	private Date notiDate;
	
//	private List<RiCircleMappingBean> riCircleMappingBean;

	private List<RICircleBeans> riCircle;
	public Long getTehsiltId() {
		return tehsiltId;
	}

	public void setTehsiltId(Long tehsiltId) {
		this.tehsiltId = tehsiltId;
	}

	public String getTehsilName() {
		return tehsilName;
	}

	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}

	public String getNotificationNum() {
		return notificationNum;
	}

	public void setNotificationNum(String notificationNum) {
		this.notificationNum = notificationNum;
	}

	public String getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
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

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	public Long getSubDivisionId() {
		return subDivisionId;
	}

	public void setSubDivisionId(Long subDivisionId) {
		this.subDivisionId = subDivisionId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
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

	public List<RICircleBeans> getRiCircle() {
		return riCircle;
	}

	public void setRiCircle(List<RICircleBeans> riCircle) {
		this.riCircle = riCircle;
	}

	
	public String getTehsilNames() {
		return tehsilNames;
	}

	public void setTehsilNames(String tehsilNames) {
		this.tehsilNames = tehsilNames;
	}

	
	
	public Date getNotiDate() {
		return notiDate;
	}

	@Override
	public String toString() {
		return "TehsilMasterBean [tehsiltId=" + tehsiltId + ", tehsilName=" + tehsilName + ", notificationNum="
				+ notificationNum + ", notificationDate=" + notificationDate + ", updatedOn=" + updatedOn
				+ ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", createdBy=" + createdBy
				+ ", deleteStatus=" + deleteStatus + ", subDivisionId=" + subDivisionId + ", districtId=" + districtId
				+ ", distName=" + distName + ", subName=" + subName + ", tehsilNames=" + tehsilNames + ", notiDate="
				+ notiDate + ", riCircle=" + riCircle + "]";
	}

	public void setNotiDate(Date notiDate) {
		this.notiDate = notiDate;
	}

	

	
}
