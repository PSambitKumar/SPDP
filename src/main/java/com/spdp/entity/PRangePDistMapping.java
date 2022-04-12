package com.spdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @author jyotiranjan.behera
 *
 */
@Entity
@Table(name="TBL_POLICE_RANGE_PDIST_MAPPING")
public class PRangePDistMapping {
	@Id
	@GenericGenerator(name="pRangePDistMapInc" , strategy="increment")
	@GeneratedValue(generator="pRangePDistMapInc")
	@Column(name="POLICE_RANGE_PDIST_ID")
	private Long pRangePDistId;
	
	@Column(name="POLICE_RANGE_ID")
	private Long pRangeId;
	
	
	@Column(name="POLICE_DISTRICT_ID")
	private Long pDistId ;
	
	@Column(name="STATUS")
	private String status ;

	public Long getpRangePDistId() {
		return pRangePDistId;
	}

	public void setpRangePDistId(Long pRangePDistId) {
		this.pRangePDistId = pRangePDistId;
	}

	public Long getpRangeId() {
		return pRangeId;
	}

	public void setpRangeId(Long pRangeId) {
		this.pRangeId = pRangeId;
	}

	public Long getpDistId() {
		return pDistId;
	}

	public void setpDistId(Long pDistId) {
		this.pDistId = pDistId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}



}
