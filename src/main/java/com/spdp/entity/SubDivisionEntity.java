package com.spdp.entity;

import java.io.Serializable;
import java.time.LocalDate;
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




/**
 * 
 * @author sanghamitra.beura
 *
 */

@Entity
@Table(name="SUB_DIVISION_MASTER")
@NamedQueries({
   // @NamedQuery(name = "SubDivisionDetails",
//    query = "select tdm.districtName,sdm.subdivisionName,sdm.createdOn,sdm.notificationNo,sdm.activeStatus from SubDivisionEntity sdm inner join District tdm"
//    		+ " on sdm.districtId=tdm.districtId"),
//    
	@NamedQuery(name = "SubDivisionEntityDetails",
		    query = " select s.subdivisionId,s.subdivisionName,s.createdOn,s.notificationNo,s.activeStatus,d.districtName,s.notificationDate from"
		    		+ "  SubDivisionEntity s  left join District d on s.districtId = d.districtId"),
    @NamedQuery(name = "deletedByNotiNo",
    query = " delete from  SubDivisionEntity where notificationNo = : notiNo"),
    
    @NamedQuery(name = "getSubDivisionRowByNotiNo",
    query = "from  SubDivisionEntity s where s.notificationNo = : notiNo"),
    

    @NamedQuery(name = "subDetails",
    query = "from  SubDivisionEntity where districtId=:districtId"),

    @NamedQuery(name = "getBlockByDistId",

    query = "select b1.blockId,b1.blockName from  BlockEntity b1 where b1.districtId=:districtId "),
    
    @NamedQuery(name = "getSubdivisionByDistId",
    query = "select s1.subdivisionId,s1.subdivisionName from  SubDivisionEntity s1 where s1.districtId=:districtId "),

})
public class SubDivisionEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( generator = "increment" )	
	@GenericGenerator( name = "increment", strategy = "increment" )
	
	@Column(name= "SUB_DIVISION_ID")
	private Long subdivisionId;
	
	@Column(name= "SUB_DIVISION_NAME")
	private String subdivisionName;
	
	@Column(name="LGD_CODE")
	private Long lgdCode;
	
	@Column(name= "NOTIFICATION_NO")
	private String notificationNo;
	
	@Column(name= "NOTIFICATION_DATE")
	private LocalDate notificationDate;
	
	@Column(name="DISTRICT_ID")
	private Long districtId;
	
	@Column(name="UPDATED_ON")
	private  Date updatedOn;
	
	@Column(name="ACTION")
	private String aciton;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;
    

	public Long getSubdivisionId() {
		return subdivisionId;
	}

	public void setSubdivisionId(Long subdivisionId) {
		this.subdivisionId = subdivisionId;
	}

	public String getSubdivisionName() {
		return subdivisionName;
	}

	public void setSubdivisionName(String subdivisionName) {
		this.subdivisionName = subdivisionName;
	}

	public String getNotificationNo() {
		return notificationNo;
	}

	public void setNotificationNo(String notificationNo) {
		this.notificationNo = notificationNo;
	}

	public LocalDate getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(LocalDate notificationDate) {
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

	public Long getLgdCode() {
		return lgdCode;
	}

	public void setLgdCode(Long lgdCode) {
		this.lgdCode = lgdCode;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	
}