/**
 * 
 */
package com.spdp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author kamalakanta.g
 *
 */
@Entity
@Table(name="TBL_POLICE_STA_VILLAGE_MAPPING")
public class PoliceSTAVillagemapping implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="POLICE_STATION_VILLAGE_ID")
	private int ssVillageId;
	@Column(name="POLICE_STATION_ID")
	private int policeStationId;
	@Column(name="VILLAGE_ID")
	private int villageId;
	@Column(name="STATUS")
	private String status;
	
	
	
	
	

}
