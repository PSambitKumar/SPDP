package com.spdp.serviceImpl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;

import com.spdp.bean.RICircleBeans;
import com.spdp.bean.TehsilMasterBean;
import com.spdp.entity.RICircleMasterEntity;
import com.spdp.entity.TehsilEntity;
import com.spdp.entity.TehsilRICircleMapping;
import com.spdp.service.TehsilService;

@ApplicationScoped
public class TehsilServiceImpl implements TehsilService {

	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;

	@Transactional
	@Override
	public String saveTehsilDetails(TehsilMasterBean tehsilMasterBean) throws ParseException {

		System.out.println("print data"+tehsilMasterBean);
		TehsilEntity tehsilEntity = new TehsilEntity();
		String str_date = tehsilMasterBean.getNotificationDate();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(str_date);
		tehsilEntity.setTehsilName(tehsilMasterBean.getTehsilName());
		tehsilEntity.setNotificationNum(tehsilMasterBean.getNotificationNum());
		tehsilEntity.setColumn1(date);
		tehsilEntity.setSubDivisionId(tehsilMasterBean.getSubDivisionId());
		tehsilEntity.setDistrictId(tehsilMasterBean.getDistrictId());
		tehsilEntity.setCreatedBy("Aman");
		tehsilEntity.setCreatedOn(new Date());
		tehsilEntity.setUpdatedBy("Aman");
		tehsilEntity.setUpdatedOn(new Date());

		tehsilEntity.setDeleteStatus("Y");

		entityManager.persist(tehsilEntity);

		BigDecimal idLong = null;
		List<BigDecimal> ids = new ArrayList<BigDecimal>();
		for (int i = 0; i < tehsilMasterBean.getRiCircle().size(); i++) {
			RICircleBeans bean = new RICircleBeans();
			bean = tehsilMasterBean.getRiCircle().get(i);
			TehsilRICircleMapping mapEnt = new TehsilRICircleMapping();
			mapEnt.setTehsilId(Long.parseLong(tehsilMasterBean.getTehsilNames()));
			mapEnt.setRicircleId(bean.getRiCircleId());
			Query q1 = entityManager.createNativeQuery("select max(TEHSIL_ID) from TBL_TEHSIL_MASTER");
			idLong=(BigDecimal) q1.getSingleResult();
			System.out.println("id value:-"+idLong);
			 mapEnt.setTehsilRICircle(((BigDecimal) q1.getSingleResult()).longValue());
			entityManager.persist(mapEnt);
//			for (int j = 0; j < tehsilMasterBean.getRiCircle().get(i).getRiCircleName().size(); j++) {
//
//				Query q = entityManager
//						.createNativeQuery("select RI_CIRCLE_ID from TBL_RI_CIRCLE_MASTER where RI_CIRCLE_NAME= :name")
//						.setParameter("name", bean.getRiCircleName().get(j));
//				idLong = (BigDecimal) q.getSingleResult();
////				System.out.println(" Village Id - " + idLong.longValue());
//
//				TehsilRICircleMapping mapEnt = new TehsilRICircleMapping();
//				mapEnt.setTehsilId(tehsilMasterBean.getRiCircle().get(i).getRicid());
//				mapEnt.setRicircleId(idLong.longValue());
//				Query q1 = entityManager.createNativeQuery("select max(RI_CIRCLE_ID) from TBL_TEHSIL_MASTER");
//				mapEnt.setTehsilRICircle(((BigDecimal) q1.getSingleResult()).longValue());
//				entityManager.persist(mapEnt);
//				ids.add(idLong);
//			}
		}
		

		return "Data saved successfully";
	}

