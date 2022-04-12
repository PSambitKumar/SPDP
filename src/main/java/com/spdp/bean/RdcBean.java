package com.spdp.bean;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RdcBean {
	
	private Long rdcId;
	private String rdcName;
	private String notiNo;
	//@JsonFormat(pattern="yyyy-MM-dd")
	private String notiDate;
	
	private List<DistrictBean> distObj;
	private List<String> distString;
	
	private List<Integer> dist;
	private List<Long> distL;
	private List<String> distList;
	
	private String status;
	private List<String> DistListForView;
	
	private String [] distLists;
	private Object[] formData;
	
	public RdcBean(){}
	
	
	
	
	
	public List<String> getDistListForView() {
		return DistListForView;
	}





	public void setDistListForView(List<String> distListForView) {
		DistListForView = distListForView;
	}





	public List<String> getDistString() {
		return distString;
	}





	public void setDistString(List<String> distString) {
		this.distString = distString;
	}





	public List<DistrictBean> getDistObj() {
		return distObj;
	}





	public void setDistObj(List<DistrictBean> distObj) {
		this.distObj = distObj;
	}





	public List<Long> getDistL() {
		return distL;
	}





	public void setDistL(List<Long> distL) {
		this.distL = distL;
	}





	public Long getRdcId() {
		return rdcId;
	}





	public void setRdcId(Long rdcId) {
		this.rdcId = rdcId;
	}





	public String[] getDistLists() {
		return distLists;
	}

	

	public List<Integer> getDist() {
		return dist;
	}



	public void setDist(List<Integer> dist) {
		this.dist = dist;
	}



	public void setDistLists(String[] distLists) {
		this.distLists = distLists;
	}



	public Object[] getFormData() {
		return formData;
	}



	public void setFormData(Object[] formData) {
		this.formData = formData;
	}



	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRdcName() {
		return rdcName;
	}
	public void setRdcName(String rdcName) {
		this.rdcName = rdcName;
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
	public List<String> getDistList() {
		return distList;
	}
	public void setDistList(List<String> dist) {
		this.distList = dist;
	}
	
	
	
	

}
