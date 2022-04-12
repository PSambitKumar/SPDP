package com.spdp.bean;




import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DistBean {
	
	private Long districtId;
	private String districtName;
	private String lgdCode;
	private String notiNo;
	//@JsonFormat(pattern="yyyy-MM-dd")
	private String notiDate;
	private List<SubdivisiondropdownBean> subdivisionObj;
	private List<String> subdivString;
	private List<Integer> subdiv;
	private List<Long> subdivL;
	private List<String> subdivList;
	private List<String> subdivListForView;
	
	private String status;
	
	private String [] subdivLists;
	private Object[] formData;
	
	public DistBean(){}
	
	
	public String getLgdCode() {
		return lgdCode;
	}


	public void setLgdCode(String lgdCode) {
		this.lgdCode = lgdCode;
	}


	public List<String> getSubdivString() {
		return subdivString;
	}
	
	public void setSubdivString(List<String> subdivString) {
		this.subdivString = subdivString;
	}

	public List<SubdivisiondropdownBean> getSubdivisionObj() {
		return subdivisionObj;
	}
	
	
	public void setSubdivisionObj(List<SubdivisiondropdownBean> subdivisionObj) {
		this.subdivisionObj = subdivisionObj;
	}
	
	public List<Long> getSubdivL() {
		return subdivL;
	}





	public List<String> getSubdivListForView() {
		return subdivListForView;
	}


	public void setSubdivListForView(List<String> subdivListForView) {
		this.subdivListForView = subdivListForView;
	}


	public void setSubdivL(List<Long> subdivL) {
		this.subdivL = subdivL;
	}





	public Long getDistrictId() {
		return districtId;
	}





	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}





	public String[] getSubdivLists() {
		return subdivLists;
	}

	

	public List<Integer> getSubdiv() {
		return subdiv;
	}



	public void setSubdiv(List<Integer> subdiv) {
		this.subdiv = subdiv;
	}



	public void setSubdivLists(String[] subdivLists) {
		this.subdivLists = subdivLists;
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
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
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
	public List<String> getSubdivList() {
		return subdivList;
	}
	public void setSubdivList(List<String> subdiv) {
		this.subdivList = subdiv;
	}

}