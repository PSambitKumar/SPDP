package com.spdp.service;

import java.util.List;

import javax.ws.rs.core.Response;

import com.spdp.bean.DepartmentBean;
import com.spdp.bean.DistLGDBean;
import com.spdp.bean.DistrictBean;
import com.spdp.bean.EducationBean;
import com.spdp.bean.GpBean;
import com.spdp.bean.PoliceStationBean;
import com.spdp.bean.RICircleBean;
import com.spdp.bean.RdcBean;
import com.spdp.bean.RdcDistrictBean;
import com.spdp.bean.casteMasterBean;
import com.spdp.bean.dropdownBean;
import com.spdp.bean.finalBucketListBean;

/*
 * 
 * @Author - Akash Deep.
 * 
 * */


public interface MasterService {

	List<RdcBean> getDetailsByRdcId();
	
	List<DistrictBean> getDistrictDetails();
	
	void deleteByNotiNo(String notiNo);
	String saveRdcDetails(RdcBean rdcBean) ;
	List<RdcDistrictBean> getRdcAndDistrictDetails();
	RdcBean getRdcDetailsForUpdate(Long id);

	void updateRdcDetails(RdcBean rdcObj);
	String saveRICircleDetails(RICircleBean obj);
	List<DistrictBean> getRiCircleDropdownLists();
	List<dropdownBean> getOnClickLists(RICircleBean obj);
	List<RICircleBean> getRiCircleListView();
	void changeRIstatus(Long id);
	RICircleBean getListForRiEditById(Long id);
	void updateRiCircle(RICircleBean obj);
	List<Object> getGPandVillListsBucket(Long id);
	String savePoliceStation(PoliceStationBean obj);
	List<PoliceStationBean> getPoliceStationView();
	List<Object> getTypesOfPoliceStation();
	void changePsStatus(Long id);
	List<finalBucketListBean> getBucketVillageListById(Long id);
	void updatePoliceStation(PoliceStationBean obj);
	String saveGPMaster(GpBean obj);
	List<GpBean> getGPListView();
	void changeGPStatus(Long id);
	List<dropdownBean> getGpVillageById(Long id);
	void updateGPList(GpBean obj);
	List<dropdownBean> getCasteCategory();
	String saveCasteByCategory(casteMasterBean obj);
	List<casteMasterBean> getCasteMasterView();
	String saveEducationalInstitution(EducationBean obj);
	List<Object> getEducationDropdownList();
	List<EducationBean> getEducationView();
	void changeEducationViewStatus(Long id);
	void updateEducationForm(EducationBean obj);
	String saveDepartment(DepartmentBean obj);
	List<DepartmentBean> getDepartmentView();
	void changeDepartmentStatus(Long id);
	void updateDepartment(DepartmentBean obj);
	String saveDistLGDMapping(DistLGDBean obj);
	List<dropdownBean> getDepartmentList();
}
