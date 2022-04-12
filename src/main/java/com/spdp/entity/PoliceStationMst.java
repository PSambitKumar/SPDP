package com.spdp.entity;

import java.io.Serializable;
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
@Table(name="TBL_POLICE_STATION_MASTER")
@NamedQueries({
    @NamedQuery(name = "policeStationDetails",
    query = " from  PoliceStationMst")
   
})
public class PoliceStationMst implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name="policeStationInc" , strategy="increment")
	@GeneratedValue(generator="policeStationInc")
	@Column(name="POLICE_STATION_ID")
	private Long policeStationId;
	@Column(name="POLICE_STATION_NAME")
	private String policeStationName;
	@Column(name="POLICE_STATION_CATEGORY")
	private int policeStationCategory;
	@Column(name="NOTIFICATION_NO")
	private String notificationNo;
	@Column(name="NOTIFICATION_DATE")
	private Date notificationDate;
	@Column(name="TYPE_OF_POLICE_STATION")
	private int typeOfpoliceStation;
	@Column(name="CREATED_DATE")
	private Date createdDate;
	@Column(name="CREATED_BY")
	private int createdBy;
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	@Column(name="UPDATED_BY")
	private int updatedBy;
	@Column(name="ACTIVE_STATUS")
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
	public int getPoliceStationCategory() {
		return policeStationCategory;
	}
	public void setPoliceStationCategory(int policeStationCategory) {
		this.policeStationCategory = policeStationCategory;
	}
	public String getNotificationNo() {
		return notificationNo;
	}
	public void setNotificationNo(String notificationNo) {
		this.notificationNo = notificationNo;
	}
	public Date getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}
	public int getTypeOfpoliceStation() {
		return typeOfpoliceStation;
	}
	public void setTypeOfpoliceStation(int typeOfpoliceStation) {
		this.typeOfpoliceStation = typeOfpoliceStation;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	

}
