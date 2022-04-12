package com.spdp.serviceImpl;

import java.text.ParseException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.spdp.bean.PoliceDistrictBean;
import com.spdp.bean.PoliceStationBean;
import com.spdp.bean.policeSubDivisionBean;
import com.spdp.entity.PoliceDistrictMst;
import com.spdp.entity.PoliceSPDO_STAMapping;
import com.spdp.entity.PoliceStationMst;




import com.spdp.entity.PoliceSubDivision;
import com.spdp.service.PoliceSubDivisionService;
/**
 * @author kamalakanta.g
 *
 */
@ApplicationScoped
public class PoliceSubDivisionalServiceImpl implements PoliceSubDivisionService {

	
	private static final Logger Logger = LoggerFactory.getLogger(PoliceSubDivisionalServiceImpl.class);
	
	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;
	

	@Override
	public List<PoliceStationBean> getPoliceStationDetails() {
		Logger.info("Starting getPoliceStationDetails() in PoliceSubDivisionalServiceImpl....");
		List<PoliceStationMst> policeStation=null;
		List<PoliceStationBean> policeStationBean=null;
		PoliceStationBean  policebean=null;
		
		try {
			policeStationBean=new ArrayList<PoliceStationBean>();
			policeStation=new ArrayList<PoliceStationMst>();
			
			policeStation=entityManager.createNamedQuery("policeStationDetails",PoliceStationMst.class).getResultList();
		 if(policeStation!=null) {
			for(PoliceStationMst polstation:policeStation) {
				System.out.println(polstation.getPoliceStationId()+" "+polstation.getPoliceStationName());
				policebean= new PoliceStationBean();
				policebean.setPoliceStationId(polstation.getPoliceStationId());
				policebean.setPoliceStationName(polstation.getPoliceStationName());
				
				policeStationBean.add(policebean);
				 
			 }
		 }
			Logger.info("Ending of getPoliceStationDetails() in PoliceSubDivisionalServiceImpl....");
		}catch(Exception e) {
			e.printStackTrace();
			Logger.error("Error in getPoliceStationDetails() method:", e);
		}
		return policeStationBean;
	}
	

	@Override
	public List<PoliceDistrictBean> getPoliceDistrict() {
		Logger.info("Starting getPoliceDistrict() in PoliceSubDivisionalServiceImpl....");
		List<PoliceDistrictMst> policeDistrictMst=null;
		PoliceDistrictBean policeDistBean=null;
		List<PoliceDistrictBean> policeDistrictBeanList=null;
		try {
			policeDistrictBeanList=new ArrayList<>();
			policeDistrictMst=new ArrayList<>();
			policeDistrictMst=entityManager.createNamedQuery("PoliceDistDetails",PoliceDistrictMst.class).getResultList();
			if(policeDistrictMst!=null) {
			for(PoliceDistrictMst districtmst:policeDistrictMst) {
				policeDistBean=new PoliceDistrictBean();
				policeDistBean.setpDistrictId(districtmst.getPdistrictId());
				policeDistBean.setpDistricname(districtmst.getDistrictName());
				policeDistrictBeanList.add(policeDistBean);
			}
		 }
		}catch(Exception e) {
			e.printStackTrace();
			Logger.error("Error in getPoliceDistrict() method:", e);
		}
		return policeDistrictBeanList;
	}

	@Transactional
	@Override
	public String savePolicesubdivision(policeSubDivisionBean policesubdivisionObj)throws ParseException {
		PoliceSubDivision policeSubDivisionEntity=null;
		PoliceSPDO_STAMapping policeSpdoMapping=null;
		String response = "";
		String validationMsg = "";
		String status="Y";
		try {
			if(policesubdivisionObj.getSpdoName()==null) {
				validationMsg="SPDO Name Can Not Be Null";
				return validationMsg;
			}
			if(policesubdivisionObj.getNotiNo()==null) {
				validationMsg="Notification Number Can Not Be Null";
				return validationMsg;
			}
			if(policesubdivisionObj.getNotiDate()==null) {
				validationMsg="Notification Date Can Not Be Null";
				return validationMsg;
			}
			if(policesubdivisionObj.getSpdoName()!=null) {
			
			  policeSubDivisionEntity=new PoliceSubDivision();
			 
			policeSubDivisionEntity.setSpdoName(policesubdivisionObj.getSpdoName());
			policeSubDivisionEntity.setNotifictionNo(policesubdivisionObj.getNotiNo());
			policeSubDivisionEntity.setNotificationDate(policesubdivisionObj.getNotiDate());
			policeSubDivisionEntity.setPoliceDistrict(policesubdivisionObj.getPolicedistrictId());
			policeSubDivisionEntity.setPoliceStationId(policesubdivisionObj.getPoliceStationId());
			policeSubDivisionEntity.setCreatedBy(01);
			policeSubDivisionEntity.setUpdatedBy(01);
			policeSubDivisionEntity.setCreatedDate(new Date());
			policeSubDivisionEntity.setUpdatedDate(new Date());
			policeSubDivisionEntity.setStatus(status);
			entityManager.persist(policeSubDivisionEntity);
			
			String notiNumber=policesubdivisionObj.getNotiNo();
			System.out.println("Notification Number From Ui : "+notiNumber);
			PoliceSubDivision policeSubDivision=entityManager.createNamedQuery("PoliceSubDivision_Statusupdate",PoliceSubDivision.class).setParameter("notiNumber",notiNumber).getSingleResult();
			
			
			for (PoliceStationBean policeStationBean : policesubdivisionObj.getPoliceStationObj()) {
				policeSpdoMapping=new PoliceSPDO_STAMapping();
				System.out.println(policeSubDivision.getSpdoId());
				policeSpdoMapping.setSpdoId(policeSubDivision.getSpdoId());
				policeSpdoMapping.setPoliceStationId(policeStationBean.getPoliceStationId());
				System.out.println(policeStationBean.getPoliceStationId());
				policeSpdoMapping.setStatus(status);
				entityManager.persist(policeSpdoMapping);
			}
			 
			
			
			}
			response = "Data Added Successfully !!";
		}catch(Exception e) {
			System.out.println("Error In savePolicesubdivision() method "+e);
			
		}
	  
		return response;
	}


	
	


