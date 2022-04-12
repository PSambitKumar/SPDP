package com.spdp.serviceImpl;


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import javax.transaction.Transactional;

import com.spdp.bean.BankBean;
import com.spdp.bean.BankMasterBean;
import com.spdp.bean.DistrictBean;
import com.spdp.bean.RdcBean;
import com.spdp.entity.BankEntity;
import com.spdp.entity.BankMasterEntity;
import com.spdp.entity.BlockEntity;
import com.spdp.entity.RdcEntity;
import com.spdp.service.BankService;





	@ApplicationScoped
	public class BankServiceImpl implements BankService {
		
		@PersistenceContext(unitName = "test")
		private EntityManager entityManager;
		
		@Override
		public List<BankBean> getDetailsByBankId(){
			
			List<BankBean> obj = new ArrayList<BankBean>();
			BankBean bankBean = null;
			
			  
			  try {
				List<Object[]> object = entityManager.createNamedQuery("BankEntityDetails", Object[].class).getResultList();
			  
				if(!object.isEmpty())
				{
					for(Object[] obj1 : object)
					{
						bankBean = new BankBean();
						
						 bankBean.setBankbranchId((Long)obj1[0]);
						 bankBean.setBankbranchName((String)obj1[1]);
						 //bankBean.setBankId((Long)obj1[2]);
						 bankBean.setIfscCode((String)obj1[2]);
						 bankBean.setStatus((String)obj1[3]);
						 bankBean.setBankName((String)obj1[4]);
						 bankBean.setDistrictName((String)obj1[5]);
						 obj.add(bankBean);
						  
						
					}
				}
			  }
					catch (Exception e) {
						e.printStackTrace();
					}
					  return obj;

			

		}

		@Override
		public List<BankMasterBean> getDetailsOfBank() {
			List<BankMasterEntity> obj = new ArrayList<BankMasterEntity>();
			//RdcEntity obj = new RdcEntity();
			 obj = entityManager.createNamedQuery("getBankName", BankMasterEntity.class).getResultList();
			 List<BankMasterBean> objBean =new ArrayList<BankMasterBean>();
			 
			 for(BankMasterEntity en:obj) {
				 BankMasterBean rdcBean =new BankMasterBean();
				 SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");
				 rdcBean.setBankId(en.getBankId());
				rdcBean.setbName(en.getBankName());
				objBean.add(rdcBean);
				//System.out.println(rdcBean.getRdcName());
			 }
			return objBean;
		}
	
			@Transactional
		@Override
		public void deleteBankByBranchName(String bankbranchName) {
			System.out.println("Service------- " + bankbranchName);
			String Status = "";
			BankEntity bkObj = entityManager.createNamedQuery("getRowByBankbranchName", BankEntity.class)
					.setParameter("bankbranchName", bankbranchName).getSingleResult();
			Status = bkObj.getActiveStatus();
			if (Status.matches("N")) {
				bkObj.setActiveStatus("Y");
			} else {
				bkObj.setActiveStatus("N");
			}
			entityManager.persist(bkObj);

		}

		@Transactional
		@Override
		public String saveBankDetails(BankBean bkBean) {
			
			BankEntity entObj = new BankEntity();
			String response = "";
//			String validationMsg = "";
		
//			
//		try {
//				if(bkBean.getBankbranchName() == null) {
//					validationMsg = "Branch Name Can Not Be Null";
//					return validationMsg;
//				}
//				
//				if(bkBean.getBankName() == null ) {
//					validationMsg = "Bank Name Can Not Be Null";
//					return validationMsg;
//				}
//				if(bkBean.getIfscCode() == null) {
//					validationMsg = "IFSC Code Can Not Be Null";
//					return validationMsg;
//				}
//				if(bkBean.getMicrNo() == null ) {
//					validationMsg = "MICR No Can Not Be Null";
//					return validationMsg;
//				}
//				if(bkBean.getDist().size() == 0) {
//					validationMsg = "District Lists Can Not Be Null";
//					return validationMsg;
//				}
//				if(bkBean.getLocation() == null ) {
//					validationMsg = "Location Can Not Be Null";
//					return validationMsg;
//				}
//				
//				if(bkBean.getPincode() == null ) {
//					validationMsg = "Pin Code Can Not Be Null";
//					return validationMsg;
//				}
				
//				Date date = Calendar.getInstance().getTime();  
//				DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
//				String createdOn = dateFormat.format(date); 
//				
				
				//String[] splittedItems= bkBean.getNotiDate().split("-");
//				int date =Integer.parseInt(splittedItems[2]);
//				int month =Integer.parseInt(splittedItems[1]);
//				int year = Integer.parseInt(splittedItems[0]);
//				System.out.println("Date- "+date+" Month- "+month+" Year"+year);
			
		    	if(bkBean.getBankbranchName()!= null ) {
				
				entObj.setBankbranchName(bkBean.getBankbranchName());
				entObj.setBankId(bkBean.getBankId());
				entObj.setIfscCode(bkBean.getIfscCode());
				entObj.setMicrNo(bkBean.getMicrNo());
				System.out.println("Dist id is ///////////////////////////////////////////////////////////////////////////////////////////////"+bkBean.getDistrictId());
				entObj.setDistrictId(bkBean.getDistrictObj());
				entObj.setLocation(bkBean.getLocation());
				entObj.setPincode(bkBean.getPincode());
				entObj.setCreatedOn(new Date());
				entObj.setUpdatedOn(new Date());
				entObj.setCreatedBy(1L);
				entObj.setUpdatedBy(1L);			
				entObj.setActiveStatus("Y");

				
				entityManager.persist(entObj);
				
				BankEntity rdcObj =  entityManager.createNamedQuery("getRowByBankbranchName",BankEntity.class).setParameter("bankbranchName", bkBean.getBankbranchName()).getSingleResult();
		
				response = "Data Added Successfully !!";
			
			}else {
					System.out.println("No data added..");
				}
		    	
//		} catch(Exception e) {			
//			e.printStackTrace();
//			response = "Some Error Occurred !!";
//	}
			return response;
			
			
		
		}
	
	
			
			
//		@Override
//		public List<DistSubdivisionBean> getDistrictAndSubdivisionDetails() {
//				
//			List<DistrictEntity> enList =new ArrayList<DistrictEntity>();
//			List<DistSubdivisionBean> details =new ArrayList<DistSubdivisionBean>();
//		
//			
//			javax.persistence.Query q= entityManager.createQuery("  from DistrictEntity ");
//			enList = q.getResultList();
//			System.out.println(" District table length  "+enList.size());
//			
//			for(DistrictEntity en: enList) {
//				
//				DistSubdivisionBean rdBean = new DistSubdivisionBean();
//				List<String> str =new ArrayList<String>();
//				
//				rdBean.setDistrictName(en.getDistrictName());
//				javax.persistence.Query que = entityManager.createQuery(" select subdivisionId from DistrictSubdivMapping where districtId = :id ");
//				que.setParameter("id", en.getDistrictId());
//				List<Long> id = que.getResultList();
//				System.out.println(" subdivisionId from Mapping length  "+id.size());
//				
//					for(Long t:id) {
//						javax.persistence.Query que1 = entityManager.createQuery(" select subdivisionName from SubDivisionEntity where subdivisionId = :id ");
//						que1.setParameter("id", t);
//						str.add(que1.getSingleResult().toString());	
//					}
//					
//					System.out.println(" Subdivision List(str) length  "+str.size());
//						rdBean.setSubdivNames( str);
//						details.add(rdBean);
//				
//			}
//			
//
//			return details;
//		}
//
		@Override
		public BankBean getBankDetailsForUpdate(Long id) {

			
			BankEntity rdcEnt = new BankEntity();
			BankBean rdcBean = new BankBean();
//			List<Long> districtId = new ArrayList<Long>();
			
			try {
				
				System.out.println(id+" >>>>>>>>>>>>>>>");
				javax.persistence.Query q= entityManager.createQuery("from BankEntity where bankbranchId = :id");
				q.setParameter("id",id);
			
				rdcEnt = (BankEntity) q.getSingleResult();
				System.out.println(rdcEnt.getBankbranchName());
			
				rdcBean.setBankbranchName(rdcEnt.getBankbranchName());
				rdcBean.setBankId(rdcEnt.getBankId());
				rdcBean.setIfscCode(rdcEnt.getIfscCode());
				rdcBean.setMicrNo(rdcEnt.getMicrNo());
				rdcBean.setDistrictId(rdcEnt.getDistrictId());
				rdcBean.setLocation(rdcEnt.getLocation());
				rdcBean.setPincode(rdcEnt.getPincode());
				entityManager.persist(rdcBean);

			}catch(Exception e) {		
				e.printStackTrace();
			}
				return rdcBean;
		}
		
		@Transactional
		@Override
		public void updateBankDetails(BankBean distObj) {
			
//			BankEntity distEnt;
			System.out.println("the bank name >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+distObj.getBankId());
			
			StoredProcedureQuery distobj1 = this.entityManager.createStoredProcedureQuery("SP_BANK")
					//.registerStoredProcedureParameter("flag", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("V_BANKBRANCH_NAME", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("V_BANK_ID_NAME", Long.class, ParameterMode.IN)
					.registerStoredProcedureParameter("V_IFSC_CODE", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("V_MICR_NO", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("V_DISTRICT_ID", Long.class, ParameterMode.IN)
					.registerStoredProcedureParameter("V_LOCATION", String.class, ParameterMode.IN)
					.registerStoredProcedureParameter("V_PINCODE", Long.class, ParameterMode.IN)
					.registerStoredProcedureParameter("V_BANKBRANCH_ID", Long.class, ParameterMode.IN);
			
			distobj1
			//.setParameter("flag", "EDIT")
			.setParameter("V_BANKBRANCH_NAME", distObj.getBankbranchName())
			.setParameter("V_BANK_ID_NAME", distObj.getBankId())
			.setParameter("V_IFSC_CODE",distObj.getIfscCode())
			.setParameter("V_MICR_NO", distObj.getMicrNo())
			.setParameter("V_DISTRICT_ID", distObj.getDistrictId())
			.setParameter("V_LOCATION", distObj.getLocation())
			.setParameter("V_PINCODE", distObj.getPincode())
			.setParameter("V_BANKBRANCH_ID", distObj.getBankbranchId());
			
			distobj1.execute();
}

		@Override
		public List<BankBean> getBankMasterDetailsFilteredByDistrictNames(Long did) {
			List<BankBean> obj = new ArrayList<BankBean>();
			BankBean bankBean = null;
			try {
				javax.persistence.Query q1 = entityManager
						.createQuery(" select b.bankbranchId,b.bankbranchName,b.ifscCode,b.activeStatus,m.bankName,d.districtName from "
								+ " BankEntity b  inner join BankMasterEntity m on b.bankId = m.bankId inner join District d on b.districtId=d.districtId where b.districtId =: id");
				q1.setParameter("id", did);
				List<Object[]> object = q1.getResultList();
				
				if(!object.isEmpty())
				{
					for(Object[] obj1 : object)
					{
						bankBean = new BankBean();
						
						 bankBean.setBankbranchId((Long)obj1[0]);
						 bankBean.setBankbranchName((String)obj1[1]);
						 //bankBean.setBankId((Long)obj1[2]);
						 bankBean.setIfscCode((String)obj1[2]);
						 bankBean.setStatus((String)obj1[3]);
						 bankBean.setBankName((String)obj1[4]);
						 bankBean.setDistrictName((String)obj1[5]);
						 obj.add(bankBean);
						  
						
					}
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
			return obj;
		}
		
		@Override
		public List<BankBean> getBankMasterDetailsFilteredByBankNames(Long bid) {
			List<BankBean> obj = new ArrayList<BankBean>();
			BankBean bankBean = null;
			try {
				javax.persistence.Query q1 = entityManager
						.createQuery(" select b.bankbranchId,b.bankbranchName,b.ifscCode,b.activeStatus,m.bankName,d.districtName from "
								+ " BankEntity b  inner join BankMasterEntity m on b.bankId = m.bankId inner join District d on b.districtId=d.districtId where b.bankId =: id");
				q1.setParameter("id", bid);
				List<Object[]> object = q1.getResultList();
				
				if(!object.isEmpty())
				{
					for(Object[] obj1 : object)
					{
						bankBean = new BankBean();
						
						 bankBean.setBankbranchId((Long)obj1[0]);
						 bankBean.setBankbranchName((String)obj1[1]);
						 //bankBean.setBankId((Long)obj1[2]);
						 bankBean.setIfscCode((String)obj1[2]);
						 bankBean.setStatus((String)obj1[3]);
						 bankBean.setBankName((String)obj1[4]);
						 bankBean.setDistrictName((String)obj1[5]);
						 obj.add(bankBean);
						  
						
					}
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			// TODO Auto-generated method stub
			return obj;
		}
		
		@Override
		public List<BankBean> getBankMasterDetailsFilteredByBankBranchNames(Long bbid) {
			List<BankBean> obj = new ArrayList<BankBean>();
			BankBean bankBean = null;
			try {
				javax.persistence.Query q1 = entityManager
						.createQuery(" select b.bankbranchId,b.bankbranchName,b.ifscCode,b.activeStatus,m.bankName,d.districtName from "
								+ " BankEntity b  inner join BankMasterEntity m on b.bankId = m.bankId inner join District d on b.districtId=d.districtId where b.bankbranchId =: id");
				q1.setParameter("id", bbid);
				List<Object[]> object = q1.getResultList();
				
				if(!object.isEmpty())
				{
					for(Object[] obj1 : object)
					{
						bankBean = new BankBean();
						
						 bankBean.setBankbranchId((Long)obj1[0]);
						 bankBean.setBankbranchName((String)obj1[1]);
						 //bankBean.setBankId((Long)obj1[2]);
						 bankBean.setIfscCode((String)obj1[2]);
						 bankBean.setStatus((String)obj1[3]);
						 bankBean.setBankName((String)obj1[4]);
						 bankBean.setDistrictName((String)obj1[5]);
						 obj.add(bankBean);
					}
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return obj;
		}
		
		@Override
		public List<BankBean> bankMasterDetailsFilterByAllData(Long did, Long bid, Long bbid) {
			List<BankBean> obj = new ArrayList<BankBean>();
			BankBean bankBean = null;
			try {
				javax.persistence.Query q1 = entityManager
						.createQuery(" select b.bankbranchId,b.bankbranchName,b.ifscCode,b.activeStatus,m.bankName,d.districtName from "
								+ " BankEntity b  inner join BankMasterEntity m on b.bankId = m.bankId inner join District d on b.districtId=d.districtId where b.districtId =: did and b.bankId =:bid and b.bankbranchId =:bbid")
						.setParameter("did", did)
						.setParameter("bid", bid)
						.setParameter("bbid", bbid);
						
				List<Object[]> object = q1.getResultList();
				
				if(!object.isEmpty())
				{
					for(Object[] obj1 : object)
					{
						bankBean = new BankBean();
						
						 bankBean.setBankbranchId((Long)obj1[0]);
						 bankBean.setBankbranchName((String)obj1[1]);
						 //bankBean.setBankId((Long)obj1[2]);
						 bankBean.setIfscCode((String)obj1[2]);
						 bankBean.setStatus((String)obj1[3]);
						 bankBean.setBankName((String)obj1[4]);
						 bankBean.setDistrictName((String)obj1[5]);
						 obj.add(bankBean);
					}
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return obj;
		}
		
		@Override
		public List<BankBean> bankMasterDetailsFilterByDistAndBank(Long did, Long bid) {
			List<BankBean> obj = new ArrayList<BankBean>();
			BankBean bankBean = null;
			try {
				javax.persistence.Query q1 = entityManager
						.createQuery(" select b.bankbranchId,b.bankbranchName,b.ifscCode,b.activeStatus,m.bankName,d.districtName from "
								+ " BankEntity b  inner join BankMasterEntity m on b.bankId = m.bankId inner join District d on b.districtId=d.districtId where b.districtId =: did and b.bankId =:bid ")
						.setParameter("did", did)
						.setParameter("bid", bid);
						
				List<Object[]> object = q1.getResultList();
				
				if(!object.isEmpty())
				{
					for(Object[] obj1 : object)
					{
						bankBean = new BankBean();
						
						 bankBean.setBankbranchId((Long)obj1[0]);
						 bankBean.setBankbranchName((String)obj1[1]);
						 //bankBean.setBankId((Long)obj1[2]);
						 bankBean.setIfscCode((String)obj1[2]);
						 bankBean.setStatus((String)obj1[3]);
						 bankBean.setBankName((String)obj1[4]);
						 bankBean.setDistrictName((String)obj1[5]);
						 obj.add(bankBean);
					}
				}
				
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			return obj;
		}

		@Override
		public List<BankBean> getBankBranchDetailsBybankId(Long id) {
			List<BankBean> ObList = new ArrayList<BankBean>();
			BankBean subDiv = null;
			try { 
				//List<Object[]> obj= blockService.getSubDivDetailsByDistId(districtId);
				List<Object[]> object = entityManager.createNamedQuery("getBankBranchbyBankId", Object[].class)
						.setParameter("id", id)
						.getResultList();
				
				if(!object.isEmpty())
				{
					for(Object[] obj1 : object)
					{
						subDiv = new BankBean();
						
						subDiv.setBankbranchId((Long)obj1[0]);
						subDiv.setBankbranchName((String)obj1[1]);
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
}