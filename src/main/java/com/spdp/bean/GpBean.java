package com.spdp.bean;

import java.util.List;

public class GpBean {
	
	private Long gpId;
	private String gpName;
	private String GPName;
	private String LGDCode;
	private String ResNo;
	private String notiDate;
	private Long dist;
	private Long subDiv;
	private Long block;
	private List<dropdownBean> village;
	private String resnoo;
	private String distName;
	private String subDivName;
	private String blockName;
	private String status;
	private Long lgd;
	private List<String> villForView;
	
	
	
	
	
	
	
	
	
	public List<String> getVillForView() {
		return villForView;
	}
	public void setVillForView(List<String> villForView) {
		this.villForView = villForView;
	}
	public Long getLgd() {
		return lgd;
	}
	public void setLgd(Long lgd) {
		this.lgd = lgd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getDistName() {
		return distName;
	}
	public void setDistName(String distName) {
		this.distName = distName;
	}
	public String getSubDivName() {
		return subDivName;
	}
	public void setSubDivName(String subDivName) {
		this.subDivName = subDivName;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public String getResnoo() {
		return resnoo;
	}
	public void setResnoo(String resnoo) {
		this.resnoo = resnoo;
	}
	public String getGPName() {
		return GPName;
	}
	public void setGPName(String gPName) {
		GPName = gPName;
	}
	public String getLGDCode() {
		return LGDCode;
	}
	public void setLGDCode(String lGDCode) {
		LGDCode = lGDCode;
	}
	public String getResNo() {
		return ResNo;
	}
	public void setResNo(String resNo) {
		ResNo = resNo;
	}
	public String getNotiDate() {
		return notiDate;
	}
	public void setNotiDate(String notiDate) {
		this.notiDate = notiDate;
	}
	public Long getDist() {
		return dist;
	}
	public void setDist(Long dist) {
		this.dist = dist;
	}
	public Long getSubDiv() {
		return subDiv;
	}
	public void setSubDiv(Long subDiv) {
		this.subDiv = subDiv;
	}
	public Long getBlock() {
		return block;
	}
	public void setBlock(Long block) {
		this.block = block;
	}
	public List<dropdownBean> getVillage() {
		return village;
	}
	public void setVillage(List<dropdownBean> village) {
		this.village = village;
	}
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
	
	

}
