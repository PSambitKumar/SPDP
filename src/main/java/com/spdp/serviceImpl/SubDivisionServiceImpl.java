package com.spdp.serviceImpl;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.persistence.Query;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import com.spdp.bean.BlockBean;
import com.spdp.bean.DistrictBean;
import com.spdp.entity.BlockEntity;
import com.spdp.entity.PoliceDistSdpoMappingEntity;
import com.spdp.entity.PoliceDistrictEntity;
import com.spdp.entity.RdcEntity;
import com.spdp.entity.SubDivisionBlockMapping;
import com.spdp.bean.SubDivisionBean;
import com.spdp.bean.SubdivisionBlockBean;
import com.spdp.bean.policeSubDivisionBean;
import com.spdp.entity.SubDivisionEntity;
import com.spdp.service.SubDivisionService;



/**
 * 
 * @author sanghamitra.beura
 *
 */

@ApplicationScoped
public class SubDivisionServiceImpl implements SubDivisionService {

	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;

	@Override
	public List<SubDivisionBean> getDetailsBySubDivisionId() {

		// List<SubDivisionEntity> sdm = new ArrayList<SubDivisionEntity>();
//		SubDivisionEntity sdm1 = null;
//		SubDivisionBean sdm2=null;
//		List<SubDivisionBean> sdm3=new ArrayList<>();
//		
//		try {
//			List<Object[]> obj = entityManager.createNamedQuery("SubDivisionDetails", Object[].class).getResultList();
//			if(!obj.isEmpty()) {
//				for(Object[] dto:obj) {
//					sdm1=new SubDivisionEntity();
//					sdm2=new SubDivisionBean();
//					if(dto[0]!=null) {
//						sdm2.setDistrictName((String) dto[0]);
//						sdm2.setSubdivisionName((String)dto[1]);
//						sdm2.setCreationDate((Date)dto[2]);
//						sdm2.setNotiNo((String)dto[3]);
//						sdm2.setStatus((String)dto[4]);
//						sdm3.add(sdm2);
//					}
//				}
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}
//		
//		 
		List<SubDivisionBean> objBean = new ArrayList<SubDivisionBean>();
		SubDivisionBean subDivisionBean = null;

		try {
			List<Object[]> object = entityManager.createNamedQuery("SubDivisionEntityDetails", Object[].class)
					.getResultList();

			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					subDivisionBean = new SubDivisionBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
//					String nDateString= dateFormat.format((Date)obj1[6]);
//					System.out.println("-----------------------------------------------"+nDateString);
					String cDateString= dateFormat.format((Date)obj1[2]);
					

					subDivisionBean.setSubdivionId((Long) obj1[0]);
					subDivisionBean.setSubdivisionName((String) obj1[1]);
					subDivisionBean.setcDate(cDateString);
					subDivisionBean.setNotiNo((String) obj1[3]);
					subDivisionBean.setStatus((String) obj1[4]);
					subDivisionBean.setDistrictName((String) obj1[5]);

//					subDivisionBean.setNotifDate(nDateString);
					 subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(subDivisionBean);

				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return objBean;
	}

