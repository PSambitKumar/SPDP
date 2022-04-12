/**
 * 
 */
package com.spdp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author kamalakanta.g
 *
 */
@Entity
@Table(name="TBL_SPDO_POLICE_STA_MAPPING")
public class PoliceSPDO_STAMapping implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( generator = "increment" )
	@GenericGenerator( name = "increment", strategy = "increment" ) 
	@Column(name="SPDO_POLICE_STA_ID")
	private Long spdoPoliceStaId;
	@Column(name="SPDO_ID")
	private Long spdoId;
	@Column(name="POLICE_STATION_ID")
	private Long policeStationId;
	@Column(name="STATUS")
	private String status;
	public Long getSpdoPoliceStaId() {
		return spdoPoliceStaId;
	}
	public void setSpdoPoliceStaId(Long spdoPoliceStaId) {
		this.spdoPoliceStaId = spdoPoliceStaId;
	}
	public Long getSpdoId() {
		return spdoId;
	}
	public void setSpdoId(Long spdoId) {
		this.spdoId = spdoId;
	}
	public Long getPoliceStationId() {
		return policeStationId;
	}
	public void setPoliceStationId(Long policeStationId) {
		this.policeStationId = policeStationId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