	@Override
	public List<policeSubDivisionBean> getPoliceSubdivisionView() {
		
		List<policeSubDivisionBean> psubdivisionBean=null;
		policeSubDivisionBean policeSubDivisionBean=null;
		
		try {
			List<Object[]> object = entityManager.createNamedQuery("policeSubDivisionEntityDetails", Object[].class).getResultList();
			psubdivisionBean=new ArrayList<>();
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {
					policeSubDivisionBean=new policeSubDivisionBean();
					policeSubDivisionBean.setPoliceSubDivId((Long) obj1[0]);
					policeSubDivisionBean.setPoliceDistrict((String) obj1[1]);
					policeSubDivisionBean.setSpdoName((String) obj1[2]);
					policeSubDivisionBean.setNotiNo((String) obj1[3]);

					LocalDate date=(LocalDate) obj1[4];
					//LocalDate today = LocalDate.now();
					String formattedDate = date.format(DateTimeFormatter.ofPattern("dd-MMM-yy"));
					 System.out.println(formattedDate);
					//System.out.println(formattedDate);
					//policeSubDivisionBean.setNotificationDate(formattedDate);

					//policeSubDivisionBean.setNotificationDate((Date) obj1[4]);

					policeSubDivisionBean.setStatus((String) obj1[5]);
					
					psubdivisionBean.add(policeSubDivisionBean);
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error In getPoliceSubdivisionView() method...."+e);
		}
		
		return psubdivisionBean;
	}

	@Transactional
	@Override
	public void SubDivStatusByNotiNo(String notificationNo) {
		System.out.println("Starting SubDivStatusByNotiNo() in PoliceSubDivisionalServiceImpl...." + notificationNo);
		String status = "";
		String active="Y";
		String inActive="N";
		PoliceSubDivision policeSubDivisionEntity=null;
		try {
		policeSubDivisionEntity=entityManager.createNamedQuery("PoliceSubDivision_Statusupdate", PoliceSubDivision.class).setParameter("notiNumber", notificationNo).getSingleResult();
		status=policeSubDivisionEntity.getStatus();
		if (status.matches(inActive)) {
			policeSubDivisionEntity.setStatus(active);
		} else {
			policeSubDivisionEntity.setStatus(inActive);
		}
		}catch(Exception e) {
			System.out.println("Error SubDivStatusByNotiNo() method in PoliceSubDivisionalServiceImpl class...."+e);
		}
		entityManager.persist(policeSubDivisionEntity);
		System.out.println("Ending SubDivStatusByNotiNo() method in PoliceSubDivisionalServiceImpl...." + notificationNo);
		
	}


	@Transactional
	@Override
	public String updatePoliceSubdivision(policeSubDivisionBean policesubdivisionObj) {
		//PoliceSubDivision policeSubDivisionEntity=null;
		PoliceSPDO_STAMapping policeSpdoMapping=null;
		String response = "";
		String status="Y";
		try {
			if(policesubdivisionObj.getSpdoName()!=null && policesubdivisionObj.getSpdoId()!=null ) {
				Long SpdoId=policesubdivisionObj.getSpdoId();
				System.out.println("Notification Number From Ui : "+SpdoId+""+policesubdivisionObj.getPolicedistrictId());
				PoliceSubDivision policeSubDivisionEntity=entityManager.createNamedQuery("PoliceSubDivision_update",PoliceSubDivision.class).setParameter("spdoId",SpdoId).getSingleResult();
			  //policeSubDivisionEntity=new PoliceSubDivision();
			policeSubDivisionEntity.setSpdoName(policesubdivisionObj.getSpdoName());
			//policeSubDivisionEntity.setSpdoId(policesubdivisionObj.getSpdoId());
			policeSubDivisionEntity.setNotifictionNo(policesubdivisionObj.getNotiNo());
			policeSubDivisionEntity.setNotificationDate(policesubdivisionObj.getNotiDate());
			policeSubDivisionEntity.setPoliceDistrict(policesubdivisionObj.getPolicedistrictId());
			policeSubDivisionEntity.setPoliceStationId(policesubdivisionObj.getPoliceStationId());
			policeSubDivisionEntity.setCreatedBy(01);
			policeSubDivisionEntity.setUpdatedBy(01);
			policeSubDivisionEntity.setCreatedDate(new Date());
			policeSubDivisionEntity.setUpdatedDate(new Date());
			policeSubDivisionEntity.setStatus(status);
			
			
			entityManager.merge(policeSubDivisionEntity);
		if(policesubdivisionObj.getPoliceStationObj()!=null) {
			for (PoliceStationBean policeStationBean : policesubdivisionObj.getPoliceStationObj()) {
				policeSpdoMapping=new PoliceSPDO_STAMapping();
				System.out.println(policeSubDivisionEntity.getSpdoId());
				policeSpdoMapping.setSpdoId(policeSubDivisionEntity.getSpdoId());
				policeSpdoMapping.setPoliceStationId(policeStationBean.getPoliceStationId());
				System.out.println(policeStationBean.getPoliceStationId());
				policeSpdoMapping.setStatus(status);
				//entityManager.persist(policeSpdoMapping);
				entityManager.merge(policeSpdoMapping);
			}
		 }else {
			System.out.println("Data Not updated in Mapping table  "+policesubdivisionObj.getPoliceStationObj());
		}
			//System.out.println("Exception");
			response = "Data updated Successfully !!";
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Error updatePoliceSubdivision() method in PoliceSubDivisionalServiceImpl class...."+e);
		}
		return response;
		
	}

	@Override
	public policeSubDivisionBean getPoliceSubDivisionForUpdate(Long id) {
		PoliceSubDivision policeSubdivisionEntity=new PoliceSubDivision();
		policeSubDivisionBean policeSubDivisionBean=new policeSubDivisionBean();
		List<Long> policeStationId=new ArrayList<Long>();
		try {
			if(id!=null) {
			Query query=entityManager.createQuery("from PoliceSubDivision where spdoId = :id");
			query.setParameter("id",id);
			System.out.println("Long Id"+id);
			policeSubdivisionEntity=(PoliceSubDivision) query.getSingleResult();
			System.out.println(policeSubdivisionEntity.getSpdoName());
			
			policeSubDivisionBean.setSpdoId(policeSubdivisionEntity.getSpdoId());
			policeSubDivisionBean.setSpdoName(policeSubdivisionEntity.getSpdoName());
			policeSubDivisionBean.setNotiNo(policeSubdivisionEntity.getNotifictionNo());
			policeSubDivisionBean.setNotiDate(policeSubdivisionEntity.getNotificationDate());
			policeSubDivisionBean.setPolicedistrictId(policeSubdivisionEntity.getPoliceDistrictId());
			//policeSubDivisionBean.setPoliceDistrict(policeSubdivisionEntity.g);
			System.out.println(policeSubdivisionEntity.getSpdoId());
			Long PoliceDistrictId=policeSubdivisionEntity.getPoliceDistrictId();
			
			Query DistrictQuery=entityManager.createQuery("select districtName from PoliceDistrictMst where pdistrictId = :id");
			DistrictQuery.setParameter("id",PoliceDistrictId);
			String districtName=(String)DistrictQuery.getSingleResult();
			policeSubDivisionBean.setPoliceDistrict(districtName);
			
			
			Query psQuery=entityManager.createQuery("select policeStationId from PoliceSPDO_STAMapping where spdoId = :id");
			psQuery.setParameter("id", id);
			policeStationId=psQuery.getResultList();
			if(policeStationId.size()!=0) {
				System.out.println("policeStationId  :"+policeStationId.size());
				//policeSubDivisionBean.setPoliceStationL(policeStationId);
			}else {
				System.out.println("policeStationId List Not found :"+id);
			}
			
			List<PoliceStationBean> policeSList=new ArrayList<>();
			List<String> policeStationView = new ArrayList<String>();
			for(Long id1:policeStationId) {
				System.out.println(id1);
				PoliceStationBean policeStationBean=new PoliceStationBean();
				Query psNameQuery=entityManager.createQuery("select policeStationName from PoliceStationMst where policeStationId = :id");
				psNameQuery.setParameter("id", id1);
				//System.out.println(psNameQuery.getSingleResult());
				String pSationName = (String) psNameQuery.getSingleResult();
				//String pSationName=PoliceSationName.toString();
				policeStationBean.setPoliceStationName(pSationName);
				policeSList.add(policeStationBean);
				policeStationView.add(pSationName);
			}
			
			policeSubDivisionBean.setPoliceStationObj(policeSList);
			policeSubDivisionBean.setPoliceStationListView(policeStationView);
			}else {
				System.out.println("Id Coming With Value"+id);
			}
		}catch(Exception e){
			 System.out.println("Error in getPoliceSubDivisionForUpdate() method .. "+e);
		}
		return policeSubDivisionBean;
	}






}
