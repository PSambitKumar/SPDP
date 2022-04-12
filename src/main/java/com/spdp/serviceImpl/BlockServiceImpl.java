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
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;
import com.spdp.bean.BlockBean;
import com.spdp.bean.BlockGpBean;
import com.spdp.bean.GpBean;
import com.spdp.bean.SubDivisionBean;
import com.spdp.entity.BlockEntity;
import com.spdp.entity.BlockGpMapping;
import com.spdp.entity.GpEntity;
import com.spdp.entity.SubDivisionEntity;
import com.spdp.service.BlockService;

/**
 * 
 * @author jyotiranjan.behera
 *
 */
@ApplicationScoped
public class BlockServiceImpl implements BlockService {

	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;

	@Override
	public List<BlockBean> getDetailsByBlockId() {
		
		List<BlockBean> objBean = new ArrayList<BlockBean>();
		BlockBean blockBean = null;
		
		  
		  try {
			List<Object[]> object = entityManager.createNamedQuery("BlockEntityDetails", Object[].class).getResultList();
		  
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					blockBean = new BlockBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String cDateString= dateFormat.format((Date)obj1[2]);
					String nDateString= dateFormat.format((Date)obj1[8]);
					
					blockBean.setBlockId((Long)obj1[0]);
					blockBean.setBlockName((String)obj1[1]);
//					blockBean.setCreationDate((Date)obj1[2]);
					blockBean.setcDate(cDateString);
					blockBean.setNotiNo((String)obj1[3]);
					blockBean.setDistrictName((String)obj1[4]);
					blockBean.setSubdivisionName((String)obj1[5]);
					blockBean.setStatus((String)obj1[6]);
					blockBean.setLgdCode((Long)obj1[7]);
					blockBean.setNotiDate(nDateString);
					
					
					  
					  
					  objBean.add(blockBean);
					  
					
				}
			}
		  }
				catch (Exception e) {
					e.printStackTrace();
				}
				  return objBean;
	}

	@Override
	public List<GpBean> getGpDetails() {
		List<GpEntity> gp = new ArrayList<GpEntity>();
		List<GpBean> gpList = new ArrayList<GpBean>();
		try {
			gp = entityManager.createNamedQuery("GpDetails", GpEntity.class).getResultList();

			for (GpEntity d : gp) {
				GpBean bn = new GpBean();
				bn.setGpId(d.getGpId());
				bn.setGpName(d.getGpName());
				gpList.add(bn);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return gpList;
	}

	@Transactional
	@Override
	public void deleteBlockByNotiNo(String notiNo) {
		System.out.println("Service------- " + notiNo);
		String Status = "";
		BlockEntity blockObj = entityManager.createNamedQuery("getBlockRowByNotiNo", BlockEntity.class)
				.setParameter("notiNo", notiNo).getSingleResult();
		Status = blockObj.getActiveStatus();
		if (Status.matches("N")) {
			blockObj.setActiveStatus("Y");
		} else {
			blockObj.setActiveStatus("N");
		}
		entityManager.persist(blockObj);

	}

	@SuppressWarnings("deprecation")
	@Transactional
	@Override
	public String saveBlockDetails(BlockBean blockBean) {
		System.out.println("DistId ---------------------------"+blockBean.getDistrictId());
		// TODO Auto-generated method stub
		BlockEntity entObj = new BlockEntity();
		String response = "";
		String validationMsg = "";
		try {
			if (blockBean.getBlockName() == null) {
				validationMsg = "Block Name Can Not Be Null";
				return validationMsg;
			}
			if (blockBean.getNotiNo() == null) {
				validationMsg = "Notification Number Can Not Be Null";
				return validationMsg;
			}
			if (blockBean.getNotiDate() == null) {
				validationMsg = "Notification Date Can Not Be Null";
				return validationMsg;
			}
			if(blockBean.getGpObj().size() == 0) {
				validationMsg = "District Lists Can Not Be Null";
				return validationMsg;
			}
			String[] splittedItems = blockBean.getNotiDate().split("-");
			int date = Integer.parseInt(splittedItems[2]);
			int month = Integer.parseInt(splittedItems[1]);
			int year = Integer.parseInt(splittedItems[0]);
			System.out.println("Date- " + date + " Month- " + month + " Year" + year);
			if (blockBean.getBlockName() != null) {

				entObj.setBlockName(blockBean.getBlockName());
				entObj.setLgdCode(blockBean.getLgdCode());
				entObj.setNotificationNo(blockBean.getNotiNo());
				entObj.setNotificationDate(new Timestamp(year - 1900, month - 1, date, 5, 5, 5, 5));
				entObj.setCreatedOn(new Timestamp(year - 1900, month - 1, date, 5, 5, 5, 5));
				entObj.setUpdatedOn(new Timestamp(year - 1900, month - 1, date, 5, 5, 5, 5));
				entObj.setDistrictId(blockBean.getDistrict());
				entObj.setSubDivisionId(blockBean.getSunDivisionId());
				entObj.setCreatedBy("Jyoti");
				entObj.setUpdatedBy("Jyoti");
				entObj.setAciton("Ok");
				entObj.setActiveStatus("Y");
				System.out.println(blockBean);
				entityManager.persist(entObj);

			BlockEntity blockObj =  entityManager.createNamedQuery("getBlockRowByNotiNo",BlockEntity.class).setParameter("notiNo", blockBean.getNotiNo()).getSingleResult();
			
			for(GpBean gpBean : blockBean.getGpObj()) {
				BlockGpMapping blockGpMapObj  = new BlockGpMapping();
				blockGpMapObj.setGpId(gpBean.getGpId());
				blockGpMapObj.setBlockId(blockObj.getBlockId());
				entityManager.persist(blockGpMapObj);
			}
		
			}else {
				System.out.println("Data Not Found");
			}
				System.out.println("Exception");
				response = "Data Added Successfully !!";
			
		} catch (Exception e) {
			e.printStackTrace();
			response = "Some Error Occurred !!";
		}
		return response;

	}
	
	@Override
	public BlockBean getBlockDetailsForUpdate(Long id) {
		BlockEntity blockEnt = new BlockEntity();
		BlockBean blockBean = new BlockBean();
		List<Long> gpId = new ArrayList<Long>();

		try {

			System.out.println(id + " >>>>>>>>>>>>>>>");
			javax.persistence.Query q = entityManager.createQuery("from BlockEntity where blockId = :id");
			q.setParameter("id", id);

			blockEnt = (BlockEntity) q.getSingleResult();

			blockBean.setBlockId(blockEnt.getBlockId());
			blockBean.setBlockName(blockEnt.getBlockName());
			blockBean.setLgdCode(blockEnt.getLgdCode());
			blockBean.setNotiNo(blockEnt.getNotificationNo());
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			blockBean.setNotiDate(formatter.format(blockEnt.getNotificationDate()));
			blockBean.setDistrictId(blockEnt.getDistrictId());
			blockBean.setSunDivisionId(blockEnt.getSubDivisionId());
			
			javax.persistence.Query q1 = entityManager
					.createQuery(" select gpId from BlockGpMapping where blockId = :id");
			q1.setParameter("id", id);
			gpId = q1.getResultList();
			System.out.println("gp Id size is ///////////////////"+gpId.size());
			


			if (gpId.size() != 0) {
				System.out.println("<<<<<<<<<<<<<<<< " + gpId.size());
				blockBean.setGpL(gpId);
			} else {
				System.out.println("Gp List Not Found");
			}

			List<GpBean> gpList = new ArrayList<GpBean>();
			List<String> gpListForView=new ArrayList<String>();

			for (Long l : gpId) {
				GpBean gpBean = new GpBean();
				javax.persistence.Query query = entityManager
						.createQuery(" select gpName from GpEntity where gpId = :id");
				query.setParameter("id", l);
				gpBean.setGpId(l);
				String s = (String) query.getSingleResult();
				gpBean.setGpName(s);
				gpList.add(gpBean);
				gpListForView.add(s);
			}
			blockBean.setGpObj(gpList);
			blockBean.setGpListForView(gpListForView);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return blockBean;
	}

	//@SuppressWarnings("deprecation")
	@Transactional
	@Override
	public void updateBlockDetails(BlockBean blockObj) {
		System.out.println("-----------------------------------"+blockObj.getNotiDate());
		String[] splittedItems = blockObj.getNotiDate().split("-");
		int date = Integer.parseInt(splittedItems[0]);
		int month = Integer.parseInt(splittedItems[1]);
		int year = Integer.parseInt(splittedItems[2]);
		System.out.println("Date- " + date + " Month- " + month + " Year-" + year);
		
		Date dtDate=new Timestamp(year - 1900, month - 1, date, 5, 5, 5, 5);
		System.out.println("The Notification Date After format -------------------------------"+dtDate);
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy HH.mm.ss.S aaa");  
		String nDateString= dateFormat.format(dtDate);
		System.out.println("The Notification Date After format after convertttttttt -------------------------------"+nDateString);

		
		StoredProcedureQuery ref = this.entityManager.createStoredProcedureQuery("SP_BLOCK")
				.registerStoredProcedureParameter("flag", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_BLOCK_NAME", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_NOTIFICATION_NO", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_NOTIFICATION_DATE", String.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_LGD_CODE", Long.class, ParameterMode.IN)
				.registerStoredProcedureParameter("V_DISTRICT_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("V_SUB_DIVISION_ID", Long.class, ParameterMode.IN)
                .registerStoredProcedureParameter("V_UPDATED_BY", String.class, ParameterMode.IN)
                .registerStoredProcedureParameter("V_BLOCK_ID", Long.class, ParameterMode.IN);
		
		ref
		.setParameter("flag", "EDIT")
		.setParameter("V_BLOCK_NAME", blockObj.getBlockName())
		.setParameter("V_NOTIFICATION_NO", blockObj.getNotiNo())
		.setParameter("V_NOTIFICATION_DATE",nDateString)//"26-FEB-22 05.05.05.000000000 AM") //blockObj.getNotiDate())
		.setParameter("V_LGD_CODE",blockObj.getLgdCode()) 
		.setParameter("V_DISTRICT_ID", blockObj.getDistrictId())
		.setParameter("V_SUB_DIVISION_ID", blockObj.getSunDivisionId())
		.setParameter("V_UPDATED_BY", "JR")
		.setParameter("V_BLOCK_ID", blockObj.getBlockId());
		
		
		ref.execute();
		
		try {
			javax.persistence.Query query = entityManager.createQuery("DELETE FROM BlockGpMapping c WHERE c.blockId = :id");
			int deletedCount = query.setParameter("id", blockObj.getBlockId()).executeUpdate();
			System.out.println("Total Number of Row Deleted are -------------------------------------------->"+deletedCount);
			
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		finally {
			BlockEntity Obj =  entityManager.createNamedQuery("getBlockRowByNotiNo",BlockEntity.class).setParameter("notiNo", blockObj.getNotiNo()).getSingleResult();
			
			for(GpBean gpBean : blockObj.getGpObj()) {
				BlockGpMapping blockGpMapObj  = new BlockGpMapping();
				blockGpMapObj.setGpId(gpBean.getGpId());
				blockGpMapObj.setBlockId(Obj.getBlockId());
				entityManager.persist(blockGpMapObj);
				System.out.println("Data added to Mapping Table !!!!");
			}
			
		}
		System.out.println("Updated successsfully !!");	
		
		
	}


	@Override
	public List<BlockGpBean> getBlockAndGpDetails() {
		List<BlockEntity> enList = new ArrayList<BlockEntity>();
		List<BlockGpBean> details = new ArrayList<BlockGpBean>();
		// List<String> str =new ArrayList<String>();

		javax.persistence.Query q = entityManager.createQuery("  from BlockEntity ");
		enList = q.getResultList();
		System.out.println(" Block table length  " + enList.size());

		for (BlockEntity en : enList) {

			BlockGpBean blockGpBean = new BlockGpBean();
			List<String> str = new ArrayList<String>();
			
			blockGpBean.setBlockName(en.getBlockName());
			javax.persistence.Query que = entityManager
					.createQuery(" select gpId from BlockGpMapping where blockId = :id ");
			que.setParameter("id", en.getBlockId());
			List<Long> id = que.getResultList();
			System.out.println(" GpId from Mapping length  " + id.size());

			for (Long t : id) {
				javax.persistence.Query que1 = entityManager
						.createQuery(" select gpName from GpEntity where gpId = :id ");
				que1.setParameter("id", t);
				str.add(que1.getSingleResult().toString());
			}

			System.out.println(" Gp List(str) length  " + str.size());
			blockGpBean.setGpNames(str);
			details.add(blockGpBean);

		}
		return details;
	}

	@Override
	public List<SubDivisionBean> getSubDivisionDetails() {
		List<SubDivisionEntity> subDivision = new ArrayList<SubDivisionEntity>();
		List<SubDivisionBean> subDivisionList = new ArrayList<SubDivisionBean>();
		try {
			subDivision = entityManager.createNamedQuery("SubDivisionDetails", SubDivisionEntity.class).getResultList();

			for (SubDivisionEntity d : subDivision) {
				SubDivisionBean bn = new SubDivisionBean();
				bn.setSubdivionId(d.getSubdivisionId());
				bn.setSubdivisionName(d.getSubdivisionName());
				subDivisionList.add(bn);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subDivisionList;
	}

	@Override
	public List<BlockBean> getSubDivDetailsByDistId(Long districtId) {
		List<BlockBean> ObList = new ArrayList<BlockBean>();
		BlockBean subDiv = null;
		try { 

			List<Object[]> object = entityManager.createNamedQuery("getSubDivisionByDistId", Object[].class)
					.setParameter("districtId", districtId)
					.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					subDiv = new BlockBean();
					
					subDiv.setSunDivisionId((Long)obj1[0]);
					subDiv.setSubdivisionName((String)obj1[1]);
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
	public BlockBean getDetailsForView(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BlockBean> getGpDetailsBySubDivId(Long subDivisionId) {
		List<BlockBean> ObList = new ArrayList<BlockBean>();
		BlockBean gp = null;
		try { 

			List<Object[]> object = entityManager.createNamedQuery("getGpDetailsBySubDivId", Object[].class)
					.setParameter("sunDivisionId", subDivisionId)
					.getResultList();
			
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					gp = new BlockBean();
					
					gp.setGpId((Long)obj1[0]);
					gp.setGpName((String)obj1[1]);
					ObList.add(gp); 
	
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
	public List<BlockBean> getBlockDetailsBySubDivId(Long subDivisionId) {
		List<BlockBean> ObList = new ArrayList<BlockBean>();
		BlockBean block = null;
		try {
			
			
			List<Object[]> object = entityManager.createNamedQuery("getBlockDetailsBySubDivId", Object[].class)
					.setParameter("sunDivisionId", subDivisionId)
					.getResultList();
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					block = new BlockBean();
					
					block.setBlockId((Long)obj1[0]);
					block.setBlockName((String)obj1[1]);
					ObList.add(block); 
	
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return ObList;
	}

	//for Block field filter
	
	@Override
	public List<BlockBean> getBlockDetailsAfterFiltered(Long bid) {         //, Object dt
		List<BlockBean> objBean = new ArrayList<BlockBean>();
		BlockBean blockBean = null;
		System.out.println("////////////////////////////////////////////////////////////----"+bid);
		

		try {
			javax.persistence.Query q1 = entityManager
					.createQuery("select b.blockId,b.blockName,b.createdOn,b.notificationNo,d.districtName,s.subdivisionName,b.activeStatus, b.lgdCode, b.notificationDate from"
							+ " BlockEntity b  inner join District d on b.districtId = d.districtId inner join SubDivisionEntity s on b.subDivisionId=s.subdivisionId where b.blockId = :bid ");
			q1.setParameter("bid", bid);
			List<Object[]> object = q1.getResultList();
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					blockBean = new BlockBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String cDateString= dateFormat.format((Date)obj1[2]);
					String nDateString= dateFormat.format((Date)obj1[8]);
					
					blockBean.setBlockId((Long)obj1[0]);
					blockBean.setBlockName((String)obj1[1]);
//					blockBean.setCreationDate((Date)obj1[2]);
					blockBean.setcDate(cDateString);
					blockBean.setNotiNo((String)obj1[3]);
					blockBean.setDistrictName((String)obj1[4]);
					blockBean.setSubdivisionName((String)obj1[5]);
					blockBean.setStatus((String)obj1[6]);
					blockBean.setLgdCode((Long)obj1[7]);
					blockBean.setNotiDate(nDateString);
					
					
					  
					  
					  objBean.add(blockBean);
					  
					
				}
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return objBean;
	}

	@Override
	public List<BlockBean> blockDetailsFilterForDist(Long did) {
		List<BlockBean> objBean = new ArrayList<BlockBean>();
		BlockBean blockBean = null;
		System.out.println("////////////////////////////////////////////////////////////----"+did);
		
		//for Block field filter
		try {
			javax.persistence.Query q1 = entityManager
					.createQuery("select b.blockId,b.blockName,b.createdOn,b.notificationNo,d.districtName,s.subdivisionName,b.activeStatus, b.lgdCode, b.notificationDate from"
							+ " BlockEntity b  inner join District d on b.districtId = d.districtId inner join SubDivisionEntity s on b.subDivisionId=s.subdivisionId where b.districtId = :did ");
			q1.setParameter("did", did);
			List<Object[]> object = q1.getResultList();
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					blockBean = new BlockBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String cDateString= dateFormat.format((Date)obj1[2]);
					String nDateString= dateFormat.format((Date)obj1[8]);
					
					blockBean.setBlockId((Long)obj1[0]);
					blockBean.setBlockName((String)obj1[1]);
//					blockBean.setCreationDate((Date)obj1[2]);
					blockBean.setcDate(cDateString);
					blockBean.setNotiNo((String)obj1[3]);
					blockBean.setDistrictName((String)obj1[4]);
					blockBean.setSubdivisionName((String)obj1[5]);
					blockBean.setStatus((String)obj1[6]);
					blockBean.setLgdCode((Long)obj1[7]);
					blockBean.setNotiDate(nDateString);
					
					
					  
					  
					  objBean.add(blockBean);
					  
					
				}
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return objBean;
	}

	@Override
	public List<BlockBean> blockDetailsFilterForSubDivision(Long sid) {
		List<BlockBean> objBean = new ArrayList<BlockBean>();
		BlockBean blockBean = null;
		System.out.println("////////////////////////////////////////////////////////////----"+sid);
		
		//for Block field filter
		try {
			javax.persistence.Query q1 = entityManager
					.createQuery("select b.blockId,b.blockName,b.createdOn,b.notificationNo,d.districtName,s.subdivisionName,b.activeStatus, b.lgdCode, b.notificationDate from"
							+ " BlockEntity b  inner join District d on b.districtId = d.districtId inner join SubDivisionEntity s on b.subDivisionId=s.subdivisionId where b.subDivisionId = :sid ");
			q1.setParameter("sid", sid);
			List<Object[]> object = q1.getResultList();
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					blockBean = new BlockBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String cDateString= dateFormat.format((Date)obj1[2]);
					String nDateString= dateFormat.format((Date)obj1[8]);
					
					blockBean.setBlockId((Long)obj1[0]);
					blockBean.setBlockName((String)obj1[1]);
//					blockBean.setCreationDate((Date)obj1[2]);
					blockBean.setcDate(cDateString);
					blockBean.setNotiNo((String)obj1[3]);
					blockBean.setDistrictName((String)obj1[4]);
					blockBean.setSubdivisionName((String)obj1[5]);
					blockBean.setStatus((String)obj1[6]);
					blockBean.setLgdCode((Long)obj1[7]);
					blockBean.setNotiDate(nDateString);
					  
					objBean.add(blockBean);
					  
					
				}
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return objBean;
	}
	
	@Override
	public List<BlockBean> blockDetailsFilterByAllData(Long bid, String date) {
		List<BlockBean> objBean = new ArrayList<BlockBean>();
		BlockBean blockBean = null;
		System.out.println("////////////////////////////////////////////////////////////----"+bid);
		System.out.println("////////////////////////////////////////////////////////////----"+date);
		

		try {
			javax.persistence.Query q1 = entityManager
					.createQuery("select b.blockId,b.blockName,b.createdOn,b.notificationNo,d.districtName,s.subdivisionName,b.activeStatus, b.lgdCode, b.notificationDate from"
							+ " BlockEntity b  inner join District d on b.districtId = d.districtId inner join SubDivisionEntity s on b.subDivisionId=s.subdivisionId where b.blockId = :bid ");
			q1.setParameter("bid", bid);
			List<Object[]> object = q1.getResultList();
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					blockBean = new BlockBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String cDateString= dateFormat.format((Date)obj1[2]);
					String nDateString= dateFormat.format((Date)obj1[8]);
					if(date.equals(cDateString)) {
						blockBean.setBlockId((Long)obj1[0]);
						blockBean.setBlockName((String)obj1[1]);
	//					blockBean.setCreationDate((Date)obj1[2]);
						blockBean.setcDate(cDateString);
						blockBean.setNotiNo((String)obj1[3]);
						blockBean.setDistrictName((String)obj1[4]);
						blockBean.setSubdivisionName((String)obj1[5]);
						blockBean.setStatus((String)obj1[6]);
						blockBean.setLgdCode((Long)obj1[7]);
						blockBean.setNotiDate(nDateString);
						  
						objBean.add(blockBean);
					}  
					
				}
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return objBean;
	}
	
	@Override
	public List<BlockBean> blockDetailsFilterByDate(String date) {
		List<BlockBean> objBean = new ArrayList<BlockBean>();
		BlockBean blockBean = null;
		System.out.println("////////////////////////////////////////////////////////////----"+date);
		

		try {
			javax.persistence.Query q1 = entityManager
					.createQuery("select b.blockId,b.blockName,b.createdOn,b.notificationNo,d.districtName,s.subdivisionName,b.activeStatus, b.lgdCode, b.notificationDate from"
							+ " BlockEntity b  inner join District d on b.districtId = d.districtId inner join SubDivisionEntity s on b.subDivisionId=s.subdivisionId ");
			List<Object[]> object = q1.getResultList();
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					blockBean = new BlockBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String cDateString= dateFormat.format((Date)obj1[2]);
					String nDateString= dateFormat.format((Date)obj1[8]);
					if(date.equals(cDateString)) {
						blockBean.setBlockId((Long)obj1[0]);
						blockBean.setBlockName((String)obj1[1]);
	//					blockBean.setCreationDate((Date)obj1[2]);
						blockBean.setcDate(cDateString);
						blockBean.setNotiNo((String)obj1[3]);
						blockBean.setDistrictName((String)obj1[4]);
						blockBean.setSubdivisionName((String)obj1[5]);
						blockBean.setStatus((String)obj1[6]);
						blockBean.setLgdCode((Long)obj1[7]);
						blockBean.setNotiDate(nDateString);
						  
						objBean.add(blockBean);
					}  
					
				}
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return objBean;
	}
	
	@Override
	public List<BlockBean> blockDetailsFilterByDistAndDate(Long did, String date) {
		List<BlockBean> objBean = new ArrayList<BlockBean>();
		BlockBean blockBean = null;
		System.out.println("////////////////////////////////////////////////////////////----"+did);
		System.out.println("////////////////////////////////////////////////////////////----"+date);
		

		try {
			javax.persistence.Query q1 = entityManager
					.createQuery("select b.blockId,b.blockName,b.createdOn,b.notificationNo,d.districtName,s.subdivisionName,b.activeStatus, b.lgdCode, b.notificationDate from"
							+ " BlockEntity b  inner join District d on b.districtId = d.districtId inner join SubDivisionEntity s on b.subDivisionId=s.subdivisionId where b.districtId = :did ");
			q1.setParameter("did", did);
			List<Object[]> object = q1.getResultList();
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					blockBean = new BlockBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String cDateString= dateFormat.format((Date)obj1[2]);
					String nDateString= dateFormat.format((Date)obj1[8]);
					if(date.equals(cDateString)) {
						blockBean.setBlockId((Long)obj1[0]);
						blockBean.setBlockName((String)obj1[1]);
	//					blockBean.setCreationDate((Date)obj1[2]);
						blockBean.setcDate(cDateString);
						blockBean.setNotiNo((String)obj1[3]);
						blockBean.setDistrictName((String)obj1[4]);
						blockBean.setSubdivisionName((String)obj1[5]);
						blockBean.setStatus((String)obj1[6]);
						blockBean.setLgdCode((Long)obj1[7]);
						blockBean.setNotiDate(nDateString);
						  
						objBean.add(blockBean);
					}  
					
				}
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return objBean;
	}
	
	@Override
	public List<BlockBean> blockDetailsFilterBySubDivAndDate(Long sid, String date) {
		List<BlockBean> objBean = new ArrayList<BlockBean>();
		BlockBean blockBean = null;
		System.out.println("////////////////////////////////////////////////////////////----"+sid);
		System.out.println("////////////////////////////////////////////////////////////----"+date);
		

		try {
			javax.persistence.Query q1 = entityManager
					.createQuery("select b.blockId,b.blockName,b.createdOn,b.notificationNo,d.districtName,s.subdivisionName,b.activeStatus, b.lgdCode, b.notificationDate from"
							+ " BlockEntity b  inner join District d on b.districtId = d.districtId inner join SubDivisionEntity s on b.subDivisionId=s.subdivisionId where b.subDivisionId = :sid ");
			q1.setParameter("sid", sid);
			List<Object[]> object = q1.getResultList();
			if(!object.isEmpty())
			{
				for(Object[] obj1 : object)
				{
					blockBean = new BlockBean();
					DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");  
					String cDateString= dateFormat.format((Date)obj1[2]);
					String nDateString= dateFormat.format((Date)obj1[8]);
					if(date.equals(cDateString)) {
						blockBean.setBlockId((Long)obj1[0]);
						blockBean.setBlockName((String)obj1[1]);
	//					blockBean.setCreationDate((Date)obj1[2]);
						blockBean.setcDate(cDateString);
						blockBean.setNotiNo((String)obj1[3]);
						blockBean.setDistrictName((String)obj1[4]);
						blockBean.setSubdivisionName((String)obj1[5]);
						blockBean.setStatus((String)obj1[6]);
						blockBean.setLgdCode((Long)obj1[7]);
						blockBean.setNotiDate(nDateString);
						  
						objBean.add(blockBean);
					}  
					
				}
			}
			
			
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return objBean;
	}


}
