package com.spdp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TBL_MST_EDUCATIONAL_INST")
public class EducationEntity {
	
	@Id
	@GenericGenerator(name="eduInc" , strategy="increment")
	@GeneratedValue(generator="eduInc")
	@Column(name= "INSTID")
	private Long eduId;
	
	@Column(name= "INSTNAME")
	private String eduName;
	
	@Column(name= "INSTCATEGORY")
	private Long category;
	
	@Column(name= "TYPEOFINSTID")
	private Long typeOfIns;
	
	@Column(name= "TYPEOFOWNERSHIPID")
	private Long typeOfOwn;
	
	@Column(name= "UNIVERSITYID")
	private Long universityId;
	
	@Column(name= "NOTIFICATIONNO")
	private String notiNo;
	
	@Column(name= "NOTIFICATIONDATE")
	private Date notiDate;
	
	@Column(name= "UDISECODE")
	private String udiseCode;
	
	@Column(name= "DISTRICTID")
	private Long distId;
	
	@Column(name= "BLOCKID")
	private Long blockId;
	
	@Column(name= "POLICESTATIONID")
	private Long psId;
	
	@Column(name= "PINCODE")
	private String pincode;
	
	@Column(name= "CONTACT")
	private String contactNo;
	
	@Column(name= "CREATEDBY")
	private Long createdBy;
	
	@Column(name= "CREATEDON")
	private Date createdOn;
	
	@Column(name= "UPDATEDBY")
	private Long updatedBy;
	
	@Column(name= "UPDATEDON")
	private Date updatedOn;
	
	@Column(name= "ACTIVE_STATUS")
	private String activeStatus;

	public Long getEduId() {
		return eduId;
	}

	public void setEduId(Long eduId) {
		this.eduId = eduId;
	}

	public String getEduName() {
		return eduName;
	}

	public void setEduName(String eduName) {
		this.eduName = eduName;
	}

	public Long getCategory() {
		return category;
	}

	public void setCategory(Long category) {
		this.category = category;
	}

	public Long getTypeOfIns() {
		return typeOfIns;
	}

	public void setTypeOfIns(Long typeOfIns) {
		this.typeOfIns = typeOfIns;
	}

	public Long getTypeOfOwn() {
		return typeOfOwn;
	}

	public void setTypeOfOwn(Long typeOfOwn) {
		this.typeOfOwn = typeOfOwn;
	}

	public Long getUniversityId() {
		return universityId;
	}

	public void setUniversityId(Long universityId) {
		this.universityId = universityId;
	}

	public String getNotiNo() {
		return notiNo;
	}

	public void setNotiNo(String notiNo) {
		this.notiNo = notiNo;
	}

	public Date getNotiDate() {
		return notiDate;
	}

	public void setNotiDate(Date notiDate) {
		this.notiDate = notiDate;
	}

	public String getUdiseCode() {
		return udiseCode;
	}

	public void setUdiseCode(String udiseCode) {
		this.udiseCode = udiseCode;
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

	public Long getPsId() {
		return psId;
	}

	public void setPsId(Long psId) {
		this.psId = psId;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
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
	
	

}
