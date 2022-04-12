package com.spdp.service;

import java.util.List;

import com.spdp.bean.DeprtBean;
import com.spdp.bean.SchemeBean;

public interface SchemeService {

	String saveSchemeMasterData(SchemeBean schemeBean);

	List<SchemeBean> getAllSchemesDatas();

	List<DeprtBean> getDepartment();

	void deleteSchemeMasterById(Long schemeId);

	SchemeBean getSchemeDetailsById(Long schemeId);

	void updateSchemeData(SchemeBean schemeBean);

}
