package com.spdp.bean;


/**
 * 
 * @author sanghamitra.beura
 *
 */


import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.spdp.entity.PoliceSubDivision;

public class PoliceDistrictBean {
	private Long pdistrictId;
	private String pdistrictName;
	private String notiNo;
	private Date notiDate;
	private String notificationDate;
	private String stringCreatedDate;
	private Long pdistrictType;
	private Long policedistcatId;
	
	//private List<SdpoBean> sdpoObj;
		private List<String> SdpoListForView;
		private String status;
		private String policedistrictName;
		private String districtName;
		private Object[] formData;
		
		private List<String> policedistList;
		private List<String> distList;
		private List<String> sdpoList;
		private List<Long> sdpoL;
		

		private Long districtId;
		private Long sdpoId;
		private String sdpoName;
		private Date creationDate;
		private String createdBy;
		private String updatedDate;
		private String updatedBy;
		
		
		
		private List<policeSubDivisionBean> policesubObj;
		
	private Long pDistrictId;
	private String pDistricname;

	public Long getPolicedistcatId() {
		return policedistcatId;
	}
	public void setPolicedistcatId(Long policedistcatId) {
		this.policedistcatId = policedistcatId;
	}

	
	
	public Long getpDistrictId() {
		return pDistrictId;
	}
	public void setpDistrictId(Long pDistrictId) {
		this.pDistrictId = pDistrictId;
	}
	public String getpDistricname() {
		return pDistricname;
	}
	public void setpDistricname(String pDistricname) {
		this.pDistricname = pDistricname;
	}

	
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}


	private List<policeSubDivisionBean> sdpoObj;
	private Long policeSubDivId;
	
	
	public Long getPoliceSubDivId() {
		return policeSubDivId;
	}
	public void setPoliceSubDivId(Long policeSubDivId) {
		this.policeSubDivId = policeSubDivId;
	}
	
	
	public List<policeSubDivisionBean> getSdpoObj() {
		return sdpoObj;
	}
	public void setSdpoObj(List<policeSubDivisionBean> sdpoObj) {
		this.sdpoObj = sdpoObj;
	}

	public List<policeSubDivisionBean> getPolicesubObj() {
		return policesubObj;
	}
	public void setPolicesubObj(List<policeSubDivisionBean> policesubObj) {
		this.policesubObj = policesubObj;
	}
	public List<Long> getSdpoL() {
		return sdpoL;
	}
	public void setSdpoL(List<Long> sdpoL) {
		this.sdpoL = sdpoL;
	}
	public String getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}
	public String getStringCreatedDate() {
		return stringCreatedDate;
	}
	public void setStringCreatedDate(String stringCreatedDate) {
		this.stringCreatedDate = stringCreatedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public Long getPdistrictId() {
		return pdistrictId;
	}
	public void setPdistrictId(Long pdistrictId) {
		this.pdistrictId = pdistrictId;
	}
	public String getPdistrictName() {
		return pdistrictName;
	}
	public void setPdistrictName(String pdistrictName) {
		this.pdistrictName = pdistrictName;
	}
	public String getNotiNo() {
		return notiNo;
	}
	public void setNotiNo(String notiNo) {
		this.notiNo = notiNo;
	}
	public Date getNotiDate() {
		return notiDate;
	}
	public void setNotiDate(Date notiDate) {
		this.notiDate = notiDate;
	}
	public Long getPdistrictType() {
		return pdistrictType;
	}
	public void setPdistrictType(Long pdistrictType) {
		this.pdistrictType = pdistrictType;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public Long getSdpoId() {
		return sdpoId;
	}
	public void setSdpoId(Long sdpoId) {
		this.sdpoId = sdpoId;
	}
	public String getSdpoName() {
		return sdpoName;
	}
	public void setSdpoName(String sdpoName) {
		this.sdpoName = sdpoName;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public List<String> getSdpoListForView() {
		return SdpoListForView;
	}
	public void setSdpoListForView(List<String> sdpoListForView) {
		SdpoListForView = sdpoListForView;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPolicedistrictName() {
		return policedistrictName;
	}
	public void setPolicedistrictName(String policedistrictName) {
		this.policedistrictName = policedistrictName;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public Object[] getFormData() {
		return formData;
	}
	public void setFormData(Object[] formData) {
		this.formData = formData;
	}
	public List<String> getPolicedistList() {
		return policedistList;
	}
	public void setPolicedistList(List<String> policedistList) {
		this.policedistList = policedistList;
	}
	public List<String> getDistList() {
		return distList;
	}
	public void setDistList(List<String> distList) {
		this.distList = distList;
	}
	public List<String> getSdpoList() {
		return sdpoList;
	}
	public void setSdpoList(List<String> sdpoList) {
		this.sdpoList = sdpoList;
	}
	@Override
	public String toString() {
		return "PoliceDistrictBean [pdistrictId=" + pdistrictId + ", pdistrictName=" + pdistrictName + ", notiNo="
				+ notiNo + ", notiDate=" + notiDate + ", notificationDate=" + notificationDate + ", stringCreatedDate="
				+ stringCreatedDate + ", pdistrictType=" + pdistrictType + ", districtId=" + districtId + ", sdpoId="
				+ sdpoId + ", sdpoName=" + sdpoName + ", creationDate=" + creationDate + ", createdBy=" + createdBy
				+ ", sdpoObj=" + sdpoObj + ", policeSubDivId=" + policeSubDivId + ", SdpoListForView=" + SdpoListForView
				+ ", status=" + status + ", policedistrictName=" + policedistrictName + ", districtName=" + districtName
				+ ", formData=" + Arrays.toString(formData) + ", policedistList=" + policedistList + ", distList="
				+ distList + ", sdpoList=" + sdpoList + ", sdpoL=" + sdpoL + ", policesubObj=" + policesubObj + "]";
	}
	
	
	

	

}
