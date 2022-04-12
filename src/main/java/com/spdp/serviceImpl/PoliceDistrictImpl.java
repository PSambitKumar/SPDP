package com.spdp.serviceImpl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import com.spdp.bean.BlockBean;
import com.spdp.bean.PoliceDistSdpoMappingBean;
import com.spdp.bean.PoliceDistrictBean;
import com.spdp.bean.PoliceDistrictCatagoryBean;
import com.spdp.bean.policeSubDivisionBean;
import com.spdp.entity.PoliceDistSdpoMappingEntity;
import com.spdp.entity.PoliceDistrictCatagoryEntity;
import com.spdp.entity.PoliceDistrictEntity;
import com.spdp.entity.PoliceSubDivision;
import com.spdp.service.PoliceDistrictService;


/**
 * 
 * @author sanghamitra.beura
 *
 */

@ApplicationScoped
public class PoliceDistrictImpl implements PoliceDistrictService {
	

	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;

	
	@Override
	public List<PoliceDistrictBean> getDetailsByPoliceDistrict() {
		List<PoliceDistrictBean> objBean = new ArrayList<PoliceDistrictBean>();
		PoliceDistrictBean pdistBean = null;
		PoliceDistrictBean policedistrictObj = new PoliceDistrictBean();
		
//		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
//		String nDateString= dateFormat.format(policedistrictObj.getNotiDate());

		try {
			List<Object[]> object = entityManager.createNamedQuery("PoliceDistrictEntityDetails", Object[].class)
					.getResultList();

			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					pdistBean = new PoliceDistrictBean();
					
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String nDateString= dateFormat.format((Date)obj1[2]);
					
					pdistBean.setPdistrictId((Long) obj1[0]);
					pdistBean.setPdistrictName((String) obj1[1]);
					pdistBean.setNotificationDate(nDateString);
					//pdistBean.setNotificationDate(nDateString);
					pdistBean.setNotiNo((String) obj1[3]);
					pdistBean.setStatus((String) obj1[4]);
					pdistBean.setDistrictName((String) obj1[5]);
					// subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(pdistBean);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return objBean;
	}
	
	
	@SuppressWarnings("deprecation")
	@Transactional
	@Override
	public String savePoliceDistrictDetails(PoliceDistrictBean policedistrictObj) {
		System.out.println("PoliceDistrict ID --"+ policedistrictObj.getPdistrictName());

		// PoliceDistrictEntity entObj = new PoliceDistrictEntity();
		String response = "";
		String validationMsg = "";
//		StoredProcedureQuery pdistrictobj = this.entityManager.createStoredProcedureQuery("sp_insert_police_district")
//				.registerStoredProcedureParameter("flag", String.class, ParameterMode.IN)
//				// .registerStoredProcedureParameter("S_PDISTRICT_ID", Long.class,
//				// ParameterMode.IN)
//				.registerStoredProcedureParameter("S_PDISTRICT_NAME", String.class, ParameterMode.IN)
//				.registerStoredProcedureParameter("S_NOTIFICATION_NO", String.class, ParameterMode.IN)
//				.registerStoredProcedureParameter("S_NOTIFICATION_DATE", String.class, ParameterMode.IN)
//				.registerStoredProcedureParameter("S_PDISTRICT_TYPE", Long.class, ParameterMode.IN)
//				.registerStoredProcedureParameter("S_DISTRICT_ID", Long.class, ParameterMode.IN)
//				.registerStoredProcedureParameter("S_SDPO_NAME", String.class, ParameterMode.IN)
//				.registerStoredProcedureParameter("S_CREATED_BY", String.class, ParameterMode.IN)
//				.registerStoredProcedureParameter("S_CREATED_DATE", String.class, ParameterMode.IN)
//				.registerStoredProcedureParameter("S_STATUS", String.class, ParameterMode.IN)
//				.registerStoredProcedureParameter("S_SDPO_ID", Long.class, ParameterMode.IN);
//
//		pdistrictobj.setParameter("flag", "INSERT")
//				// .setParameter("S_PDISTRICT_ID", policedistrictObj.getPdistrictId())
//				.setParameter("S_PDISTRICT_NAME", policedistrictObj.getPdistrictName())
//				.setParameter("S_NOTIFICATION_NO", policedistrictObj.getNotiNo())
//				.setParameter("S_NOTIFICATION_DATE", "null")// policedistrictObj.getNotificationDate())
//				.setParameter("S_PDISTRICT_TYPE", policedistrictObj.getPdistrictType())
//				.setParameter("S_DISTRICT_ID", policedistrictObj.getDistrictId())
//				.setParameter("S_SDPO_NAME", policedistrictObj.getSdpoName())
//				.setParameter("S_CREATED_BY", policedistrictObj.getCreatedBy())
//				.setParameter("S_CREATED_DATE", "null") // policedistrictObj.getStringCreatedDate())
//				.setParameter("S_STATUS", policedistrictObj.getStatus())
//				.setParameter("S_SDPO_ID", policedistrictObj.getSdpoId());
//		pdistrictobj.execute();
//		System.out.println("inserted sucessfully");
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
		String nDateString= dateFormat.format(policedistrictObj.getNotiDate());
		
		
		
		try {
			if (policedistrictObj.getPdistrictName() == null) {
				validationMsg = "PoliceDistrict Name Can Not Be Null";
				return validationMsg;
			}
			if (policedistrictObj.getNotiNo() == null) {
				validationMsg = "Notification Number Can Not Be Null";
				return validationMsg;
			}
			if (policedistrictObj.getNotiDate() == null) {
				validationMsg = "Notification Date Can Not Be Null";
				return validationMsg;
			}
			if (policedistrictObj.getSdpoObj().size() == 0) {
				validationMsg = "Sdpo Lists Can Not Be Null";
				return validationMsg;
			}
		
		StoredProcedureQuery pdistrictobj = this.entityManager.createStoredProcedureQuery("sp_save_police_district")
				
				// .registerStoredProcedureParameter("S_PDISTRICT_ID", Long.class,
				// ParameterMode.IN)
				.registerStoredProcedureParameter("S_PDISTRICT_NAME", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("S_NOTIFICATION_NO", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("S_NOTIFICATION_DATE", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("S_PDISTRICT_TYPE", Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter("S_DISTRICT_ID", Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter("S_CREATED_BY", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("S_UPDATED_BY", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("S_STATUS", String.class, ParameterMode.IN)
				
		
				// .setParameter("S_PDISTRICT_ID", policedistrictObj.getPdistrictId())
				.setParameter("S_PDISTRICT_NAME", policedistrictObj.getPdistrictName())
				.setParameter("S_NOTIFICATION_NO", policedistrictObj.getNotiNo())
				.setParameter("S_NOTIFICATION_DATE",nDateString) //policedistrictObj.getNotificationDate())// policedistrictObj.getNotificationDate())
				.setParameter("S_PDISTRICT_TYPE", policedistrictObj.getPdistrictType())
				.setParameter("S_DISTRICT_ID", policedistrictObj.getPdistrictId())
				.setParameter("S_CREATED_BY", "San")
				.setParameter("S_UPDATED_BY", "san") // policedistrictObj.getStringCreatedDate())
				.setParameter("S_STATUS","Y");
				
		pdistrictobj.execute();
		
		PoliceDistrictEntity policedistrictEnt= entityManager.createNamedQuery("getPoliceDistrictByNotiNo",PoliceDistrictEntity.class )
				                                     .setParameter("notiNo", policedistrictObj.getNotiNo()).getSingleResult();
			for(policeSubDivisionBean psBean : policedistrictObj.getSdpoObj() )
			{
				PoliceDistSdpoMappingEntity policedistMapObj = new PoliceDistSdpoMappingEntity();
				
				policedistMapObj.setSdpoId(psBean.getPoliceSubDivId());
				policedistMapObj.setStatus("Y");
				policedistMapObj.setPdistrictId(policedistrictEnt.getPdistrictId());
				entityManager.persist(policedistMapObj);
			}
		
		
		System.out.println("inserted sucessfully");
		response = "Data Added Successfully !!";
       
		}catch (Exception e) {
			e.printStackTrace();
			response = "Some Error Occurred !!";
		}
		return response;

	}

	
	
	@Transactional
	@Override
	public void deleteByNotiNo(String notiNo) {
		
		System.out.println("Service------- " + notiNo);
		String Status = "";

		PoliceDistrictEntity  pdistObj = entityManager
				.createNamedQuery("statusByNotiNo", PoliceDistrictEntity.class).setParameter("notiNo", notiNo)
				.getSingleResult();
		Status = pdistObj.getActiveStatus();
		if (Status.matches("N")) {
			pdistObj.setActiveStatus("Y");
		} else {
			System.out.println("Else Part");
			pdistObj.setActiveStatus("N");
		}
		entityManager.persist(pdistObj);
	}

	@Override
	public List<PoliceDistrictCatagoryBean> getTypeOfPoliceDistDetails() {
		
		List<PoliceDistrictCatagoryEntity> pdist = new ArrayList<PoliceDistrictCatagoryEntity>();
		List<PoliceDistrictCatagoryBean> pdistList = new ArrayList<PoliceDistrictCatagoryBean>();
		try {
			pdist = entityManager.createNamedQuery("PoliceDistrictDetails", PoliceDistrictCatagoryEntity.class).getResultList(); 
				for(PoliceDistrictCatagoryEntity p : pdist) {
					PoliceDistrictCatagoryBean pb = new PoliceDistrictCatagoryBean();
					
					pb.setPolicedistcatId(p.getPolicedistcatId());
					pb.setPolicedistcatagory(p.getPolicedistcatagory());
					pdistList.add(pb);
					
				}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return pdistList;
	}

	@Override
	public List<PoliceDistSdpoMappingBean> getPoliceDistrictAndSdpoDetails() {
		List<PoliceDistSdpoMappingBean> psList = new ArrayList<PoliceDistSdpoMappingBean>();
		List<PoliceDistSdpoMappingBean>   pdsDetails = new ArrayList<PoliceDistSdpoMappingBean>();
		PoliceDistSdpoMappingBean bean=null;
		
//		select d.SPDO_NAME , p.pdistrictname from 
//		tbl_police_dist_spdo_mapping s 
//		inner join 
//		tbl_police_district_master p on s.police_dist_spdo_id = p.pdistrictid
//		inner join tbl_police_sub_division_master d on d.spdo_id = s.spdo_id;
		
		try {
			List<Object[]> object = entityManager.createQuery("select  pdist.pdistrictName ,psub.spdoName from PoliceDistSdpoMappingEntity psdpo "
				+ "inner join PoliceDistrictEntity pdist on psdpo.pdistsdpoId = pdist.pdistrictId "
				+ "inner join PoliceSubDivision psub on psub.spdoId = psdpo.sdpoId").getResultList();
//		pdsDetails = q.getResultList();
//			System.out.println("policedistrict table length : " + psList.size());
//			System.out.println("\nList Data : "+psList.toString());

				if (!object.isEmpty()) {
					for (Object[] obj1 : object) {
						bean = new PoliceDistSdpoMappingBean();

						bean.setPdistrictName((String) obj1[0]);
						bean.setSdpoNames((String) obj1[1]);
//						bean.setSpdoNames((String) obj1[1]);

						psList.add(bean);

					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return psList;
			
			
//			for(PoliceDistrictEntity pd : psList)
//			{
//				PoliceDistSdpoMappingBean pdsBean = new PoliceDistSdpoMappingBean();
//				List<String> str = new ArrayList<String>();
//				
//				pdsBean.setPdistrictName(pd.getPdistrictName());
//				
//				javax.persistence.Query que = entityManager
//						
//						.createQuery("select s.sdpoId from PoliceDistSdpoMappingEntity s inner join PoliceDistrictEntity p on s.pdistsdpoId = p.pdistrictId");
//				
//				List<Long> id = que.getResultList();
//				System.out.println(" sdpoId from Mapping length  " + id.size());
//				
//				for(Long t : id) {
//					javax.persistence.Query que1 = entityManager
//							.createQuery("select spdoName from PoliceSubDivision where sdpoId = :id" );
//					que1.setParameter("id",t);
//					str.add(que1.getSingleResult().toString());
//						
//				}
//				System.out.println(" Sdpo List(str) length  " + str.size());
//				pdsBean.setSpdoName(str);
//				pdsDetails.add(pdsBean);
//						
//			}
//			System.out.println("###################################" + pdsDetails.get(0).getSpdoName() + " "+
//					pdsDetails.get(1).getPdistrictName() );
//					
			
//		return pdsDetails;
	}

	@Override
	public PoliceDistrictBean getPoliceDistrictDetailsForUpdate(Long id) {
	
		PoliceDistrictEntity policedistEnt = new PoliceDistrictEntity();
		PoliceDistrictBean policedistBean = new PoliceDistrictBean();
		List<Long> sdpoId = new ArrayList<Long>();
		try
		{
			System.out.println(id + " >>>>>>>>>>>>>>>");
			Query q = entityManager.createQuery("from PoliceDistrictEntity where pdistrictId = :id");
			q.setParameter("id", id);
			
			policedistEnt = (PoliceDistrictEntity) q.getSingleResult();
			
			policedistBean.setPdistrictId(policedistEnt.getPdistrictId());
			policedistBean.setPdistrictName(policedistEnt.getPdistrictName());
			policedistBean.setNotiNo(policedistEnt.getNotificationNo());
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
			String nDateString= dateFormat.format(policedistEnt.getNotificationDate());
			System.out.println("-------------------------------------------------"+nDateString);
			policedistBean.setNotificationDate(nDateString);
			policedistBean.setPdistrictType(policedistEnt.getPdistrictType());
			policedistBean.setPolicedistrictName(policedistEnt.getPdistrictName());	
			
		
		
		Query q1 = entityManager.createQuery("select sdpoId from PoliceDistSdpoMappingEntity where pdistrictId = :id ");
		q1.setParameter("id", id);
		sdpoId = q1.getResultList();
		
		if (sdpoId.size() != 0) {
			System.out.println("<<<<<<<<<<<<<<<< " + sdpoId.size());
			policedistBean.setSdpoL(sdpoId);
		} else {
			System.out.println("Sdpo List Not Found");
		}
		
		List<policeSubDivisionBean> sdpoList = new ArrayList<policeSubDivisionBean>();
		List<String>  SdpoListForView = new ArrayList<String>();
		 for(Long l : sdpoId) {
			 
			 policeSubDivisionBean policesubBean = new policeSubDivisionBean();
			 Query query = entityManager.createQuery("select spdoName from PoliceSubDivision where spdoId = :id" );
			 query.setParameter("id", l);
			 policesubBean.setPoliceSubDivId(l);
			 String s = (String) query.getSingleResult();
			 policesubBean.setSdpoName(s);
			 sdpoList.add(policesubBean);
			 SdpoListForView.add(s);
		 }
		
		 policedistBean.setPolicesubObj(sdpoList);
		 
		 policedistBean.setSdpoListForView(SdpoListForView);
		
		

		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return policedistBean;
	}

	@Transactional
	@Override
	public void updatePoliceDistrictDetails(PoliceDistrictBean policedistrictObj) {
		System.out.println("PoliceDistrict ID ----------------------------------------check"+ policedistrictObj.getNotiDate());
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
		String nDateString= dateFormat.format(policedistrictObj.getNotiDate());
		
		
		
		
	   try {
		StoredProcedureQuery pdistobj = this.entityManager.createStoredProcedureQuery("POLICE_DISTRICT_UPDATE")
		   .registerStoredProcedureParameter("V_PDISTRICT_NAME",String.class, ParameterMode.IN)
		   .registerStoredProcedureParameter("V_NOTIFICATION_NO",String.class, ParameterMode.IN)
		   .registerStoredProcedureParameter("V_NOTIFICATION_DATE",String.class, ParameterMode.IN)
		   .registerStoredProcedureParameter("V_PDISTRICT_TYPE",Long.class, ParameterMode.IN)
//		   .registerStoredProcedureParameter("V_CREATED_ON",String.class, ParameterMode.IN)
//		   .registerStoredProcedureParameter("V_CREATED_BY",String.class, ParameterMode.IN)
//		   .registerStoredProcedureParameter("V_UPDATED_ON",String.class, ParameterMode.IN)
		   .registerStoredProcedureParameter("V_UPDATED_BY",String.class, ParameterMode.IN)
		   .registerStoredProcedureParameter("V_ACTIVE_STATUS",String.class, ParameterMode.IN)
		   .registerStoredProcedureParameter("V_PDISTRICT_ID",Long.class, ParameterMode.IN);
		
		pdistobj.setParameter("V_PDISTRICT_NAME",policedistrictObj.getPdistrictName())
		         .setParameter("V_NOTIFICATION_NO",policedistrictObj.getNotiNo())
		         .setParameter("V_NOTIFICATION_DATE",nDateString)//policedistrictObj.getNotificationDate()
		         .setParameter("V_PDISTRICT_TYPE",policedistrictObj.getPolicedistcatId())
//		         .setParameter("V_CREATED_ON","")//policedistrictObj.getStringCreatedDate()
//		         .setParameter("V_CREATED_BY","san")//policedistrictObj.getCreatedBy())
//		         .setParameter("V_UPDATED_ON","")//policedistrictObj.getUpdatedDate()
		         .setParameter("V_UPDATED_BY","san")//policedistrictObj.getUpdatedBy())
		         .setParameter("V_ACTIVE_STATUS","Y" )//policedistrictObj.getStatus())
		         .setParameter("V_PDISTRICT_ID",policedistrictObj.getPdistrictId());
				
		pdistobj.execute();
		System.out.println("Master Table Executed !!!!");
		
		
		javax.persistence.Query query = entityManager.createQuery("DELETE FROM PoliceDistSdpoMappingEntity c WHERE c.pdistrictId = :id");
		int deletedCount = query.setParameter("id", policedistrictObj.getPdistrictId()).executeUpdate();
		System.out.println("Total Number of Row Deleted are -------------------------------------------->"+deletedCount);
		
		
		PoliceDistrictEntity policedistrictEnt= entityManager.createNamedQuery("getPoliceDistrictByNotiNo",PoliceDistrictEntity.class )
                .setParameter("notiNo", policedistrictObj.getNotiNo()).getSingleResult();
				for(policeSubDivisionBean psBean : policedistrictObj.getSdpoObj() )
				{
				PoliceDistSdpoMappingEntity policedistMapObj = new PoliceDistSdpoMappingEntity();
				
				policedistMapObj.setSdpoId(psBean.getPoliceSubDivId());
				policedistMapObj.setStatus("Y");
				policedistMapObj.setPdistrictId(policedistrictEnt.getPdistrictId());
				entityManager.persist(policedistMapObj);
				System.out.println("Data Inserted in mapping table !!!!");
				}


		System.out.println("inserted sucessfully");
		
		
		
		
//		PoliceDistrictEntity policedistrictEnt1= entityManager.createNamedQuery("getPoliceDistrictByNotiNo",PoliceDistrictEntity.class )
//                .setParameter("notiNo", policedistrictObj.getNotiNo()).getSingleResult();
//		
//		System.out.println("Id List: "+policedistrictObj.getPolicesubObj());
//		
//		for(policeSubDivisionBean psBean1 : policedistrictObj.getPolicesubObj() )
//		{
//			PoliceDistSdpoMappingEntity policedistMapObj1 = new PoliceDistSdpoMappingEntity();
//			
//			policedistMapObj1.setSdpoId(psBean1.getPoliceSubDivId());
//			policedistMapObj1.setStatus("Y");
//			policedistMapObj1.setPdistrictId(policedistrictEnt1.getPdistrictId());
//			entityManager.persist(policedistMapObj1);
//		}
		
	   }catch (Exception e) {
			e.printStackTrace();
		}
		
		
	   
	}


	@Override
	public List<PoliceDistrictBean> getPoliceDistDetailsByTypeId(Long id) {
		List<PoliceDistrictBean> ObList = new ArrayList<PoliceDistrictBean>();
		PoliceDistrictBean subDiv = null;
		try { 
			//List<Object[]> obj= blockService.getSubDivDetailsByDistId(districtId);
			List<Object[]> object = entityManager.createNamedQuery("getPoliceDistDetailsByTypeId", Object[].class)
					.setParameter("id", id)
					.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					subDiv = new PoliceDistrictBean();
					subDiv.setPdistrictId((Long)obj1[0]);
					subDiv.setPdistrictName((String)obj1[1]);
					ObList.add(subDiv);
	
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ObList;
	}


	@Override
	public List<PoliceDistrictBean> getSdpoByDistrictId(Long id) {
		List<PoliceDistrictBean> ObList = new ArrayList<PoliceDistrictBean>();
		PoliceDistrictBean subDiv = null;


		try { 
			//List<Object[]> obj= blockService.getSubDivDetailsByDistId(districtId);
			List<Object[]> object = entityManager.createNamedQuery("getSdpoDetailsByDistrictId", Object[].class)
					.setParameter("id", id)
					.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					subDiv = new PoliceDistrictBean();
					subDiv.setPoliceSubDivId((Long)obj1[0]);
					subDiv.setSdpoName((String)obj1[1]);
					ObList.add(subDiv);
	
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ObList;
	}


	@Override
	public List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredByPDistrict(Long pdid) {
		List<PoliceDistrictBean> objBean = new ArrayList<PoliceDistrictBean>();
		PoliceDistrictBean pdistBean = null;
		
		try {
			Query q1 = entityManager.createQuery(" select p.pdistrictId,p.pdistrictName,p.notificationDate,p.notificationNo,p.activeStatus,d.districtName from"
		    		+ " PoliceDistrictEntity p left join District d on p.districtId = d.districtId where p.pdistrictId =:pdid");
			q1.setParameter("pdid", pdid);
			List<Object[]> object = q1.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					pdistBean = new PoliceDistrictBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String nDateString= dateFormat.format((Date)obj1[2]);
					
					pdistBean.setPdistrictId((Long) obj1[0]);
					pdistBean.setPdistrictName((String) obj1[1]);
					pdistBean.setNotificationDate(nDateString);
					//pdistBean.setNotificationDate(nDateString);
					pdistBean.setNotiNo((String) obj1[3]);
					pdistBean.setStatus((String) obj1[4]);
					pdistBean.setDistrictName((String) obj1[5]);
					// subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(pdistBean);

				}
			}
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return objBean;
	}
	
	
	//search Pipe Use 
	
	@Override
	public List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredByStatus(String status) {
		List<PoliceDistrictBean> objBean = new ArrayList<PoliceDistrictBean>();
		PoliceDistrictBean pdistBean = null;
		
		try {
			Query q1 = entityManager.createQuery(" select p.pdistrictId,p.pdistrictName,p.notificationDate,p.notificationNo,p.activeStatus,d.districtName from"
		    		+ " PoliceDistrictEntity p left join District d on p.districtId = d.districtId where p.activeStatus =:status");
			q1.setParameter("status", status);
			List<Object[]> object = q1.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					pdistBean = new PoliceDistrictBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String nDateString= dateFormat.format((Date)obj1[2]);
					
					pdistBean.setPdistrictId((Long) obj1[0]);
					pdistBean.setPdistrictName((String) obj1[1]);
					pdistBean.setNotificationDate(nDateString);
					//pdistBean.setNotificationDate(nDateString);
					pdistBean.setNotiNo((String) obj1[3]);
					pdistBean.setStatus((String) obj1[4]);
					pdistBean.setDistrictName((String) obj1[5]);
					// subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(pdistBean);

				}
			}
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return objBean;
	}
	
	
	@Override
	public List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredByDate(String date) {
		List<PoliceDistrictBean> objBean = new ArrayList<PoliceDistrictBean>();
		PoliceDistrictBean pdistBean = null;
		
		try {
			 Date dt = new Date(date);
			 DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
				String nDateString= dateFormat.format(dt);
//				System.out.println("front end date -----------------------"+nDateString);
					
			Query q1 = entityManager.createQuery(" select p.pdistrictId,p.pdistrictName,p.notificationDate,p.notificationNo,p.activeStatus,d.districtName from"
		    		+ " PoliceDistrictEntity p left join District d on p.districtId = d.districtId ");
			List<Object[]> object = q1.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					pdistBean = new PoliceDistrictBean();
					String dbdate = dateFormat.format((Date)obj1[2]);
//					System.out.println("DB date -----------------------------------"+dbdate);
					if(nDateString.equals(dbdate)) {
						pdistBean.setPdistrictId((Long) obj1[0]);
						pdistBean.setPdistrictName((String) obj1[1]);
						pdistBean.setNotificationDate(dbdate);
						//pdistBean.setNotificationDate(nDateString);
						pdistBean.setNotiNo((String) obj1[3]);
						pdistBean.setStatus((String) obj1[4]);
						pdistBean.setDistrictName((String) obj1[5]);
						// subDivisionBean.setNotiDate((LocalDate)obj1[6]);

						objBean.add(pdistBean);
					}
					

				}
			}
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return objBean;
	}
	
	
	@Override
	public List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredPdIdAndStatus(Long pdid, String status) {
		List<PoliceDistrictBean> objBean = new ArrayList<PoliceDistrictBean>();
		PoliceDistrictBean pdistBean = null;
		System.out.println("////////////////////////////////////////////////////////////----"+status);
		System.out.println("////////////////////////////////////////////////////////////----"+pdid);
		

		try {
			Query q1 = entityManager.createQuery(" select p.pdistrictId,p.pdistrictName,p.notificationDate,p.notificationNo,p.activeStatus,d.districtName from"
		    		+ " PoliceDistrictEntity p left join District d on p.districtId = d.districtId where p.activeStatus =:status and p.pdistrictId =:pdid ")
			.setParameter("status", status)
			.setParameter("pdid",pdid);
			List<Object[]> object = q1.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					pdistBean = new PoliceDistrictBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String nDateString= dateFormat.format((Date)obj1[2]);
					
					pdistBean.setPdistrictId((Long) obj1[0]);
					pdistBean.setPdistrictName((String) obj1[1]);
					pdistBean.setNotificationDate(nDateString);
					//pdistBean.setNotificationDate(nDateString);
					pdistBean.setNotiNo((String) obj1[3]);
					pdistBean.setStatus((String) obj1[4]);
					pdistBean.setDistrictName((String) obj1[5]);
					// subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(pdistBean);

				}
			}
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return objBean;
	}


	@Override
	public List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredPDateAndStatus(String date, String status) {
		List<PoliceDistrictBean> objBean = new ArrayList<PoliceDistrictBean>();
		PoliceDistrictBean pdistBean = null;
		System.out.println("////////////////////////////////////////////////////////////----"+status);
		System.out.println("////////////////////////////////////////////////////////////----"+date);
		

		try {
			Query q1 = entityManager.createQuery(" select p.pdistrictId,p.pdistrictName,p.notificationDate,p.notificationNo,p.activeStatus,d.districtName from"
		    		+ " PoliceDistrictEntity p left join District d on p.districtId = d.districtId where p.activeStatus =:status")
			.setParameter("status", status);
			
			List<Object[]> object = q1.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					pdistBean = new PoliceDistrictBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String nDateString= dateFormat.format((Date)obj1[2]);
					
					if(date.equals(nDateString))
					{
					pdistBean.setPdistrictId((Long) obj1[0]);
					pdistBean.setPdistrictName((String) obj1[1]);
					pdistBean.setNotificationDate(nDateString);
					//pdistBean.setNotificationDate(nDateString);
					pdistBean.setNotiNo((String) obj1[3]);
					pdistBean.setStatus((String) obj1[4]);
					pdistBean.setDistrictName((String) obj1[5]);
					// subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(pdistBean);
					}

				}
			}
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return objBean;
		
	}


	@Override
	public List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredPDateAndStatusAndPdId(String date, String status,
			Long pdId) {
		List<PoliceDistrictBean> objBean = new ArrayList<PoliceDistrictBean>();
		PoliceDistrictBean pdistBean = null;
		System.out.println("////////////////////////////////////////////////////////////----"+status);
		System.out.println("////////////////////////////////////////////////////////////----"+pdId);
		

		try {
			Query q1 = entityManager.createQuery(" select p.pdistrictId,p.pdistrictName,p.notificationDate,p.notificationNo,p.activeStatus,d.districtName from"
		    		+ " PoliceDistrictEntity p left join District d on p.districtId = d.districtId where p.activeStatus =:status and p.pdistrictId =:pdId ")
			.setParameter("status", status)
			.setParameter("pdId",pdId);
			List<Object[]> object = q1.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					pdistBean = new PoliceDistrictBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String nDateString= dateFormat.format((Date)obj1[2]);
					if(date.equals(nDateString)) {
					pdistBean.setPdistrictId((Long) obj1[0]);
					pdistBean.setPdistrictName((String) obj1[1]);
					pdistBean.setNotificationDate(nDateString);
					//pdistBean.setNotificationDate(nDateString);
					pdistBean.setNotiNo((String) obj1[3]);
					pdistBean.setStatus((String) obj1[4]);
					pdistBean.setDistrictName((String) obj1[5]);
					// subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(pdistBean);
					}

				}
			}
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return objBean;
	}


}
