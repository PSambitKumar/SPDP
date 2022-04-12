package com.spdp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;


@Entity
@Table(name="TBL_ULB_MASTER")
@NamedQueries({
    @NamedQuery(name = "ublDetails", query = "from  ULBEntity"),
    @NamedQuery(name = "deleteByNotiNumber",
    query = "from ULBEntity where notificationNum=:notificationNumber"),
    @NamedQuery(name = "updateByulbId",
    query = "from  ULBEntity where ulbId=:ulbId"),
    
    @NamedQuery(name = "getulbName",
    query = "from  ULBEntity where districtId=:districtId")
   /* @NamedQuery(name = "BylbId",
    query = "from  ULBEntity where ulbId=:ulbId"),*/
   
})
public class ULBEntity {

	
	@GenericGenerator(name="ulbInc" , strategy="increment")
	@GeneratedValue(generator="ulbInc")	
	@Id
	@Column(name="ULB_ID")
	private Long ulbId;
	
	@Column(name="ULB_NAME")
	private String ulbName;
	
	
	@Column(name="NOTIFICATION_NO")
	private String notificationNum;
	
	@Column(name="NOTIFICATION_DATE")
	private Date notificationDate;
	
	@Column(name="TYPE_OF_ULB")
	private Long typeOfUlb;
	
	//@OneToOne(fetch = FetchType.LAZY)
   // @JoinColumn(name = "DISTRICT_ID")
	@Column(name="DISTRICT_ID")
	private Long districtId;
	
	@Column(name="CREATED_BY")
	private String createdBy;
	
	@Column(name="CREATED_DATE")
	private Date createdDate;
	
	@Column(name="UPDATED_BY")
	private String updatedBy;
	
	@Column(name="UPDATED_DATE")
	private Date updatedDate;
	
	@Column(name="ACTIVE_STATUS")
	private String deleteStatus;

	public Long getUlbId() {
		return ulbId;
	}

	public void setUlbId(Long ulbId) {
		this.ulbId = ulbId;
	}

	public String getUlbName() {
		return ulbName;
	}

	public void setUlbName(String ulbName) {
		this.ulbName = ulbName;
	}

	public String getNotificationNum() {
		return notificationNum;
	}

	public void setNotificationNum(String notificationNum) {
		this.notificationNum = notificationNum;
	}

	public Date getNotificationDate() {
		return notificationDate;
	}

	public void setNotificationDate(Date notificationDate) {
		this.notificationDate = notificationDate;
	}

	public Long getTypeOfUlb() {
		return typeOfUlb;
	}

	public void setTypeOfUlb(Long typeOfUlb) {
		this.typeOfUlb = typeOfUlb;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDeleteStatus() {
		return deleteStatus;
	}

	public void setDeleteStatus(String deleteStatus) {
		this.deleteStatus = deleteStatus;
	}

	@Override
	public String toString() {
		return "ULBEntity [ulbId=" + ulbId + ", ulbName=" + ulbName + ", notificationNum=" + notificationNum
				+ ", notificationDate=" + notificationDate + ", typeOfUlb=" + typeOfUlb + ", districtId=" + districtId
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy
				+ ", updatedDate=" + updatedDate + ", deleteStatus=" + deleteStatus + "]";
	}
	
}
