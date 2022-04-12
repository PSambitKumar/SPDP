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
@Table(name="TBL_RI_CIRCLE_MASTER")

@NamedQueries({
    @NamedQuery(name = "circleDetails",
    query = "from  RICircleMasterEntity where tehsilId=:riCircleId"),
    
    @NamedQuery(name = "ricircleDetails",
    query = "from  RICircleMasterEntity"),
    
    @NamedQuery(name = "riDetails",
    query = "from  RICircleMasterEntity where riCircleId IN (:riCircleIds)")
})
public class RICircleMasterEntity {
	

	@Id
	@GenericGenerator(name="riCircleMst" , strategy="increment")
	@GeneratedValue(generator="riCircleMst")	
	@Column(name= "RI_CIRCLE_ID")
	private Long riCircleId;
	
	@Column(name= "RI_CIRCLE_NAME")
	private String riCircleName;
	
	@Column(name= "NOTIFICATION_NO")
	private String notificationNo;
	
	@Column(name= "DISTRICT_ID")
	private Long distId;
	
	@Column(name= "SUB_DIVISION_ID")
	private Long subDivisionId;
	
	@Column(name= "TEHSIL_ID")
	private Long tehsilId;
	
	@Column(name= "UPDATED_ON")
	private Date updatedOn;
	
	@Column(name= "CREATED_ON")
	private Date createdOn;
	
	@Column(name= "UPDATED_BY")
	private String updatedBy;
	
	@Column(name= "CREATED_BY")
	private String createdBy;
	
	@Column(name= "ACTION")
	private String action;
	
	@Column(name= "ACTIVE_STATUS")
	private String activeStatus;
	
	@Column(name= "NOTIFICATION_DATE")
	private Date notificationDate;

	
	
	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public Long getRiCircleId() {
		return riCircleId;
	}

	public void setRiCircleId(Long riCircleId) {
		this.riCircleId = riCircleId;
	}

	public String getRiCircleName() {
		return riCircleName;
	}

	public void setRiCircleName(String riCircleName) {
		this.riCircleName = riCircleName;
	}

	public String getNotificationNo() {
		return notificationNo;
	}

	public void setNotificationNo(String notificationNo) {
		this.notificationNo = notificationNo;
	}

	public Long getDistId() {
		return distId;
	}

	public void setDistId(Long distId) {
		this.distId = distId;
	}

	public Long getSubDivisionId() {
		return subDivisionId;
	}

	public void setSubDivisionId(Long subDivisionId) {
		this.subDivisionId = subDivisionId;
	}

	public Long getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
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

	@Override
	public String toString() {
		return "RICircleMasterEntity [riCircleId=" + riCircleId + ", riCircleName=" + riCircleName + ", notificationNo="
				+ notificationNo + ", distId=" + distId + ", subDivisionId=" + subDivisionId + ", tehsilId=" + tehsilId
				+ ", updatedOn=" + updatedOn + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy + ", createdBy="
				+ createdBy + ", action=" + action + ", activeStatus=" + activeStatus + "]";
	}
	
	
	

}
