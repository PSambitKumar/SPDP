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
@Table(name="TBL_MST_DEPARTMENT")

@NamedQueries({
    @NamedQuery(name = "DepartDetails",
    query = " from  DepartmentEntity")
   
})

public class DepartmentEntity {
	
	@Id
	@GenericGenerator(name="deptInc" , strategy="increment")
	@GeneratedValue(generator="deptInc")
	@Column(name= "DEPTID")
	private Long deptId;
	
	@Column(name= "DEPTNAME")
	private String deptName;
	
	@Column(name= "DESCRIPTION")
	private String description;
	
	@Column(name= "SHORTNAME")
	private String shortName;
	
	@Column(name= "CREATEDBY")
	private Long createdBy;
	
	@Column(name= "CREATEDON")
	private Date createdOn;
	
	@Column(name= "UPDATEDBY")
	private Long updatedBy;
	
	@Column(name= "UPDATEDON")
	private Date updatedOn;
	
	@Column(name= "IS_ACTIVE_STATUS")
	private String status;

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
