package com.spdp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * 
 * @Author : Akash Deep
 * 
 **/


@Entity
@Table(name="TBL_DISTRICT_MST")
@NamedQueries({
    @NamedQuery(name = "DistDetails",
    query = " from  District")
   
})
public class District {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="DISTRICT_ID")
	private Long districtId;
	
	@Column(name="DISTRICT_NAME")
	private String districtName;
	
	
	@Column(name="NOTIFICATION_NUM")
	private String notificationNum;
	
	@Column(name="NOTIFICATION_DATE")
	private Date notificationDate;
	
	@Column(name="UPDATED_ON")
	private Date updatedOn;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="ACTION")
	private String action;
	
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;

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

	public String getNotificationNum() {
		return notificationNum;
	}

	public void setNotificationNum(String notificationNum) {
		this.notificationNum = notificationNum;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
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

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}


	
	
	

}