	@Override
	public List<TehsilMasterBean> getAllTehsilData() {

		List<TehsilEntity> tehsilEntity = new ArrayList<TehsilEntity>();
		List<TehsilMasterBean> tehsilMasterBean = new ArrayList<TehsilMasterBean>();

		try {

			tehsilEntity = entityManager.createNamedQuery("tehsilDetails", TehsilEntity.class).getResultList();
			for (TehsilEntity teh : tehsilEntity) {
				TehsilMasterBean tehsilBean = new TehsilMasterBean();

//				Format formatter = new SimpleDateFormat("dd-MM-yyyy");
//				String s = formatter.format(vill.getNotificationDate());

				Format formatter = new SimpleDateFormat("dd-MM-yyyy");
				String s = formatter.format(teh.getColumn1());
				tehsilBean.setTehsiltId(teh.getTehsiltId());
				tehsilBean.setTehsilName(teh.getTehsilName());

				tehsilBean.setNotificationNum(teh.getNotificationNum());
				tehsilBean.setDistrictId(teh.getDistrictId());
				tehsilBean.setDeleteStatus(teh.getDeleteStatus());
				tehsilBean.setNotificationDate(s);
				
				if (teh.getDistrictId() != null) {
					System.out.println("dddd");
					javax.persistence.Query que = entityManager
							.createQuery("select districtName from District where districtId = :id");
					que.setParameter("id", teh.getDistrictId());

					String qs = (String) que.getSingleResult();
					tehsilBean.setDistName(qs);
				}
				if (teh.getSubDivisionId() != null) {
					javax.persistence.Query que = entityManager
							.createQuery("select subdivisionName from SubDivisionEntity where subdivisionId = :id");
					que.setParameter("id", teh.getSubDivisionId());

					String qs = (String) que.getSingleResult();
					tehsilBean.setSubName(qs);
				}
				tehsilMasterBean.add(tehsilBean);

			}

		}

		catch (Exception e) {
			e.printStackTrace();
		}

		return tehsilMasterBean;
	}

	@Transactional
	@Override
	public void deleteTehsilMasterById(Long tehsiltId) {

		String Status = "";
		TehsilEntity tehsilObj = entityManager.createNamedQuery("deleteBytehsiltId", TehsilEntity.class)
				.setParameter("tehsiltId", tehsiltId).getSingleResult();

		Status = tehsilObj.getDeleteStatus();
		if (Status.matches("N")) {
			tehsilObj.setDeleteStatus("Y");
		} else {
			tehsilObj.setDeleteStatus(("N"));
		}
		entityManager.persist(tehsilObj);
		// return Boolean.TRUE;

	}

	@Transactional
	@Override
	public TehsilMasterBean getTehsilDetailsById(Long tehsiltId) {

		TehsilEntity tehsilEntity = new TehsilEntity();

		List<TehsilRICircleMapping> TehsilRICircleMapping=new ArrayList<TehsilRICircleMapping>();
		
		TehsilMasterBean tehsilMasterBean = new TehsilMasterBean();

		tehsilEntity = entityManager.createNamedQuery("updateBytehsiltId", TehsilEntity.class)
				.setParameter("tehsiltId", tehsiltId).getSingleResult();

		tehsilMasterBean.setTehsiltId(tehsilEntity.getTehsiltId());
		tehsilMasterBean.setTehsilName(tehsilEntity.getTehsilName());
		tehsilMasterBean.setNotificationNum(tehsilEntity.getNotificationNum());
		tehsilMasterBean.setDistrictId(tehsilEntity.getDistrictId());
		tehsilMasterBean.setSubDivisionId(tehsilEntity.getSubDivisionId());
		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		String s = formatter.format(tehsilEntity.getColumn1());
		tehsilMasterBean.setNotificationDate(s);
		//tehsilMasterBean.setNotiDate(tehsilEntity.getColumn1());
		System.out.println("date ---"+tehsilEntity.getColumn1());
		//tehsilMasterBean.setNotificationDate(s);


		TehsilRICircleMapping = entityManager.createNamedQuery("getBytehsiltId", TehsilRICircleMapping.class)
				.setParameter("tehsilRICircle", tehsiltId).getResultList();
		
		System.out.println("print tehsil details:-"+TehsilRICircleMapping);
		List<RICircleBeans> ri=new ArrayList<RICircleBeans>();
		List<Long> ricirleIds =new ArrayList<Long>();
		Long tehsilId=0L;
		
		
		
		 for(TehsilRICircleMapping tehsilRI:TehsilRICircleMapping) {
			 tehsilId= tehsilRI.getTehsilId();
			// RICircleBeans ricirle=new RICircleBeans();
			 ricirleIds.add(tehsilRI.getRicircleId());
			// ricirle.setRiCircleId(tehsilRI.getRicircleId());
			// ri.add(ricirle);
			 
		 }
		 
	List<RICircleMasterEntity>  riEntity = entityManager.createNamedQuery("riDetails", RICircleMasterEntity.class).setParameter("riCircleIds", ricirleIds)
					.getResultList();

			for (RICircleMasterEntity riCer : riEntity) {
				 RICircleBeans ricirle=new RICircleBeans();
				 ricirle.setRiCircleId(riCer.getRiCircleId());
				 ricirle.setRiCircleName(riCer.getRiCircleName());
				 ri.add(ricirle);
			}
		 tehsilMasterBean.setTehsilNames(String.valueOf(tehsilId));
		 tehsilMasterBean.setRiCircle(ri);
		return tehsilMasterBean;

	}

