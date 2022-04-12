package com.spdp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TBL_MST_POLICEDISTRICTCATEGORY")
@NamedQuery(name = "PoliceDistrictDetails",
		query = "from PoliceDistrictCatagoryEntity")
public class PoliceDistrictCatagoryEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( generator = "increment" )	
	@GenericGenerator( name = "increment", strategy = "increment" )
	@Column(name= "POLICEDISTCAT_ID")
	private Long policedistcatId;
	
	@Column(name= "POLICEDISTCATEGORY")
	private String policedistcatagory;
	
	@Column(name= "STATUS")
	private String status;

	public Long getPolicedistcatId() {
		return policedistcatId;
	}

	public void setPolicedistcatId(Long policedistcatId) {
		this.policedistcatId = policedistcatId;
	}

	public String getPolicedistcatagory() {
		return policedistcatagory;
	}

	public void setPolicedistcatagory(String policedistcatagory) {
		this.policedistcatagory = policedistcatagory;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
}
