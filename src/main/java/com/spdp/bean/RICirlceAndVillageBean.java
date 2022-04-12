package com.spdp.bean;

import java.util.List;

public class RICirlceAndVillageBean {
	
	private String gpName;
	private List<String> villageName;
	private Long gpId;
	private List<Long> villageNameId;
	
	
	
	public List<Long> getVillageNameId() {
		return villageNameId;
	}

	public void setVillageNameId(List<Long> villageNameId) {
		this.villageNameId = villageNameId;
	}

	public RICirlceAndVillageBean() {}
	
	public Long getGpId() {
		return gpId;
	}
	public void setGpId(Long gpId) {
		this.gpId = gpId;
	}
	public String getGpName() {
		return gpName;
	}
	public void setGpName(String gpName) {
		this.gpName = gpName;
	}
	public List<String> getVillageName() {
		return villageName;
	}
	public void setVillageName(List<String> villageName) {
		this.villageName = villageName;
	}
	
	

}
