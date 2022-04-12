package com.spdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TBL_TYPE_OF_ULB")
public class TypeOfUlbMst {
	@GenericGenerator(name="ulbTypeInc" , strategy="increment")
	@GeneratedValue(generator="ulbTypeInc")	
	@Id
	@Column(name="ID")
	private Long ulbTypeId;
	
	@Column(name="TYPE_OF_ULB")
	private String ulbTypeName;

	public Long getUlbTypeId() {
		return ulbTypeId;
	}

	public void setUlbTypeId(Long ulbTypeId) {
		this.ulbTypeId = ulbTypeId;
	}

	public String getUlbTypeName() {
		return ulbTypeName;
	}

	public void setUlbTypeName(String ulbTypeName) {
		this.ulbTypeName = ulbTypeName;
	}
	
	

}
