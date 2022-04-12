package com.spdp.service;

import java.util.Date;
import java.util.List;

import com.spdp.bean.PRangePDistrictBean;
import com.spdp.bean.PoliceRangeBean;

/**
 * 
 * @author jyotiranjan.behera
 *
 */
public interface PoliceRangeService {
	List<PoliceRangeBean> getPoliceRangeDetailsById();

	void deletePoliceRangeByNotiNo(String notificationNo);

	List<PRangePDistrictBean> getPRangeAndPDistDetails();

	PoliceRangeBean getPoliceRangeDetailsForUpdate(Long id);

	String savePoliceRangeDetails(PoliceRangeBean policeRBean);
	
	void updatePoliceRangeDetails(PoliceRangeBean policeRBean);

	List<PoliceRangeBean> getPoliceRangeDetailsFilteredByPoliceRange(Long pid);

	List<PoliceRangeBean> getPoliceRangeDetailsFilteredByStatus(String status);

	List<PoliceRangeBean> getPoliceRangeDetailsFilteredByDate(String date);

	List<PoliceRangeBean> getPoliceRangeDetailsFilteredByPrIdAndStatus(Long prid, String status);

	List<PoliceRangeBean> getPoliceRangeDetailsFilteredByDateAndStatus(String date, String status);

	List<PoliceRangeBean> getPoliceRangeDetailsFilteredByAllData(Long prid, String status, String date);

}
