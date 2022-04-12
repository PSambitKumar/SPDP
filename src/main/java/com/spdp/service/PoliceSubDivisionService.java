package com.spdp.service;

import java.text.ParseException;
import java.util.List;
import com.spdp.bean.PoliceDistrictBean;
import com.spdp.bean.PoliceStationBean;
import com.spdp.bean.policeSubDivisionBean;
/**
 * @author kamalakanta.g
 *
 */
public interface PoliceSubDivisionService {

	List<PoliceStationBean> getPoliceStationDetails();
	List<PoliceDistrictBean> getPoliceDistrict();
	String savePolicesubdivision(policeSubDivisionBean policesubdivisionObj)throws ParseException;
	///List<policeSubDivisionBean> getPoliceSubdivisionView();
	//void SubDivStatusByNotiNo(String notificationNo);
	String updatePoliceSubdivision(policeSubDivisionBean policeSubDivisionBeanobj);
	policeSubDivisionBean getPoliceSubDivisionForUpdate(Long id);

	//List<PoliceStationBean> getPoliceStationDetails();
	//String saveSubDivisionDetails(policeSubDivisionBean policeSubDivBean) ;
	//List<PoliceDistrictBean> getPoliceDistrict();
	//String savePolicesubdivision(policeSubDivisionBean policesubdivisionObj)throws ParseException;
	List<policeSubDivisionBean> getPoliceSubdivisionView();
	void SubDivStatusByNotiNo(String notificationNo);
	//String updatePoliceSubdivision(policeSubDivisionBean policeSubDivisionBeanobj);
	//policeSubDivisionBean getPoliceSubDivisionForUpdate(Long id);

	

}
