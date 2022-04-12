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
@Table(name = "TBL_URBAN_WARD_MASTER")

@NamedQueries({
    
    
    @NamedQuery(name = "urbanDetails",
    query = "from  UrbanWardEntity"),
    @NamedQuery(name = "deleteByUrbanId",
    query = "from UrbanWardEntity where urbanId=:urbanId"),
    @NamedQuery(name = "updateByUrbanId",
    query = "from  UrbanWardEntity where urbanId=:urbanId"),
    
    @NamedQuery(name = "getUrbanDetails",
    query = "select uwm.urbanId,uwm.wardNumber,uwm.locality, dm.districtName ,um.ulbName,uwm.districtId, uwm.activeStatus from UrbanWardEntity uwm left join District dm on uwm.districtId=dm.districtId left join  ULBEntity um on uwm.ulbId=um.ulbId"),
 
 
})
public class UrbanWardEntity {

	@GenericGenerator(name = "urbanInc", strategy = "increment")
	@GeneratedValue(generator = "urbanInc")
	@Id
	@Column(name = "URBAN_WARD_ID")
	private Long urbanId;

	@Column(name = "WORD_NO")
	private String wardNumber;

	@Column(name = "LOCALITY")
	private String locality;

	@Column(name = "DISTRICT_ID")
	private Long districtId;

	@Column(name = "ULB_ID")
	private Long ulbId;

	@Column(name = "CREATED_BY")
	private Long createdBy;

	@Column(name = "CREATED_ON")
	private Date createdOn;

	@Column(name = "UPDATED_BY")
	private Long updatedBy;

	@Column(name = "UPDATED_ON")
	private Long updatedOn;

	@Column(name = "ACTIVE_STATUS")
	private String activeStatus;

	public Long getUrbanId() {
		return urbanId;
	}

	public void setUrbanId(Long urbanId) {
		this.urbanId = urbanId;
	}

	public String getWardNumber() {
		return wardNumber;
	}

	public void setWardNumber(String wardNumber) {
		this.wardNumber = wardNumber;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getUlbId() {
		return ulbId;
	}

	public void setUlbId(Long ulbId) {
		this.ulbId = ulbId;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Long getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Long updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	@Override
	public String toString() {
		return "UrbanWardEntity [urbanId=" + urbanId + ", wardNumber=" + wardNumber + ", locality=" + locality
				+ ", districtId=" + districtId + ", ulbId=" + ulbId + ", createdBy=" + createdBy + ", createdOn="
				+ createdOn + ", updatedBy=" + updatedBy + ", updatedOn=" + updatedOn + ", activeStatus=" + activeStatus
				+ "]";
	}

}
