package com.spdp.service;

import java.text.ParseException;
import java.util.List;

import com.spdp.bean.DistUlbBean;
import com.spdp.bean.UlbBean;

public interface ULBMasterService {
  
	String saveUlbData(UlbBean ulbBean) throws ParseException;
	
	List<UlbBean> getAllUlbMasterData();
	
	Boolean deleteUlbMasterById(String notificationNumber);
	
	List<DistUlbBean> getAllDistAndUlbName();
	
	UlbBean getUlbMasterDataById(Long ulbId);
	
	String updateUlbData(UlbBean ulbBean) throws ParseException;
	
}
