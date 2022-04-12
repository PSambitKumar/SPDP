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
 * @Author : Akash Deep
 * 
 **/


@Entity
@Table(name="TBL_RDC_MST")
@NamedQueries({
    @NamedQuery(name = "RdcEntityDetails",
    query = " from  RdcEntity"),
    @NamedQuery(name = "deleteByNotiNo",
    query = " delete from  RdcEntity where notificationNo=:notiNo"),
    @NamedQuery(name = "getRdcRowByNotiNo",
    query = " from  RdcEntity r where r.notificationNo=:notiNo")
})
public class RdcEntity{
	
	
	
	@Id
	@GenericGenerator(name="rdcInc" , strategy="increment")
	@GeneratedValue(generator="rdcInc")	
	@Column(name= "RDC_ID")
	private Long rdcId;
	
	@Column(name= "RDC_NAME")
	private String rdcName;
	
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
	
	@Column(name="DELETE_STATUS")
	private String deleteStatus;
	
	@Column(name="RDC_CODE")
	private Long rdcCode;
	
	@Column(name="STATE_ID")
	private Long stateId;

	public Long getRdcId() {
		return rdcId;
	}

	public void setRdcId(Long rdcId) {
		this.rdcId = rdcId;
	}

	public String getRdcName() {
		return rdcName;
	}

	public void setRdcName(String rdcName) {
		this.rdcName = rdcName;
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

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}
	
	
	
	public Long getRdcCode() {
		return rdcCode;
	}

	public void setRdcCode(Long rdcCode) {
		this.rdcCode = rdcCode;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	


}
