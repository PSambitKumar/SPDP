package com.spdp.serviceImpl;


import java.sql.Timestamp;
import java.text.DateFormat;
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

import com.spdp.bean.BankBean;
import com.spdp.bean.DistBean;
import com.spdp.bean.DistSubdivisionBean;
import com.spdp.bean.SubdivisionBlockBean;
//import com.spdp.bean.SubdivisionBean;
import com.spdp.bean.SubdivisiondropdownBean;
import com.spdp.entity.DistrictEntity;
import com.spdp.entity.DistrictSubdivMapping;
import com.spdp.entity.SubDivisionEntity;
//import com.spdp.entity.Subdivision;
import com.spdp.service.DistrictMasterService;


/**
 * 
 * @Author : Akash Deep
 * 
 **/


@ApplicationScoped
public class DistrictMasterServiceImpl implements DistrictMasterService {
	
	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;
	
	@Override
	public List<DistBean> getDetailsByDistrictId(){
		
		List<DistrictEntity> obj = new ArrayList<DistrictEntity>();
		
		 obj = entityManager.createNamedQuery("DistrictEntityDetails", DistrictEntity.class).getResultList();
		 List<DistBean> objBean =new ArrayList<DistBean>();
		 
		 for(DistrictEntity en:obj) {
			 DistBean rdcBean =new DistBean();
			// SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			 DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");  
			 rdcBean.setDistrictId(en.getDistrictId());
			rdcBean.setDistrictName(en.getDistrictName());
			rdcBean.setNotiNo(en.getNotificationNo());
			rdcBean.setStatus(en.getActiveStatus());
			rdcBean.setLgdCode(en.getLgdCode());
			if(en.getNotificationDate()!=null) {
				 String strDate = dateFormat.format(en.getNotificationDate());
				 rdcBean.setNotiDate(strDate);
			 }
			objBean.add(rdcBean);
			
		 }
		return objBean;
	}
	

		
	@Override
	public List<SubDivisionEntity> getSubdivisionDetails(){
		
		List<SubDivisionEntity> obj = new ArrayList<SubDivisionEntity>();
		SubDivisionEntity sdBean = null;
		
		  
		  try {
			List<Object[]> object = entityManager.createNamedQuery("SubDivisionEntityDetails", Object[].class).getResultList();
		  
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					sdBean = new SubDivisionEntity();
					
					sdBean.setSubdivisionId((Long)obj1[0]);
					sdBean.setSubdivisionName((String)obj1[1]);

					 obj.add(sdBean);
					  
					
				}
			}
		  }
				catch (Exception e) {
					e.printStackTrace();
				}
				  return obj;

		

	}

	
	
	
		
	
	@Transactional
	@Override
	public void deleteDistByNotiNo(String notiNo) {
		
		System.out.println("Service------- "+notiNo);
		String Status= "";
		
		DistrictEntity rdcObj =  entityManager.createNamedQuery("getDistrictRowByNotiNo",DistrictEntity.class).setParameter("notiNo", notiNo).getSingleResult();

		Status = rdcObj.getActiveStatus();
		if(Status.matches("N")) {
			rdcObj.setActiveStatus("Y");
		}else {
			System.out.println("Else Part");
			rdcObj.setActiveStatus("N");
		}
		entityManager.persist(rdcObj);

	}

	@Transactional
	@Override
	public String saveDistrictDetails(DistBean rdcBean){
		
		DistrictEntity entObj = new DistrictEntity();
		String response = "";
		String validationMsg = "";
		try {
			if(rdcBean.getDistrictName() == null) {
				validationMsg = "District Name Can Not Be Null";
				return validationMsg;
			}
			if(rdcBean.getNotiNo() == null) {
				validationMsg = "Notification Number Can Not Be Null";
				return validationMsg;
			}
			if(rdcBean.getNotiDate() == null ) {
				validationMsg = "Notification Date Can Not Be Null";
				return validationMsg;
			}
			
		    if(rdcBean.getSubdivisionObj().size() == 0) {
		       validationMsg = "Subdivision Lists Can Not Be Null"; 
			  return validationMsg; 
			  }
			 
			String[] splittedItems= rdcBean.getNotiDate().split("-");
			int date =Integer.parseInt(splittedItems[2]);
			int month =Integer.parseInt(splittedItems[1]);
			int year = Integer.parseInt(splittedItems[0]);
			System.out.println("Date- "+date+" Month- "+month+" Year"+year);
			if(rdcBean.getDistrictName() != null ) {
			
			entObj.setDistrictName(rdcBean.getDistrictName());
			entObj.setNotificationNo(rdcBean.getNotiNo());
			entObj.setNotificationDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			entObj.setCreatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			entObj.setUpdatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			entObj.setCreatedBy("Rajat");
			entObj.setUpdatedBy("Rajat");
			entObj.setAciton("Ok");
			entObj.setActiveStatus("Y");
			entObj.setLgdCode(rdcBean.getLgdCode());

			entityManager.persist(entObj);
			System.out.println("-----------------------------------------------------------------------------------------Data saved in master table !!!");
			
			DistrictEntity rdcObj =  entityManager.createNamedQuery("getDistrictRowByNotiNo",DistrictEntity.class).setParameter("notiNo", rdcBean.getNotiNo()).getSingleResult();
			
			System.out.println("--------------------------------------------------------------------------------------- Element in subdiv Obj"+rdcBean.getSubdivisionObj().size());
			
			for(SubdivisiondropdownBean distBean : rdcBean.getSubdivisionObj()) {
				DistrictSubdivMapping dcDistMapObj  = new DistrictSubdivMapping();
				dcDistMapObj.setSubdivisionId(distBean.getSubdivisionId());
				dcDistMapObj.setDistrictId(rdcObj.getDistrictId());
				entityManager.persist(dcDistMapObj);
				System.out.println("------------------------------------------------------------------------------------ Data Saved in mapping table !!!");
			}
		
			}else {
				System.out.println("Data Not Found");
			}
//			}
		response = "Data Added Successfully !!";
		System.out.println(response+"---------------------------------in service ");
		} catch(Exception e) {
			e.printStackTrace();
			response = "Some Error Occurred !!";
		}
		return response;
		
	}
	
	@Override
	public List<DistSubdivisionBean> getDistrictAndSubdivisionDetails() {
			
		List<DistrictEntity> enList =new ArrayList<DistrictEntity>();
		List<DistSubdivisionBean> details =new ArrayList<DistSubdivisionBean>();
	//	List<String> str =new ArrayList<String>();
			
		javax.persistence.Query q = entityManager.createQuery("  from DistrictEntity ");
		enList = q.getResultList();
		System.out.println(" District table length  " + enList.size());

		for (DistrictEntity en : enList) {

			DistSubdivisionBean sdBean = new DistSubdivisionBean();
			List<String> str = new ArrayList<String>();

			sdBean.setDistrictName(en.getDistrictName());

			javax.persistence.Query que = entityManager
					.createQuery(" select subdivisionId from DistrictSubdivMapping where districtId = :id ");
			que.setParameter("id", en.getDistrictId());
			List<Long> id = que.getResultList();

			System.out.println(" SubdivisionId from Mapping length  " + id.size());

			for (Long t : id) {
				javax.persistence.Query que1 = entityManager
						.createQuery(" select subdivisionName from SubDivisionEntity where subdivisionId = :id ");
				que1.setParameter("id", t);
				str.add(que1.getSingleResult().toString());
			}

			System.out.println(" Subdivision List(str) length  " + str.size());
			sdBean.setSubdivisionNames(str);
			details.add(sdBean);

		}

		return details;
	}

		
		

	@Override
	public DistBean getDistrictDetailsForUpdate(Long id) {
		
		DistrictEntity rdcEnt = new DistrictEntity();
		DistBean rdcBean = new DistBean();
		List<Long> subdivisionId = new ArrayList<Long>();
		
		try {
			
			System.out.println(id+" >>>>>>>>>>>>>>>");
			javax.persistence.Query q= entityManager.createQuery("from DistrictEntity where districtId = :id");
			q.setParameter("id",id);
		
			rdcEnt = (DistrictEntity) q.getSingleResult();
			System.out.println(rdcEnt.getDistrictName());
		
		
			rdcBean.setDistrictId(rdcEnt.getDistrictId());
			rdcBean.setDistrictName(rdcEnt.getDistrictName());
			rdcBean.setNotiNo(rdcEnt.getNotificationNo());
			rdcBean.setLgdCode(rdcEnt.getLgdCode());
			rdcBean.setStatus(rdcEnt.getActiveStatus());
		
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			rdcBean.setNotiDate(formatter.format(rdcEnt.getNotificationDate()));
//			entityManager.persist(rdcBean);
			
			javax.persistence.Query q1= entityManager.createQuery(" select subdivisionId from DistrictSubdivMapping where districtId = :id");
			q1.setParameter("id",id);
			subdivisionId = q1.getResultList();
			
				if(subdivisionId.size() != 0) {
				System.out.println("<<<<<<<<<<<<<<<< "+subdivisionId.size());
				rdcBean.setSubdivL(subdivisionId);
				}else {
					System.out.println("Subdivision List Not Found");
				}
				

				List<SubdivisiondropdownBean> distList = new ArrayList<SubdivisiondropdownBean>();
				List<String> subDivListForView=new ArrayList<String>();
				
				for(Long l:subdivisionId) {
					SubdivisiondropdownBean distBean =new SubdivisiondropdownBean();
					javax.persistence.Query query= entityManager.createQuery(" select subdivisionName from SubDivisionEntity where subdivisionId = :id");
					query.setParameter("id",l);
					distBean.setSubdivisionId(l);
					String s = (String) query.getSingleResult();
					distBean.setSubdivisionName(s);
					distList.add(distBean);
					subDivListForView.add(s);
				}
				rdcBean.setSubdivisionObj(distList);
				rdcBean.setSubdivListForView(subDivListForView);
		}catch(Exception e) {		
			e.printStackTrace();
		}
			return rdcBean;
	}
	
	@Transactional
	@Override
	public void updateDistrictDetails(DistBean distObj) {
		
		System.out.println("--------------------------------------------------------Date =:"+distObj.getNotiDate());
		String[] splittedItems =distObj.getNotiDate().split("-");
		int date = Integer.parseInt(splittedItems[0]);
		int month = Integer.parseInt(splittedItems[1]);
		int year = Integer.parseInt(splittedItems[2]);
		System.out.println("Date- " + date + " Month- " + month + " Year-" + year);
		
		Date dtDate=new Timestamp(year - 1900, month - 1, date, 5, 5, 5, 5);
		System.out.println("The Notification Date After format -------------------------------"+dtDate);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy HH.mm.ss.S aaa");  
		String nDateString= dateFormat.format(dtDate);
		System.out.println("The Notification Date After format after convertttttttt -------------------------------"+nDateString);
		DistrictEntity distEnt;// = new SubdivisionEntity();
		
		StoredProcedureQuery distobj1 = this.entityManager.createStoredProcedureQuery("SP_DISTRICT")
				.registerStoredProcedureParameter("flag", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_DISTRICT_NAME", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_NOTIFICATION_NO", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_NOTIFICATION_DATE", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_LGDCODE", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_DISTRICT_ID", Long.class, ParameterMode.IN);
		
		distobj1
		.setParameter("flag", "EDIT")
		.setParameter("V_DISTRICT_NAME", distObj.getDistrictName())
		.setParameter("V_NOTIFICATION_NO", distObj.getNotiNo())
		.setParameter("V_NOTIFICATION_DATE",nDateString) //subdivisionObj.getNotiDate())---
		.setParameter("V_LGDCODE", distObj.getLgdCode())
		.setParameter("V_DISTRICT_ID", distObj.getDistrictId());
		
		distobj1.execute();
		System.out.println("Updated successsfully");
		
		
		
		
		
		
//		DistrictEntity rdcEnt;// = new RdcEntity();
//		List<Long> distLong = new ArrayList<Long>();	
//		String[] splittedItems= rdcObj.getNotiDate().split("-");
//		int date =Integer.parseInt(splittedItems[0]);
//		int month =Integer.parseInt(splittedItems[1]);
//		int year = Integer.parseInt(splittedItems[2]);
//		try {
//			for(SubdivisionBean b:rdcObj.getSubdivObj()) {
//				distLong.add(b.getSubdivId());
//			}
//			
//			rdcEnt = entityManager.createNamedQuery("getDistrictRowByNotiNo",DistrictEntity.class).setParameter("notiNo", rdcObj.getNotiNo()).getSingleResult();
//			rdcEnt.setDistrictName(rdcObj.getDistrictName());
//			rdcEnt.setNotificationNo(rdcObj.getNotiNo());
//			rdcEnt.setNotificationDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
//			entityManager.persist(rdcEnt);
//			
//			javax.persistence.Query q1= entityManager.createQuery(" from DistrictSubdivMapping where districtId = :id");
//			q1.setParameter("id",rdcEnt.getDistrictId());
//			List<DistrictSubdivMapping> rdcMap;// = new ArrayList<RdcDistrictMapping>();
//			rdcMap = q1.getResultList();
//			
//			if(distLong.size() == rdcMap.size()) {
//				
//				//System.out.println(rdcEnt.getRdcName()+"<<<<><1><>>>>>>-----"+rdcMap.size());
//				for(int i=0,j=0; i<distLong.size(); i++) {
//					rdcMap.get(i).setSubdivisionId(distLong.get(i));
//					entityManager.persist(rdcMap.get(i));
//				}	
//				
//			 }else if(distLong.size() < rdcMap.size()) {
//				 if(distLong.size() ==0) {
//					 javax.persistence.Query q= entityManager.createQuery("delete from DistrictSubdivMapping where districtId = :id");
//						q.setParameter("id", rdcEnt.getDistrictId());
//						int deletedRows = q.executeUpdate();
//				 }else {
//					for(int i=0,j=0; i<rdcMap.size(); i++) {
//						System.out.println(distLong.size()+"<<<<<<<<<<<<<<<");
//						if(i != distLong.size()) {
//							rdcMap.get(i).setSubdivisionId(distLong.get(i));
//							entityManager.persist(rdcMap.get(i));
//						}else {
//							javax.persistence.Query q= entityManager.createQuery("delete from DistrictSubdivMapping where subdivisionDistId = :id");
//							q.setParameter("id", rdcMap.get(i).getSubdivisionDistId());
//							int deletedRows = q.executeUpdate();
//							System.out.println("Update List is less Than Db List So,Deleted row - "+deletedRows);
//					}
//				}
//			 }
//			}else {
//			   for(int i=0,j=0; i<distLong.size(); i++) {
//				   if(i < rdcMap.size()) {
//					   rdcMap.get(i).setSubdivisionId(distLong.get(i));
//					   entityManager.persist(rdcMap.get(i));
//				   }else {
//					   DistrictSubdivMapping objSave = new DistrictSubdivMapping();
//					   objSave.setSubdivisionId(distLong.get(i));
//					   objSave.setDistrictId(rdcEnt.getDistrictId());
//					   entityManager.persist(objSave);
//					
//					}
//			   	}
//			}
//			
//			
//		} catch(Exception e) {
//			e.printStackTrace();
//		}
		
		
	}
}