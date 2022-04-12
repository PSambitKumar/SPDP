package com.spdp.serviceImpl;

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
import javax.transaction.Transactional;

import org.hibernate.Session;

import com.spdp.bean.DistUlbBean;
import com.spdp.bean.UlbBean;
import com.spdp.entity.ULBEntity;
import com.spdp.service.ULBMasterService;

@ApplicationScoped
public class ULBMasterServiceImpl implements ULBMasterService {

	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;

	@Transactional
	@Override
	public String saveUlbData(UlbBean ulbBean) throws ParseException {

		ULBEntity uLBEntity = new ULBEntity();
		String str_date = ulbBean.getNotificationDate();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(str_date);

		uLBEntity.setUlbName(ulbBean.getUlbName());
		uLBEntity.setNotificationNum(ulbBean.getNotificationNum());
		uLBEntity.setNotificationDate(date);
		uLBEntity.setDistrictId(ulbBean.getDistrictId());
		uLBEntity.setCreatedDate(new Date());
		uLBEntity.setUpdatedDate(new Date());
		uLBEntity.setTypeOfUlb(ulbBean.getTypeOfUlb());
		uLBEntity.setCreatedBy("Aman Jha");
		uLBEntity.setUpdatedBy("Aman Jha");
		uLBEntity.setDeleteStatus("Y");

		entityManager.persist(uLBEntity);
		return "Data saved successfully";
	}

	@Override
	public List<UlbBean> getAllUlbMasterData() {
		List<ULBEntity> ulbList = new ArrayList<ULBEntity>();
		List<UlbBean> ulbBeanList = new ArrayList<UlbBean>();

		try {
			ulbList = entityManager.createNamedQuery("ublDetails", ULBEntity.class).getResultList();

			for (ULBEntity ulb : ulbList) {
				UlbBean ulbs = new UlbBean();
				Format formatter = new SimpleDateFormat("dd-MM-yyyy");
				String s = formatter.format(ulb.getNotificationDate());

				ulbs.setDistrictId(ulb.getDistrictId());
				// ulbs.setNotificationDate(ulb.getNotificationDate());
				ulbs.setUlbName(ulb.getUlbName());
				ulbs.setUlbId(ulb.getUlbId());
				ulbs.setNotificationNum(ulb.getNotificationNum());
				ulbs.setStatus(ulb.getDeleteStatus().equalsIgnoreCase("Y") ? "Active" : "In-Active");
				ulbs.setTypeOfUlb(ulb.getTypeOfUlb());
				ulbs.setNotificationDate(s);
				javax.persistence.Query que = entityManager
						.createQuery("select districtName from District where districtId = :id");
				que.setParameter("id", ulb.getDistrictId());
				// List<Long> id = que.getResultList();

				String qs = (String) que.getSingleResult();
				ulbs.setDistName(qs);
				ulbBeanList.add(ulbs);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return ulbBeanList;
	}

	@Transactional
	@Override
	public Boolean deleteUlbMasterById(String notificationNumber) {
		String Status = "";

		ULBEntity ulbObj = entityManager.createNamedQuery("deleteByNotiNumber", ULBEntity.class)
				.setParameter("notificationNumber", notificationNumber).getSingleResult();

		Status = ulbObj.getDeleteStatus();
		if (Status.matches("N")) {
			ulbObj.setDeleteStatus("Y");
		} else {
			ulbObj.setDeleteStatus("N");
		}
		entityManager.persist(ulbObj);
		return Boolean.TRUE;
	}

	@Override
	public List<DistUlbBean> getAllDistAndUlbName() {

		List<DistUlbBean> distu = new ArrayList<>();
		List<ULBEntity> ulbss = new ArrayList<ULBEntity>();

		javax.persistence.Query q = entityManager.createQuery("  from ULBEntity ");
		ulbss = q.getResultList();
		System.out.println("print data" + ulbss);

		for (ULBEntity u : ulbss) {
			DistUlbBean u1 = new DistUlbBean();
			u1.setUlbName(u.getUlbName());

			javax.persistence.Query que = entityManager
					.createQuery("select districtName from District where districtId = :id");
			que.setParameter("id", u.getDistrictId());
			// List<Long> id = que.getResultList();

			String qs = (String) que.getSingleResult();
			u1.setDistName(qs);
			distu.add(u1);

		}

		return distu;
	}

	@Transactional
	@Override
	public UlbBean getUlbMasterDataById(Long ulbId) {
		ULBEntity ulbEnt = new ULBEntity();
		UlbBean ulbBean = new UlbBean();
		ulbEnt = entityManager.createNamedQuery("updateByulbId", ULBEntity.class).setParameter("ulbId", ulbId)
				.getSingleResult();
		ulbBean.setUlbId(ulbEnt.getUlbId());
		ulbBean.setUlbName(ulbEnt.getUlbName());
		ulbBean.setNotificationNum(ulbEnt.getNotificationNum());
		ulbBean.setDistrictId(ulbEnt.getDistrictId());
		ulbBean.setTypeOfUlb(ulbEnt.getTypeOfUlb());
		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		String s = formatter.format(ulbEnt.getNotificationDate());
		ulbBean.setNotificationDate(s);
		// ulbBean.setNotiDate(ulbEnt.getNotificationDate());
		return ulbBean;
	}

	@Transactional
	@Override
	public String updateUlbData(UlbBean ulbBean) throws ParseException {
		// TODO Auto-generated method stub
		ULBEntity ulbEnt = new ULBEntity();
		String str_date = ulbBean.getNotificationDate();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(str_date);
		ulbEnt = entityManager.createNamedQuery("updateByulbId", ULBEntity.class)
				.setParameter("ulbId", ulbBean.getUlbId()).getSingleResult();
		ulbEnt.setUlbId(ulbBean.getUlbId());
		ulbEnt.setUlbName(ulbBean.getUlbName());
		ulbEnt.setNotificationDate(date);
		ulbEnt.setNotificationNum(ulbBean.getNotificationNum());
		ulbEnt.setDistrictId(ulbBean.getDistrictId());
		ulbEnt.setTypeOfUlb(ulbBean.getTypeOfUlb());
		// ulbEnt.e
		Session ses = entityManager.unwrap(Session.class);
		ses.update(ulbEnt);
		return "Data updated successfully";
	}

}
