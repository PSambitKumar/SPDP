package com.spdp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 
 * @Author : Akash Deep
 * 
 **/


@Entity
@Table(name="TBL_RICIRCLE_TEST")
@NamedQueries({
    @NamedQuery(name = "RICircleDetails",
    query = " from  RICircleEntity"),
    @NamedQuery(name = "getLevelDetailId",
    query = " from  RICircleEntity r where r.levelName=:levelName")
   
})
public class RICircleEntity {
	
	@Id
	@GenericGenerator(name="riCircle" , strategy="increment")
	@GeneratedValue(generator="riCircle")	
	@Column(name="LEVEL_DETAIL_ID")
	private Long levelDetailID;
	
	@Column(name="LEVEL_NAME")
	private String levelName;
	
	@Column(name="LEVEL_ID")
	private Long levelID;
	
	@Column(name="PARENT_ID")
	private Long parentID;

	public Long getLevelDetailID() {
		return levelDetailID;
	}

	public void setLevelDetailID(Long levelDetailID) {
		this.levelDetailID = levelDetailID;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public Long getLevelID() {
		return levelID;
	}

	public void setLevelID(Long levelID) {
		this.levelID = levelID;
	}

	public Long getParentID() {
		return parentID;
	}

	public void setParentID(Long parentID) {
		this.parentID = parentID;
	}
	
	

}
