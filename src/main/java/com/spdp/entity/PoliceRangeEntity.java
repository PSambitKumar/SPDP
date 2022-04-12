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

/**
 * 
 * @author jyotiranjan.behera
 *
 */
@Entity
@Table(name="TBL_POLICE_RANGE_MASTER")
@NamedQueries({
	@NamedQuery(name = "PoliceRangeEntityDetails",
		    query = " select p.policeRangeId,p.policeRangeName,p.notificationNo,p.notificationDate,p.activeStatus from"
		    		+ "  PoliceRangeEntity p "),
	@NamedQuery(name = "getPoliceRangeRowByNotiNo",
    query = " from  PoliceRangeEntity r where r.notificationNo=:notificationNo")

})
public class PoliceRangeEntity {
	@Id
	@GenericGenerator(name="prInc" , strategy="increment")
	@GeneratedValue(generator="prInc")	
	@Column(name= "POLICE_RANGE_ID")
	private Long policeRangeId;
	
	@Column(name= "POLICE_RANGE_NAME")
	private String policeRangeName;
	
	@Column(name= "NOTIFICATION_NO")
	private String notificationNo;
	
	@Column(name= "NOTIFICATION_DATE")
	private Date notificationDate;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="UPDATED_DATE")
	private  Date updatedDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;

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

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Override
	public String toString() {
		return "PoliceRangeEntity [policeRangeId=" + policeRangeId + ", policeRangeName=" + policeRangeName
				+ ", notificationNo=" + notificationNo + ", notificationDate=" + notificationDate + ", updatedBy="
				+ updatedBy + ", updatedDate=" + updatedDate + ", createdBy=" + createdBy + ", createdDate="
				+ createdDate + ", activeStatus=" + activeStatus + "]";
	}
	
	
	

}
