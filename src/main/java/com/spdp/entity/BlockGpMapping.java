package com.spdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
/**
 * 
 * @author jyotiranjan.behera
 *
 */
@Entity
@Table(name="TBL_GP_BLOCK_MAPPING")
public class BlockGpMapping {
	
	@Id
	@GenericGenerator(name="blockGpMapInc" , strategy="increment")
	@GeneratedValue(generator="blockGpMapInc")	
	@Column(name="GP_BLOCK_MAPPING_ID")
	private Long gpBlockMappingId;
	
	@Column(name="BLOCK_ID")
	private Long blockId;
	
	@Column(name="GP_ID")
	private Long gpId;

	public Long getGpBlockMappingId() {
		return gpBlockMappingId;
	}

	public void setGpBlockMappingId(Long gpBlockMappingId) {
		this.gpBlockMappingId = gpBlockMappingId;
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
