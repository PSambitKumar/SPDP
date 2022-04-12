package com.spdp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="T_MST_CASTEMASTER")
public class CasteMasterEntity {
	
	@Id
	@GenericGenerator(name="casteMstInc" , strategy="increment")
	@GeneratedValue(generator="casteMstInc")
	@Column(name= "CASTEID")
	private Long casteId;
	
	@Column(name= "CASTECATID")
	private Long castCatId;
	
	@Column(name= "CASTENAME")
	private String castName;
	
	@Column(name= "CREATEDBY")
	private Long createdBy;
	
	@Column(name= "CREATEDON")
	private Date createdOn;
	
	@Column(name= "UPDATEDBY")
	private Long updatedBy;
	
	@Column(name= "UPDATEDON")
	private Date updatedOn;
	
	@Column(name= "STATUS")
	private Long status;
	
	

	public Long getCastCatId() {
		return castCatId;
	}

	public void setCastCatId(Long castCatId) {
		this.castCatId = castCatId;
	}

	public Long getCasteId() {
		return casteId;
	}

	public void setCasteId(Long casteId) {
		this.casteId = casteId;
	}

	public String getCastName() {
		return castName;
	}

	public void setCastName(String castName) {
		this.castName = castName;
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

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}
	
	
	

}
