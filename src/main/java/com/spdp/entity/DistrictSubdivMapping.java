package com.spdp.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TBL_SUBDIVISION_DIST_MAPPING")
public class DistrictSubdivMapping {
	@Id
	@GenericGenerator(name="districtSubdivMapInc" , strategy="increment")
	@GeneratedValue(generator="districtSubdivMapInc")	
	@Column(name="SUBDIVISION_DIST_ID")
	
	private Long subdivisionDistId;
	
	@Column(name="SUBDIVISION_ID")
	private Long subdivisionId;
	
	@Column(name="DISTRICT_ID")
	private Long districtId;

	public Long getSubdivisionDistId() {
		return subdivisionDistId;
	}

	public void setSubdivisionDistId(Long subdivisionDistId) {
		this.subdivisionDistId = subdivisionDistId;
	}

	public Long getSubdivisionId() {
		return subdivisionId;
	}

	public void setSubdivisionId(Long subdivisionId) {
		this.subdivisionId = subdivisionId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

}	

	


