package com.spdp.bean;

import java.util.List;

public class DistSubdivisionBean {
	
	
	private String districtName;
	private List<String> subdivisionNames;
	
	public DistSubdivisionBean() { }

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public List<String> getSubdivisionNames() {
		return subdivisionNames;
	}

	public void setSubdivisionNames(List<String> subdivisionNames) {
		this.subdivisionNames = subdivisionNames;
	}

	
	
	
	
}