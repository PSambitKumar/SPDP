package com.spdp.service;

import java.text.ParseException;
import java.util.List;

import javax.ws.rs.PathParam;

import com.spdp.bean.RICircleBeans;
import com.spdp.bean.TehsilMasterBean;

public interface TehsilService {

	String saveTehsilDetails(TehsilMasterBean tehsilMasterBean) throws ParseException;
	
	List <TehsilMasterBean> getAllTehsilData();
	
	void deleteTehsilMasterById(Long tehsiltId);
	
	TehsilMasterBean getTehsilDetailsById(@PathParam(value = "tehsiltId") Long tehsiltId);
	
	void updateTehsilMasterData(TehsilMasterBean tehsilMasterBean) throws ParseException;
	
	List<RICircleBeans> getRICircleByTehsilId();
}
