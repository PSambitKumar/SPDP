package com.spdp.serviceImpl;



import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.enterprise.context.ApplicationScoped;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;


import com.spdp.bean.PRangePDistrictBean;
import com.spdp.bean.PoliceDistrictBean;
import com.spdp.bean.PoliceRangeBean;

import com.spdp.entity.PRangePDistMapping;
import com.spdp.entity.PoliceRangeEntity;
import com.spdp.service.PoliceRangeService;

/**
 * 
 * @author jyotiranjan.behera
 *
 */
@ApplicationScoped
public class PoliceRangeServiceImpl implements PoliceRangeService {
	
	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;

	@Override
	public List<PoliceRangeBean> getPoliceRangeDetailsById() {
		List<PoliceRangeBean> objBean = new ArrayList<PoliceRangeBean>();
		PoliceRangeBean policeRangeBean = null;
		
		try {
			List<Object[]> object = entityManager.createNamedQuery("PoliceRangeEntityDetails", Object[].class).getResultList();
		  
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					policeRangeBean = new PoliceRangeBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String nDateString= dateFormat.format((Date)obj1[3]);
					
					policeRangeBean.setPoliceRangeId((Long)obj1[0]);
					policeRangeBean.setPoliceRangeName((String)obj1[1]);
					policeRangeBean.setNotificationNo((String)obj1[2]);
//					policeRangeBean.setNotificationDate((Date)obj1[3]);
					policeRangeBean.setNotiDate(nDateString);
					policeRangeBean.setActiveStatus((String)obj1[4]);
					
	  
					objBean.add(policeRangeBean);
					  
				}
			}
		  }
		catch (Exception e) {
			e.printStackTrace();
		}
		return objBean;
	}
	
	@Transactional
	@Override
	public void deletePoliceRangeByNotiNo(String notificationNo) {
		System.out.println("Service------- " + notificationNo);
		String Status = "";
		PoliceRangeEntity Obj = entityManager.createNamedQuery("getPoliceRangeRowByNotiNo", PoliceRangeEntity.class)
				.setParameter("notificationNo", notificationNo).getSingleResult();
		Status = Obj.getActiveStatus();
		if (Status.matches("N")) {
			Obj.setActiveStatus("Y");
		} else {
			Obj.setActiveStatus("N");
		}
		entityManager.persist(Obj);

	}

	@Override
	public List<PRangePDistrictBean> getPRangeAndPDistDetails() {
		List<PoliceRangeEntity> enList = new ArrayList<PoliceRangeEntity>();
		List<PRangePDistrictBean> details = new ArrayList<PRangePDistrictBean>();
		// List<String> str =new ArrayList<String>();

		javax.persistence.Query q = entityManager.createQuery("  from PoliceRangeEntity ");
		enList = q.getResultList();
		System.out.println(" Police Range table length  " + enList.size());

		for (PoliceRangeEntity en : enList) {

			PRangePDistrictBean Bean = new PRangePDistrictBean();
			List<String> str = new ArrayList<String>();
			//from here to check
			
			Bean.setpRangeName(en.getPoliceRangeName());
			javax.persistence.Query que = entityManager
					.createQuery(" select pDistId from PRangePDistMapping where pRangeId = :id ");
			que.setParameter("id", en.getPoliceRangeId());
			List<Long> id = que.getResultList();
			System.out.println(id);
			System.out.println(" pDistId from Mapping length  " + id.size());

			for (Long t : id) {
				javax.persistence.Query que1 = entityManager
						.createQuery(" select pdistrictName from PoliceDistrictEntity where pdistrictId = :id ");
				que1.setParameter("id", t);
				str.add(que1.getSingleResult().toString());
			}

			System.out.println(" PDistrict List(str) length  " + str.size());
			Bean.setpDistNames(str);
			details.add(Bean);

		}
		return details;
	}
	//upto this

	@Override
	public PoliceRangeBean getPoliceRangeDetailsForUpdate(Long id) {
		PoliceRangeEntity policeREnt = new PoliceRangeEntity();
		PoliceRangeBean policeRBean = new PoliceRangeBean();
		List<Long> pDistId = new ArrayList<Long>();
		

		try {
			System.out.println(id + " >>>>>>>>>>>>>>>");
			javax.persistence.Query q = entityManager.createQuery("from PoliceRangeEntity where policeRangeId = :id");
			q.setParameter("id", id);
			
			policeREnt = (PoliceRangeEntity) q.getSingleResult();
			System.out.println(policeREnt.getPoliceRangeName());
			
			policeRBean.setPoliceRangeId(id);
			policeRBean.setPoliceRangeName(policeREnt.getPoliceRangeName());
			policeRBean.setNotificationNo(policeREnt.getNotificationNo());
      		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");  
		
			String nDateString= dateFormat.format(policeREnt.getNotificationDate());
			System.out.println("---------------------------------------------------------"+nDateString);
			policeRBean.setNotiDate(nDateString);
			
			
			javax.persistence.Query q1 = entityManager
					.createQuery(" select pDistId from PRangePDistMapping where pRangeId = :id");
			q1.setParameter("id", id);
			pDistId = q1.getResultList();
			
			if (pDistId.size() != 0) {
				System.out.println("<<<<<<<<<<<<<<<< " + pDistId.size());
				policeRBean.setpDistListId(pDistId);
			} else {
				System.out.println("Police District List Ids Not Found !!");
			}
			
			List<PoliceDistrictBean> pdList = new ArrayList<PoliceDistrictBean>();
			List<String> pdListForView=new ArrayList<String>();
			
			for (Long l : pDistId) {
				PoliceDistrictBean pdBean = new PoliceDistrictBean();
				javax.persistence.Query query = entityManager
						.createQuery(" select pdistrictName from PoliceDistrictEntity where pdistrictId = :id");
				query.setParameter("id", l);
				pdBean.setPdistrictId(l);
				String s = (String) query.getSingleResult();
				pdBean.setPdistrictName(s);
				pdList.add(pdBean);
				pdListForView.add(s);
			}
			policeRBean.setPdObj(pdList);
			policeRBean.setPdListForView(pdListForView);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return policeRBean;
	}
	
//	@SuppressWarnings("deprecation")
	@Transactional
	@Override
	public String savePoliceRangeDetails(PoliceRangeBean policeRBean)	{
		System.out.println("PoliceRangeId//////////////////////////////////////----------------------"+policeRBean.getNotificationDate());
		System.out.println("PoliceRangeId//////////////////////////////////////"+policeRBean.getNotificationNo());
		String response = "";
		String validationMsg = "";
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
		String nDateString= dateFormat.format(policeRBean.getNotificationDate());
		System.out.println("---------------//////////////////---------------------------------"+nDateString);
		try {
			if (policeRBean.getPoliceRangeName() == null) {
				validationMsg = "Police Range Name Can Not Be Null";
				return validationMsg;
			}
			if (policeRBean.getNotificationNo() == null) {
				validationMsg = "Notification Number Can Not Be Null";
				return validationMsg;
			}
			if (policeRBean.getNotificationDate() == null) {
				validationMsg = "Notification Date Can Not Be Null";
				return validationMsg;
			}


			
			StoredProcedureQuery ref = this.entityManager.createStoredProcedureQuery("SP_SAVE_POLICE_RANGE")
					.registerStoredProcedureParameter("S_PRANGE_NAME", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("S_NOTIFICATION_NO", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("S_NOTIFICATION_DATE", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("S_UPDATED_BY", Long.class, ParameterMode.IN)
					.registerStoredProcedureParameter("S_CREATED_BY", Long.class, ParameterMode.IN)
					.registerStoredProcedureParameter("S_STATUS", String.class, ParameterMode.IN);
			ref
				.setParameter("S_PRANGE_NAME", policeRBean.getPoliceRangeName())
				.setParameter("S_NOTIFICATION_NO", policeRBean.getNotificationNo())
				.setParameter("S_NOTIFICATION_DATE",nDateString)
				.setParameter("S_UPDATED_BY", 1L)//policeRBean.getUpdatedBy()
				.setParameter("S_CREATED_BY",2L )//policeRBean.getCreatedBy()
				.setParameter("S_STATUS","Y");
			
			ref.execute();
			PoliceRangeEntity prObj =  entityManager.createNamedQuery("getPoliceRangeRowByNotiNo",PoliceRangeEntity.class).setParameter("notificationNo", policeRBean.getNotificationNo()).getSingleResult();
			for(PoliceDistrictBean pdbean:policeRBean.getDistrictObj()) {
				PRangePDistMapping MapObj  = new PRangePDistMapping();
				MapObj.setpDistId(pdbean.getPdistrictId());
				MapObj.setpRangeId(prObj.getPoliceRangeId());
				MapObj.setStatus("ACTIVE");
				entityManager.persist(MapObj);
				System.out.println("Data Inserted in mapping table");
			}
			
			System.out.println("Inserted succcessfully");
			response = "Data Added Successfully !!";
		}
		catch(Exception e) {
			e.printStackTrace();
			response = "Some Error Occurred !!";
		}
		return response;
	}
	
	@Transactional
	@Override
	public void updatePoliceRangeDetails(PoliceRangeBean policeRBean) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
		String nDateString= dateFormat.format(policeRBean.getNotificationDate());
		
		
		StoredProcedureQuery ref = this.entityManager.createStoredProcedureQuery("SP_EDIT_POLICE_RANGE")
				.registerStoredProcedureParameter("flag", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("S_PRANGE_NAME", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("S_NOTIFICATION_NO", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("S_NOTIFICATION_DATE", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("S_UPDATED_BY", Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter("S_STATUS", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("S_POLICE_RANGE_ID", Long.class, ParameterMode.IN);
		
		ref
		.setParameter("flag", "EDIT")
		.setParameter("S_PRANGE_NAME", policeRBean.getPoliceRangeName())
		.setParameter("S_NOTIFICATION_NO", policeRBean.getNotificationNo())
		.setParameter("S_NOTIFICATION_DATE",nDateString)
		.setParameter("S_UPDATED_BY",1L) 
		.setParameter("S_STATUS", "Y")
		.setParameter("S_POLICE_RANGE_ID", policeRBean.getPoliceRangeId());
		
		
		ref.execute();
		try {
			
		
		
		javax.persistence.Query query = entityManager.createQuery("DELETE FROM PRangePDistMapping c WHERE c.pRangeId = :id");
		int deletedCount = query.setParameter("id", policeRBean.getPoliceRangeId()).executeUpdate();
		System.out.println("Total Number of Row Deleted are -------------------------------------------->"+deletedCount);
		}
		catch(Exception e) {
			System.out.println("No Row Available for Delete !!");
			e.printStackTrace();
		}
		finally {
		
		PoliceRangeEntity prObj =  entityManager.createNamedQuery("getPoliceRangeRowByNotiNo",PoliceRangeEntity.class).setParameter("notificationNo", policeRBean.getNotificationNo()).getSingleResult();
		for(PoliceDistrictBean pdbean:policeRBean.getDistrictObj()) {
			PRangePDistMapping MapObj  = new PRangePDistMapping();
			MapObj.setpDistId(pdbean.getPdistrictId());
			MapObj.setpRangeId(prObj.getPoliceRangeId());
			MapObj.setStatus("ACTIVE");
			entityManager.persist(MapObj);
			System.out.println("Data Inserted in mapping table");
		}
		System.out.println("Updated successsfully !!");	
		}
	}

	@Override
	public List<PoliceRangeBean> getPoliceRangeDetailsFilteredByPoliceRange(Long pid) {
		List<PoliceRangeBean> objBean = new ArrayList<PoliceRangeBean>();
		PoliceRangeBean policeRangeBean = null;
		System.out.println("////////////////////////////////////////////////////////////----"+pid);
		
		try {
			javax.persistence.Query q1 = entityManager
					.createQuery(" select p.policeRangeId,p.policeRangeName,p.notificationNo,p.notificationDate,p.activeStatus from"
				    		+ "  PoliceRangeEntity p where p.policeRangeId = :pid ");
			q1.setParameter("pid", pid);
			List<Object[]> object = q1.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					policeRangeBean = new PoliceRangeBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String nDateString= dateFormat.format((Date)obj1[3]);
					
					policeRangeBean.setPoliceRangeId((Long)obj1[0]);
					policeRangeBean.setPoliceRangeName((String)obj1[1]);
					policeRangeBean.setNotificationNo((String)obj1[2]);
//					policeRangeBean.setNotificationDate((Date)obj1[3]);
					policeRangeBean.setNotiDate(nDateString);
					policeRangeBean.setActiveStatus((String)obj1[4]);
					
					objBean.add(policeRangeBean);
					  
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return objBean;
	}

	@Override
	public List<PoliceRangeBean> getPoliceRangeDetailsFilteredByStatus(String status) {
		List<PoliceRangeBean> objBean = new ArrayList<PoliceRangeBean>();
		PoliceRangeBean policeRangeBean = null;
		System.out.println("////////////////////////////////////////////////////////////----"+status);
		
		try {
			javax.persistence.Query q1 = entityManager
					.createQuery(" select p.policeRangeId,p.policeRangeName,p.notificationNo,p.notificationDate,p.activeStatus from"
				    		+ "  PoliceRangeEntity p where p.activeStatus = :status ");
			q1.setParameter("status", status);
			List<Object[]> object = q1.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					policeRangeBean = new PoliceRangeBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String nDateString= dateFormat.format((Date)obj1[3]);
					
					policeRangeBean.setPoliceRangeId((Long)obj1[0]);
					policeRangeBean.setPoliceRangeName((String)obj1[1]);
					policeRangeBean.setNotificationNo((String)obj1[2]);
//					policeRangeBean.setNotificationDate((Date)obj1[3]);
					policeRangeBean.setNotiDate(nDateString);
					policeRangeBean.setActiveStatus((String)obj1[4]);
					
					objBean.add(policeRangeBean);
					  
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return objBean;
	}
	
	@Override
	public List<PoliceRangeBean> getPoliceRangeDetailsFilteredByDate(String date) {
		List<PoliceRangeBean> objBean = new ArrayList<PoliceRangeBean>();
		PoliceRangeBean policeRangeBean = null;
		
		
		try {
			Date dtDate=new Date(date);
			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
			String nDateString= dateFormat.format(dtDate);
				
			javax.persistence.Query q1 = entityManager
					.createQuery(" select p.policeRangeId,p.policeRangeName,p.notificationNo,p.notificationDate,p.activeStatus from"
				    		+ "  PoliceRangeEntity p ");
			List<Object[]> object = q1.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					policeRangeBean = new PoliceRangeBean(); 
					String DString= dateFormat.format((Date)obj1[3]);
					if(nDateString.equals(DString)) {
//						System.out.println("---------------------------DB Date is "+DString);
						policeRangeBean.setPoliceRangeId((Long)obj1[0]);
						policeRangeBean.setPoliceRangeName((String)obj1[1]);
						policeRangeBean.setNotificationNo((String)obj1[2]);
//						policeRangeBean.setNotificationDate((Date)obj1[3]);
						policeRangeBean.setNotiDate(DString);
						policeRangeBean.setActiveStatus((String)obj1[4]);
						
						objBean.add(policeRangeBean);

					}
					
					
										  
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return objBean;
	}
	@Override
	public List<PoliceRangeBean> getPoliceRangeDetailsFilteredByPrIdAndStatus(Long prid, String status) {
		List<PoliceRangeBean> objBean = new ArrayList<PoliceRangeBean>();
		PoliceRangeBean policeRangeBean = null;
		System.out.println("////////////////////////////////////////////////////////////----"+status);
		System.out.println("////////////////////////////////////////////////////////////----"+prid);
		
		try {
			javax.persistence.Query q1 = entityManager
					.createQuery(" select p.policeRangeId,p.policeRangeName,p.notificationNo,p.notificationDate,p.activeStatus from"
				    		+ "  PoliceRangeEntity p where p.activeStatus = :status and p.policeRangeId =:prid")
			.setParameter("status", status)
			.setParameter("prid", prid);
			List<Object[]> object = q1.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					policeRangeBean = new PoliceRangeBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String nDateString= dateFormat.format((Date)obj1[3]);
					
					policeRangeBean.setPoliceRangeId((Long)obj1[0]);
					policeRangeBean.setPoliceRangeName((String)obj1[1]);
					policeRangeBean.setNotificationNo((String)obj1[2]);
//					policeRangeBean.setNotificationDate((Date)obj1[3]);
					policeRangeBean.setNotiDate(nDateString);
					policeRangeBean.setActiveStatus((String)obj1[4]);
					
					objBean.add(policeRangeBean);
					  
				}
			}
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return objBean;
	}
	
	@Override
	public List<PoliceRangeBean> getPoliceRangeDetailsFilteredByDateAndStatus(String date, String status) {
		List<PoliceRangeBean> objBean = new ArrayList<PoliceRangeBean>();
		PoliceRangeBean policeRangeBean = null;
		System.out.println("-------------------------------------------------Date is "+date);
		System.out.println("------------------------------------------------status is "+status);


		
		try {
				
			javax.persistence.Query q1 = entityManager
					.createQuery(" select p.policeRangeId,p.policeRangeName,p.notificationNo,p.notificationDate,p.activeStatus from"
				    		+ "  PoliceRangeEntity p where p.activeStatus = :status ").setParameter("status", status);
			List<Object[]> object = q1.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					policeRangeBean = new PoliceRangeBean(); 
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
					String DString= dateFormat.format((Date)obj1[3]);
					if(date.equals(DString)) {
//						System.out.println("---------------------------DB Date is "+DString);
						policeRangeBean.setPoliceRangeId((Long)obj1[0]);
						policeRangeBean.setPoliceRangeName((String)obj1[1]);
						policeRangeBean.setNotificationNo((String)obj1[2]);
//						policeRangeBean.setNotificationDate((Date)obj1[3]);
						policeRangeBean.setNotiDate(DString);
						policeRangeBean.setActiveStatus((String)obj1[4]);
						
						objBean.add(policeRangeBean);

					}
					
					
										  
				}
			}
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return objBean;
	}
	
	@Override
	public List<PoliceRangeBean> getPoliceRangeDetailsFilteredByAllData(Long prid, String status, String date) {
		List<PoliceRangeBean> objBean = new ArrayList<PoliceRangeBean>();
		PoliceRangeBean policeRangeBean = null;
		System.out.println("-------------------------------------------------Date is "+date);
		System.out.println("------------------------------------------------status is "+status);
		System.out.println("------------------------------------------------status is "+prid);


		
		try {
				
			javax.persistence.Query q1 = entityManager
					.createQuery(" select p.policeRangeId,p.policeRangeName,p.notificationNo,p.notificationDate,p.activeStatus from"
				    		+ "  PoliceRangeEntity p where p.activeStatus = :status and p.policeRangeId =:prid ")
					.setParameter("status", status)
					.setParameter("prid", prid);
			List<Object[]> object = q1.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					policeRangeBean = new PoliceRangeBean(); 
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
					String DString= dateFormat.format((Date)obj1[3]);
					if(date.equals(DString)) {
//						System.out.println("---------------------------DB Date is "+DString);
						policeRangeBean.setPoliceRangeId((Long)obj1[0]);
						policeRangeBean.setPoliceRangeName((String)obj1[1]);
						policeRangeBean.setNotificationNo((String)obj1[2]);
//						policeRangeBean.setNotificationDate((Date)obj1[3]);
						policeRangeBean.setNotiDate(DString);
						policeRangeBean.setActiveStatus((String)obj1[4]);
						
						objBean.add(policeRangeBean);

					}
					
					
										  
				}
			}
			
		}
		
		catch (Exception e) {
			e.printStackTrace();
		}
		return objBean;
	}

}
