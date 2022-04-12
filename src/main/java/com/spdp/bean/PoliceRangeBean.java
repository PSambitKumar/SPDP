package com.spdp.bean;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author jyotiranjan.behera
 *
 */
public class PoliceRangeBean {
	private Long policeRangeId;
	private String policeRangeName;
	private String notificationNo;
	private Date notificationDate;
	private String activeStatus;
	private List<Long> pDistListId;
	private List<String> pdListForView;
	private List<PoliceDistrictBean> pdObj;
	private String notiDate;
	//private List<PoliceDistrictBean> districtObj;
	private List<PoliceRangeBean> prangeObj;
	private List<PoliceDistrictBean> districtObj;
	private Long updatedBy;
	private Date updateDate;
	private Long createdBy;
	private Date createdDate;
	
	public List<PoliceDistrictBean> getDistrictObj() {
		return districtObj;
	}
	public void setDistrictObj(List<PoliceDistrictBean> districtObj) {
		this.districtObj = districtObj;
	}
	public List<PoliceRangeBean> getPrangeObj() {
		return prangeObj;
	}
	public void setPrangeObj(List<PoliceRangeBean> prangeObj) {
		this.prangeObj = prangeObj;
	}

	
	public Long getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Long getPoliceRangeId() {
		return policeRangeId;
	}
	public void setPoliceRangeId(Long policeRangeId) {
		this.policeRangeId = policeRangeId;
	}
	public String getPoliceRangeName() {
		return policeRangeName;
	}
	public void setPoliceRangeName(String policeRangeName) {
		this.policeRangeName = policeRangeName;
	}
	public String getNotificationNo() {
		return notificationNo;
	}
	public void setNotificationNo(String notificationNo) {
		this.notificationNo = notificationNo;
	}
	public Date getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}
	public String getActiveStatus() {
		return activeStatus;
	}
	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	public List<Long> getpDistListId() {
		return pDistListId;
	}
	public void setpDistListId(List<Long> pDistListId) {
		this.pDistListId = pDistListId;
	}
	public List<String> getPdListForView() {
		return pdListForView;
	}
	public void setPdListForView(List<String> pdListForView) {
		this.pdListForView = pdListForView;
	}
	public List<PoliceDistrictBean> getPdObj() {
		return pdObj;
	}
	public void setPdObj(List<PoliceDistrictBean> pdObj) {
		this.pdObj = pdObj;
	}
	public String getNotiDate() {
		return notiDate;
	}
	public void setNotiDate(String notiDate) {
		this.notiDate = notiDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
//	public List<PoliceDistrictBean> getDistrictObj() {
//		return districtObj;
//	}
//	public void setDistrictObj(List<PoliceDistrictBean> districtObj) {
//		this.districtObj = districtObj;
//	}
//	
	
	

}
