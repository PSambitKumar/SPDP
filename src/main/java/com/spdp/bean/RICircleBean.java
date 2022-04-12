package com.spdp.bean;

import java.util.List;

public class RICircleBean {

	private Long riCircleId;
	private String riCircleName;
	private String notiNo;
	private String notiDate;
	private Long dist;
	private Long subDivision;
	private Long tehsil;
	private List<RICirlceAndVillageBean> villageList;
	private Long id;
	private String key;
	private String status;
	private String distName;
	private String subDivName;
	private String tehsilName;
	
	
	
	
	
	
	
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
	public String getTehsilName() {
		return tehsilName;
	}
	public void setTehsilName(String tehsilName) {
		this.tehsilName = tehsilName;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Long getRiCircleId() {
		return riCircleId;
	}
	public void setRiCircleId(Long riCircleId) {
		this.riCircleId = riCircleId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public List<RICirlceAndVillageBean> getVillageList() {
		return villageList;
	}
	public void setVillageList(List<RICirlceAndVillageBean> villageList) {
		this.villageList = villageList;
	}
	public String getRiCircleName() {
		return riCircleName;
	}
	public void setRiCircleName(String riCircleName) {
		this.riCircleName = riCircleName;
	}
	public String getNotiNo() {
		return notiNo;
	}
	public void setNotiNo(String notiNo) {
		this.notiNo = notiNo;
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
	public Long getSubDivision() {
		return subDivision;
	}
	public void setSubDivision(Long subDivision) {
		this.subDivision = subDivision;
	}
	public Long getTehsil() {
		return tehsil;
	}
	public void setTehsil(Long tehsil) {
		this.tehsil = tehsil;
	}
	
	
}
