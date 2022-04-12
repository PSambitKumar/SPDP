package com.spdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="TBL_GP_BLOCK_MAPPING")
public class GPMappingEntity {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="GP_BLOCK_MAPPING_ID")
	private Long gpBlock;
	
	@Column(name="BLOCK_ID")
	private Long blockId;
	
	
	@Column(name="GP_ID")
	private Long gpId ;


	public Long getGpBlock() {
		return gpBlock;
	}


	public void setGpBlock(Long gpBlock) {
		this.gpBlock = gpBlock;
	}


	public Long getBlockId() {
		return blockId;
	}


	public void setBlockId(Long blockId) {
		this.blockId = blockId;
	}


	public Long getGpId() {
		return gpId;
	}


	public void setGpId(Long gpId) {
		this.gpId = gpId;
	}
	
	
}
