package com.spdp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author jyotiranjan.behera
 *
 */
@Entity
@Table(name="TBL_BLOCK_MASTER")
@NamedQueries({
	@NamedQuery(name = "BlockEntityDetails",
		    query = " select b.blockId,b.blockName,b.createdOn,b.notificationNo,d.districtName,s.subdivisionName,b.activeStatus, b.lgdCode, b.notificationDate from"
		    		+ "  BlockEntity b  inner join District d on b.districtId = d.districtId inner join SubDivisionEntity s on b.subDivisionId=s.subdivisionId"),
//    @NamedQuery(name = "BlockEntityDetails",
//    query = " from  BlockEntity"),
    @NamedQuery(name = "deletedBlockByNotiNo",
    query = " delete from  BlockEntity be where be.notificationNo=:notiNo"),//deletedByNotiNo-->deletedBlockByNotiNo
    @NamedQuery(name = "getBlockRowByNotiNo",
    query = " from  BlockEntity r where r.notificationNo=:notiNo"),

    @NamedQuery(name = "blkDetails",
    query = "from  BlockEntity where subDivisionId=:subdivisionId"),

    @NamedQuery(name = "getSubDivisionByDistId",
    query = "select s1.subdivisionId,s1.subdivisionName from  SubDivisionEntity s1 where s1.districtId=:districtId "),
	@NamedQuery(name = "getGpDetailsBySubDivId",

    query = "select s1.gpId,s1.gpName from  GpEntity s1 where s1.subDivisionId=:sunDivisionId "),
	@NamedQuery(name = "getBlockDetailsBySubDivId",
    query = "select s1.blockId,s1.blockName from  BlockEntity s1 where s1.subDivisionId=:sunDivisionId ")

    
})

public class BlockEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="blockIncr" , strategy="increment")
	@GeneratedValue(generator="blockIncr")	
	@Column(name= "BLOCK_ID")
	private Long blockId;
	
	@Column(name= "BLOCK_NAME")
	private String blockName;
	
	@Column(name="LGD_CODE")
	private Long lgdCode;
	
	@Column(name= "NOTIFICATION_NO")
	private String notificationNo;
	
	@Column(name= "NOTIFICATION_DATE")
	private Date notificationDate;
	
	@Column(name= "DISTRICT_ID")
	private Long districtId;
	
//	@Column(name= "GP_ID")
//	private Long gpId;
	
	@Column(name= "SUB_DIVISION_ID")
	private Long subDivisionId;
	
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

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public Long getLgdCode() {
		return lgdCode;
	}

	public void setLgdCode(Long lgdCode) {
		this.lgdCode = lgdCode;
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

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		System.out.println("setting DistrictId ..");
		this.districtId = districtId;
	}

//	public Long getGpId() {
//		return gpId;
//	}
//
//	public void setGpId(Long gpId) {
//		this.gpId = gpId;
//	}

	public Long getSubDivisionId() {
		return subDivisionId;
	}

	public void setSubDivisionId(Long subDivisionId) {
		this.subDivisionId = subDivisionId;
	}

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getAciton() {
		return aciton;
	}

	public void setAciton(String aciton) {
		this.aciton = aciton;
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

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Override
	public String toString() {
		return "BlockEntity [blockId=" + blockId + ", blockName=" + blockName + ", lgdCode=" + lgdCode
				+ ", notificationNo=" + notificationNo + ", notificationDate=" + notificationDate + ", districtId="
				+ districtId + ", subDivisionId=" + subDivisionId + ", updatedOn=" + updatedOn + ", aciton=" + aciton
				+ ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", updatedBy=" + updatedBy
				+ ", activeStatus=" + activeStatus + "]";
	}
	
	
	

}
