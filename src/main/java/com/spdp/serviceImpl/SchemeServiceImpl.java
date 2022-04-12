package com.spdp.serviceImpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.spdp.bean.DeprtBean;
import com.spdp.bean.SchemeBean;
import com.spdp.entity.DepartmentEntity;
import com.spdp.entity.SchemeEntity;
import com.spdp.service.SchemeService;

@ApplicationScoped

public class SchemeServiceImpl implements SchemeService {

	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;

	@Transactional
	@Override
	public String saveSchemeMasterData(SchemeBean schemeBean) {
		System.out.println("aman jha");
		SchemeEntity schemeEntity = new SchemeEntity();
		schemeEntity.setSchemeName(schemeBean.getSchemeName());
		schemeEntity.setDescription(schemeBean.getDescription());
		schemeEntity.setDeptId(schemeBean.getDeptId());
		schemeEntity.setActiveStatus("Y");
		schemeEntity.setCreatedBy(7L);
		schemeEntity.setCreatedOn(new Date());
		entityManager.persist(schemeEntity);
		return "Data Saved Successfully";
	}

	@Override
	public List<SchemeBean> getAllSchemesDatas() {
		List<SchemeEntity> schemeEntity = new ArrayList<SchemeEntity>();
		List<SchemeBean> schemeBean = new ArrayList<SchemeBean>();
		try {

			schemeEntity = entityManager.createNamedQuery("schemeDetails", SchemeEntity.class).getResultList();
			System.out.println("lenght:-" + schemeEntity.size());
			for (SchemeEntity sch : schemeEntity) {
				SchemeBean schBean = new SchemeBean();

				schBean.setSchemeId(sch.getSchemeId());
				schBean.setActiveStatus(sch.getActiveStatus());
				schBean.setDeptId(sch.getDeptId());
				schBean.setSchemeName(sch.getSchemeName());
				schBean.setDescription(sch.getDescription());

				javax.persistence.Query que = entityManager
						.createQuery("select deptName from DepartmentEntity where deptId = :id");
				que.setParameter("id", sch.getDeptId());

				String qs = (String) que.getSingleResult();
				schBean.setDeptName(qs);
				schemeBean.add(schBean);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return schemeBean;
	}

	@Override
	public List<DeprtBean> getDepartment() {
		List<DepartmentEntity> depart = new ArrayList<DepartmentEntity>();
		List<DeprtBean> departList = new ArrayList<DeprtBean>();
		try {
			depart = entityManager.createNamedQuery("DepartDetails", DepartmentEntity.class).getResultList();

			for (DepartmentEntity d : depart) {
				DeprtBean bn = new DeprtBean();
				bn.setDeptId(d.getDeptId());
				bn.setDeptName(d.getDeptName());
				departList.add(bn);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return departList;

	}

	@Transactional
	@Override
	public void deleteSchemeMasterById(Long schemeId) {

		String Status = "";
		SchemeEntity schemeObj = entityManager.createNamedQuery("deleteByschemeId", SchemeEntity.class)
				.setParameter("schemeId", schemeId).getSingleResult();

		Status = schemeObj.getActiveStatus();
		if (Status.matches("N")) {
			schemeObj.setActiveStatus("Y");
		} else {
			schemeObj.setActiveStatus(("N"));
		}
		entityManager.persist(schemeObj);

	}

	@Override
	public SchemeBean getSchemeDetailsById(Long schemeId) {

		SchemeEntity schemeEntity = new SchemeEntity();

		SchemeBean schemeBean = new SchemeBean();

		schemeEntity = entityManager.createNamedQuery("deleteByschemeId", SchemeEntity.class)
				.setParameter("schemeId", schemeId).getSingleResult();

		schemeBean.setDeptId(schemeEntity.getDeptId());
		schemeBean.setSchemeName(schemeEntity.getSchemeName());
		schemeBean.setSchemeId(schemeEntity.getSchemeId());
		schemeBean.setDescription(schemeEntity.getDescription());
		schemeBean.setActiveStatus(schemeEntity.getActiveStatus());
		return schemeBean;

	}

	@Transactional
	@Override
	public void updateSchemeData(SchemeBean schemeBean) {

		SchemeEntity schemeEntity = new SchemeEntity();

		schemeEntity = entityManager.createNamedQuery("deleteByschemeId", SchemeEntity.class)
				.setParameter("schemeId", schemeBean.getSchemeId()).getSingleResult();
		schemeEntity.setSchemeId(schemeEntity.getSchemeId());
		schemeEntity.setDeptId(schemeBean.getDeptId());
		schemeEntity.setSchemeName(schemeBean.getSchemeName());
		schemeEntity.setDescription(schemeBean.getDescription());

		entityManager.merge(schemeEntity);
	}
}
