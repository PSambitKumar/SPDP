package com.spdp.service;

import java.util.List;

import com.spdp.bean.DistBean;
import com.spdp.bean.DistSubdivisionBean;
//import com.spdp.bean.SubdivisionBean;
import com.spdp.bean.SubdivisiondropdownBean;
import com.spdp.entity.SubDivisionEntity;

public interface DistrictMasterService {

    List<DistBean> getDetailsByDistrictId();
	
	//List<DistrictBean> getDistrictDetails();
	List<SubDivisionEntity> getSubdivisionDetails();
	
	void deleteDistByNotiNo(String notiNo);
	String saveDistrictDetails(DistBean rdcBean) ;
	List<DistSubdivisionBean> getDistrictAndSubdivisionDetails();
	DistBean getDistrictDetailsForUpdate(Long id);

	void updateDistrictDetails(DistBean rdcObj);

	
}