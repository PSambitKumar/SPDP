package com.spdp.bean;

import java.util.List;
/**
 * 
 * @author jyotiranjan.behera
 *
 */

public class BlockGpBean {
	private String blockName;
	private List<String> gpNames;
	
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public List<String> getGpNames() {
		return gpNames;
	}
	public void setGpNames(List<String> gpNames) {
		this.gpNames = gpNames;
	}
	
	

}
