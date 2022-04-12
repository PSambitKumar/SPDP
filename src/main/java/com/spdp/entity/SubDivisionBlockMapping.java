package com.spdp.entity;

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
@Table(name = "TBL_SUBDIVISION_BLOCK_MAPPING")
public class SubDivisionBlockMapping {
	@Id
	
    @GeneratedValue( generator = "increment" )
	
	@GenericGenerator( name = "increment", strategy = "increment" )  
	@Column(name = "BLOCK_MAPPING_ID")
	private Long blockSubdivionId;

	@Column(name = "BLOCK_ID")
	private Long blockId;

	@Column(name = "SUB_DIVISION_ID")
	private Long subdivisionId;

	public Long getBlockSubdivionId() {
		return blockSubdivionId;
	}

	public void setBlockSubdivionId(Long blockSubdivionId) {
		this.blockSubdivionId = blockSubdivionId;
	}

	public Long getBlockId() {
		return blockId;
	}

	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}

	public Long getSubdivisionId() {
		return subdivisionId;
	}

	public void setSubdivisionId(Long subdivisionId) {
		this.subdivisionId = subdivisionId;
	}

}
