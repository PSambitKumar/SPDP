package com.spdp.serviceImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.spdp.bean.DepartmentBean;
import com.spdp.bean.DistLGDBean;
import com.spdp.bean.DistrictBean;
import com.spdp.bean.EducationBean;
import com.spdp.bean.GpBean;
import com.spdp.bean.PoliceStationBean;
import com.spdp.bean.RICircleBean;
import com.spdp.bean.RICirlceAndVillageBean;
import com.spdp.bean.RdcBean;
import com.spdp.bean.RdcDistrictBean;
import com.spdp.bean.casteMasterBean;
import com.spdp.bean.dropdownBean;
import com.spdp.bean.finalBucketListBean;
import com.spdp.entity.CasteMasterEntity;
import com.spdp.entity.DepartmentEntity;
import com.spdp.entity.District;
import com.spdp.entity.EducationCourseEntity;
import com.spdp.entity.EducationEntity;
import com.spdp.entity.GPVillageMapEntity;
import com.spdp.entity.GpEntity;
import com.spdp.entity.PoliceStnVillMapEntity;
import com.spdp.entity.RICircleMasterEntity;
import com.spdp.entity.RdcDistrictMapping;
import com.spdp.entity.RdcEntity;
import com.spdp.entity.RiCircleVillageMapping;
import com.spdp.entity.policeStationEntity;
import com.spdp.service.MasterService;

/**
 * 
 * @Author : Akash Deep
 * 
 **/


@ApplicationScoped
public class MasterServiceImpl implements MasterService {
	
	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;
	
	@Override
	public List<RdcBean> getDetailsByRdcId(){
		
		List<RdcEntity> obj = new ArrayList<RdcEntity>();
		//RdcEntity obj = new RdcEntity();
		 obj = entityManager.createNamedQuery("RdcEntityDetails", RdcEntity.class).getResultList();
		 List<RdcBean> objBean =new ArrayList<RdcBean>();
		 
		 for(RdcEntity en:obj) {
			 RdcBean rdcBean =new RdcBean();
			 SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			 rdcBean.setRdcId(en.getRdcId());
			 rdcBean.setNotiDate((formatter.format((en.getNotificationDate()))));
			rdcBean.setRdcName(en.getRdcName());
			rdcBean.setNotiNo(en.getNotificationNo());
			rdcBean.setStatus(en.getDeleteStatus());
			objBean.add(rdcBean);
			//System.out.println(rdcBean.getRdcName());
		 }
		return objBean;
	}
	
