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

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TBL_GP_MASTER")
@NamedQueries({
    @NamedQuery(name = "GpDetails",
    query = " from  GpEntity")
   
})
public class GpEntity {
	@Id
	@GenericGenerator(name="gpEntityInc" , strategy="increment")
	@GeneratedValue(generator="gpEntityInc")
	@Column(name= "GP_ID")
	private Long gpId;
	
	@Column(name= "GP_NAME")
	private String gpName;
	
	@Column(name= "RESOLUTION_NO")
	private String resolutionNo;
	
	@Column(name= "NOTIFICATION_DATE")
	private Date notificationDate;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="UPDATED_ON")
	private  Date updatedOn;
	
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
	

	@Column(name="LGD_CODE")
	private Long lgdCode;
	
	@Column(name="DISTRICT_ID")
	private Long distId;
	
	@Column(name="BLOCK_ID")
	private Long blockId;
	
	@Column(name="SUBDIVISION_ID")
	private Long subDivisionId;
	
	

	public Long getLgdCode() {
		return lgdCode;
	}

	public void setLgdCode(Long lgdCode) {
		this.lgdCode = lgdCode;
	}

	public Long getDistId() {
		return distId;
	}

	public void setDistId(Long distId) {
		this.distId = distId;
	}

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public Long getSubDivisionId() {
		return subDivisionId;
	}

	public void setSubDivisionId(Long subDivisionId) {
		this.subDivisionId = subDivisionId;
	}


	public Long getGpId() {
		return gpId;
	}

	public void setGpId(Long gpId) {
		this.gpId = gpId;
	}

	public String getGpName() {
		return gpName;
	}

	public void setGpName(String gpName) {
		this.gpName = gpName;
	}

	public String getResolutionNo() {
		return resolutionNo;
	}

	public void setResolutionNo(String resolutionNo) {
		this.resolutionNo = resolutionNo;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
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

	
	
}
