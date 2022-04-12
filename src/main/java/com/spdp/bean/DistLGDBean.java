package com.spdp.bean;

import java.io.File;

public class DistLGDBean {
	
	private Long deptId;
	private Long schemeId;
	private File distFile;
	
	public Long getDeptId() {
		return deptId;
	}
	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}
	public Long getSchemeId() {
		return schemeId;
	}
	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}
	public File getDistFile() {
		return distFile;
	}
	public void setDistFile(File distFile) {
		this.distFile = distFile;
	}
	
	
}
