package com.spdp.serviceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import com.spdp.bean.SubDivisionBean;
import com.spdp.bean.UlbBean;
import com.spdp.bean.UrbanWardBean;
import com.spdp.bean.WardLocalityBean;
import com.spdp.entity.ULBEntity;
import com.spdp.entity.UrbanWardEntity;
import com.spdp.service.UrbanWardService;

@ApplicationScoped
public class UrbanWardServiceImpl implements UrbanWardService {

	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;

	@Override
	public List<UlbBean> getUlbDetailsByDistrictId(Long districtId) {

		List<ULBEntity> uLBEntity = new ArrayList<ULBEntity>();

		List<UlbBean> ulbBean = new ArrayList<UlbBean>();

		try {
			uLBEntity = entityManager.createNamedQuery("getulbName", ULBEntity.class)
					.setParameter("districtId", districtId).getResultList();

			for (ULBEntity ulb : uLBEntity) {
				UlbBean ulbB = new UlbBean();

				ulbB.setUlbId(ulb.getUlbId());
				ulbB.setUlbName(ulb.getUlbName());
				ulbBean.add(ulbB);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}

		return ulbBean;
	}

	@Transactional
	@Override
	public String saveUrbanMasterData(UrbanWardBean urbanWardBean) {

		for (int i = 0; i < urbanWardBean.getWardLocalityBean().size(); i++) {
			UrbanWardEntity urbanWardEntity = new UrbanWardEntity();

			urbanWardEntity.setDistrictId(urbanWardBean.getDistrictId());
			urbanWardEntity.setUlbId(urbanWardBean.getUlbId());
			urbanWardEntity.setWardNumber(urbanWardBean.getWardLocalityBean().get(i).getWardNumber());
			urbanWardEntity.setLocality(urbanWardBean.getWardLocalityBean().get(i).getLocality());
			urbanWardEntity.setActiveStatus("Y");
			entityManager.persist(urbanWardEntity);
		}

		return "Data Saved Successfully";
	}

	@Override
	public List<UrbanWardBean> getAllUrbanDatas() {

		List<UrbanWardEntity> urbanWardEntity = new ArrayList<UrbanWardEntity>();
		List<UrbanWardBean> urbanWardBean = new ArrayList<UrbanWardBean>();
	//Map<Long,WardLocalityBean> wardLocalityBeans = new HashMap<Long,WardLocalityBean>();
		
		Map<Long,UrbanWardBean> urbanWardMap = new HashMap<Long,UrbanWardBean>();
//		try {
//			List<Object[]> object = entityManager.createNamedQuery("getUrbanDetails", Object[].class)
//					.getResultList();
//
//			if (!object.isEmpty()) {
//				for (Object[] obj1 : object) {
//					
//					
//					UrbanWardBean urbanBean = new UrbanWardBean();
//					urbanBean.setUrbanId(urban.getUrbanId());
//					urbanBean.setDistrictId(urban.getDistrictId());
//				
//					urbanBean.setUlbId(urban.getUlbId());
//					urbanBean.setActiveStatus(urban.getActiveStatus());
//
//					
//					
//					urbanBean.setDistName(qs);
//
//					
//					urbanBean.setUlbName(qs1);
//					urbanWardBean.add(urbanBean);
//					
//					
//					
//					
//					
//					
//					
//					
//					
//					subDivisionBean = new SubDivisionBean();
//
//					subDivisionBean.setSubdivionId((Long) obj1[0]);
//					subDivisionBean.setSubdivisionName((String) obj1[1]);
//
//					subDivisionBean.setCreationDate((Date) obj1[2]);
//					subDivisionBean.setNotiNo((String) obj1[3]);
//					subDivisionBean.setStatus((String) obj1[4]);
//					subDivisionBean.setDistrictName((String) obj1[5]);
//					// subDivisionBean.setNotiDate((LocalDate)obj1[6]);
//
//					objBean.add(subDivisionBean);
//
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try {
			List<Object[]> object = entityManager.createNamedQuery("getUrbanDetails", Object[].class)
					.getResultList();
			if (!object.isEmpty()) {
				for (Object[] obj1 : object) {

				
				
				WardLocalityBean wardLocalityBean= new WardLocalityBean((String) obj1[1],(String) obj1[2]);
				
				if(urbanWardMap.containsKey((Long) obj1[5]))
				{
					//wardLocalityBeans.get(urban.getDistrictId()).add(wardLocalityBean);
					
					UrbanWardBean urbanWardBean2 = urbanWardMap.get((Long) obj1[5]);
					
					List<WardLocalityBean> wardLocalityBean2 = new ArrayList<WardLocalityBean>(urbanWardBean2.getWardLocalityBean());
					wardLocalityBean2.add(wardLocalityBean);
				
					urbanWardBean2.setWardLocalityBean(wardLocalityBean2);
					urbanWardMap.put((Long) obj1[5],urbanWardBean2);
				}
				else
				{
					
					UrbanWardBean urbanBean = new UrbanWardBean();
					urbanBean.setUrbanId((Long) obj1[0]);
					urbanBean.setDistrictId((Long) obj1[5]);
					
				    urbanBean.setActiveStatus((String) obj1[6]);

				
					urbanBean.setDistName((String) obj1[3]);

				
					urbanBean.setUlbName((String) obj1[4]);
					urbanBean.setWardLocalityBean(Arrays.asList(wardLocalityBean));
					urbanWardMap.put((Long) obj1[5],urbanBean);
					//urbanWardBean.add(urbanBean);
				}
				//wardLocalityBeans.add(wardLocalityBean);
//				urbanBean.setWardLocalityBean(wardLocalityBeans);
				//urbanBean.setWardLocalityBean(wardLocalityBeans.get(0).getLocality().);
//				urbanBean.setWardNumber(urban.getWardNumber());
//				urbanBean.setLocality(urban.getLocality());
			
				
			}


				}
			for (Map.Entry<Long,UrbanWardBean> entry : urbanWardMap.entrySet()) {
		        System.out.println(entry.getKey() + ":" + entry.getValue());
		        urbanWardBean.add(entry.getValue());
		        
		    }
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return urbanWardBean;
	}

	@Transactional
	@Override
	public void deleteUrbanMasterById(Long urbanId) {

		String Status = "";
		UrbanWardEntity urbanObj = entityManager.createNamedQuery("deleteByUrbanId", UrbanWardEntity.class)
				.setParameter("urbanId", urbanId).getSingleResult();

		Status = urbanObj.getActiveStatus();
		if (Status.matches("N")) {
			urbanObj.setActiveStatus("Y");
		} else {
			urbanObj.setActiveStatus(("N"));
		}
		entityManager.persist(urbanObj);

	}

	@Override
	public UrbanWardBean getUrbanDetailsById(Long urbanId) {

		UrbanWardEntity urbanWardEntity = new UrbanWardEntity();

		UrbanWardBean urbanWardBean = new UrbanWardBean();

		urbanWardEntity = entityManager.createNamedQuery("updateByUrbanId", UrbanWardEntity.class)
				.setParameter("urbanId", urbanId).getSingleResult();

		urbanWardBean.setUrbanId(urbanWardEntity.getUrbanId());
		urbanWardBean.setDistrictId(urbanWardEntity.getDistrictId());
		urbanWardBean.setUlbId(urbanWardEntity.getUlbId());
//		urbanWardBean.setWardNumber(urbanWardEntity.getWardNumber());
//		urbanWardBean.setLocality(urbanWardEntity.getLocality());

		return urbanWardBean;

	}

	@Transactional
	@Override
	public void updateUrbanData(UrbanWardBean urbanWardBean) {

		UrbanWardEntity urbanWardEntity = new UrbanWardEntity();

		urbanWardEntity = entityManager.createNamedQuery("updateByUrbanId", UrbanWardEntity.class)
				.setParameter("urbanId", urbanWardBean.getUrbanId()).getSingleResult();

		urbanWardEntity.setUrbanId(urbanWardBean.getUrbanId());
		urbanWardEntity.setDistrictId(urbanWardBean.getDistrictId());
		urbanWardEntity.setUlbId(urbanWardBean.getUlbId());
//		urbanWardEntity.setWardNumber(urbanWardBean.getWardNumber());
//		urbanWardEntity.setLocality(urbanWardBean.getLocality());
		entityManager.merge(urbanWardEntity);
	}

}
