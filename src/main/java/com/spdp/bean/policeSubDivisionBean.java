package com.spdp.bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
/**
 * @author kamalakanta.g
 *
 */
public class policeSubDivisionBean {
	private Long policeSubDivId;
	private String spdoName;
	private Long spdoId;
	private String notiNo;
	private LocalDate notiDate;
	private Long policedistrictId;
	private String policeDistrict;
	private List<PoliceStationBean> policeStationObj;
	//private List<PoliceStationBean> policeStationObj;

	private List<Integer> policeSation;
	private List<Long> policeStationL;
	private List<String> policeStationList;
	private String status;
	private String Range;
	private Long policeStationId;
	private String sdpoName;
	
	
	public String getSdpoName() {
		return sdpoName;
	}
	public void setSdpoName(String sdpoName) {
		this.sdpoName = sdpoName;
	}

	private List<String> policeStationListView;
	private String notificationDate;
	public List<String> getPoliceStationListView() {
		return policeStationListView;
	}
	public void setPoliceStationListView(List<String> policeStationListView) {
		this.policeStationListView = policeStationListView;
	}
	public Long getPoliceStationId() {
		return policeStationId;
	}
	public void setPoliceStationId(Long policeStationId) {
		this.policeStationId = policeStationId;
	}
	public Long getPoliceSubDivId() {
		return policeSubDivId;
	}
	public void setPoliceSubDivId(Long policeSubDivId) {
		this.policeSubDivId = policeSubDivId;
	}
	public String getSpdoName() {
		return spdoName;
	}
	public void setSpdoName(String spdoName) {
		this.spdoName = spdoName;
	}
	public String getNotiNo() {
		return notiNo;
	}
	public void setNotiNo(String notiNo) {
		this.notiNo = notiNo;
	}
	public LocalDate getNotiDate() {
		return notiDate;
	}
	public void setNotiDate(LocalDate notiDate) {
		this.notiDate = notiDate;
	}
	public Long getPolicedistrictId() {
		return policedistrictId;
	}
	public void setPolicedistrictId(Long policedistrictId) {
		this.policedistrictId = policedistrictId;
	}

	public List<PoliceStationBean> getPoliceStationObj() {
		return policeStationObj;
	}
	public void setPoliceStationObj(List<PoliceStationBean> policeStationObj) {
		this.policeStationObj = policeStationObj;
	}

	public List<Integer> getPoliceSation() {
		return policeSation;
	}
	public void setPoliceSation(List<Integer> policeSation) {
		this.policeSation = policeSation;
	}
	public List<Long> getPoliceStationL() {
		return policeStationL;
	}
	public void setPoliceStationL(List<Long> policeStationL) {
		this.policeStationL = policeStationL;
	}
	public List<String> getPoliceStationList() {
		return policeStationList;
	}
	public void setPoliceStationList(List<String> policeStationList) {
		this.policeStationList = policeStationList;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getPoliceDistrict() {
		return policeDistrict;
	}
	public void setPoliceDistrict(String policeDistrict) {
		this.policeDistrict = policeDistrict;
	}
	public String getRange() {
		return Range;
	}
	public void setRange(String range) {
		Range = range;
	}

	public Long getSpdoId() {
		return spdoId;
	}
	public void setSpdoId(Long spdoId) {
		this.spdoId = spdoId;
	}
	public String getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}
	
	
	
	

}
