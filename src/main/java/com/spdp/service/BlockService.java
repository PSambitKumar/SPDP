package com.spdp.service;

import java.util.List;
import com.spdp.bean.BlockBean;
import com.spdp.bean.BlockGpBean;
import com.spdp.bean.GpBean;
import com.spdp.bean.SubDivisionBean;

/**
 * 
 * @author jyotiranjan.behera
 *
 */
public interface BlockService {
	
	List<BlockBean> getDetailsByBlockId();//checked
	List<GpBean> getGpDetails();
	List<SubDivisionBean> getSubDivisionDetails();
	
	void deleteBlockByNotiNo(String notiNo);
	String saveBlockDetails(BlockBean blockBean) ;
	List<BlockGpBean> getBlockAndGpDetails();
	BlockBean getBlockDetailsForUpdate(Long id);
	void updateBlockDetails(BlockBean blockObj);
	
	BlockBean getDetailsForView(Long id);
	
	List<BlockBean> getSubDivDetailsByDistId(Long districtId);
	List<BlockBean> getGpDetailsBySubDivId(Long id);
	List<BlockBean> getBlockDetailsBySubDivId(Long id);
	List<BlockBean> getBlockDetailsAfterFiltered(Long bid); 
	List<BlockBean> blockDetailsFilterForDist(Long did);
	List<BlockBean> blockDetailsFilterForSubDivision(Long sid);
	List<BlockBean> blockDetailsFilterByAllData(Long bid, String date);
	List<BlockBean> blockDetailsFilterByDate(String date);
	List<BlockBean> blockDetailsFilterByDistAndDate(Long did, String date);
	List<BlockBean> blockDetailsFilterBySubDivAndDate(Long sid, String date);

}
