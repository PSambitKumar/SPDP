package com.spdp.bean;

import java.util.List;



/**
 * 
 * @author sanghamitra.beura
 *
 */

public class SubdivisionBlockBean {

	private String subdivisionName;
	private List<String> blockNames;
	
	
	public SubdivisionBlockBean() {
		
	}
	public String getSubdivisionName() {
		return subdivisionName;
	}
	public void setSubdivisionName(String subdivisionName) {
		this.subdivisionName = subdivisionName;
	}
	public List<String> getBlockNames() {
		return blockNames;
	}
	public void setBlockNames(List<String> blockNames) {
		this.blockNames = blockNames;
	}
	
	
}