	@Override
	public List<BlockBean> getBlockDetails() {

		List<BlockEntity> block = new ArrayList<BlockEntity>();
		List<BlockBean> blockList = new ArrayList<BlockBean>();
		try {
			block = entityManager.createNamedQuery("getBlockByDistId", BlockEntity.class).getResultList();

			////////////////////////////////////
			for (BlockEntity d : block) {
				BlockBean bn = new BlockBean();

				bn.setBlockId(d.getBlockId());
				bn.setBlockName(d.getBlockName());
				blockList.add(bn);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return blockList;

	}

	@Transactional
	@Override
	public void deleteByNotiNo(String notiNo) {
		System.out.println("Service------- " + notiNo);
		String Status = "";

		SubDivisionEntity subdivisionObj = entityManager
				.createNamedQuery("getSubDivisionRowByNotiNo", SubDivisionEntity.class).setParameter("notiNo", notiNo)
				.getSingleResult();
		Status = subdivisionObj.getActiveStatus();
		if (Status.matches("N")) {
			subdivisionObj.setActiveStatus("Y");
		} else {
			System.out.println("Else Part");
			subdivisionObj.setActiveStatus("N");
		}
		entityManager.persist(subdivisionObj);
	}

	@SuppressWarnings("deprecation")
	@Transactional
	@Override
	public String saveSubDivisionDetails(SubDivisionBean subdivisionBean) throws ParseException {

		SubDivisionEntity entObj = new SubDivisionEntity();

		// Date str_date =subdivisionBean.getCreationDate();
//		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = formatter.parse(str_date);

		String response = "";
		String validationMsg = "";
		try {
			if (subdivisionBean.getSubdivisionName() == null) {
				validationMsg = "SubDivision Name Can Not Be Null";
				return validationMsg;
			}
			if (subdivisionBean.getNotiNo() == null) {
				validationMsg = "Notification Number Can Not Be Null";
				return validationMsg;
			}
			if (subdivisionBean.getNotiDate() == null) {
				validationMsg = "Notification Date Can Not Be Null";
				return validationMsg;
			}
			if (subdivisionBean.getBlockObj().size() == 0) {
				validationMsg = "Block Lists Can Not Be Null";
				return validationMsg;
			}
//			String[] splittedItems= subdivisionBean.getNotiDate().split("-");
//			int date =Integer.parseInt(splittedItems[2]);
//			int month =Integer.parseInt(splittedItems[1]);
//			int year = Integer.parseInt(splittedItems[0]);
//			System.out.println("Date- "+date+" Month- "+month+" Year"+year);
			if (subdivisionBean.getSubdivisionName() != null) {

				entObj.setSubdivisionName(subdivisionBean.getSubdivisionName());
				entObj.setNotificationNo(subdivisionBean.getNotiNo());
				// entObj.setNotificationDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5
				// ));
				// entObj.setCreatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
				// entObj.setUpdatedOn(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
				entObj.setNotificationDate(subdivisionBean.getNotiDate());
				entObj.setCreatedOn(new Date());
				entObj.setUpdatedOn(new Date());
				entObj.setCreatedBy("Sanghamitra");
				entObj.setUpdatedBy("Sanghamitra");
				entObj.setAciton("Ok");
				entObj.setActiveStatus("Y");
				entObj.setLgdCode(null);
				entObj.setDistrictId(subdivisionBean.getDistrictId());
				System.out.println("Sub ID :" + subdivisionBean.getDistrictId());

				// entObj.setStateId(5L);
				entityManager.persist(entObj);
				
			SubDivisionEntity subdivisionObj = entityManager
					.createNamedQuery("getSubDivisionRowByNotiNo", SubDivisionEntity.class)
						.setParameter("notiNo", subdivisionBean.getNotiNo()).getSingleResult();
				
				for (BlockBean blockBean : subdivisionBean.getBlockObj()) {

				SubDivisionBlockMapping subdivisionBlockMapObj = new SubDivisionBlockMapping();
			
				subdivisionBlockMapObj.setBlockId(blockBean.getBlockId());
				subdivisionBlockMapObj .setSubdivisionId(subdivisionObj.getSubdivisionId());
				entityManager.persist(subdivisionBlockMapObj );
				}

			} else {
				System.out.println("Data Not Found");
			}
			 
			//}

			System.out.println("Exception");
			response = "Data Added Successfully !!";

		} catch (Exception e) {
			e.printStackTrace();
			response = "Some Error Occurred !!";
		}
		return response;
	}

	@Override
	public List<SubdivisionBlockBean> getSubdivisionAndBlockDetails() {

		List<SubDivisionEntity> enList = new ArrayList<SubDivisionEntity>();
		List<SubdivisionBlockBean> details = new ArrayList<SubdivisionBlockBean>();
		// List<String> str =new ArrayList<String>();

		javax.persistence.Query q = entityManager.createQuery("  from SubDivisionEntity ");
		enList = q.getResultList();
		System.out.println(" subdivision table length  " + enList.size());

		for (SubDivisionEntity en : enList) {

			SubdivisionBlockBean sdBean = new SubdivisionBlockBean();
			List<String> str = new ArrayList<String>();

			sdBean.setSubdivisionName(en.getSubdivisionName());

			javax.persistence.Query que = entityManager
					.createQuery(" select blockId from SubDivisionBlockMapping where subdivisionId = :id ");
			que.setParameter("id", en.getSubdivisionId());
			List<Long> id = que.getResultList();

			System.out.println(" BlockId from Mapping length  " + id.size());

			for (Long t : id) {
				javax.persistence.Query que1 = entityManager
						.createQuery(" select blockName from BlockEntity where blockId = :id ");
				que1.setParameter("id", t);
				str.add(que1.getSingleResult().toString());
			}

			System.out.println(" Block List(str) length  " + str.size());
			sdBean.setBlockNames(str);
			details.add(sdBean);

		}

		System.out.println("---------------------------------" + details.get(0).getBlockNames() + "     "
				+ details.get(2).getSubdivisionName() + "  " + details.get(2).getBlockNames());
		return details;
	}

	
	
	@Override
	public SubDivisionBean getSubdivisionDetailsForUpdate(Long id) {

		SubDivisionEntity subdivisionEnt = new SubDivisionEntity();
		SubDivisionBean subdivisionBean = new SubDivisionBean();
		List<Long> blockId = new ArrayList<Long>();

		try {
			System.out.println(id + " >>>>>>>>>>>>>>>");
			Query q = entityManager.createQuery("from SubDivisionEntity where subdivisionId = :id");
			q.setParameter("id", id);

			subdivisionEnt = (SubDivisionEntity) q.getSingleResult();
			System.out.println(subdivisionEnt.getSubdivisionName());

			subdivisionBean.setSubdivionId(subdivisionEnt.getSubdivisionId());
			subdivisionBean.setSubdivisionName(subdivisionEnt.getSubdivisionName());
			subdivisionBean.setNotiNo(subdivisionEnt.getNotificationNo());
			subdivisionBean.setNotiDate(subdivisionEnt.getNotificationDate());
//			DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
//			String nDateString= dateFormat.format(subdivisionBean.getNotificationDate());
//			System.out.println("-------------------------------------------------"+nDateString);
//			subdivisionBean.setNotifDate(nDateString);
			subdivisionBean.setDistrictId(subdivisionEnt.getDistrictId());
			subdivisionBean.setStatus(subdivisionEnt.getActiveStatus());
//		
//			SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
//			subdivisionBean.setNotiDate(formatter.format(subdivisionEnt.getNotificationDate()));

			javax.persistence.Query q1 = entityManager
					.createQuery(" select blockId from SubDivisionBlockMapping where subdivisionId = :id");
			q1.setParameter("id", id);
			blockId = q1.getResultList();

			if (blockId.size() != 0) {
				System.out.println("<<<<<<<<<<<<<<<< " + blockId.size());
				subdivisionBean.setBlockL(blockId);
			} else {
				System.out.println("Block List Not Found");
			}

			List<BlockBean> blockList = new ArrayList<BlockBean>();
			List<String> BlockListForView = new ArrayList<String>();
			
			for (Long l : blockId) {
				BlockBean blockBean = new BlockBean();
				javax.persistence.Query query = entityManager
						.createQuery(" select blockName from BlockEntity where blockId = :id");
				query.setParameter("id", l);
				blockBean.setBlockId(l);
				String s = (String) query.getSingleResult();
				blockBean.setBlockName(s);
				blockList.add(blockBean);
				BlockListForView.add(s);
			}
			subdivisionBean.setBlockObj(blockList);
			subdivisionBean.setBlockListForView(BlockListForView);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subdivisionBean;
	}

	 @Transactional
	@Override
	public void updateSubDivisionDetails(SubDivisionBean subdivisionObj) {
		 System.out.println("---------------------Date "+subdivisionObj.getNotifDate());
		 
		 String[] splittedItems= subdivisionObj.getNotifDate().split("-");
			int date =Integer.parseInt(splittedItems[2]);
			int month =Integer.parseInt(splittedItems[1]);
			int year = Integer.parseInt(splittedItems[0]);
			System.out.println("Date- "+date+" Month- "+month+" Year"+year);
			
			Date dt=new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 );
			
		 
		
		 DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
		String nDateString= dateFormat.format(dt);
		System.out.println("DAteeeeem ---------------------------------"+nDateString);
//		
//    	System.out.println("------------------------------------------------------------------- dateString "+nDateString);

		StoredProcedureQuery subobj = this.entityManager.createStoredProcedureQuery("SP_SUB_DIVISION")
				.registerStoredProcedureParameter("flag", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_SUB_NAME", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_NOTIFICATION_NO", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_NOTIFICATION_DATE", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_DISTRICT_ID", Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_UPDATED_BY", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_SUB_DIVISION_ID", Long.class, ParameterMode.IN);

		subobj.setParameter("flag", "EDIT")
				.setParameter("V_SUB_NAME", subdivisionObj.getSubdivisionName())
				.setParameter("V_NOTIFICATION_NO", subdivisionObj.getNotiNo())
				.setParameter("V_NOTIFICATION_DATE",nDateString) //nDateString)
				.setParameter("V_DISTRICT_ID", subdivisionObj.getDistrictId())
				.setParameter("V_UPDATED_BY", "san")
				.setParameter("V_SUB_DIVISION_ID", subdivisionObj.getSubdivionId());

		subobj.execute();
		System.out.println("Master TableUPdated successsfully");
		
		javax.persistence.Query query = entityManager.createQuery("DELETE FROM SubDivisionBlockMapping c WHERE c.subdivisionId = :id");
		int deletedCount = query.setParameter("id", subdivisionObj.getSubdivionId()).executeUpdate();
		System.out.println("Total Number of Row Deleted are -------------------------------------------->"+deletedCount);

		SubDivisionEntity Obj = entityManager
				.createNamedQuery("getSubDivisionRowByNotiNo", SubDivisionEntity.class)
					.setParameter("notiNo", subdivisionObj.getNotiNo()).getSingleResult();
			
			for (BlockBean blockBean : subdivisionObj.getBlockObj()) {

			SubDivisionBlockMapping subdivisionBlockMapObj = new SubDivisionBlockMapping();
		
			subdivisionBlockMapObj.setBlockId(blockBean.getBlockId());
			subdivisionBlockMapObj .setSubdivisionId(Obj.getSubdivisionId());
			entityManager.persist(subdivisionBlockMapObj );
			System.out.println("Data inserted in mapping tableb !!!!");
			}

		System.out.println("inserted sucessfully");

	}

	@Override
	public List<SubDivisionBean> getBlockDetaildByDistId(Long districtId) {
		
		List<SubDivisionBean> ObList = new ArrayList<SubDivisionBean>();
		SubDivisionBean subDiv = null;
		try { 
			//List<Object[]> obj= blockService.getSubDivDetailsByDistId(districtId);
			List<Object[]> object = entityManager.createNamedQuery("getBlockByDistId", Object[].class)
					.setParameter("districtId", districtId)
					.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					subDiv = new SubDivisionBean();
					
					subDiv.setBlockId((Long)obj1[0]);
					subDiv.setBlockName((String)obj1[1]);
					ObList.add(subDiv);
	
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return ObList;	
	}

	@Override
	public List<SubDivisionBean> getSubdivisionDetaildByDistId(Long districtId) {
		
		List<SubDivisionBean> ObList1 = new ArrayList<SubDivisionBean>();
		SubDivisionBean subDiv1 = null;
		try { 
			//List<Object[]> obj= blockService.getSubDivDetailsByDistId(districtId);
			List<Object[]> object = entityManager.createNamedQuery("getSubdivisionByDistId", Object[].class)
					.setParameter("districtId", districtId)
					.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					subDiv1 = new SubDivisionBean();
					
					subDiv1.setSubdivionId((Long)obj1[0]);
					subDiv1.setSubdivisionName((String)obj1[1]);
					ObList1.add(subDiv1);
	
				}
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return ObList1;	
	}

	@Override
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByDistrict(Long did) {
		List<SubDivisionBean> objBean = new ArrayList<SubDivisionBean>();
		SubDivisionBean subDivisionBean = null;
		
		try {
			javax.persistence.Query que = entityManager
					.createQuery("select s.subdivisionId,s.subdivisionName,s.createdOn,s.notificationNo,s.activeStatus,d.districtName,s.notificationDate from "
							+ " SubDivisionEntity s  left join District d on s.districtId = d.districtId where s.districtId =: did");
			que.setParameter("did",did);
			List<Object[]> object = que.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					subDivisionBean = new SubDivisionBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
//					String nDateString= dateFormat.format((Date)obj1[6]);
//					System.out.println("-----------------------------------------------"+nDateString);
					String cDateString= dateFormat.format((Date)obj1[2]);
					

					subDivisionBean.setSubdivionId((Long) obj1[0]);
					subDivisionBean.setSubdivisionName((String) obj1[1]);
					subDivisionBean.setcDate(cDateString);
					subDivisionBean.setNotiNo((String) obj1[3]);
					subDivisionBean.setStatus((String) obj1[4]);
					subDivisionBean.setDistrictName((String) obj1[5]);

//					subDivisionBean.setNotifDate(nDateString);
					 subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(subDivisionBean);

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
	public List<SubDivisionBean> getSubDivisionDetailsFilteredBySubDivision(Long sid) {
		List<SubDivisionBean> objBean = new ArrayList<SubDivisionBean>();
		SubDivisionBean subDivisionBean = null;
		
		try {
			javax.persistence.Query que = entityManager
					.createQuery("select s.subdivisionId,s.subdivisionName,s.createdOn,s.notificationNo,s.activeStatus,d.districtName,s.notificationDate from "
							+ " SubDivisionEntity s  left join District d on s.districtId = d.districtId where s.subdivisionId =: sid");
			que.setParameter("sid",sid);
			List<Object[]> object = que.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					subDivisionBean = new SubDivisionBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
//					String nDateString= dateFormat.format((Date)obj1[6]);
//					System.out.println("-----------------------------------------------"+nDateString);
					String cDateString= dateFormat.format((Date)obj1[2]);
					

					subDivisionBean.setSubdivionId((Long) obj1[0]);
					subDivisionBean.setSubdivisionName((String) obj1[1]);
					subDivisionBean.setcDate(cDateString);
					subDivisionBean.setNotiNo((String) obj1[3]);
					subDivisionBean.setStatus((String) obj1[4]);
					subDivisionBean.setDistrictName((String) obj1[5]);

//					subDivisionBean.setNotifDate(nDateString);
					 subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(subDivisionBean);

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
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByStatus(String status) {
		List<SubDivisionBean> objBean = new ArrayList<SubDivisionBean>();
		SubDivisionBean subDivisionBean = null;
		
		try {
			javax.persistence.Query que = entityManager
					.createQuery("select s.subdivisionId,s.subdivisionName,s.createdOn,s.notificationNo,s.activeStatus,d.districtName,s.notificationDate from "
							+ " SubDivisionEntity s  left join District d on s.districtId = d.districtId where s.activeStatus =: status")
			.setParameter("status",status);
			List<Object[]> object = que.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					subDivisionBean = new SubDivisionBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
//					String nDateString= dateFormat.format((Date)obj1[6]);
//					System.out.println("-----------------------------------------------"+nDateString);
					String cDateString= dateFormat.format((Date)obj1[2]);
					

					subDivisionBean.setSubdivionId((Long) obj1[0]);
					subDivisionBean.setSubdivisionName((String) obj1[1]);
					subDivisionBean.setcDate(cDateString);
					subDivisionBean.setNotiNo((String) obj1[3]);
					subDivisionBean.setStatus((String) obj1[4]);
					subDivisionBean.setDistrictName((String) obj1[5]);

//					subDivisionBean.setNotifDate(nDateString);
					 subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(subDivisionBean);

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
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByDate(String date) {
		List<SubDivisionBean> objBean = new ArrayList<SubDivisionBean>();
		SubDivisionBean subDivisionBean = null;
		
		
		try {
			javax.persistence.Query que = entityManager
					.createQuery("select s.subdivisionId,s.subdivisionName,s.createdOn,s.notificationNo,s.activeStatus,d.districtName,s.notificationDate from "
							+ " SubDivisionEntity s  left join District d on s.districtId = d.districtId ");
			
			List<Object[]> object = que.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					subDivisionBean = new SubDivisionBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
//					String nDateString= dateFormat.format((Date)obj1[6]);
//					System.out.println("-----------------------------------------------"+nDateString);
					String cDateString= dateFormat.format((Date)obj1[2]);
					if(date.equals(cDateString))
					{
					subDivisionBean.setSubdivionId((Long) obj1[0]);
					subDivisionBean.setSubdivisionName((String) obj1[1]);
					subDivisionBean.setcDate(cDateString);
					subDivisionBean.setNotiNo((String) obj1[3]);
					subDivisionBean.setStatus((String) obj1[4]);
					subDivisionBean.setDistrictName((String) obj1[5]);

//					subDivisionBean.setNotifDate(nDateString);
					 subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(subDivisionBean);
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
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByDIdAndDate(Long dId, String date) {
		List<SubDivisionBean> objBean = new ArrayList<SubDivisionBean>();
		SubDivisionBean subDivisionBean = null;
		
		
		try {
			javax.persistence.Query que = entityManager
					.createQuery("select s.subdivisionId,s.subdivisionName,s.createdOn,s.notificationNo,s.activeStatus,d.districtName,s.notificationDate from "
							+ " SubDivisionEntity s  left join District d on s.districtId = d.districtId where s.districtId =: dId ")
					              .setParameter("dId", dId);
			
			List<Object[]> object = que.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					subDivisionBean = new SubDivisionBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
//					String nDateString= dateFormat.format((Date)obj1[6]);
			
					String cDateString= dateFormat.format((Date)obj1[2]);
					System.out.println("-----------------------------------------------"+cDateString);
					if(date.equals(cDateString))
					{
					subDivisionBean.setSubdivionId((Long) obj1[0]);
					subDivisionBean.setSubdivisionName((String) obj1[1]);
					subDivisionBean.setcDate(cDateString);
					subDivisionBean.setNotiNo((String) obj1[3]);
					subDivisionBean.setStatus((String) obj1[4]);
					subDivisionBean.setDistrictName((String) obj1[5]);

//					subDivisionBean.setNotifDate(nDateString);
					 subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(subDivisionBean);
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
	public List<SubDivisionBean> getSubDivisionDetailsFilteredBySIdAndDate(Long sId, String date) {
		
		List<SubDivisionBean> objBean = new ArrayList<SubDivisionBean>();
		SubDivisionBean subDivisionBean = null;
		
		
		try {
			javax.persistence.Query que = entityManager
					.createQuery("select s.subdivisionId,s.subdivisionName,s.createdOn,s.notificationNo,s.activeStatus,d.districtName,s.notificationDate from "
							+ " SubDivisionEntity s  left join District d on s.districtId = d.districtId where s.subdivisionId =: sId ")
					              .setParameter("sId", sId);
			
			List<Object[]> object = que.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					subDivisionBean = new SubDivisionBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
//					String nDateString= dateFormat.format((Date)obj1[6]);
			
					String cDateString= dateFormat.format((Date)obj1[2]);
					System.out.println("-----------------------------------------------"+cDateString);
					if(date.equals(cDateString))
					{
					subDivisionBean.setSubdivionId((Long) obj1[0]);
					subDivisionBean.setSubdivisionName((String) obj1[1]);
					subDivisionBean.setcDate(cDateString);
					subDivisionBean.setNotiNo((String) obj1[3]);
					subDivisionBean.setStatus((String) obj1[4]);
					subDivisionBean.setDistrictName((String) obj1[5]);

//					subDivisionBean.setNotifDate(nDateString);
					 subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(subDivisionBean);
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
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByStatusAndDate(String status, String date) {
		List<SubDivisionBean> objBean = new ArrayList<SubDivisionBean>();
		SubDivisionBean subDivisionBean = null;
		
		
		try {
			javax.persistence.Query que = entityManager
					.createQuery("select s.subdivisionId,s.subdivisionName,s.createdOn,s.notificationNo,s.activeStatus,d.districtName,s.notificationDate from "
							+ " SubDivisionEntity s  left join District d on s.districtId = d.districtId where s.activeStatus =: status ")
					              .setParameter("status", status);
			
			List<Object[]> object = que.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					subDivisionBean = new SubDivisionBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
//					String nDateString= dateFormat.format((Date)obj1[6]);
			
					String cDateString= dateFormat.format((Date)obj1[2]);
					System.out.println("-----------------------------------------------"+cDateString);
					if(date.equals(cDateString))
					{
					subDivisionBean.setSubdivionId((Long) obj1[0]);
					subDivisionBean.setSubdivisionName((String) obj1[1]);
					subDivisionBean.setcDate(cDateString);
					subDivisionBean.setNotiNo((String) obj1[3]);
					subDivisionBean.setStatus((String) obj1[4]);
					subDivisionBean.setDistrictName((String) obj1[5]);

//					subDivisionBean.setNotifDate(nDateString);
					 subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(subDivisionBean);
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
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByStatusAndDist(Long did, String status) {
		List<SubDivisionBean> objBean = new ArrayList<SubDivisionBean>();
		SubDivisionBean subDivisionBean = null;
		
		try {
			javax.persistence.Query que = entityManager
					.createQuery("select s.subdivisionId,s.subdivisionName,s.createdOn,s.notificationNo,s.activeStatus,d.districtName,s.notificationDate from "
							+ " SubDivisionEntity s  left join District d on s.districtId = d.districtId where s.districtId =: did and s.activeStatus =:status")
			.setParameter("did",did)
			.setParameter("status",status);
			
			
			List<Object[]> object = que.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					subDivisionBean = new SubDivisionBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
//					String nDateString= dateFormat.format((Date)obj1[6]);
//					System.out.println("-----------------------------------------------"+nDateString);
					String cDateString= dateFormat.format((Date)obj1[2]);
					

					subDivisionBean.setSubdivionId((Long) obj1[0]);
					subDivisionBean.setSubdivisionName((String) obj1[1]);
					subDivisionBean.setcDate(cDateString);
					subDivisionBean.setNotiNo((String) obj1[3]);
					subDivisionBean.setStatus((String) obj1[4]);
					subDivisionBean.setDistrictName((String) obj1[5]);

//					subDivisionBean.setNotifDate(nDateString);
					 subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(subDivisionBean);

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
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByStatusAndSubdiv(Long sid, String status) {
		List<SubDivisionBean> objBean = new ArrayList<SubDivisionBean>();
		SubDivisionBean subDivisionBean = null;
		
		try {
			javax.persistence.Query que = entityManager
					.createQuery("select s.subdivisionId,s.subdivisionName,s.createdOn,s.notificationNo,s.activeStatus,d.districtName,s.notificationDate from "
							+ " SubDivisionEntity s  left join District d on s.districtId = d.districtId where s.subdivisionId =: sid and s.activeStatus =:status")
			.setParameter("sid",sid)
			.setParameter("status",status);
			
			
			List<Object[]> object = que.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					subDivisionBean = new SubDivisionBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
//					String nDateString= dateFormat.format((Date)obj1[6]);
//					System.out.println("-----------------------------------------------"+nDateString);
					String cDateString= dateFormat.format((Date)obj1[2]);
					

					subDivisionBean.setSubdivionId((Long) obj1[0]);
					subDivisionBean.setSubdivisionName((String) obj1[1]);
					subDivisionBean.setcDate(cDateString);
					subDivisionBean.setNotiNo((String) obj1[3]);
					subDivisionBean.setStatus((String) obj1[4]);
					subDivisionBean.setDistrictName((String) obj1[5]);

//					subDivisionBean.setNotifDate(nDateString);
					 subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(subDivisionBean);

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
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByStatusAndSubdivAndDate(Long sid, String status,String date){ 
		List<SubDivisionBean> objBean = new ArrayList<SubDivisionBean>();
		SubDivisionBean subDivisionBean = null;
		
		try {
			javax.persistence.Query que = entityManager
					.createQuery("select s.subdivisionId,s.subdivisionName,s.createdOn,s.notificationNo,s.activeStatus,d.districtName,s.notificationDate from "
							+ " SubDivisionEntity s  left join District d on s.districtId = d.districtId where s.subdivisionId =: sid and s.activeStatus =:status")
			.setParameter("sid",sid)
			.setParameter("status",status);
			
			
			List<Object[]> object = que.getResultList();
			
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					subDivisionBean = new SubDivisionBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
//					String nDateString= dateFormat.format((Date)obj1[6]);
//					System.out.println("-----------------------------------------------"+nDateString);
					String cDateString= dateFormat.format((Date)obj1[2]);
					if(date.equals(cDateString)) {

					subDivisionBean.setSubdivionId((Long) obj1[0]);
					subDivisionBean.setSubdivisionName((String) obj1[1]);
					subDivisionBean.setcDate(cDateString);
					subDivisionBean.setNotiNo((String) obj1[3]);
					subDivisionBean.setStatus((String) obj1[4]);
					subDivisionBean.setDistrictName((String) obj1[5]);

//					subDivisionBean.setNotifDate(nDateString);
					 subDivisionBean.setNotiDate((LocalDate)obj1[6]);

					objBean.add(subDivisionBean);
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

}

//		List<Long> distLong = new  ArrayList<Long>();	
//		String[] splittedItems= subdivisionObj.getNotiDate().split("-");
//		int date =Integer.parseInt(splittedItems[0]);
//		int month =Integer.parseInt(splittedItems[1]);
//		int year = Integer.parseInt(splittedItems[2]);
//		try {
//			for(BlockBean b:subdivisionObj.getBlockObj()) {
//				distLong.add(b.getBlockId());
//			}
//			
//			subdivisionEnt = entityManager.createNamedQuery("getSubdivisionRowByNotiNo",SubDivisionEntity.class).setParameter("notiNo", subdivisionObj.getNotiNo()).getSingleResult();
//			subdivisionEnt.setSubdivisionName(subdivisionObj.getSubdivisionName());
//			subdivisionEnt.setNotificationNo(subdivisionObj.getNotiNo());
//			subdivisionEnt.setNotificationDate(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
//			subdivisionEnt.setDistrictId(subdivisionObj.getDistrictId());
//			entityManager.persist(subdivisionEnt);
//			
//			javax.persistence.Query q1= entityManager.createQuery(" from SubDivisionBlockMapping where subdivisionId = :id");
//			q1.setParameter("id",subdivisionEnt.getSubdivisionId());
//			List<SubDivisionBlockMapping>subdivisionMap;// = new ArrayList<RdcDistrictMapping>();
//			subdivisionMap = q1.getResultList();
//			
//			if(distLong.size() == subdivisionMap.size()) {
//				
//				//System.out.println(rdcEnt.getRdcName()+"<<<<><1><>>>>>>-----"+rdcMap.size());
//				for(int i=0,j=0; i<distLong.size(); i++) {
//					subdivisionMap.get(i).setBlockId(distLong.get(i));
//					entityManager.persist(subdivisionMap.get(i));
//				}	
//				
//			 }else if(distLong.size() < subdivisionMap.size()) {
//				 if(distLong.size() ==0) {
//					 javax.persistence.Query q= entityManager.createQuery("delete from SubDivisionBlockMapping where subdivisionId = :id");
//						q.setParameter("id", subdivisionEnt.getSubdivisionId());
//						int deletedRows = q.executeUpdate();
//				 }else {
//					for(int i=0,j=0; i<subdivisionMap.size(); i++) {
//						System.out.println(distLong.size()+"<<<<<<<<<<<<<<<");
//						if(i != distLong.size()) {
//							subdivisionMap.get(i).setBlockId(distLong.get(i));
//							entityManager.persist(subdivisionMap.get(i));
//						}else {
//							javax.persistence.Query q= entityManager.createQuery("delete from SubDivisionBlockMapping where BlockSubdivionId = :id");
//							q.setParameter("id", subdivisionMap.get(i).getBlockSubdivionId());
//							int deletedRows = q.executeUpdate();
//							System.out.println("Update List is less Than Db List So,Deleted row - "+deletedRows);
//					}
//				}
//			 }
//			}else {
//			   for(int i=0,j=0; i<distLong.size(); i++) {
//				   if(i < subdivisionMap .size()) {
//					   subdivisionMap .get(i).setBlockId(distLong.get(i));
//					   entityManager.persist(subdivisionMap .get(i));
//				   }else {
//					   SubDivisionBlockMapping objSave = new SubDivisionBlockMapping();
//					   objSave.setBlockId(distLong.get(i));
//					   objSave.setSubdivisionId(subdivisionEnt.getSubdivisionId());
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
//		
