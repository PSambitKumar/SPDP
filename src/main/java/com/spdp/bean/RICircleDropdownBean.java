package com.spdp.bean;

import java.util.List;

public class RICircleDropdownBean {
	private String levelName;
	private Long levelDetailId;
	private Long levelId;
	private Long parentId;
	private String subDivList;
	private List<dropdownBean> dropdownValues;
	
	
	
	
	
	public List<dropdownBean> getDropdownValues() {
		return dropdownValues;
	}
	public void setDropdownValues(List<dropdownBean> dropdownValues) {
		this.dropdownValues = dropdownValues;
	}
	public String getSubDivList() {
		return subDivList;
	}
	public void setSubDivList(String subDivList) {
		this.subDivList = subDivList;
	}
	public RICircleDropdownBean() {}
	public String getLevelName() {
		return levelName;
	}
	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}
	public Long getLevelDetailId() {
		return levelDetailId;
	}
	public void setLevelDetailId(Long levelDetailId) {
		this.levelDetailId = levelDetailId;
	}
	public Long getLevelId() {
		return levelId;
	}
	public void setLevelId(Long levelId) {
		this.levelId = levelId;
	}
	public Long getParentId() {
		return parentId;
	}
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
	
	
}
