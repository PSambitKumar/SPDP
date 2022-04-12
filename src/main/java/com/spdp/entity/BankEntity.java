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


@Entity
@Table(name="TBL_BANK_BRANCH_MASTER")
@NamedQueries({
    
	@NamedQuery(name = "BankEntityDetails",
		    query = " select b.bankbranchId,b.bankbranchName,b.ifscCode,b.activeStatus,m.bankName,d.districtName from"
		    		+ "  BankEntity b  inner join BankMasterEntity m on b.bankId = m.bankId inner join District d on b.districtId=d.districtId"),
    @NamedQuery(name = "deletedByBranchName",
    query = " delete from  BankEntity b  where b.bankbranchName = : bankbranchName"),
    @NamedQuery(name = "getBankBranchbyBankId",
    query = "select s1.bankbranchId,s1.bankbranchName from  BankEntity s1 where s1.bankId=:id "),
    
    	@NamedQuery(name = "getRowByBankbranchName",
    	query = "from BankEntity b where b.bankbranchName=:bankbranchName"),
//    @NamedQuery(name = "getBlockByDistId",
//    query = "select b1.blockId,b1.blockName from  BlockEntity b1 where b1.districtId=:districtId ")
})
public class BankEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@GenericGenerator(name="bankInc" , strategy="increment")
	@GeneratedValue(generator="bankInc")	
	@Id
	@Column(name= "BANK_BRANCH_ID")
	private Long bankbranchId;
	
	@Column(name= "BANK_BRANCH_NAME")
	private String bankbranchName;
	
	@Column(name="BANK_ID")
	private Long bankId;
	
	
	@Column(name= "IFSC_CODE")
	private String ifscCode;
	
	@Column(name= "MICR_NO")
	private String micrNo;
	
	@Column(name="DISTRICT_ID")
	private Long districtId;
	
	@Column(name="LOCATION")
	private  String location;
	
	@Column(name="PINCODE")
	private Long pincode;
	
	@Column(name="CREATED_BY")
	private Long createdBy;
	
	@Column(name="CREATED_ON")
	private Date createdOn;
	
	@Column(name="UPDATED_BY")
	private Long updatedBy;
	
	@Column(name="UPDATED_ON")
	private Date updatedOn;
	
	@Column(name="ACTIVE_STATUS")
	private String activeStatus;

	public Long getBankbranchId() {
		return bankbranchId;
	}

	public void setBankbranchId(Long bankbranchId) {
		this.bankbranchId = bankbranchId;
	}

	public String getBankbranchName() {
		return bankbranchName;
	}

	public void setBankbranchName(String bankbranchName) {
		this.bankbranchName = bankbranchName;
	}

	public Long getBankId() {
		return bankId;
	}

	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	public String getIfscCode() {
		return ifscCode;
	}

	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}

	public String getMicrNo() {
		return micrNo;
	}

	public void setMicrNo(String micrNo) {
		this.micrNo = micrNo;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}