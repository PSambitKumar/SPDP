package com.spdp.bean;

import java.util.List;



/**
 * 
 * @author sanghamitra.beura
 *
 */
public class PoliceDistSdpoMappingBean {
	private String pdistrictName;
	private List<String> spdoName;
	private String SdpoNames;
	
	
	public String getSdpoNames() {
		return SdpoNames;
	}
	public void setSdpoNames(String sdpoNames) {
		SdpoNames = sdpoNames;
	}
	public String getPdistrictName() {
		return pdistrictName;
	}
	public void setPdistrictName(String pdistrictName) {
		this.pdistrictName = pdistrictName;
	}
	public List<String> getSpdoName() {
		return spdoName;
	}
	public void setSpdoName(List<String> spdoName) {
		this.spdoName = spdoName;
	}
	
	
	

}
