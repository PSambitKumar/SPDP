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
@Table(name = "TBL_TEHSIL_MASTER")

@NamedQueries({ @NamedQuery(name = "tehDetails", query = "from  TehsilEntity where subDivisionId=:subdivisionId"),
	
		@NamedQuery(name = "tehsilDetails", query = "from  TehsilEntity"),
		
		 @NamedQuery(name = "deleteBytehsiltId",
		    query = "from TehsilEntity where tehsiltId=:tehsiltId"),
		 
		 @NamedQuery(name = "updateBytehsiltId",
		    query = "from  TehsilEntity where tehsiltId=:tehsiltId")

})

public class TehsilEntity {

	@GenericGenerator(name = "tehsilInc", strategy = "increment")
	@GeneratedValue(generator = "tehsilInc")
	@Id
	@Column(name = "TEHSIL_ID")
	private Long tehsiltId;

	@Column(name = "TEHSIL_NAME")
	private String tehsilName;

	@Column(name = "NOTIFICATION_NO")
	private String notificationNum;

	@Column(name = "NOTIFICATION_DATE")
	private Date column1;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "CREATED_ON")
	private Date createdOn;

	@Column(name = "UPDATED_BY")
	private String updatedBy;

	@Column(name = "UPDATED_ON")
	private Date updatedOn;

	@Column(name = "ACTIVE_STATUS")
	private String deleteStatus;

	@Column(name = "SUBDIVISION_ID")
	private Long subDivisionId;

	@Column(name = "DISTRICT_ID")
	private Long districtId;
	
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

	public Date getColumn1() {
		return column1;
	}

	public void setColumn1(Date column1) {
		this.column1 = column1;
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

	@Override
	public String toString() {
		return "TehsilEntity [tehsiltId=" + tehsiltId + ", tehsilName=" + tehsilName + ", notificationNum="
				+ notificationNum + ", column1=" + column1 + ", createdBy=" + createdBy + ", createdOn=" + createdOn
				+ ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + ", deleteStatus=" + deleteStatus
				+ ", subDivisionId=" + subDivisionId + ", districtId=" + districtId + "]";
	}

	
}
