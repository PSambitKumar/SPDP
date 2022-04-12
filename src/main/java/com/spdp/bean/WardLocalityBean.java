package com.spdp.bean;

public class WardLocalityBean {

	private String wardNumber;

	private String locality;

//	private Long urbanId;
	
	//private Long districtId;

	
	public WardLocalityBean(String wardNumber, String locality) {
		super();
		this.wardNumber = wardNumber;
		this.locality = locality;
	}

	public WardLocalityBean() {
		super();
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
	

//	public Long getUrbanId() {
//		return urbanId;
//	}
//
//	public void setUrbanId(Long urbanId) {
//		this.urbanId = urbanId;
//	}

	

	@Override
	public String toString() {
		return "WardLocalityBean [wardNumber=" + wardNumber + ", locality=" + locality + "]";
	}
	
}
