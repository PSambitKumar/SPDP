package com.spdp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author kamalakanta.g
 *
 */
@Entity
@Table(name="TBL_POLICE_DISTRICT_MASTER")
@NamedQueries({
    @NamedQuery(name = "PoliceDistDetails",
    query = " from  PoliceDistrictMst")
   
})
public class PoliceDistrictMst implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="policeDistricInc" , strategy="increment")
	@GeneratedValue(generator="policeDistricInc")
	@Column(name="PDISTRICTID")
	private Long pdistrictId;
	@Column(name="PDISTRICTNAME")
	private String districtName;
	@Column(name="NOTIFICATION_NO")
	private String notificationNo;
	@Column(name="NOTIFICATION_DATE")
	private Date notificationDate;
	/*
	 * @Column(name="PDISTRICTTYPE") private int districType;
	 */
	@Column(name="DISTRICT_ID")
	private Long districId;
	@Column(name="CREATED_BY")
	private String createdBy;
	@Column(name="CREATEDON")
	private Date createdOn;
	@Column(name="UPDATED_ON")
	private Date updatedon;
	@Column(name="UPDATED_BY")
	private String updatedBy;
	@Column(name="ACTIVE_STATUS")
	private String status;
	public Long getPdistrictId() {
		return pdistrictId;
	}
	public void setPdistrictId(Long pdistrictId) {
		this.pdistrictId = pdistrictId;
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

	/*
	 * public int getDistricType() { return districType; } public void
	 * setDistricType(int districType) { this.districType = districType; }
	 */
	public Long getDistricId() {
		return districId;
	}
	public void setDistricId(Long districId) {
		this.districId = districId;
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
	public Date getUpdatedon() {
		return updatedon;
	}
	public void setUpdatedon(Date updatedon) {
		this.updatedon = updatedon;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	
}
