package com.spdp.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TBL_INST_COURSEOFFERED")
public class EducationCourseEntity {
	
	@Id
	@GenericGenerator(name="eduCourseInc" , strategy="increment")
	@GeneratedValue(generator="eduCourseInc")
	@Column(name= "INSTCOURSEID")
	private Long eduCourseId;
	
	@Column(name= "INSTID")
	private Long eduId;
	
	@Column(name= "COURSEID")
	private Long courseId;
	
	@Column(name= "CREATEDBY")
	private Long createdBy;
	
	@Column(name= "CREATEDON")
	private Date createdOn;
	
	@Column(name= "UPDATEDON")
	private Date updatedOn;
	
	@Column(name= "UPDATEDBY")
	private Long updatedBy;
	
	@Column(name= "ACTIVE_STATUS")
	private String activeStatus;

	public Long getEduCourseId() {
		return eduCourseId;
	}

	public void setEduCourseId(Long eduCourseId) {
		this.eduCourseId = eduCourseId;
	}

	public Long getEduId() {
		return eduId;
	}

	public void setEduId(Long eduId) {
		this.eduId = eduId;
	}

	public Long getCourseId() {
		return courseId;
	}

	public void setCourseId(Long courseId) {
		this.courseId = courseId;
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

	public Date getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	public Long getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getActiveStatus() {
		return activeStatus;
	}

	public void setActiveStatus(String activeStatus) {
		this.activeStatus = activeStatus;
	}
	
	

}
