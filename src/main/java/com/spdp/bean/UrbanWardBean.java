package com.spdp.bean;

import java.util.Date;
import java.util.List;

public class UrbanWardBean {

	private Long urbanId;

	private List<WardLocalityBean> wardLocalityBean;

//	private String wardNumber;
//
//	private String locality;

	private Long districtId;

	private Long ulbId;

	private Long createdBy;

	private Date createdOn;

	private Long updatedBy;

	private Long updatedOn;

	private String activeStatus;

	private String distName;

	private String ulbName;
	
	private String wardNumber;
	
	private String locality;


	public Long getUrbanId() {
		return urbanId;
	}

	public void setUrbanId(Long urbanId) {
		this.urbanId = urbanId;
	}

	public List<WardLocalityBean> getWardLocalityBean() {
		return wardLocalityBean;
	}

	public void setWardLocalityBean(List<WardLocalityBean> wardLocalityBean) {
		this.wardLocalityBean = wardLocalityBean;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getUlbId() {
		return ulbId;
	}

	public void setUlbId(Long ulbId) {
		this.ulbId = ulbId;
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

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getDistName() {
		return distName;
	}

	public void setDistName(String distName) {
		this.distName = distName;
	}

	public String getUlbName() {
		return ulbName;
	}

	public void setUlbName(String ulbName) {
		this.ulbName = ulbName;
	}

	public String getWardNumber() {
		return wardNumber;
	}

	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}
	


	
}
