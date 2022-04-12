package com.spdp.entity;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@NamedQueries({ @NamedQuery(name = "schemeDetails", query = "from  SchemeEntity"),

		@NamedQuery(name = "deleteByschemeId", query = "from SchemeEntity where schemeId=:schemeId")

})
@Entity
@Table(name = "TBL_MST_SCHEME_MASTER")
public class SchemeEntity {

	@GenericGenerator(name = "schemeInc", strategy = "increment")
	@GeneratedValue(generator = "schemeInc")
	@Id
	@Column(name = "SCHEMEID")
	private Long schemeId;

	@Column(name = "DEPTID")
	private Long deptId;

	@Column(name = "SCHEMENAME")
	private String schemeName;

	@Column(name = "CREATEDBY")
	private Long createdBy;

	@Column(name = "CREATEDON")
	private Date createdOn;

	@Column(name = "UPDATEDBY")
	private Long updatedBy;

	@Column(name = "UPDATEDON")
	private Timestamp updatedOn;

	@Column(name = "IS_ACTIVE_STATUS")
	private String activeStatus;

	@Column(name = "DESCRIPTION")
	private String description;

	public Long getSchemeId() {
		return schemeId;
	}

	public void setSchemeId(Long schemeId) {
		this.schemeId = schemeId;
	}

	public Long getDeptId() {
		return deptId;
	}

	public void setDeptId(Long deptId) {
		this.deptId = deptId;
	}

	public String getSchemeName() {
		return schemeName;
	}

	public void setSchemeName(String schemeName) {
		this.schemeName = schemeName;
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

	public Timestamp getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Timestamp updatedOn) {
		this.updatedOn = updatedOn;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
