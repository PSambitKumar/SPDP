package com.spdp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



/**
 * 
 * @author sanghamitra.beura
 *
 */

@Entity
@Table(name="TBL_POLICE_DIST_SPDO_MAPPING")
public class PoliceDistSdpoMappingEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue( generator = "increment" )	
	@GenericGenerator( name = "increment", strategy = "increment" )
	@Column(name= "POLICE_DIST_SPDO_ID")
	private Long pdistsdpoId;
	
	@Column(name= "POLICE_DIST_ID")
	private Long pdistrictId;
	
	@Column(name= "SPDO_ID")
	private Long sdpoId;
	
	@Column(name= "STATUS")
	private String status;
	

	public Long getPdistsdpoId() {
		return pdistsdpoId;
	}

	public void setPdistsdpoId(Long pdistsdpoId) {
		this.pdistsdpoId = pdistsdpoId;
	}

	

	public Long getPdistrictId() {
		return pdistrictId;
	}

	public void setPdistrictId(Long pdistrictId) {
		this.pdistrictId = pdistrictId;
	}

	public Long getSdpoId() {
		return sdpoId;
	}

	public void setSdpoId(Long sdpoId) {
		this.sdpoId = sdpoId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
}
