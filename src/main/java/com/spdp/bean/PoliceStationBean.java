package com.spdp.bean;


import java.util.List;

public class PoliceStationBean {
	private Long policeStationId;
	private String policeStationName;
	private Long stationId;
	private String stationName;
	private String psName;
	private Long psStId; 
	private String outPostName;
	private String beatHouseName;
	private String name;
	private String notiNo;
	private String notiDate;
	private Long typeOfPs;
	private Long dist;
	private Long subDiv;
	private Long block;
	private List<finalBucketListBean> finalBucketList;
	private String distName;
	private String topsName;
	private String status;
	
	
	public Long getPoliceStationId() {
		return policeStationId;
	}
	public void setPoliceStationId(Long policeStationId) {
		this.policeStationId = policeStationId;
	}
	public String getPoliceStationName() {
		return policeStationName;
	}
	public void setPoliceStationName(String policeStationName) {
		this.policeStationName = policeStationName;
	}
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public PoliceStationBean() {}
	
	public Long getPsStId() {
		return psStId;
	}
	public void setPsStId(Long psStId) {
		this.psStId = psStId;
	}
	public String getDistName() {
		return distName;
	}
	public void setDistName(String distName) {
		this.distName = distName;
	}
	public String getTopsName() {
		return topsName;
	}
	public void setTopsName(String topsName) {
		this.topsName = topsName;
	}
	public List<finalBucketListBean> getFinalBucketList() {
		return finalBucketList;
	}
	public void setFinalBucketList(List<finalBucketListBean> finalBucketList) {
		this.finalBucketList = finalBucketList;
	}
	public Long getStationId() {
		return stationId;
	}
	public void setStationId(Long stationId) {
		this.stationId = stationId;
	}
	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getPsName() {
		return psName;
	}
	public void setPsName(String psName) {
		this.psName = psName;
	}
	public String getOutPostName() {
		return outPostName;
	}
	public void setOutPostName(String outPostName) {
		this.outPostName = outPostName;
	}
	public String getBeatHouseName() {
		return beatHouseName;
	}
	public void setBeatHouseName(String beatHouseName) {
		this.beatHouseName = beatHouseName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public Long getTypeOfPs() {
		return typeOfPs;
	}
	public void setTypeOfPs(Long typeOfPs) {
		this.typeOfPs = typeOfPs;
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
	
	

	

}
