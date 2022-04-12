package com.spdp.entity;



import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Entity
@Table(name="TBL_DISTRICT_MST")
@NamedQueries({
	
    @NamedQuery(name = "DistrictEntityDetails",
    query = " from  DistrictEntity"),
    @NamedQuery(name = "deleteDistByNotiNo",
    query = " delete from  DistrictEntity where notificationNo=:notiNo"),
    @NamedQuery(name = "getDistrictRowByNotiNo",
    query = " from  DistrictEntity d where d.notificationNo=:notiNo")

    
})
public class DistrictEntity {
	
	//private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="districtInc" , strategy="increment")
	@GeneratedValue(generator="districtInc")	
	@Column(name= "DISTRICT_ID")
	private Long districtId;
	
	@Column(name= "DISTRICT_NAME")
	private String districtName;
	
	@Column(name= "NOTIFICATION_NUM")
	private String notificationNo;
	
	@Column(name= "NOTIFICATION_DATE")
	private Date notificationDate;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="UPDATED_ON")
	private  Date updatedOn;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="ACTION")
	private String aciton;
	
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	
	@Column(name="LGD_CODE")
	private String lgdCode;
	
	
	

	public String getLgdCode() {
		return lgdCode;
	}

	public void setLgdCode(String lgdCode) {
		this.lgdCode = lgdCode;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getAciton() {
		return aciton;
	}

	public void setAciton(String aciton) {
		this.aciton = aciton;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	

	


}