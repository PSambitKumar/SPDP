package com.spdp.service;

import java.util.List;

import javax.ws.rs.PathParam;

import com.spdp.bean.UlbBean;
import com.spdp.bean.UrbanWardBean;

public interface UrbanWardService {

	List<UlbBean> getUlbDetailsByDistrictId(@PathParam(value = "districtId") Long districtId);

	String saveUrbanMasterData(UrbanWardBean urbanWardBean);
	
	List<UrbanWardBean> getAllUrbanDatas();
	
	void deleteUrbanMasterById(@PathParam(value = "urbanId") Long urbanId);
	
	UrbanWardBean getUrbanDetailsById(@PathParam(value = "urbanId") Long urbanId); 
	
	void updateUrbanData(UrbanWardBean urbanWardBean);
}
