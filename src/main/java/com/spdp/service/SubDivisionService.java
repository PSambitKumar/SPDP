package com.spdp.service;
import java.text.ParseException;
import java.util.List;

import com.spdp.bean.BlockBean;

import com.spdp.bean.SubDivisionBean;
import com.spdp.bean.SubdivisionBlockBean;


/**
 * 
 * @author sanghamitra.beura
 *
 */

public interface SubDivisionService {
	
List<SubDivisionBean> getDetailsBySubDivisionId();
	
	List<BlockBean> getBlockDetails();
	
	void deleteByNotiNo(String notiNo);
	
	String saveSubDivisionDetails(SubDivisionBean subdivisionBean) throws ParseException ;
	
	List<SubdivisionBlockBean> getSubdivisionAndBlockDetails();
	
	//SubDivisionBean getSubDivisionetailsForUpdate(Long id);
	SubDivisionBean getSubdivisionDetailsForUpdate(Long id);

	void updateSubDivisionDetails(SubDivisionBean subdivisionObj);

	List<SubDivisionBean> getBlockDetaildByDistId(Long id);
	List<SubDivisionBean> getSubdivisionDetaildByDistId(Long id);

	List<SubDivisionBean> getSubDivisionDetailsFilteredByDistrict(Long did);

	List<SubDivisionBean> getSubDivisionDetailsFilteredBySubDivision(Long sid);

	List<SubDivisionBean> getSubDivisionDetailsFilteredByStatus(String status);

	List<SubDivisionBean> getSubDivisionDetailsFilteredByDate(String date);

	List<SubDivisionBean> getSubDivisionDetailsFilteredByDIdAndDate(Long dId, String date);

	List<SubDivisionBean> getSubDivisionDetailsFilteredBySIdAndDate(Long sId, String date);

	List<SubDivisionBean> getSubDivisionDetailsFilteredByStatusAndDate(String status, String date);

	List<SubDivisionBean> getSubDivisionDetailsFilteredByStatusAndDist(Long did, String status);

	List<SubDivisionBean> getSubDivisionDetailsFilteredByStatusAndSubdiv(Long sid, String status);

	List<SubDivisionBean> getSubDivisionDetailsFilteredByStatusAndSubdivAndDate(Long sid, String status, String date);
}
