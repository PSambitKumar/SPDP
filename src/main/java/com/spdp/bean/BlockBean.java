package com.spdp.bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author jyotiranjan.behera
 *
 */

public class BlockBean {
	
	private Long blockId;
	private String blockName;
	private Long lgdCode;
	private Long districtId;
	private Long sunDivisionId;
	private Long gpId;
	private String notiNo;
	private String notiDate;
	private Date creationDate;
	private Long district;
	private String districtName;
	private String gpName;
	private String subdivisionName;
	private List<String> gpListForView;
	private Date notificationDate;
	private Date nDate;
	private String cDate;
	
	
	
	
	
	
	
	
	
public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
public Date getnDate() {
		return nDate;
	}
	public void setnDate(Date nDate) {
		this.nDate = nDate;
	}
public Date getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}
public List<String> getGpListForView() {
		return gpListForView;
	}
	public void setGpListForView(List<String> gpListForView) {
		this.gpListForView = gpListForView;
	}
public Long getDistrict() {
		return district;
	}
	public void setDistrict(Long district) {
		this.district = district;
	}
	//	private List<SubDivisionBean> subDivisionObj;
//	private List<String> subDivisionString;
//	
	private List<GpBean> gpObj;
	private List<String> gpString;
	
	private List<Integer> dist;
	private List<Long> distL;
	private List<String> distList;
	
	private List<Integer> subDivision;
	private List<Long> subDivisionL;
	private List<String> subDivisionList;
	
	private List<Integer> gp;
	private List<Long> gpL;
	private List<String> gpList;
	
	private String status;
	
	private String [] distLists;
	private String [] subDivisionLists;
	private String [] gpLists;
	private Object[] formData;
	
	public Long getBlockId() {
		return blockId;
	}
	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}
	public String getBlockName() {
		return blockName;
	}
	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}
	public Long getLgdCode() {
		return lgdCode;
	}
	public void setLgdCode(Long lgdCode) {
		this.lgdCode = lgdCode;
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
	public List<Integer> getDist() {
		return dist;
	}
	public void setDist(List<Integer> dist) {
		this.dist = dist;
	}
	public List<Long> getDistL() {
		return distL;
	}
	public void setDistL(List<Long> distL) {
		this.distL = distL;
	}
	public List<String> getDistList() {
		return distList;
	}
	public void setDistList(List<String> distList) {
		this.distList = distList;
	}
	public List<Integer> getSubDivision() {
		return subDivision;
	}
	public void setSubDivision(List<Integer> subDivision) {
		this.subDivision = subDivision;
	}
	public List<Long> getSubDivisionL() {
		return subDivisionL;
	}
	public void setSubDivisionL(List<Long> subDivisionL) {
		this.subDivisionL = subDivisionL;
	}
	public List<String> getSubDivisionList() {
		return subDivisionList;
	}
	public void setSubDivisionList(List<String> subDivisionList) {
		this.subDivisionList = subDivisionList;
	}
	public List<Integer> getGp() {
		return gp;
	}
	public void setGp(List<Integer> gp) {
		this.gp = gp;
	}
	public List<Long> getGpL() {
		return gpL;
	}
	public void setGpL(List<Long> gpL) {
		this.gpL = gpL;
	}
	public List<String> getGpList() {
		return gpList;
	}
	public void setGpList(List<String> gpList) {
		this.gpList = gpList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String[] getDistLists() {
		return distLists;
	}
	public void setDistLists(String[] distLists) {
		this.distLists = distLists;
	}
	public String[] getSubDivisionLists() {
		return subDivisionLists;
	}
	public void setSubDivisionLists(String[] subDivisionLists) {
		this.subDivisionLists = subDivisionLists;
	}
	public String[] getGpLists() {
		return gpLists;
	}
	public void setGpLists(String[] gpLists) {
		this.gpLists = gpLists;
	}
	public Object[] getFormData() {
		return formData;
	}
	public void setFormData(Object[] formData) {
		this.formData = formData;
	}
	public List<GpBean> getGpObj() {
		return gpObj;
	}
	public void setGpObj(List<GpBean> gpObj) {
		this.gpObj = gpObj;
	}
	public List<String> getGpString() {
		return gpString;
	}
	public void setGpString(List<String> gpString) {
		this.gpString = gpString;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Long getDistrictId() {

		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getSunDivisionId() {
		return sunDivisionId;
	}
	public void setSunDivisionId(Long sunDivisionId) {
		this.sunDivisionId = sunDivisionId;
	}
	
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getSubdivisionName() {
		return subdivisionName;
	}
	public void setSubdivisionName(String subdivisionName) {
		this.subdivisionName = subdivisionName;
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
	@Override
	public String toString() {
		return "BlockBean [blockId=" + blockId + ", blockName=" + blockName + ", lgdCode=" + lgdCode + ", districtId="
				+ districtId + ", sunDivisionId=" + sunDivisionId + ", gpId=" + gpId + ", notiNo=" + notiNo
				+ ", notiDate=" + notiDate + ", creationDate=" + creationDate + ", district=" + district
				+ ", districtName=" + districtName + ", gpName=" + gpName + ", subdivisionName=" + subdivisionName
				+ ", gpListForView=" + gpListForView + ", gpObj=" + gpObj + ", gpString=" + gpString + ", dist=" + dist
				+ ", distL=" + distL + ", distList=" + distList + ", subDivision=" + subDivision + ", subDivisionL="
				+ subDivisionL + ", subDivisionList=" + subDivisionList + ", gp=" + gp + ", gpL=" + gpL + ", gpList="
				+ gpList + ", status=" + status + ", distLists=" + Arrays.toString(distLists) + ", subDivisionLists="
				+ Arrays.toString(subDivisionLists) + ", gpLists=" + Arrays.toString(gpLists) + ", formData="
				+ Arrays.toString(formData) + "]";
	}
	
	
	
		

}
