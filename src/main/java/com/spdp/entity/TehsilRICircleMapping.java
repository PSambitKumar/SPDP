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

@Entity
@Table(name="TBL_TEHSIL_RICIRCLE_MAPPING")

@NamedQueries({ 
	 @NamedQuery(name = "getBytehsiltId",
	    query = "from  TehsilRICircleMapping where tehsilRICircle=:tehsilRICircle"),
	 @NamedQuery(name = "BytehsiltId",
	    query = "from  TehsilRICircleMapping where tehsilRICircle=:tehsilRICircle")


})
public class TehsilRICircleMapping {

	@GenericGenerator(name = "tehsilIn", strategy = "increment")
	@GeneratedValue(generator = "tehsilIn")
	@Id
	@Column(name="TEHSIL_RICIRCLE_ID")
	private Long tehsilRicircleId;
	
	@Column(name="TEHSIL_ID")
	private Long tehsilId;
	
	
	@Column(name="RICIRCLE_ID")
	private Long ricircleId ;
	
	@Column(name="STATUS")
	private String status;
	
	@Column(name="TEHSIL_PRIMARY_ID")
	private Long tehsilRICircle;

	public Long getTehsilRicircleId() {
		return tehsilRicircleId;
	}

	public void setTehsilRicircleId(Long tehsilRicircleId) {
		this.tehsilRicircleId = tehsilRicircleId;
	}

	public Long getTehsilId() {
		return tehsilId;
	}

	public void setTehsilId(Long tehsilId) {
		this.tehsilId = tehsilId;
	}

	public Long getRicircleId() {
		return ricircleId;
	}

	public void setRicircleId(Long ricircleId) {
		this.ricircleId = ricircleId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Long getTehsilRICircle() {
		return tehsilRICircle;
	}

	public void setTehsilRICircle(Long tehsilRICircle) {
		this.tehsilRICircle = tehsilRICircle;
	}

	@Override
	public String toString() {
		return "TehsilRICircleMapping [tehsilRicircleId=" + tehsilRicircleId + ", tehsilId=" + tehsilId
				+ ", ricircleId=" + ricircleId + ", status=" + status + ", tehsilRICircle=" + tehsilRICircle + "]";
	}
	
	
	
}
