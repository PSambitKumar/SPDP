package com.spdp.service;

import java.text.ParseException;
import java.util.List;

import com.spdp.bean.PoliceDistSdpoMappingBean;
import com.spdp.bean.PoliceDistrictBean;
import com.spdp.bean.PoliceDistrictCatagoryBean;


/**
 * 
 * @author sanghamitra.beura
 *
 */

public interface PoliceDistrictService {

	String savePoliceDistrictDetails(PoliceDistrictBean policedistrictObj) throws ParseException ;

	List<PoliceDistrictBean> getDetailsByPoliceDistrict();

	void deleteByNotiNo(String notiNo);

	List<PoliceDistrictCatagoryBean> getTypeOfPoliceDistDetails();

	List<PoliceDistSdpoMappingBean> getPoliceDistrictAndSdpoDetails();

	PoliceDistrictBean getPoliceDistrictDetailsForUpdate(Long id);

	void updatePoliceDistrictDetails(PoliceDistrictBean policedistrictObj);

	List<PoliceDistrictBean> getPoliceDistDetailsByTypeId(Long id);

	List<PoliceDistrictBean> getSdpoByDistrictId(Long id);

	List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredByPDistrict(Long pdid);

	List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredByStatus(String status);

	List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredByDate(String date);

	List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredPdIdAndStatus(Long pdid, String status);

	List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredPDateAndStatus(String date, String status);

	List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredPDateAndStatusAndPdId(String date, String status,Long pdId);
			

	



}
