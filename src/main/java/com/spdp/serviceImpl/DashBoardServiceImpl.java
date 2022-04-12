package com.spdp.serviceImpl;

import java.util.ArrayList;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;

import com.spdp.bean.UlbBean;
import com.spdp.bean.UrbanWardBean;
import com.spdp.service.DashBoardService;

@ApplicationScoped
public class DashBoardServiceImpl implements DashBoardService {
	
	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;


	@Override
	public Map<String, Long> getCountforDistrict() {
		
		Map<String, Long> responseMap = new HashMap<String,Long>();
	
		StoredProcedureQuery ref = this.entityManager.createStoredProcedureQuery("DASH_BOARD_COUNT")
				
				.registerStoredProcedureParameter("v_dist", Long.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("v_sub_division", Long.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("v_block", Long.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("v_gp", Long.class, ParameterMode.OUT)
				.registerStoredProcedureParameter("v_village", Long.class, ParameterMode.OUT)
		
		       .registerStoredProcedureParameter("v_tahasil", Long.class, ParameterMode.OUT)
		       .registerStoredProcedureParameter("v_ri_circle", Long.class, ParameterMode.OUT)
		       .registerStoredProcedureParameter("v_police_range", Long.class, ParameterMode.OUT)
		       .registerStoredProcedureParameter("v_police_district", Long.class, ParameterMode.OUT)
		       .registerStoredProcedureParameter("v_police_subdivision", Long.class, ParameterMode.OUT)
		       .registerStoredProcedureParameter("v_police_station", Long.class, ParameterMode.OUT)
		       
			   .registerStoredProcedureParameter("v_rdc", Long.class, ParameterMode.OUT)
			   .registerStoredProcedureParameter("v_ulbward", Long.class, ParameterMode.OUT)
			   .registerStoredProcedureParameter("v_ulb", Long.class, ParameterMode.OUT)

		       .registerStoredProcedureParameter("v_type1_ulb", Long.class, ParameterMode.OUT)
		       .registerStoredProcedureParameter("v_type2_ulb", Long.class, ParameterMode.OUT)
		       .registerStoredProcedureParameter("v_type3_ulb", Long.class, ParameterMode.OUT);
		
		
		 Long district =  (Long) ref.getOutputParameterValue("v_dist");
		 Long subdivision =  (Long) ref.getOutputParameterValue("v_sub_division");
		 
		 Long block =  (Long) ref.getOutputParameterValue("v_block");
		 Long gp =  (Long) ref.getOutputParameterValue("v_gp");
		 Long village =  (Long) ref.getOutputParameterValue("v_village");
		 
		 Long tahasil =  (Long) ref.getOutputParameterValue("v_tahasil");
		 Long ricircle =  (Long) ref.getOutputParameterValue("v_ri_circle");
		 Long prange =  (Long) ref.getOutputParameterValue("v_police_range");
		 Long pdistrict =  (Long) ref.getOutputParameterValue("v_police_district");
		 Long psubdivision =  (Long) ref.getOutputParameterValue("v_police_subdivision");
		 Long pstation =  (Long) ref.getOutputParameterValue("v_police_station");
		 Long rdc =  (Long) ref.getOutputParameterValue("v_rdc");
		 Long ulbward =  (Long) ref.getOutputParameterValue("v_ulbward");

		 Long ulb =  (Long) ref.getOutputParameterValue("v_ulb");
		 Long municipalities =  (Long) ref.getOutputParameterValue("v_type1_ulb");
		 Long mcorporation =  (Long) ref.getOutputParameterValue("v_type2_ulb");
		 Long nac =  (Long) ref.getOutputParameterValue("v_type3_ulb");

		 responseMap.put("dist", district);
		 responseMap.put("subDiv", subdivision);	 
		 responseMap.put("block", block);
		 responseMap.put("gp", gp);
		 responseMap.put("village", village);
		 
		 responseMap.put("tahasil", tahasil);
		 responseMap.put("ricircle", ricircle);
		 responseMap.put("prange", prange);
		 responseMap.put("pdistrict", pdistrict);
		 responseMap.put("psubdivision", psubdivision);
		 responseMap.put("pstation", pstation);
		 responseMap.put("rdc", rdc);
		 responseMap.put("ulbward", ulbward);

		 responseMap.put("ulb", ulb);
		 responseMap.put("municipalities", municipalities);
		 responseMap.put("mcorporation", mcorporation);
		 responseMap.put("nac", nac);

		 return responseMap;
	}
	
	@Override
	public List<UlbBean> getDashBoardUlbData(Long ulbTypeId) {
		List<UlbBean> objBean = new ArrayList<UlbBean>();
		UlbBean ulbBean=null;

		try {
			
			
			javax.persistence.Query q1 = entityManager
					.createQuery("select u.ulbId,u.ulbName,u.notificationNum,u.notificationDate,u.typeOfUlb,u.districtId,u.deleteStatus,t.ulbTypeName,d.districtName  from"
							+ " ULBEntity u inner join TypeOfUlbMst t on u.typeOfUlb = t.ulbTypeId inner join DistrictEntity d on u.districtId = d.districtId where u.typeOfUlb = :uTypeId ");
			q1.setParameter("uTypeId", ulbTypeId);
			List<Object[]> object = q1.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					ulbBean = new UlbBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					
					ulbBean.setUlbId((Long)obj1[0]);
					ulbBean.setUlbName((String)obj1[1]);
					ulbBean.setNotificationNum((String)obj1[2]);
					String nDateString= dateFormat.format((Date)obj1[3]);
					ulbBean.setNotificationDate(nDateString);
					ulbBean.setTypeOfUlb((Long)obj1[4]);
					ulbBean.setDistrictId((Long)obj1[5]);
					ulbBean.setStatus((String)obj1[6]);
					ulbBean.setUlbTypeName((String)obj1[7]);
					ulbBean.setDistName((String)obj1[8]);
					
					objBean.add(ulbBean);
					  
					
				}
			}


		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return objBean;
	}
	
	@Override
	public List<UrbanWardBean> getDashBoardUlbWardData() {
		List<UrbanWardBean> objBean = new ArrayList<UrbanWardBean>();
		UrbanWardBean ulbwardBean=null;

		try {
			
			
			javax.persistence.Query q1 = entityManager
					.createQuery("select u.urbanId,u.wardNumber,u.locality,u.activeStatus,d.districtName,l.ulbName  from "
							+ " UrbanWardEntity u inner join ULBEntity l on u.ulbId = l.ulbId inner join DistrictEntity d on u.districtId = d.districtId ");
			List<Object[]> object = q1.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					ulbwardBean = new UrbanWardBean();  
					
					ulbwardBean.setUrbanId((Long)obj1[0]);
					ulbwardBean.setWardNumber((String)obj1[1]);
					ulbwardBean.setLocality((String)obj1[2]);
					ulbwardBean.setActiveStatus((String)obj1[3]);
					ulbwardBean.setDistName((String)obj1[4]);
					ulbwardBean.setUlbName((String)obj1[5]);
					
					
					objBean.add(ulbwardBean);
					  
					
				}
			}

		} catch (Exception e) {
		
			e.printStackTrace();
		}

		return objBean;
	}
}