	@Override
	public List<DistrictBean> getDistrictDetails() {
		List<District> dist = new ArrayList<District>();
		List<DistrictBean> districtList= new ArrayList<DistrictBean>();
		try {
		 dist =  entityManager.createNamedQuery("DistDetails", District.class).getResultList();
		
		 ////////////////////////////////////
		 for(District d:dist) {
			 DistrictBean bn = new DistrictBean();
			 bn.setDistId(d.getDistrictId());
			 bn.setDistName(d.getDistrictName());
			 districtList.add(bn);
			 
		 }
		 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return districtList;
		
		
	}
	@Transactional
	@Override
	public void deleteByNotiNo(String notiNo) {
		
		System.out.println("Service------- "+notiNo);
		String Status= "";
		
		RdcEntity rdcObj =  entityManager.createNamedQuery("getRdcRowByNotiNo",RdcEntity.class).setParameter("notiNo", notiNo).getSingleResult();
		//entityManager.remove(rdcObj);
		//entityManager.flush();
		//System.out.println(" RdcRow -  "+rdcObj.getNotificationNo()+"  , "+rdcObj.getRdcName());
		
		
		
		
		Status = rdcObj.getDeleteStatus();
		if(Status.matches("In-Active")) {
			rdcObj.setDeleteStatus("Active");
		}else {
			System.out.println("Else Part");
			rdcObj.setDeleteStatus("In-Active");
		}
		entityManager.persist(rdcObj);
//		javax.persistence.Query q= entityManager.createQuery("delete from RdcEntity where rdcId = :id");
//		q.setParameter("id", rdcObj.getRdcId());
//		int deletedRows = q.executeUpdate();
//		System.out.println("Deleted Rows Affected "+deletedRows);
		
		
		// entityManager.createNamedQuery("DistDetails", District.class).getResultList();
//
//		Configuration cnf = new Configuration();
//        SessionFactory factory = cnf.buildSessionFactory();
//
//        Session session = factory.openSession();
//        Transaction transaction = session.beginTransaction();
//        String query =" from RdcEntity";
//        Query q = session.createQuery(query);
//        List<RdcEntity> rdc = q.list();
//        System.out.println(rdc.get(0).getRdcName());
//        session.close();
	}

	@Transactional
	@Override
	public String saveRdcDetails(RdcBean rdcBean){
		
		RdcEntity entObj = new RdcEntity();
		String response = "";
		String validationMsg = "";
		try {
			if(rdcBean.getRdcName() == null) {
				validationMsg = "RDC Name Can Not Be Null";
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
			if(rdcBean.getDistObj().size() == 0) {
				validationMsg = "District Lists Can Not Be Null";
				return validationMsg;
			}
			String[] splittedItems= rdcBean.getNotiDate().split("-");
			int date =Integer.parseInt(splittedItems[2]);
			int month =Integer.parseInt(splittedItems[1]);
			int year = Integer.parseInt(splittedItems[0]);
			System.out.println("Date- "+date+" Month- "+month+" Year"+year);
			if(rdcBean.getRdcName() != null ) {
			
			entObj.setRdcName(rdcBean.getRdcName());
			entObj.setNotificationNo(rdcBean.getNotiNo());
			entObj.setNotificationDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			entObj.setCreatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			entObj.setUpdatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			entObj.setCreatedBy("Akash");
			entObj.setUpdatedBy("Akash");
			entObj.setAciton("Ok");
			entObj.setDeleteStatus("Active");
			entObj.setRdcCode(5L);
			entObj.setStateId(5L);
			entityManager.persist(entObj);
			
			RdcEntity rdcObj =  entityManager.createNamedQuery("getRdcRowByNotiNo",RdcEntity.class).setParameter("notiNo", rdcBean.getNotiNo()).getSingleResult();
			
			for(DistrictBean distBean : rdcBean.getDistObj()) {
				RdcDistrictMapping rdcDistMapObj  = new RdcDistrictMapping();
				Query qqq = entityManager.createNativeQuery
						("select count(*) from tbl_district_rdc_mapping where district_id = :id").setParameter("id", distBean.getDistId());
				BigDecimal ch = (BigDecimal) qqq.getSingleResult();
				if(ch.intValue() == 0) {
				System.out.println("---------------=================----- "+ch);
				rdcDistMapObj.setDistrictId(distBean.getDistId());
				}else {
					Query q5 = entityManager.createQuery("delete from RdcDistrictMapping where district_id = :id").setParameter("id", distBean.getDistId());
					q5.executeUpdate();
					rdcDistMapObj.setDistrictId(distBean.getDistId());
				}
				rdcDistMapObj.setRdcId(rdcObj.getRdcId());
				entityManager.persist(rdcDistMapObj);
			}
		
			}else {
				System.out.println("Data Not Found");
			}
		System.out.println("Exception");
		response = "Data Added Successfully !!";
		} catch(Exception e) {
			e.printStackTrace();
			response = "Some Error Occurred !!";
		}
		return response;
		
	}
			
	@Override
	public List<RdcDistrictBean> getRdcAndDistrictDetails() {
		
		List<RdcEntity> enList =new ArrayList<RdcEntity>();
		List<RdcDistrictBean> details =new ArrayList<RdcDistrictBean>();
	//	List<String> str =new ArrayList<String>();
		
		javax.persistence.Query q= entityManager.createQuery("  from RdcEntity ");
		enList = q.getResultList();
		System.out.println(" Rdc table length  "+enList.size());
		
		for(RdcEntity en: enList) {
			
			RdcDistrictBean rdBean = new RdcDistrictBean();
			List<String> str =new ArrayList<String>();
			
			rdBean.setRdcName(en.getRdcName());
			javax.persistence.Query que = entityManager.createQuery(" select districtId from RdcDistrictMapping where rdcId = :id ");
			que.setParameter("id", en.getRdcId());
			List<Long> id = que.getResultList();
			System.out.println(" DistrictId from Mapping length  "+id.size());
			
				for(Long t:id) {
					javax.persistence.Query que1 = entityManager.createQuery(" select districtName from District where districtId = :id ");
					que1.setParameter("id", t);
					str.add(que1.getSingleResult().toString());	
				}
				
				System.out.println(" District List(str) length  "+str.size());
					rdBean.setDistNames( str);
					details.add(rdBean);
			
		}
		
//		System.out.println("---------------------------------"+details.get(0).getDistNames() +
//				"     "+details.get(2).getRdcName()+"  "+details.get(2).getDistNames());
		return details;
	}

	@Override
	public RdcBean getRdcDetailsForUpdate(Long id) {
		
		RdcEntity rdcEnt = new RdcEntity();
		RdcBean rdcBean = new RdcBean();
		List<Long> districtId = new ArrayList<Long>();
		
		try {
			
			System.out.println(id+" >>>>>>>>>>>>>>>");
			javax.persistence.Query q= entityManager.createQuery("from RdcEntity where rdcId = :id");
			q.setParameter("id",id);
		
			rdcEnt = (RdcEntity) q.getSingleResult();
			System.out.println(rdcEnt.getRdcName());
		
		
			rdcBean.setRdcId(rdcEnt.getRdcId());
			rdcBean.setRdcName(rdcEnt.getRdcName());
			rdcBean.setNotiNo(rdcEnt.getNotificationNo());
			rdcBean.setStatus(rdcEnt.getDeleteStatus());
		
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			rdcBean.setNotiDate(formatter.format(rdcEnt.getNotificationDate()));
			
			javax.persistence.Query q1= entityManager.createQuery(" select districtId from RdcDistrictMapping where rdcId = :id");
			q1.setParameter("id",id);
			districtId = q1.getResultList();
			
				if(districtId.size() != 0) {
				System.out.println("<<<<<<<<<<<<<<<< "+districtId.size());
				rdcBean.setDistL(districtId);
				}else {
					System.out.println("District List Not Found");
				}
				

				List<DistrictBean> distList = new ArrayList<DistrictBean>();
				List<String> DistListForView = new ArrayList<String>();
				for(Long l:districtId) {
					DistrictBean distBean =new DistrictBean();
					javax.persistence.Query query= entityManager.createQuery(" select districtName from District where districtId = :id");
					query.setParameter("id",l);
					distBean.setDistId(l);
					String s = (String) query.getSingleResult();
					distBean.setDistName(s);
					distList.add(distBean);
					DistListForView.add(s);
				}
				rdcBean.setDistObj(distList);
				rdcBean.setDistListForView(DistListForView);
		}catch(Exception e) {		
			e.printStackTrace();
		}
			return rdcBean;
	}
	
	@Transactional
	@Override
	public void updateRdcDetails(RdcBean rdcObj) {
		RdcEntity rdcEnt;// = new RdcEntity();
		List<Long> distLong = new ArrayList<Long>();	
		String[] splittedItems= rdcObj.getNotiDate().split("-");
		int date =Integer.parseInt(splittedItems[0]);
		int month =Integer.parseInt(splittedItems[1]);
		int year = Integer.parseInt(splittedItems[2]);
		try {
			for(DistrictBean b:rdcObj.getDistObj()) {
				distLong.add(b.getDistId());
			}
			
			rdcEnt = entityManager.createNamedQuery("getRdcRowByNotiNo",RdcEntity.class).setParameter("notiNo", rdcObj.getNotiNo()).getSingleResult();
			rdcEnt.setRdcName(rdcObj.getRdcName());
			rdcEnt.setNotificationNo(rdcObj.getNotiNo());
			rdcEnt.setNotificationDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			entityManager.persist(rdcEnt);
			
			javax.persistence.Query q1= entityManager.createQuery(" from RdcDistrictMapping where rdcId = :id");
			q1.setParameter("id",rdcEnt.getRdcId());
			List<RdcDistrictMapping> rdcMap;// = new ArrayList<RdcDistrictMapping>();
			rdcMap = q1.getResultList();
			
			if(distLong.size() == rdcMap.size()) {
				
				//System.out.println(rdcEnt.getRdcName()+"<<<<><1><>>>>>>-----"+rdcMap.size());
				for(int i=0,j=0; i<distLong.size(); i++) {
					rdcMap.get(i).setDistrictId(distLong.get(i));
					entityManager.persist(rdcMap.get(i));
				}	
				
			 }else if(distLong.size() < rdcMap.size()) {
				 if(distLong.size() ==0) {
					 javax.persistence.Query q= entityManager.createQuery("delete from RdcDistrictMapping where rdcId = :id");
						q.setParameter("id", rdcEnt.getRdcId());
						int deletedRows = q.executeUpdate();
				 }else {
					for(int i=0,j=0; i<rdcMap.size(); i++) {
						System.out.println(distLong.size()+"<<<<<<<<<<<<<<<");
						if(i < distLong.size()) {
							rdcMap.get(i).setDistrictId(distLong.get(i));
							entityManager.persist(rdcMap.get(i));
						}else {
							javax.persistence.Query q= entityManager.createQuery("delete from RdcDistrictMapping where distRdcId = :id");
							q.setParameter("id", rdcMap.get(i).getDistRdcId());
							int deletedRows = q.executeUpdate();
							System.out.println("Update List is less Than Db List So,Deleted row - "+deletedRows);
					}
				}
			 }
			}else {
			   for(int i=0,j=0; i<distLong.size(); i++) {
				   if(i < rdcMap.size()) {
					   rdcMap.get(i).setDistrictId(distLong.get(i));
					   entityManager.persist(rdcMap.get(i));
				   }else {
					   RdcDistrictMapping objSave = new RdcDistrictMapping();
					   objSave.setDistrictId(distLong.get(i));
					   objSave.setRdcId(rdcEnt.getRdcId());
					   entityManager.persist(objSave);
					
					}
			   	}
			}
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
			
	}
	
	@Transactional
	@Override
	public String saveRICircleDetails(RICircleBean obj) {
		String response = "";
		RICircleMasterEntity mstEnt = new RICircleMasterEntity();
		try {
		
			String[] splittedItems= obj.getNotiDate().split("-");
			int date =Integer.parseInt(splittedItems[0]);
			int month =Integer.parseInt(splittedItems[1]);
			int year = Integer.parseInt(splittedItems[2]);
			
			mstEnt.setRiCircleName(obj.getRiCircleName());
			mstEnt.setNotificationNo(obj.getNotiNo());
			mstEnt.setDistId(obj.getDist());
			mstEnt.setNotificationDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			mstEnt.setSubDivisionId(obj.getSubDivision());
			mstEnt.setTehsilId(obj.getTehsil());
			mstEnt.setAction("Ok");
			mstEnt.setActiveStatus("Y");
			mstEnt.setUpdatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			mstEnt.setCreatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			mstEnt.setUpdatedBy("Akash");
			mstEnt.setCreatedBy("Akash");
			entityManager.persist(mstEnt);
			System.out.println("-----"+obj.getVillageList().get(0).getGpId()+"  -------  "+obj.getVillageList().get(0).getVillageName());
			response = "Data Added Successfully !!!";
			
			//List<Long> idLong = new ArrayList<Long>();
			BigDecimal idLong=null;
			List<BigDecimal> ids = new ArrayList<BigDecimal>();
			for(int i=0; i<obj.getVillageList().size(); i++) {
				RICirlceAndVillageBean bean = new RICirlceAndVillageBean();
				bean = obj.getVillageList().get(i);
					for(int j=0;j<obj.getVillageList().get(i).getVillageName().size();j++) {
						
						Query q =entityManager.createNativeQuery
								("select VILLAGE_ID from TBL_VILLAGE_MASTER where VILLAGE_NAME= :id").setParameter
									("id", bean.getVillageName().get(j));
						idLong =  (BigDecimal) q.getSingleResult();
						System.out.println(" Village Id - "+idLong.longValue());
						
						RiCircleVillageMapping mapEnt = new RiCircleVillageMapping();
						mapEnt.setRiCircleId(obj.getVillageList().get(i).getGpId());
						mapEnt.setVillageId(idLong.longValue());
						Query q1 =entityManager.createNativeQuery("select max(RI_CIRCLE_ID) from TBL_RI_CIRCLE_MASTER");
						mapEnt.setRiCirclePK(((BigDecimal) q1.getSingleResult()).longValue());
						entityManager.persist(mapEnt);
						ids.add(idLong);
					}	
			}
			System.out.println(ids);
		
		}catch(Exception e) {
			e.printStackTrace();
			response = "Some Error Occured !!!";
			System.out.println("Response - "+response);
		}
		return response;
	}

	@Override
	public List<DistrictBean> getRiCircleDropdownLists() {
		
		List<DistrictBean> beanList = new ArrayList<DistrictBean>();
		
//		Query query = entityManager.createNativeQuery("select * from TBL_RICIRCLE_TEST where LEVEL_ID= :id", RICircleEntity.class);
//			  query.setParameter("id", 1);
//		List<RICircleEntity> riObj = query.getResultList();
//		for(RICircleEntity en:riObj) {
//			RICircleDropdownBean beanObj = new RICircleDropdownBean();
//			beanObj.setLevelDetailId(en.getLevelDetailID());
//			beanObj.setLevelName(en.getLevelName());
//			beanList.add(beanObj);
//		}
		
		Query query = entityManager.createNativeQuery("select * from TBL_DISTRICT_MST ", District.class);
				List<District> dObj = query.getResultList();
				for(District en:dObj) {
					DistrictBean beanObj = new DistrictBean();
					beanObj.setDistId(en.getDistrictId());
					beanObj.setDistName(en.getDistrictName());
					beanList.add(beanObj);
				}
		
		return beanList;
	}

	@Override
	public List<dropdownBean> getOnClickLists(RICircleBean obj) {
		
		List<dropdownBean> beanList = new ArrayList<dropdownBean>();
		
			if(obj.getKey().matches("Dist")) {
				System.out.println("---Coming Brother---");
				
				Query query1 = entityManager.createNativeQuery
						("Select SUB_DIVISION_NAME from SUB_DIVISION_MASTER where DISTRICT_ID= :id").setParameter("id", (long)obj.getId());
				List<String> names = query1.getResultList();
				Query query = entityManager.createNativeQuery
						("Select SUB_DIVISION_ID from SUB_DIVISION_MASTER where DISTRICT_ID= :id").setParameter("id",(long) obj.getId());
				List<BigDecimal> id = query.getResultList();
				
					for(int i=0;i<id.size();i++) {
						dropdownBean beanObj = new dropdownBean();
						beanObj.setId((id.get(i).longValue()));
						beanObj.setName(names.get(i));
						beanObj.setToken("subDiv");
						
						beanList.add(beanObj);
						System.out.println("Id  - "+id.get(i)+" Name  - "+names.get(i));
					}
			}else if(obj.getKey().matches("subDiv")) {
				System.out.println("---Coming Brother---");
				
				Query query1 = entityManager.createNativeQuery
						("Select TEHSIL_NAME from TBL_TEHSIL_MASTER where SUBDIVISION_ID= :id").setParameter("id",(long) obj.getId());
				List<String> names = query1.getResultList();
				Query query = entityManager.createNativeQuery
						("Select TEHSIL_ID from TBL_TEHSIL_MASTER where SUBDIVISION_ID= :id").setParameter("id", (long) obj.getId());
				List<BigDecimal> id = query.getResultList();
				
					for(int i=0;i<id.size();i++) {
						dropdownBean beanObj = new dropdownBean();
						beanObj.setId((id.get(i).longValue()));
						beanObj.setName(names.get(i));
						beanObj.setToken("tehsil");
						beanList.add(beanObj);
						System.out.println("Id  - "+id.get(i)+" Name  - "+names.get(i));
					}
			}else if(obj.getKey().matches("tehsil")) {
				System.out.println("---Coming Brother---");
				
				Query query1 = entityManager.createNativeQuery
						("Select RI_CIRCLE_NAME from TBL_RI_CIRCLE_MASTER where TEHSIL_ID= :id").setParameter("id",(long) obj.getId());
				List<String> names = query1.getResultList();
				Query query = entityManager.createNativeQuery
						("Select RI_CIRCLE_ID from TBL_RI_CIRCLE_MASTER where TEHSIL_ID= :id").setParameter("id",(long) obj.getId());
				List<BigDecimal> id = query.getResultList();
				
					for(int i=0;i<id.size();i++) {
						dropdownBean beanObj = new dropdownBean();
						beanObj.setId((id.get(i).longValue()));
						beanObj.setName(names.get(i));
						beanObj.setToken("riCirlce");
						beanList.add(beanObj);
						System.out.println("Id  - "+id.get(i)+" Name  - "+names.get(i));
					}
			}else if(obj.getKey().matches("gramPanchayat")) {
				System.out.println("---Coming Brother---");
				
				Query query1 = entityManager.createNativeQuery
						("Select VILLAGE_NAME from TBL_VILLAGE_MASTER where RI_CIRCLE= :id").setParameter("id", (long)obj.getId());
				List<String> names = query1.getResultList();
				Query query = entityManager.createNativeQuery
						("Select VILLAGE_ID from TBL_VILLAGE_MASTER where RI_CIRCLE= :id").setParameter("id", (long)obj.getId());
				List<BigDecimal> id = query.getResultList();
				
					for(int i=0;i<id.size();i++) {
						dropdownBean beanObj = new dropdownBean();
						beanObj.setId((id.get(i).longValue()));
						beanObj.setName(names.get(i));
						beanObj.setToken("village");
						beanList.add(beanObj);
						System.out.println("Id  - "+id.get(i)+" Name  - "+names.get(i));
					}
			}else if(obj.getKey().matches("block")) {
				System.out.println("---Coming Brother---");
				
				Query query1 = entityManager.createNativeQuery
						("Select BLOCK_NAME from TBL_BLOCK_MASTER where DISTRICT_ID= :id").setParameter("id", (long)obj.getId());
				List<String> names = query1.getResultList();
				Query query = entityManager.createNativeQuery
						("Select BLOCK_ID from TBL_BLOCK_MASTER where DISTRICT_ID= :id").setParameter("id", (long)obj.getId());
				List<BigDecimal> id = query.getResultList();
				
					for(int i=0;i<id.size();i++) {
						dropdownBean beanObj = new dropdownBean();
						beanObj.setId((id.get(i).longValue()));
						beanObj.setName(names.get(i));
						beanObj.setToken("block");
						beanList.add(beanObj);
						System.out.println("Id  - "+id.get(i)+" Name  - "+names.get(i));
					}
			}else if(obj.getKey().matches("blockS")) {
						System.out.println("---Coming Brother---");
						
						Query query1 = entityManager.createNativeQuery
								("Select BLOCK_NAME from TBL_BLOCK_MASTER where SUB_DIVISION_ID= :id").setParameter("id", (long)obj.getId());
						List<String> names = query1.getResultList();
						Query query = entityManager.createNativeQuery
								("Select BLOCK_ID from TBL_BLOCK_MASTER where SUB_DIVISION_ID= :id").setParameter("id", (long)obj.getId());
						List<BigDecimal> id = query.getResultList();
						
							for(int i=0;i<id.size();i++) {
								dropdownBean beanObj = new dropdownBean();
								beanObj.setId((id.get(i).longValue()));
								beanObj.setName(names.get(i));
								beanObj.setToken("blockS");
								beanList.add(beanObj);
								System.out.println("Id  - "+id.get(i)+" Name  - "+names.get(i));
							}
					
			}else if(obj.getKey().matches("blockVill")) {
				System.out.println("---Coming Brother---");
				
				Query query1 = entityManager.createNativeQuery
						("Select VILLAGE_NAME from TBL_VILLAGE_MASTER where BLOCK_ID= :id").setParameter("id", (long)obj.getId());
				List<String> names = query1.getResultList();
				Query query = entityManager.createNativeQuery
						("Select VILLAGE_ID from TBL_VILLAGE_MASTER where BLOCK_ID= :id").setParameter("id", (long)obj.getId());
				List<BigDecimal> id = query.getResultList();
				
					for(int i=0;i<id.size();i++) {
						dropdownBean beanObj = new dropdownBean();
						beanObj.setId((id.get(i).longValue()));
						beanObj.setName(names.get(i));
						beanObj.setToken("blockVill");
						beanList.add(beanObj);
						System.out.println("Id  - "+id.get(i)+" Name  - "+names.get(i));
					}
			}else if(obj.getKey().matches("blockPs")) {
				System.out.println("---Coming Brother---");
				
				Query query1 = entityManager.createNativeQuery
						("Select POLICE_STATION_NAME from TBL_POLICE_STATION_MASTER where BLOCK_ID= :id").setParameter("id", (long)obj.getId());
				List<String> names = query1.getResultList();
				Query query = entityManager.createNativeQuery
						("Select POLICE_STATION_ID from TBL_POLICE_STATION_MASTER where BLOCK_ID= :id").setParameter("id", (long)obj.getId());
				List<BigDecimal> id = query.getResultList();
				
					for(int i=0;i<id.size();i++) {
						dropdownBean beanObj = new dropdownBean();
						beanObj.setId((id.get(i).longValue()));
						beanObj.setName(names.get(i));
						beanObj.setToken("blockPs");
						beanList.add(beanObj);
						System.out.println("Id  - "+id.get(i)+" Name  - "+names.get(i));
					}
			}else if(obj.getKey().matches("Dept")) {
				System.out.println("---Coming Brother---");
				
				Query query1 = entityManager.createNativeQuery
						("Select SCHEMENAME from TBL_MST_SCHEME_MASTER where DEPTID= :id").setParameter("id", (long)obj.getId());
				List<String> names = query1.getResultList();
				Query query = entityManager.createNativeQuery
						("Select SCHEMEID from TBL_MST_SCHEME_MASTER where DEPTID= :id").setParameter("id", (long)obj.getId());
				List<BigDecimal> id = query.getResultList();
				
					for(int i=0;i<id.size();i++) {
						dropdownBean beanObj = new dropdownBean();
						beanObj.setId((id.get(i).longValue()));
						beanObj.setName(names.get(i));
						beanObj.setToken("dept");
						beanList.add(beanObj);
						System.out.println("Id  - "+id.get(i)+" Name  - "+names.get(i));
					}
			}
//		if(paramValue.matches("RiCirle1")) {
//			System.out.println("ParamValue ------ "+paramValue);
//			Query query = entityManager.createNativeQuery("select * from TBL_RICIRCLE_TEST where LEVEL_ID= :id and PARENT_ID= :parentId", RICircleEntity.class);
//			query.setParameter("id", 2);
//			query.setParameter("parentId", 1);
//			List<RICircleEntity> riObj = query.getResultList();
//			for(RICircleEntity en:riObj) {
//				RICircleDropdownBean beanObj = new RICircleDropdownBean();
//				beanObj.setLevelDetailId(en.getLevelDetailID());
//				beanObj.setLevelName(en.getLevelName());
//				beanList.add(beanObj);
//			}
//		}else if(paramValue.matches("RiCirle2")) {
//			Query query = entityManager.createNativeQuery("select * from TBL_RICIRCLE_TEST where LEVEL_ID= :id and PARENT_ID= :parentId", RICircleEntity.class);
//			query.setParameter("id", 2);
//			query.setParameter("parentId", 2);
//			List<RICircleEntity> riObj = query.getResultList();
//			for(RICircleEntity en:riObj) {
//				RICircleDropdownBean beanObj = new RICircleDropdownBean();
//				beanObj.setLevelDetailId(en.getLevelDetailID());
//				beanObj.setLevelName(en.getLevelName());
//				beanList.add(beanObj);
//			}
//			
//		}else {
//			Query query = entityManager.createNativeQuery("select * from TBL_RICIRCLE_TEST where LEVEL_ID= :id and PARENT_ID= :parentId", RICircleEntity.class);
//			query.setParameter("id", 2);
//			query.setParameter("parentId", 3);
//			List<RICircleEntity> riObj = query.getResultList();
//			for(RICircleEntity en:riObj) {
//				RICircleDropdownBean beanObj = new RICircleDropdownBean();
//				beanObj.setLevelDetailId(en.getLevelDetailID());
//				beanObj.setLevelName(en.getLevelName());
//				beanList.add(beanObj);
//			}
//			
//		}
		
//		RICircleEntity levelId =  entityManager.createNamedQuery("getLevelDetailId",RICircleEntity.class).setParameter("levelName", paramValue).getSingleResult();
//		Query query1 = entityManager.createNativeQuery("select * from TBL_RICIRCLE_TEST where LEVEL_ID= :id and PARENT_ID= :parentId", RICircleEntity.class);
//		query1.setParameter("id", 2);
//		query1.setParameter("parentId", levelId.getLevelDetailID());
//		List<RICircleEntity> riObj = query1.getResultList();
//		for(RICircleEntity en:riObj) {
//			RICircleDropdownBean beanObj = new RICircleDropdownBean();
//			beanObj.setLevelDetailId(en.getLevelDetailID());
//			beanObj.setLevelName(en.getLevelName());
//			beanList.add(beanObj);
//		}
			return beanList;
	}

	@Override
	public List<RICircleBean> getRiCircleListView() {
		
		List<RICircleBean> viewList = new ArrayList<RICircleBean>();
		List<RICircleMasterEntity> entList = new ArrayList<RICircleMasterEntity>();
		try {
			 SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
			 
			Query q = entityManager.createNativeQuery("select * from TBL_RI_CIRCLE_MASTER",RICircleMasterEntity.class);
			entList = q.getResultList();
			for(RICircleMasterEntity en:entList) {
				RICircleBean bean = new RICircleBean();
					bean.setRiCircleId(en.getRiCircleId());
					bean.setRiCircleName(en.getRiCircleName());
					bean.setNotiNo(en.getNotificationNo());
					bean.setNotiDate(formatter.format(en.getNotificationDate()));
					Query q1 = entityManager.createNativeQuery
							("select DISTRICT_NAME from TBL_DISTRICT_MST where DISTRICT_ID = :id").setParameter("id", en.getDistId());
					bean.setDistName((String) q1.getSingleResult());
					Query q2 = entityManager.createNativeQuery
							("select SUB_DIVISION_NAME from SUB_DIVISION_MASTER where SUB_DIVISION_ID = :id").setParameter("id", en.getSubDivisionId());
					bean.setSubDivName((String) q2.getSingleResult());
					Query q3 = entityManager.createNativeQuery
							("select TEHSIL_NAME from TBL_TEHSIL_MASTER where TEHSIL_ID = :id").setParameter("id", en.getTehsilId());
					bean.setTehsilName((String) q3.getSingleResult());
					//bean.setNotiDate((formatter.format((en.getNotificationDate()))));
					if(en.getActiveStatus().matches("Y")) {
						bean.setStatus("Active");
					}else {
						bean.setStatus("De-Active");
					}
					viewList.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return viewList;
	}

	@Transactional
	@Override
	public void changeRIstatus(Long id) {
		
		try {
			Query q = entityManager.createNativeQuery
					("select * from TBL_RI_CIRCLE_MASTER where RI_CIRCLE_ID= :id",RICircleMasterEntity.class).setParameter("id", id);
			RICircleMasterEntity enObj =(RICircleMasterEntity) q.getSingleResult(); 
			
			if(enObj.getActiveStatus().matches("Y")){
				enObj.setActiveStatus("N");
			}else {
				enObj.setActiveStatus("Y");
			}
			entityManager.persist(enObj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public RICircleBean getListForRiEditById(Long id) {
		
		RICircleBean bean = new RICircleBean();
		List<RiCircleVillageMapping> mapEnt = new ArrayList<RiCircleVillageMapping>();
		List<RICirlceAndVillageBean> riVil = new ArrayList<RICirlceAndVillageBean>();
		List<String> villageName = new ArrayList<String>();
		List<Long> villageNameId = new ArrayList<Long>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		try {
			
			Query q =entityManager.createNativeQuery
					("select * from TBL_RI_CIRCLE_MASTER where RI_CIRCLE_ID = :id",	RICircleMasterEntity.class).setParameter("id", id);
			RICircleMasterEntity ent = (RICircleMasterEntity) q.getSingleResult();
			
			bean.setRiCircleId(ent.getRiCircleId());
			bean.setRiCircleName(ent.getRiCircleName());
			bean.setNotiNo(ent.getNotificationNo());
			bean.setDist(ent.getDistId());
			bean.setSubDivision(ent.getSubDivisionId());
			bean.setTehsil(ent.getTehsilId());
			bean.setNotiDate(formatter.format(ent.getNotificationDate()));
			
			Query q1 = entityManager.createNativeQuery
					("select * from TBL_RICIRCLE_VILLAGE_MAPPING where RI_CIRCLE_PRIMARY_ID = :id",
							RiCircleVillageMapping.class).setParameter("id", ent.getRiCircleId());
			
				mapEnt = q1.getResultList();
				
					
					
					//if( mapEnt.size() != 1 && i != (mapEnt.size() - 1) && mapEnt.get(i).getRiCircleId() == mapEnt.get(i+1).getRiCircleId() ) {
				RICirlceAndVillageBean riVill = new RICirlceAndVillageBean(); 
					for(int i=0;i<mapEnt.size();i++) {
						
						
						riVill.setGpId(mapEnt.get(i).getRiCircleId());
						Query q5 = entityManager.createNativeQuery
								("select RI_CIRCLE_NAME from TBL_RI_CIRCLE_MASTER where RI_CIRCLE_ID = :id");
						q5.setParameter("id", mapEnt.get(i).getRiCircleId());
						
						riVill.setGpName((String)q5.getSingleResult() );
						//for(int j =0;j<mapEnt.size();j++) {
								
							Query q2 = entityManager.createNativeQuery
									("select VILLAGE_NAME from TBL_VILLAGE_MASTER where VILLAGE_ID = :id").setParameter
										("id",mapEnt.get(i).getVillageId());
//							Query q3 = entityManager.createNamedQuery
//									("select VILLAGE_ID from TBL_VILLAGE_MASTER where VILLAGE_ID = :id").setParameter
//										("id",mapEnt.get(j).getVillageId());
							 Long villId = mapEnt.get(i).getVillageId();
							 String villName = (String) q2.getSingleResult();
							 villageName.add(villName);
							 villageNameId.add(villId);
					}
						riVill.setVillageName(villageName);
						riVill.setVillageNameId(villageNameId);
						riVil.add(riVill);
						System.out.println("Village List - "+riVill.getVillageName());
//					}else if(mapEnt.size() == 1){
//
//						riVill.setGpId(mapEnt.get(i).getRiCircleId());
//						Query q5 = entityManager.createNativeQuery
//								("select RI_CIRCLE_NAME from TBL_RI_CIRCLE_MASTER where RI_CIRCLE_ID = :id");
//						q5.setParameter("id", mapEnt.get(i).getRiCircleId());
//						
//						riVill.setGpName((String)q5.getSingleResult() );
//						for(int j =0;j<mapEnt.size();j++) {
//								
//							Query q2 = entityManager.createNativeQuery
//									("select VILLAGE_NAME from TBL_VILLAGE_MASTER where VILLAGE_ID = :id").setParameter
//										("id",mapEnt.get(j).getVillageId());
////							Query q3 = entityManager.createNamedQuery
////									("select VILLAGE_ID from TBL_VILLAGE_MASTER where VILLAGE_ID = :id").setParameter
////										("id",mapEnt.get(j).getVillageId());
//							 Long villId = mapEnt.get(j).getVillageId();
//							 String villName = (String) q2.getSingleResult();
//							 villageName.add(villName);
//							 villageNameId.add(villId);
//						}
//						riVill.setVillageName(villageName);
//						riVill.setVillageNameId(villageNameId);
//						riVil.add(riVill);
//						System.out.println("Village List - "+riVill.getVillageName());
//					}
				
				
			//System.out.println("Data For RI Edit - "+mapEnt.get(0).getRiCirclePK());
		
		}catch(Exception e) {
			
			e.printStackTrace();
			
		}
		bean.setVillageList(riVil);
		return bean;
	}

	@Transactional
	@Override
	public void updateRiCircle(RICircleBean obj) {
		
		RICircleMasterEntity mstEnt = new RICircleMasterEntity();
		String[] splittedItems= obj.getNotiDate().split("-");
		int date =Integer.parseInt(splittedItems[0]);
		int month =Integer.parseInt(splittedItems[1]);
		int year = Integer.parseInt(splittedItems[2]);
		List<RiCircleVillageMapping> mapEnt = new ArrayList<RiCircleVillageMapping>();
		try {
		Query qq = entityManager.createNativeQuery
					("select * from TBL_RI_CIRCLE_MASTER where RI_CIRCLE_ID =:id",RICircleMasterEntity.class).setParameter
						("id", obj.getRiCircleId());
		mstEnt = (RICircleMasterEntity) qq.getSingleResult();
		
		mstEnt.setRiCircleName(obj.getRiCircleName());
		mstEnt.setNotificationNo(obj.getNotiNo());
		mstEnt.setNotificationDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
		mstEnt.setDistId(obj.getDist());
		mstEnt.setSubDivisionId(obj.getSubDivision());
		mstEnt.setTehsilId(obj.getTehsil());
		mstEnt.setAction("Ok");
		mstEnt.setActiveStatus("Y");
		mstEnt.setUpdatedOn(new Timestamp(0, 0, 0, 0, 0, 0, 0));
		mstEnt.setCreatedOn(new Timestamp(0, 0, 0, 0, 0, 0, 0));
		mstEnt.setUpdatedBy("Akash");
		mstEnt.setCreatedBy("Akash");
		entityManager.persist(mstEnt);
		
		Query q =entityManager.createNativeQuery
				("select * from TBL_RICIRCLE_VILLAGE_MAPPING where RI_CIRCLE_PRIMARY_ID= :id",RiCircleVillageMapping.class).setParameter
					("id", obj.getRiCircleId());
		mapEnt = q.getResultList();
		
		for(int i=0; i<obj.getVillageList().size(); i++) {
			
			RICirlceAndVillageBean bean = new RICirlceAndVillageBean();
			bean = obj.getVillageList().get(i);
			
			if(mapEnt.size() == obj.getVillageList().get(i).getVillageName().size()) {
				for(int j=0;j<obj.getVillageList().get(i).getVillageName().size();j++) {
					
					
					mapEnt.get(j).setRiCircleId(obj.getVillageList().get(i).getGpId());
					
					mapEnt.get(j).setVillageId(obj.getVillageList().get(i).getVillageNameId().get(j));
					entityManager.persist(mapEnt.get(j));
				}
				}else if(mapEnt.size() < obj.getVillageList().get(i).getVillageName().size()) {
					for(int j=0;j<obj.getVillageList().get(i).getVillageName().size();j++) {
						if(j < mapEnt.size()) {
							mapEnt.get(j).setRiCircleId(obj.getVillageList().get(i).getGpId());
							
							mapEnt.get(j).setVillageId(obj.getVillageList().get(i).getVillageNameId().get(j));
							entityManager.persist(mapEnt.get(j));
						}else {
							RiCircleVillageMapping en = new RiCircleVillageMapping();
							en.setRiCircleId(obj.getVillageList().get(i).getGpId());
							en.setVillageId(obj.getVillageList().get(i).getVillageNameId().get(j));
							en.setRiCirclePK(obj.getRiCircleId());
							entityManager.persist(en);
							
						}
					}
				}else {
					for(int j=0;j<mapEnt.size();j++) {
						if(j < obj.getVillageList().get(i).getVillageName().size()) {
							mapEnt.get(j).setRiCircleId(obj.getVillageList().get(i).getGpId());
							
							mapEnt.get(j).setVillageId(obj.getVillageList().get(i).getVillageNameId().get(j));
							entityManager.persist(mapEnt.get(j));
						}else {
							Query query = entityManager.createNativeQuery
									("delete from TBL_RICIRCLE_VILLAGE_MAPPING where VILLAGE_MAPPING_ID =:id");
							query.setParameter("id", mapEnt.get(j).getRiVillMapId());
							int deletedRows = query.executeUpdate();
							System.out.println("Deleted Rows -------------- "+deletedRows +"  ID  ----- "+mapEnt.get(j).getRiVillMapId());
						}
					}
				}
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	//Police Station By Akash Deep

	@Override
	public List<Object> getGPandVillListsBucket(Long id) {
		
		List<String> gpList = new ArrayList<String>();
		List<BigDecimal> gpListId = new ArrayList<BigDecimal>();
		List<String> villList = new ArrayList<String>();
		List<BigDecimal> villListId = new ArrayList<BigDecimal>();
		List<Object> objL = new ArrayList<Object>();
		try {
			
			Query q = entityManager.createNativeQuery("select GP_NAME from TBL_GP_MASTER where BLOCK_ID = :id").setParameter("id", id);
			gpList = q.getResultList();
			
			Query q1 = entityManager.createNativeQuery("select GP_ID from TBL_GP_MASTER where BLOCK_ID = :id").setParameter("id", id);
			gpListId = q1.getResultList();
			objL.add(gpList);
			for(BigDecimal bg: gpListId) {
			Query q2 = entityManager.createNativeQuery("select VILLAGE_NAME from TBL_VILLAGE_MASTER where GP_ID = :id").setParameter("id", bg.longValue());
			villList = q2.getResultList();
			
			Query q3 = entityManager.createNativeQuery("select VILLAGE_ID from TBL_VILLAGE_MASTER where GP_ID = :id").setParameter("id", bg.longValue());
			villListId = q3.getResultList();
			objL.add(villList);
			}
			System.out.println(objL);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return objL;
		
	}

	@Transactional
	@Override
	public String savePoliceStation(PoliceStationBean obj) {
	
		
		policeStationEntity entObj = new policeStationEntity();
		String response = "";
		try {
			String[] splittedItems= obj.getNotiDate().split("-");
			int date =Integer.parseInt(splittedItems[0]);
			int month =Integer.parseInt(splittedItems[1]);
			int year = Integer.parseInt(splittedItems[2]);
			
			entObj.setPoliceStationName(obj.getPsName());
			entObj.setPoliceStationCatg(5L);
			entObj.setNotiNo(obj.getNotiNo());
			entObj.setNotiDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			entObj.setTypeOfPs(obj.getTypeOfPs());
			entObj.setCreatedDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			entObj.setCreatedBy(5L);
			entObj.setUpdatedDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			entObj.setUpdatedBy(5L);
			entObj.setActiveStatus("Y");
			entObj.setDistId(obj.getDist());
			entObj.setBlockId(obj.getBlock());
			entityManager.persist(entObj);
			
			System.out.println("Data - - ---- -"+obj.getFinalBucketList().get(0));
			
			for(int i=0;i<obj.getFinalBucketList().size();i++) {
				PoliceStnVillMapEntity map = new PoliceStnVillMapEntity();
				Query q = entityManager.createNativeQuery
						("select VILLAGE_ID from TBL_VILLAGE_MASTER where VILLAGE_NAME =:name").setParameter
							("name", obj.getFinalBucketList().get(i).getName());
				Query q1 =entityManager.createNativeQuery("select max(POLICE_STATION_ID) from TBL_POLICE_STATION_MASTER");
				map.setPoliceStationId(((BigDecimal) q1.getSingleResult()).longValue());
				BigDecimal bd = (BigDecimal) q.getSingleResult();
				map.setVillageId(bd.longValue());
				map.setStatus("Y");
				entityManager.persist(map);
			}
			response = "Data Added Successfully !!!";
		}catch(Exception e) {
			e.printStackTrace();
			response = "Some Error Occurred !!!";
		}
		return response;
		
	}

	@Override
	public List<PoliceStationBean> getPoliceStationView() {
		
		List<policeStationEntity> entList = new ArrayList<policeStationEntity>();
		List<PoliceStationBean> bean = new ArrayList<PoliceStationBean>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		
		try {
			
			Query q = entityManager.createNativeQuery
					("select * from TBL_POLICE_STATION_MASTER",policeStationEntity.class);
			entList = q.getResultList();
			for(policeStationEntity ent:entList) {
				PoliceStationBean b = new PoliceStationBean();
				b.setPsStId(ent.getPoliceStationId());
				b.setPsName(ent.getPoliceStationName());
				Query q1 = entityManager.createNativeQuery("select DISTRICT_NAME from TBL_DISTRICT_MST where DISTRICT_ID = :id").setParameter("id",ent.getDistId());
				
				b.setDistName((String) q1.getSingleResult());
				b.setNotiNo(ent.getNotiNo());
				b.setNotiDate(formatter.format((ent.getNotiDate())));
				Query q2 = entityManager.createNativeQuery("select POLICE_STATION_TYPES from TBL_POLICE_STATION_TYPES where ID = :id").setParameter("id", ent.getTypeOfPs());
				b.setTopsName((String) q2.getSingleResult());
				b.setStatus(ent.getActiveStatus());
				b.setTypeOfPs(ent.getTypeOfPs());
				b.setDist(ent.getDistId());
				b.setBlock(ent.getBlockId());
				
				bean.add(b);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public List<Object> getTypesOfPoliceStation() {
		
		List<Object> obj = new ArrayList<Object>();
		try {
			Query q = entityManager.createNativeQuery("select * from TBL_POLICE_STATION_TYPES");
			obj = q.getResultList();
			System.out.println("  -----  "+obj);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return obj;
	}

	@Transactional
	@Override
	public void changePsStatus(Long id) {
		
		try {
			Query q = entityManager.createNativeQuery
					("select * from TBL_POLICE_STATION_MASTER where POLICE_STATION_ID= :id",policeStationEntity.class).setParameter("id", id);
			policeStationEntity enObj =(policeStationEntity) q.getSingleResult(); 
			
			if(enObj.getActiveStatus().matches("Y")){
				enObj.setActiveStatus("N");
			}else {
				enObj.setActiveStatus("Y");
			}
			entityManager.persist(enObj);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<finalBucketListBean> getBucketVillageListById(Long id) {
		
		List<PoliceStnVillMapEntity> mapList = new ArrayList<PoliceStnVillMapEntity>();
		List<finalBucketListBean> beanList = new ArrayList<finalBucketListBean>();
		try {
			
			Query q = entityManager.createNativeQuery("select * from TBL_POLICE_STA_VILLAGE_MAPPING where POLICE_STATION_ID =:id",PoliceStnVillMapEntity.class).setParameter
					("id", id);
			mapList = q.getResultList();
			
			for(int i=0; i<mapList.size();i++) {
				finalBucketListBean b = new finalBucketListBean();
				Query q1 = entityManager.createNativeQuery("select VILLAGE_NAME from TBL_VILLAGE_MASTER where VILLAGE_ID = :id").setParameter
						("id", mapList.get(i).getVillageId());
				b.setName((String) q1.getSingleResult());
				beanList.add(b);
			}

			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return beanList;
		
	}

	@Transactional
	@Override
	public void updatePoliceStation(PoliceStationBean obj) {
		policeStationEntity ent = new policeStationEntity();
		List<PoliceStnVillMapEntity> mapList = new ArrayList<PoliceStnVillMapEntity>();
		try {
			String[] splittedItems= obj.getNotiDate().split("-");
			int date =Integer.parseInt(splittedItems[0]);
			int month =Integer.parseInt(splittedItems[1]);
			int year = Integer.parseInt(splittedItems[2]);
			
			Query q1 = entityManager.createNativeQuery
					("select * from TBL_POLICE_STATION_MASTER where POLICE_STATION_ID = :id",policeStationEntity.class).setParameter("id", obj.getPsStId());
			ent = (policeStationEntity) q1.getSingleResult();
			ent.setPoliceStationName(obj.getPsName());
			ent.setNotiNo(obj.getNotiNo());
			ent.setNotiDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			ent.setUpdatedDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			ent.setUpdatedBy(5L);
			ent.setDistId(obj.getDist());
			ent.setBlockId(obj.getBlock());
			ent.setTypeOfPs(obj.getTypeOfPs());
			entityManager.persist(ent);
			
			Query q2 = entityManager.createNativeQuery
					("select * from TBL_POLICE_STA_VILLAGE_MAPPING where POLICE_STATION_ID = :id",PoliceStnVillMapEntity.class).setParameter("id", obj.getPsStId());
			mapList = q2.getResultList();
			
			if(mapList.size() == obj.getFinalBucketList().size()) {
				for(int i=0;i<obj.getFinalBucketList().size();i++) {
					
					Query q3 = entityManager.createNativeQuery
							("select VILLAGE_ID from TBL_VILLAGE_MASTER where VILLAGE_NAME =:name").setParameter
								("name", obj.getFinalBucketList().get(i).getName());
					
					
					mapList.get(i).setVillageId((((BigDecimal) q3.getSingleResult()).longValue()));
					
					entityManager.persist(mapList.get(i));
				}
			}else if(mapList.size()< obj.getFinalBucketList().size()) {
				for(int i=0;i<obj.getFinalBucketList().size();i++) {
					if(mapList.size() > i) {
					Query q3 = entityManager.createNativeQuery
							("select VILLAGE_ID from TBL_VILLAGE_MASTER where VILLAGE_NAME =:name").setParameter
								("name", obj.getFinalBucketList().get(i).getName());
					
					
					mapList.get(i).setVillageId((((BigDecimal) q3.getSingleResult()).longValue()));
					
					entityManager.persist(mapList.get(i));
					}else {
						PoliceStnVillMapEntity map = new PoliceStnVillMapEntity();
						Query q4 = entityManager.createNativeQuery
								("select VILLAGE_ID from TBL_VILLAGE_MASTER where VILLAGE_NAME =:name").setParameter
									("name", obj.getFinalBucketList().get(i).getName());
						
						map.setPoliceStationId(obj.getPsStId());
						BigDecimal bd = (BigDecimal) q4.getSingleResult();
						map.setVillageId(bd.longValue());
						map.setStatus("Y");
						entityManager.persist(map);
					}
				}
			}else {
				for(int i=0;i<mapList.size();i++) {
					if(obj.getFinalBucketList().size() > i) {
					Query q3 = entityManager.createNativeQuery
							("select VILLAGE_ID from TBL_VILLAGE_MASTER where VILLAGE_NAME =:name").setParameter
								("name", obj.getFinalBucketList().get(i).getName());
					
					
					mapList.get(i).setVillageId((((BigDecimal) q3.getSingleResult()).longValue()));
					
					entityManager.persist(mapList.get(i));
					}else {
						
						Query q5 = entityManager.createNativeQuery
								("delete from TBL_POLICE_STA_VILLAGE_MAPPING where POLICE_STATION_ID =:name").setParameter
									("name", mapList.get(i).getPoliceStVillMapId());
						
						q5.executeUpdate();
					}
				
			}
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Transactional
	@Override
	public String saveGPMaster(GpBean obj) {
		
		String response = "";
		GpEntity ent = new GpEntity();
		String[] splittedItems= obj.getNotiDate().split("-");
		int date =Integer.parseInt(splittedItems[0]);
		int month =Integer.parseInt(splittedItems[1]);
		int year = Integer.parseInt(splittedItems[2]);
		
		try {
			ent.setGpName(obj.getGPName());
			ent.setLgdCode(Long.parseLong(obj.getLGDCode()));
			ent.setResolutionNo(obj.getResnoo());
			ent.setNotificationDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			ent.setDistId(obj.getDist());
			ent.setSubDivisionId(obj.getSubDiv());
			ent.setBlockId(obj.getBlock());
			ent.setActiveStatus("Y");
			ent.setCreatedBy("Akash");
			ent.setCreatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			ent.setUpdatedBy("Akash");
			ent.setUpdatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			
			entityManager.persist(ent);
			Query q1 =entityManager.createNativeQuery("select max(GP_ID) from TBL_GP_MASTER");
			
			for(dropdownBean str:obj.getVillage()) {
				GPVillageMapEntity map = new GPVillageMapEntity();
				
				map.setGpId(((BigDecimal) q1.getSingleResult()).longValue());
				map.setVillageId(str.getId());
				map.setStatus("Y");
				entityManager.persist(map);
			}
			
			response = "Data Added Successfully !!!";
			
		}catch(Exception e) {
			e.printStackTrace();
			response = "Some Error Occurred !!!";
		}
		return response;
	}

	@Override
	public List<GpBean> getGPListView() {
		
		List<GpEntity> list = new ArrayList<GpEntity>();
		List<GpBean> beanList = new ArrayList<>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
		
		
		try {
		Query q1 =entityManager.createNativeQuery("select * from TBL_GP_MASTER",GpEntity.class);
		list = q1.getResultList();
		
		for(GpEntity ent:list) {
			
			GpBean b = new GpBean();
			b.setGpId(ent.getGpId());
			b.setGPName(ent.getGpName());
			Query q = entityManager.createNativeQuery
					("select DISTRICT_NAME from TBL_DISTRICT_MST where DISTRICT_ID = :id").setParameter("id",ent.getDistId());
			Query q2 = entityManager.createNativeQuery
					("select SUB_DIVISION_NAME from SUB_DIVISION_MASTER where SUB_DIVISION_ID = :id").setParameter("id", ent.getSubDivisionId());
			Query q3 = entityManager.createNativeQuery
					("select BLOCK_NAME from TBL_BLOCK_MASTER where BLOCK_ID = :id").setParameter("id",ent.getBlockId());
			
			b.setDistName((String) q.getSingleResult());
			b.setSubDivName((String) q2.getSingleResult());
			b.setBlockName((String) q3.getSingleResult());
			b.setNotiDate(formatter.format(ent.getNotificationDate()));
			b.setStatus(ent.getActiveStatus());
			b.setLgd(ent.getLgdCode());
			b.setResnoo(ent.getResolutionNo());
			b.setDist(ent.getDistId());
			b.setSubDiv(ent.getSubDivisionId());
			b.setBlock(ent.getBlockId());
			b.setStatus(ent.getActiveStatus());
			Query q6 = entityManager.createNativeQuery
					("select count(VILLAGE_ID) from TBL_GP_VILLAGE_MAPPING where GP_ID = :id").setParameter("id", ent.getGpId());
			BigDecimal bd = (BigDecimal) q6.getSingleResult();
			if(bd.longValue() > 0){
			Query q4 = entityManager.createNativeQuery
					("select VILLAGE_ID from TBL_GP_VILLAGE_MAPPING where GP_ID = :id").setParameter("id", ent.getGpId());
			
			List<BigDecimal> map =  q4.getResultList();
			System.out.println(" Village ID Length "+map.size());
			List<String> str = new ArrayList<String>();
			for(BigDecimal en:map) {
				String s = "";
				
				Query q5 = entityManager.createNativeQuery("select village_name from tbl_village_master where village_id = :id").setParameter
						("id",en.longValue());
				s = (String) q5.getSingleResult();
				str.add(s);
			}
				
			b.setVillForView(str);
			
			}
			System.out.println(" Village List Length "+b.getVillForView());
			beanList.add(b);
			
		}
		
		}catch(Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}

	@Transactional
	@Override
	public void changeGPStatus(Long id) {
		
		try {
			Query q = entityManager.createNativeQuery
					("select * from TBL_GP_MASTER where GP_ID= :id",GpEntity.class).setParameter("id", id);
			GpEntity enObj =(GpEntity) q.getSingleResult(); 
			
			if(enObj.getActiveStatus().matches("Y")){
				enObj.setActiveStatus("N");
			}else {
				enObj.setActiveStatus("Y");
			}
			entityManager.persist(enObj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<dropdownBean> getGpVillageById(Long id) {
		
		Query q = entityManager.createNativeQuery
				("select * from TBL_GP_VILLAGE_MAPPING where GP_ID = :id",GPVillageMapEntity.class).setParameter("id", id);
		List<dropdownBean> beanList = new ArrayList<dropdownBean>();
		List<GPVillageMapEntity> map =  q.getResultList();
		
		for(GPVillageMapEntity en:map) {
			
			dropdownBean d = new dropdownBean();
			
			Query q1 = entityManager.createNativeQuery("select village_name from tbl_village_master where village_id = :id").setParameter
					("id",en.getVillageId());
			
			d.setId(en.getVillageId());
			d.setName((String) q1.getSingleResult());
			beanList.add(d);
		}
		return beanList;
	}

	@Transactional
	@Override
	public void updateGPList(GpBean obj) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
		String[] splittedItems= obj.getNotiDate().split("-");
		int date =Integer.parseInt(splittedItems[0]);
		int month =Integer.parseInt(splittedItems[1]);
		int year = Integer.parseInt(splittedItems[2]);
		List<GPVillageMapEntity> map = new ArrayList<GPVillageMapEntity>();
		try {
			Query q1 =entityManager.createNativeQuery("select * from TBL_GP_MASTER where GP_ID = :id",GpEntity.class).setParameter("id",obj.getGpId());
			GpEntity ent = (GpEntity) q1.getSingleResult();
			
			ent.setGpName(obj.getGPName());
			ent.setLgdCode(Long.parseLong(obj.getLGDCode()));
			ent.setResolutionNo(obj.getResnoo());
			ent.setNotificationDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			ent.setDistId(obj.getDist());
			ent.setSubDivisionId(obj.getSubDiv());
			ent.setBlockId(obj.getBlock());
			ent.setUpdatedBy("Akash");
			ent.setUpdatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			
			entityManager.persist(ent);
			
			Query q2 =entityManager.createNativeQuery
					("select * from TBL_GP_VILLAGE_MAPPING where GP_ID = :id",GPVillageMapEntity.class).setParameter("id",obj.getGpId());
			
			 map = q2.getResultList();
			
			if(map.size() == obj.getVillage().size()) {
				for(int i=0; i<map.size(); i++) {
					map.get(i).setVillageId(obj.getVillage().get(i).getId());
					entityManager.persist(map.get(i));
				}
			}else if(map.size() < obj.getVillage().size()) {
				for(int i=0; i<obj.getVillage().size(); i++) {
					if(map.size() > i) {
						map.get(i).setVillageId(obj.getVillage().get(i).getId());
						entityManager.persist(map.get(i));
					}else {
						GPVillageMapEntity m = new GPVillageMapEntity();
						m.setVillageId(obj.getVillage().get(i).getId());
						m.setGpId(obj.getGpId());
						m.setStatus("Y");
						entityManager.persist(m);
					}
				}
			}else {
				for(int i=0; i<map.size(); i++) {
					if(obj.getVillage().size() > i) {
						map.get(i).setVillageId(obj.getVillage().get(i).getId());
						entityManager.persist(map.get(i));
					}else {
						Query q3 =entityManager.createNativeQuery
								("delete  from TBL_GP_VILLAGE_MAPPING where GP_VILLAGE_ID = :id").setParameter("id",map.get(i).getGpVillId());
						q3.executeUpdate();
						
					}
				}
				
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<dropdownBean> getCasteCategory() {
		
		List<dropdownBean> beanList = new ArrayList<dropdownBean>();
		try {
			Query q =entityManager.createNativeQuery("select categoryname from t_caste_category_master");
			List<String> name = q.getResultList();
			
			Query q1 =entityManager.createNativeQuery("select castecatid from t_caste_category_master");
			List<BigDecimal> id = q1.getResultList();
			
			for(int i=0;i<name.size();i++) {
				dropdownBean d = new dropdownBean();
				d.setId(id.get(i).longValue());
				d.setName(name.get(i));
				beanList.add(d);
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}

	@Transactional
	@Override
	public String saveCasteByCategory(casteMasterBean obj) {
		
		String response = "";
		try {
			if(obj.getCategoryId() == 0) {
				throw new Exception("Category Id Is Null");
			}else {
			CasteMasterEntity en = new CasteMasterEntity();
			en.setCastCatId(obj.getCategoryId());
			en.setCastName(obj.getCasteName());
			en.setCreatedBy(5L);
			en.setUpdatedBy(5L);
			en.setStatus(5L);
			en.setCreatedOn(new Date());
			en.setUpdatedOn(new Date());
			entityManager.persist(en);
			response = "Data Added Successfully !!!";
			}
		}catch(Exception e) {
			e.printStackTrace();
			response = "Some Error Occurred !!!";
		}
		return response;
		
	}

	@Override
	public List<casteMasterBean> getCasteMasterView() {
		
		List<Object> obj =new ArrayList<Object>();
		List<CasteMasterEntity> enList = new ArrayList<CasteMasterEntity>();
		List<casteMasterBean> beanList = new ArrayList<casteMasterBean>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
//		Query q =entityManager.createNativeQuery
//			("select m.categoryname,c.castename,c.createdon,c.createdby from t_mst_castemaster c inner join t_caste_category_master m on c.castecatid = m.castecatid");
		Query q =entityManager.createNativeQuery("select * from t_mst_castemaster",CasteMasterEntity.class);
		enList = q.getResultList();
		
		for(CasteMasterEntity e:enList) {
			casteMasterBean b = new casteMasterBean();
			b.setCasteName(e.getCastName());
			Query q1 =entityManager.createNativeQuery("select categoryname from t_caste_category_master where castecatid = :id").setParameter("id", e.getCastCatId());
			b.setCategoryName((String) q1.getSingleResult());
			b.setCreatedBy("Akash");
			b.setCreatedOn(formatter.format(e.getCreatedOn()));
			beanList.add(b);
		}
		
		return beanList;
	}

	@Transactional
	@Override
	public String saveEducationalInstitution(EducationBean obj) {
		
		String response = "";
		EducationEntity ent = new EducationEntity();
		String[] splittedItems= obj.getNotiDate().split("-");
		int date =Integer.parseInt(splittedItems[0]);
		int month =Integer.parseInt(splittedItems[1]);
		int year = Integer.parseInt(splittedItems[2]);
		
		try {
			System.out.println(obj.getInstituteName());
			
			ent.setEduName(obj.getInstituteName());
			ent.setCategory(5L);
			ent.setTypeOfIns(obj.getTypeOfInstitute());
			ent.setTypeOfOwn(obj.getTypeOfOwnership());
			ent.setUniversityId(obj.getUniversityName());
			ent.setNotiNo(obj.getNotiNo());
			ent.setNotiDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			ent.setUdiseCode(obj.getUdiseCode());
			ent.setDistId(obj.getDist());
			ent.setBlockId(obj.getBlock());
			ent.setPsId(obj.getPoliceStation());
			ent.setPincode(obj.getPincode());
			ent.setContactNo(obj.getContactNo());
			ent.setCreatedBy(5L);
			ent.setCreatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			ent.setUpdatedBy(5L);
			ent.setUpdatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			ent.setActiveStatus("Y");
			
			entityManager.persist(ent);
			
			for(int i=0;i<obj.getCoursesOffered().size();i++) {
				
				EducationCourseEntity map= new EducationCourseEntity();
				
				map.setCourseId(obj.getCoursesOffered().get(i).getId());
				map.setEduId(((BigDecimal) (entityManager.createNativeQuery("select max(INSTID) from TBL_MST_EDUCATIONAL_INST").getSingleResult())).longValue());
				entityManager.persist(map);
			}
			response = "Data Added Successfully !!!";
			
		}catch(Exception e) {
			response = "Some Error Occurred !!!";
			e.printStackTrace();
			
		}
		return response;
	}

	@Override
	public List<Object> getEducationDropdownList() {
		
		List<Object> objList = new ArrayList<Object>();
		List<BigDecimal> id = new ArrayList<BigDecimal>();
		List<String> name = new ArrayList<String>();
		List<dropdownBean> dList = new ArrayList<dropdownBean>();
		List<dropdownBean> dListOwn = new ArrayList<dropdownBean>();
		List<dropdownBean> dListUni = new ArrayList<dropdownBean>();
		List<dropdownBean> dListCourse = new ArrayList<dropdownBean>();
		
		try {
			Query q1 =entityManager.createNativeQuery("select insttypeid from tbl_institute_type where active_status = :id").setParameter("id", "Y");;
			id = q1.getResultList();
			
			Query q2 =entityManager.createNativeQuery("select insttypename from tbl_institute_type");
			name = q2.getResultList();
			
			for(int i=0; i<id.size();i++) {
				dropdownBean d = new dropdownBean();
				d.setId(id.get(i).longValue());
				d.setName(name.get(i));
				dList.add(d);
			}
			objList.add(dList);
			
			Query q3 =entityManager.createNativeQuery("select instownershiptypeid from tbl_inst_ownership_type where active_status = :id").setParameter("id", "Y");
			id = q3.getResultList();
			
			Query q4 =entityManager.createNativeQuery("select instownershiptypename from tbl_inst_ownership_type");
			name = q4.getResultList();
			
			for(int i=0; i<id.size();i++) {
				dropdownBean d = new dropdownBean();
				d.setId(id.get(i).longValue());
				d.setName(name.get(i));
				dListOwn.add(d);
			}
			
			objList.add(dListOwn);
			
			Query q5 =entityManager.createNativeQuery("select instuniversityid from tbl_mst_inst_university where active_status = :id").setParameter("id", "Y");
			id = q5.getResultList();
			
			Query q6 =entityManager.createNativeQuery("select instuniversityname from tbl_mst_inst_university");
			name = q6.getResultList();
			
			for(int i=0; i<id.size();i++) {
				dropdownBean d = new dropdownBean();
				d.setId(id.get(i).longValue());
				d.setName(name.get(i));
				dListUni.add(d);
			}
			
			objList.add(dListUni);
			
			Query q7 =entityManager.createNativeQuery("select courseid from tbl_inst_coursemaster where active_status = :id").setParameter("id", "Y");
			id = q7.getResultList();
			
			Query q8 =entityManager.createNativeQuery("select coursename from tbl_inst_coursemaster");
			name = q8.getResultList();
			
			for(int i=0; i<id.size();i++) {
				dropdownBean d = new dropdownBean();
				d.setId(id.get(i).longValue());
				d.setName(name.get(i));
				dListCourse.add(d);
			}
			
			objList.add(dListCourse);
			
			
			System.out.println(objList);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return objList;
	}

	@Override
	public List<EducationBean> getEducationView() {
		
		List<EducationEntity> enList = new ArrayList<EducationEntity>();
		List<EducationBean> beanList = new ArrayList<EducationBean>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

		try {
			Query q =entityManager.createNativeQuery("select * from tbl_mst_educational_inst",EducationEntity.class);
			enList = q.getResultList();
			
			for(EducationEntity e : enList) {
				EducationBean b = new EducationBean();
				List<dropdownBean> dList = new ArrayList<dropdownBean>();
				
				b.setEduId(e.getEduId());
				Query q1 =entityManager.createNativeQuery("select district_name from tbl_district_mst where district_id = :id").setParameter("id", e.getDistId());
				Query q2 =entityManager.createNativeQuery("select instuniversityname from tbl_mst_inst_university where instuniversityid = :id").setParameter("id", e.getUniversityId());
				Query q3 =entityManager.createNativeQuery("select insttypename from tbl_institute_type where insttypeid = :id").setParameter("id", e.getTypeOfIns());
				Query q4 =entityManager.createNativeQuery("select instownershiptypename from tbl_inst_ownership_type where instownershiptypeid = :id").setParameter("id", e.getTypeOfOwn());
				Query q5 =entityManager.createNativeQuery("select police_station_name from tbl_police_station_master where police_station_id = :id").setParameter("id", e.getPsId());
				Query q6 =entityManager.createNativeQuery("select block_name from tbl_block_master where block_id = :id").setParameter("id", e.getBlockId());
				b.setPsName((String) q5.getSingleResult());
				b.setBlockName((String) q6.getSingleResult());
				b.setInstituteName(e.getEduName());
				b.setOwnershipName((String) q4.getSingleResult());
				b.setNotiNo(e.getNotiNo());
				b.setNotiDate(formatter.format(e.getNotiDate()));
				b.setUdiseCode(e.getUdiseCode());
				b.setDistName((String) q1.getSingleResult());
				b.setUniName((String) q2.getSingleResult());
				b.setInsName((String) q3.getSingleResult());
				b.setStatus(e.getActiveStatus());
				b.setPincode(e.getPincode());
				b.setContactNo(e.getContactNo());
				b.setBlock(e.getBlockId());
				b.setDist(e.getDistId());
				b.setPoliceStation(e.getPsId());
				b.setTypeOfInstitute(e.getTypeOfIns());
				b.setTypeOfOwnership(e.getTypeOfOwn());
				b.setUniversityName(e.getUniversityId());
				String courseStr = "";
				List<String> courseStrL = new ArrayList<String>();
				
				List<EducationCourseEntity> em = new ArrayList<EducationCourseEntity>();
				Query q7 =entityManager.createNativeQuery("select * from tbl_inst_courseoffered where instid = :id",EducationCourseEntity.class).setParameter("id", e.getEduId());
				em = q7.getResultList();
				
				for(int i =0;i<em.size();i++) {
					dropdownBean d = new dropdownBean();
					d.setId(em.get(i).getCourseId());
					Query q8 =entityManager.createNativeQuery("select coursename from tbl_inst_coursemaster where courseid = :id").setParameter("id", em.get(i).getCourseId());
					d.setName((String) q8.getSingleResult());
					courseStr = (String) q8.getSingleResult();
					courseStrL.add(courseStr);
					dList.add(d);
				}
				
				b.setCouOffList(courseStrL);
				b.setCoursesOffered(dList);
				beanList.add(b);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}

	@Transactional
	@Override
	public void changeEducationViewStatus(Long id) {

		try {
			Query q = entityManager.createNativeQuery
					("select * from tbl_mst_educational_inst where instid= :id",EducationEntity.class).setParameter("id", id);
			EducationEntity enObj =(EducationEntity) q.getSingleResult(); 
			
			if(enObj.getActiveStatus().matches("Y")){
				enObj.setActiveStatus("N");
			}else {
				enObj.setActiveStatus("Y");
			}
			entityManager.persist(enObj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Transactional
	@Override
	public void updateEducationForm(EducationBean obj) {

		String[] splittedItems= obj.getNotiDate().split("-");
		int date =Integer.parseInt(splittedItems[0]);
		int month =Integer.parseInt(splittedItems[1]);
		int year = Integer.parseInt(splittedItems[2]);
		
		try {
			Query q = entityManager.createNativeQuery
					("select * from tbl_mst_educational_inst where instid= :id",EducationEntity.class).setParameter("id", obj.getEduId());
			EducationEntity ent =(EducationEntity) q.getSingleResult(); 

			ent.setEduName(obj.getInstituteName());
			ent.setCategory(5L);
			ent.setTypeOfIns(obj.getTypeOfInstitute());
			ent.setTypeOfOwn(obj.getTypeOfOwnership());
			ent.setUniversityId(obj.getUniversityName());
			ent.setNotiNo(obj.getNotiNo());
			ent.setNotiDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			ent.setUdiseCode(obj.getUdiseCode());
			ent.setDistId(obj.getDist());
			ent.setBlockId(obj.getBlock());
			ent.setPsId(obj.getPoliceStation());
			ent.setPincode(obj.getPincode());
			ent.setContactNo(obj.getContactNo());
			ent.setUpdatedBy(5L);
			ent.setUpdatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
			
			entityManager.persist(ent);
			
			List<EducationCourseEntity> map = new ArrayList<EducationCourseEntity>();
			Query q1 =entityManager.createNativeQuery("select * from tbl_inst_courseoffered where instid = :id",EducationCourseEntity.class).setParameter("id", obj.getEduId());
			map =  q1.getResultList();
			
			if(map.size() == obj.getCoursesOffered().size()) {
				for(int i=0; i<map.size(); i++) {
					map.get(i).setCourseId(obj.getCoursesOffered().get(i).getId());
					entityManager.persist(map.get(i));
				}
			}else if(map.size() < obj.getCoursesOffered().size()) {
				for(int i=0; i<obj.getCoursesOffered().size(); i++) {
					if(i < map.size()) {
					map.get(i).setCourseId(obj.getCoursesOffered().get(i).getId());
					entityManager.persist(map.get(i));
					}else {
						EducationCourseEntity e = new EducationCourseEntity();
						e.setCourseId(obj.getCoursesOffered().get(i).getId());
						e.setEduId(obj.getEduId());
						entityManager.persist(e);
					}
				}
			}else {
				
					for(int i=0; i<map.size(); i++) {
						if(i < obj.getCoursesOffered().size()) {
						map.get(i).setCourseId(obj.getCoursesOffered().get(i).getId());
						entityManager.persist(map.get(i));
						}else {
							Query q2 = entityManager.createNativeQuery("delete from tbl_inst_courseoffered where instcourseid = :id").setParameter("id",map.get(i).getEduCourseId());
							q2.executeUpdate();
						}
					}
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Transactional
	@Override
	public String saveDepartment(DepartmentBean obj) {

		String response = "";
		try {
			DepartmentEntity en = new DepartmentEntity();
			
			en.setDeptName(obj.getDeptName());
			en.setShortName(obj.getShortName());
			en.setDescription(obj.getDescription());
			en.setCreatedBy(5L);
			en.setCreatedOn(new Date());
			en.setUpdatedBy(5L);
			en.setUpdatedOn(new Date());
			en.setStatus("Y");
			
			entityManager.persist(en);
			response = "Data Added Successfully !!!";
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}

	@Override
	public List<DepartmentBean> getDepartmentView() {

		List<DepartmentEntity> entList = new ArrayList<DepartmentEntity>();
		List<DepartmentBean> beanList = new ArrayList<DepartmentBean>();
		try {
			Query q1 = entityManager.createNativeQuery("select * from tbl_mst_department",DepartmentEntity.class);
			entList = q1.getResultList();
			for(DepartmentEntity e:entList) {
				DepartmentBean b = new DepartmentBean();
				b.setDeptId(e.getDeptId());
				b.setDeptName(e.getDeptName());
				b.setShortName(e.getShortName());
				b.setDescription(e.getDescription());
				b.setStatus(e.getStatus());
				beanList.add(b);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return beanList;
	}

	@Transactional
	@Override
	public void changeDepartmentStatus(Long id) {

		try {
			Query q = entityManager.createNativeQuery
					("select * from tbl_mst_department where deptid= :id",DepartmentEntity.class).setParameter("id", id);
			DepartmentEntity enObj =(DepartmentEntity) q.getSingleResult(); 
			
			if(enObj.getStatus().matches("Y")){
				enObj.setStatus("N");
			}else {
				enObj.setStatus("Y");
			}
			entityManager.persist(enObj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}

	@Transactional
	@Override
	public void updateDepartment(DepartmentBean obj) {

		try {
			Query q = entityManager.createNativeQuery
					("select * from tbl_mst_department where deptid= :id",DepartmentEntity.class).setParameter("id", obj.getDeptId());
			DepartmentEntity enObj =(DepartmentEntity) q.getSingleResult(); 
				enObj.setDeptName(obj.getDeptName());
				enObj.setDescription(obj.getDescription());
				enObj.setShortName(obj.getShortName());
				enObj.setUpdatedBy(5L);
				enObj.setUpdatedOn(new Date());
				entityManager.persist(enObj);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public String saveDistLGDMapping(DistLGDBean obj) {

		try {
			System.out.println("File ------- "+obj.getDeptId());
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<dropdownBean> getDepartmentList() {
		
		List<dropdownBean> dList = new ArrayList<dropdownBean>();
		try {
			Query q = entityManager.createNativeQuery
					("select * from tbl_mst_department",DepartmentEntity.class);
			List<DepartmentEntity> List = q.getResultList();
			for(DepartmentEntity d : List) {
				dropdownBean b = new dropdownBean();
				b.setId(d.getDeptId());
				b.setName(d.getDeptName());
				dList.add(b);
			}
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return dList;
	}
	

}
