package com.spdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @Author : Akash Deep
 * 
 **/


@Entity
@Table(name="TBL_DISTRICT_RDC_MAPPING")
public class RdcDistrictMapping {

	@Id
	@GenericGenerator(name="rdcDistMapInc" , strategy="increment")
	@GeneratedValue(generator="rdcDistMapInc")	
	@Column(name="DIST_RDC_ID")
	private Long distRdcId;
	
	@Column(name="DISTRICT_ID")
	private Long districtId;
	
	@Column(name="RDC_ID")
	private Long rdcId;

	public Long getDistRdcId() {
		return distRdcId;
	}

	public void setDistRdcId(Long distRdcId) {
		this.distRdcId = distRdcId;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getRdcId() {
		return rdcId;
	}

	public void setRdcId(Long rdcId) {
		this.rdcId = rdcId;
	}
	
	
	
}
