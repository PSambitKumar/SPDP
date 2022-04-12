package com.spdp.bean;

import java.util.Date;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

public class UlbBean {

	
	private Long ulbId;
	
	private String ulbName;
	
	
	private String notificationNum;
	
	private Date notiDate;
	
	//@JsonbDateFormat
	//@JsonFormat(pattern = "dd-MM-yyyy")
	//@Temporal(TemporalType.TIMESTAMP)
	private String notificationDate;
	
	private Long typeOfUlb;
	
	private Long districtId;
	
	private String distName;
	
	private String status;
	
	private String ulbTypeName;
	
	

	public String getUlbTypeName() {
		return ulbTypeName;
	}

	public void setUlbTypeName(String ulbTypeName) {
		this.ulbTypeName = ulbTypeName;
	}

	public Long getUlbId() {
		return ulbId;
	}

	public void setUlbId(Long ulbId) {
		this.ulbId = ulbId;
	}

	public String getUlbName() {
		return ulbName;
	}

	public void setUlbName(String ulbName) {
		this.ulbName = ulbName;
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

	public Long getTypeOfUlb() {
		return typeOfUlb;
	}

	public void setTypeOfUlb(Long typeOfUlb) {
		this.typeOfUlb = typeOfUlb;
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

	public Date getNotiDate() {
		return notiDate;
	}

	public void setNotiDate(Date notiDate) {
		this.notiDate = notiDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