	@Transactional
	@Override
	public void updateTehsilMasterData(TehsilMasterBean tehsilMasterBean) throws ParseException {

		System.out.println("print tehsil data"+tehsilMasterBean);
		TehsilEntity tehsilEntity = new TehsilEntity();
		String[] splittedItems= tehsilMasterBean.getNotificationDate().split("-");
		int date =Integer.parseInt(splittedItems[0]);
		int month =Integer.parseInt(splittedItems[1]);
		int year = Integer.parseInt(splittedItems[2]);
//		String str_date = tehsilMasterBean.getNotificationDate();
//		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = formatter.parse(str_date);
		System.out.println("aman jha 123");
		tehsilEntity = entityManager.createNamedQuery("updateBytehsiltId", TehsilEntity.class)
				.setParameter("tehsiltId", tehsilMasterBean.getTehsiltId()).getSingleResult();
		System.out.println("print tehsil data:--"+tehsilEntity);
		System.out.println("aman jha 456");
		// tehsilEntity.setTehsiltId(tehsilEntity.getTehsiltId());
		tehsilEntity.setTehsilName(tehsilMasterBean.getTehsilName());
		tehsilEntity.setNotificationNum(tehsilMasterBean.getNotificationNum());
		tehsilEntity.setDistrictId(tehsilMasterBean.getDistrictId());
		tehsilEntity.setSubDivisionId(tehsilMasterBean.getSubDivisionId());
	//	tehsilEntity.setColumn1(date);
		tehsilEntity.setColumn1(new Timestamp(year-1900, month-1, date, 5, 5, 5, 5 ));
		Session ses = entityManager.unwrap(Session.class);
		ses.update(tehsilEntity);
		
//		TehsilRICircleMapping tehsilRICircleMapping=new TehsilRICircleMapping();
//		
//		tehsilRICircleMapping=entityManager.createNamedQuery("BytehsiltId", TehsilRICircleMapping.class)
//				.setParameter("tehsilRICircle", tehsilMasterBean.getTehsiltId()).getSingleResult();
//		
//		System.out.println("result data:--"+tehsilRICircleMapping);
	}

	@Override
	public List<RICircleBeans> getRICircleByTehsilId() {

		List<RICircleMasterEntity> rICircleMasterEntity = new ArrayList<RICircleMasterEntity>();
		List<RICircleBeans> rICircleBeans = new ArrayList<RICircleBeans>();

		try {
			System.out.println("aman123");
			rICircleMasterEntity = entityManager.createNamedQuery("ricircleDetails", RICircleMasterEntity.class)
					.getResultList();

			System.out.println("print data" + rICircleMasterEntity);

			for (RICircleMasterEntity riCircle : rICircleMasterEntity) {
				RICircleBeans riCirclebean = new RICircleBeans();
				riCirclebean.setRiCircleId(riCircle.getRiCircleId());
				riCirclebean.setRiCircleName(riCircle.getRiCircleName());
				rICircleBeans.add(riCirclebean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return rICircleBeans;
	}
}
