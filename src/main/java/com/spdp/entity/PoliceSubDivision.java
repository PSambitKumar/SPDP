package com.spdp.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * @author kamalakanta.g
 *
 */

@Entity
@Table(name="TBL_POLICE_SUB_DIVISION_MASTER")
@NamedQueries({
    @NamedQuery(name = "policeSubdivisionmstDetails",
    query = " from  PoliceSubDivision"),
    @NamedQuery(name = "policeSubDivisionEntityDetails",

    query = " select s.spdoId,d.districtName,s.spdoName,s.notifictionNo,s.notificationDate,s.status from"
    		+ "  PoliceSubDivision s  left join PoliceDistrictMst d on s.policeDistrictId = d.pdistrictId"),
    @NamedQuery(name = "PoliceSubDivision_Statusupdate",
    query = "from  PoliceSubDivision s where s.notifictionNo = : notiNumber"),
    @NamedQuery(name = "PoliceSubDivision_update",
    query = "from  PoliceSubDivision s where s.spdoId = : spdoId"),
//    @NamedQuery(name = "getSdpoDetailsByDistrictId",
//    query = " select s.spdoId,d.pdistrictName,s.spdoName,s.notifictionNo,s.notificationDate,s.status from"
//    		+ "  PoliceSubDivision s left join PoliceDistrictEntity d on s.policeDistrictId = d.pdistrictId")

   
})
public class PoliceSubDivision implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="policeSubDivInc" , strategy="increment")
	@GeneratedValue(generator="policeSubDivInc")
	@Column(name="SPDO_ID")
	private Long spdoId;
	@Column(name="SPDO_NAME")
	private String spdoName;
	
	@Column(name="NOTIFICATION_NO")
	private String notifictionNo;
	@Column(name="NOTIFICATION_DATE")
	private LocalDate notificationDate;
	@Column(name="TYPE_OF_SUB_DIVISION_PS")
	private int typeOfSubdivisionPs;
	@Column(name="CREATED_BY")
	private int createdBy;
	@Column(name="CREATED_DATE")
	private Date createdDate;
	@Column(name="UPDATED_BY")
	private int updatedBy;
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	@Column(name="ACTIVE_STATUS")
	private String status;
	@Column(name="POLICE_DISTRICT")
	private Long policeDistrictId;
	@Column(name="POLICE_STATION")
	private Long policeStationId;
	public Long getPoliceDistrictId() {
		return policeDistrictId;
	}
	public void setPoliceDistrict(Long policeDistrictId) {
		this.policeDistrictId = policeDistrictId;
	}
	public Long getPoliceStationId() {
		return policeStationId;
	}
	public void setPoliceStationId(Long policeStationId) {
		this.policeStationId = policeStationId;
	}
	public Long getSpdoId() {
		return spdoId;
	}
	public void setSpdoId(Long spdoId) {
		this.spdoId = spdoId;
	}
	public String getSpdoName() {
		return spdoName;
	}
	public void setSpdoName(String spdoName) {
		this.spdoName = spdoName;
	}
	public String getNotifictionNo() {
		return notifictionNo;
	}
	public void setNotifictionNo(String notifictionNo) {
		this.notifictionNo = notifictionNo;
	}
	public LocalDate getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(LocalDate notificationDate) {
		this.notificationDate = notificationDate;
	}
	public int getTypeOfSubdivisionPs() {
		return typeOfSubdivisionPs;
	}
	public void setTypeOfSubdivisionPs(int typeOfSubdivisionPs) {
		this.typeOfSubdivisionPs = typeOfSubdivisionPs;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	

}
