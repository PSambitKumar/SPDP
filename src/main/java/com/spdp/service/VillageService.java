package com.spdp.service;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.PathParam;

import com.spdp.bean.BlockBean;
import com.spdp.bean.GpBean;
import com.spdp.bean.RICircleBeans;
import com.spdp.bean.SubDivisionBean;
import com.spdp.bean.TehsilBean;
import com.spdp.bean.VillageBean;

public interface VillageService {

	String saveVillageData(VillageBean villageBean) throws ParseException;

	List<SubDivisionBean> getAllSubDivisionDetails(Long districtId);

	List<BlockBean> getBlockDetailsBySubDivisionId(Long subdivisionId);

	List<VillageBean> getAllVillageData();

	List<TehsilBean> getTehsilBySubDivisionById(Long subdivisionId);

	Boolean deleteVillageMasterById(Long villageId);

	void updateVillageMasterData(VillageBean villageBean) throws ParseException;

	List<GpBean> getGpByBlockId(Long blockId);

	List<RICircleBeans> getRICircleByTehsilId(@PathParam(value = "tehsil") Long tehsil);

	VillageBean getVillageMasterDataById(@PathParam(value = "villageId") Long villageId);

}
