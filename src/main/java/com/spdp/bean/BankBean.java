package com.spdp.bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class BankBean {

	
	private Long bankbranchId;
	private String bankbranchName;
	private Long bankId;
	private String ifscCode;
	private String micrNo;
	private Long districtId;
	private String location;
	private Long pincode;
	private String status;
	private String bankName;
	private String districtName;
	

	private List<DistrictBean> distObj;
	private List<String> distString;
	
	private List<Integer> dist;
	private List<Long> distL;
	private List<String> distList;

	private List<String> DistListForView;
	
	private String [] distLists;
	
	
	private List<Integer> bank;
	private List<Long> bankL;
	private List<String> bankList;
	private Object[] formData;
	private Long districtObj;
	
	public List<DistrictBean> getDistObj() {
		return distObj;
	}
	public void setDistObj(List<DistrictBean> distObj) {
		this.distObj = distObj;
	}
	public List<String> getDistString() {
		return distString;
	}
	public void setDistString(List<String> distString) {
		this.distString = distString;
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
	public List<String> getDistListForView() {
		return DistListForView;
	}
	public void setDistListForView(List<String> distListForView) {
		DistListForView = distListForView;
	}
	public String[] getDistLists() {
		return distLists;
	}
	public void setDistLists(String[] distLists) {
		this.distLists = distLists;
	}
	public String getDistrictName() {
		return districtName;
	}
	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public Long getBankbranchId() {
		return bankbranchId;
	}
	public void setBankbranchId(Long bankbranchId) {
		this.bankbranchId = bankbranchId;
	}
	public String getBankbranchName() {
		return bankbranchName;
	}
	public void setBankbranchName(String bankbranchName) {
		this.bankbranchName = bankbranchName;
	}
	public Long getBankId() {
		return bankId;
	}
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getMicrNo() {
		return micrNo;
	}
	public void setMicrNo(String micrNo) {
		this.micrNo = micrNo;
	}
	public Long getDistrictId() {
		return districtId;
	}
	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Long getPincode() {
		return pincode;
	}
	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}
	public List<Integer> getBank() {
		return bank;
	}
	public void setBank(List<Integer> bank) {
		this.bank = bank;
	}
	public List<Long> getBankL() {
		return bankL;
	}
	public void setBankL(List<Long> bankL) {
		this.bankL = bankL;
	}
	public List<String> getBankList() {
		return bankList;
	}
	public void setBankList(List<String> bankList) {
		this.bankList = bankList;
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
	
	public void setStatus(String activeStatus) {
		// TODO Auto-generated method stub
		this.status=activeStatus;
	}
	public Long getDistrictObj() {
		return districtObj;
	}
	public void setDistrictObj(Long districtObj) {
		this.districtObj = districtObj;
	}
	
	
	//private String [] distLists;
	//private String [] blockLists;

	//private String districtName;
	
}
