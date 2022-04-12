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

import org.hibernate.annotations.GenericGenerator;

import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 
 * @author sanghamitra.beura
 *
 */

@Entity
@Table(name="TBL_POLICE_DISTRICT_MASTER")
@NamedQueries({
	@NamedQuery(name = "PoliceDistrictEntityDetails",
		    query = " select p.pdistrictId,p.pdistrictName,p.notificationDate,p.notificationNo,p.activeStatus,d.districtName from"
		    		+ "  PoliceDistrictEntity p left join District d on p.districtId = d.districtId"),
	
	@NamedQuery(name = "statusByNotiNo",
    query = "from  PoliceDistrictEntity s where s.notificationNo = : notiNo"),
	
	@NamedQuery(name = "getPoliceDistrictByNotiNo",
    query = "from  PoliceDistrictEntity s where s.notificationNo = : notiNo"),
    
	@NamedQuery(name = "getPoliceDistDetailsByTypeId",
    query = "select s1.pdistrictId,s1.pdistrictName from  PoliceDistrictEntity s1 where s1.pdistrictType=:id "),
	
	@NamedQuery(name = "getSdpoDetailsByDistrictId",
    query = "select s1.spdoId,s1.spdoName from  PoliceSubDivision s1 where s1.policeDistrictId=:id ")

    
})
public class PoliceDistrictEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( generator = "increment" )	
	@GenericGenerator( name = "increment", strategy = "increment" )
	
	@Column(name= "PDISTRICTID")
	private Long pdistrictId;
	
	@Column(name= "PDISTRICTNAME")
	private String pdistrictName;
	
	@Column(name= "NOTIFICATION_NO")
	private String notificationNo;
	
	@Column(name= "NOTIFICATION_DATE")
	//@JsonFormat(pattern="dd-MMM-yyyy")
	private Date notificationDate;
	
	@Column(name= "PDISTRICTTYPE")
	private Long pdistrictType;
	
	@Column(name= "DISTRICT_ID")
	private Long districtId;
	
	@Column(name= "CREATED_BY")
	private String createdBy;
	
	@Column(name= "CREATEDON")
	private Date createdOn;
	
	@Column(name= "UPDATED_BY")
	private String updatedBy;
	
	@Column(name= "UPDATED_ON")
	private  Date updatedOn;
	
	@Column(name= "ACTIVE_STATUS")
	private String activeStatus;

	public Long getPdistrictId() {
		return pdistrictId;
	}

	public void setPdistrictId(Long pdistrictId) {
		this.pdistrictId = pdistrictId;
	}

	public String getPdistrictName() {
		return pdistrictName;
	}

	public void setPdistrictName(String pdistrictName) {
		this.pdistrictName = pdistrictName;
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

	public Long getPdistrictType() {
		return pdistrictType;
	}

	public void setPdistrictType(Long pdistrictType) {
		this.pdistrictType = pdistrictType;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
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
