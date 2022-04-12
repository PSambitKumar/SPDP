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

import com.spdp.bean.BlockBean;
import com.spdp.bean.GpBean;
import com.spdp.bean.RICircleBeans;
import com.spdp.bean.SubDivisionBean;
import com.spdp.bean.TehsilBean;
import com.spdp.bean.VillageBean;
import com.spdp.entity.BlockEntity;
import com.spdp.entity.GpEntity;
import com.spdp.entity.RICircleMasterEntity;
import com.spdp.entity.SubDivisionEntity;
import com.spdp.entity.TehsilEntity;
import com.spdp.entity.VillageEntity;
import com.spdp.service.VillageService;

@ApplicationScoped
public class VillageServiceImpl implements VillageService {

	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;

	@Transactional
	@Override
	public String saveVillageData(VillageBean villageBean) throws ParseException {

		System.out.println("print:-" + villageBean);

		VillageEntity villageEntity = new VillageEntity();

		String str_date = villageBean.getNotificationDate();
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formatter.parse(str_date);
		villageEntity.setVillageName(villageBean.getVillageName());
		villageEntity.setLgdCode(villageBean.getLgdCode());
		villageEntity.setResolutionNumber(villageBean.getResolutionNumber());
		villageEntity.setNotificationDate(date);
		villageEntity.setDistrictId(villageBean.getDistrictId());
		villageEntity.setSubDivisionId(villageBean.getSubdivisionId());
		villageEntity.setBlocktId(villageBean.getBlocktId());
		villageEntity.setGpId(villageBean.getGpId());
		villageEntity.setTehsiltId(villageBean.getTehsilId());
		villageEntity.setRiCircleId(villageBean.getRiCircleId());
		// villageEntity.setCreatedBy("Aman Jha");
		villageEntity.setCreatedOn(new Date());
		// villageEntity.setUpdatedBy("Aman Jha");
		villageEntity.setUpdatedOn(new Date());
		villageEntity.setActiveStatus("Y");
		entityManager.persist(villageEntity);

		return "Data saved successfully";
	}

	@Override
	public List<SubDivisionBean> getAllSubDivisionDetails(Long districtId) {

		List<SubDivisionEntity> subDivisionEntity = new ArrayList<SubDivisionEntity>();
		List<SubDivisionBean> subDivisionBean = new ArrayList<SubDivisionBean>();

		try {
			// subDivisionEntity = entityManager.createNamedQuery("subDetails",
			// SubDivisionEntity.class).getResultList();
			subDivisionEntity = entityManager.createNamedQuery("subDetails", SubDivisionEntity.class)
					.setParameter("districtId", districtId).getResultList();

			for (SubDivisionEntity sube : subDivisionEntity) {
				SubDivisionBean subb = new SubDivisionBean();
				subb.setSubdivionId(sube.getSubdivisionId());
				subb.setSubdivisionName(sube.getSubdivisionName());
				subDivisionBean.add(subb);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return subDivisionBean;

	}

	@Override
	public List<BlockBean> getBlockDetailsBySubDivisionId(Long subdivisionId) {

		List<BlockEntity> blockEntity = new ArrayList<BlockEntity>();
		List<BlockBean> blockBean = new ArrayList<BlockBean>();

		try {
			System.out.println("aman123");
			blockEntity = entityManager.createNamedQuery("blkDetails", BlockEntity.class)
					.setParameter("subdivisionId", subdivisionId).getResultList();

			System.out.println("print data" + blockEntity);

			for (BlockEntity blk : blockEntity) {
				BlockBean blkb = new BlockBean();
				blkb.setBlockId(blk.getBlockId());
				blkb.setBlockName(blk.getBlockName());
				blockBean.add(blkb);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return blockBean;
	}

	@Override
	public List<VillageBean> getAllVillageData() {

		List<VillageEntity> villageEntity = new ArrayList<VillageEntity>();
		List<VillageBean> vllageBean = new ArrayList<VillageBean>();

		try {
			villageEntity = entityManager.createNamedQuery("villageDetails", VillageEntity.class).getResultList();
			System.out.println("lenght:-" + villageEntity.size());
			for (VillageEntity vill : villageEntity) {
				VillageBean villBean = new VillageBean();

				Format formatter = new SimpleDateFormat("dd-MM-yyyy");
				String s = formatter.format(vill.getNotificationDate());

//				if (vill.getNotificationDate() != null) {
//					villBean.setNotificationDate(s);
//				}
//				
				villBean.setVillageId(vill.getVillageId());
				villBean.setVillageName(vill.getVillageName());
				villBean.setLgdCode(vill.getLgdCode());
				villBean.setDistrictId(vill.getDistrictId());
				villBean.setSubdivisionId(vill.getSubDivisionId());
				villBean.setBlocktId(vill.getBlocktId());
				villBean.setGpId(vill.getGpId());
				villBean.setTehsilId(vill.getTehsiltId());
				villBean.setRiCircleId(vill.getRiCircleId());
				villBean.setActiveStatus(vill.getActiveStatus());
				villBean.setResolutionNumber(vill.getResolutionNumber());
				villBean.setNotificationDate(s);

				if (vill.getDistrictId() != null) {
					System.out.println("dddd");
					javax.persistence.Query que = entityManager
							.createQuery("select districtName from District where districtId = :id");
					que.setParameter("id", vill.getDistrictId());

					String qs = (String) que.getSingleResult();
					villBean.setDistName(qs);
				}
				if (vill.getSubDivisionId() != null) {
					javax.persistence.Query que = entityManager
							.createQuery("select subdivisionName from SubDivisionEntity where subdivisionId = :id");
					que.setParameter("id", vill.getSubDivisionId());

					String qs = (String) que.getSingleResult();
					villBean.setSubName(qs);
				}

				if (vill.getBlocktId() != null) {
					javax.persistence.Query que = entityManager
							.createQuery("select blockName from BlockEntity where blockId = :id");
					que.setParameter("id", vill.getBlocktId());

					String qs = (String) que.getSingleResult();
					villBean.setBlockName(qs);
				}

				if (vill.getGpId() != null) {
					System.out.println("gggg");
					javax.persistence.Query que = entityManager
							.createQuery("select gpName from GpEntity where gpId = :id");
					que.setParameter("id", vill.getGpId());

					String qs = (String) que.getSingleResult();
					villBean.setGPName(qs);
				}

//				if (vill.getTehsiltId() != null) {
//
//					System.out.println("aaaa");
//
//					javax.persistence.Query que = entityManager
//							.createQuery("select tehsilName from TehsilEntity where tehsiltId = :id");
//					que.setParameter("id", vill.getTehsiltId());
//
//					String qs = (String) que.getSingleResult();
//					villBean.setTehsilName(qs);
//				}

				if (vill.getRiCircleId() != null) {
					System.out.println("bbbb");

					javax.persistence.Query que = entityManager
							.createQuery("select riCircleName from RICircleMasterEntity where riCircleId = :id");
					que.setParameter("id", vill.getRiCircleId());

					String qs = (String) que.getSingleResult();
					villBean.setRiCircleName(qs);
				}

				vllageBean.add(villBean);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return vllageBean;
	}

	@Override
	public List<TehsilBean> getTehsilBySubDivisionById(Long subdivisionId) {

		List<TehsilEntity> tehsilEntity = new ArrayList<TehsilEntity>();
		List<TehsilBean> tehsilBean = new ArrayList<TehsilBean>();

		try {
			System.out.println("aman123");
			tehsilEntity = entityManager.createNamedQuery("tehDetails", TehsilEntity.class)
					.setParameter("subdivisionId", subdivisionId).getResultList();

			System.out.println("print data" + tehsilEntity);

			for (TehsilEntity tlk : tehsilEntity) {
				TehsilBean tlkb = new TehsilBean();
				tlkb.setTehsiltId(tlk.getTehsiltId());
				tlkb.setTehsilName(tlk.getTehsilName());
				tehsilBean.add(tlkb);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return tehsilBean;
	}

	@Transactional
	@Override
	public Boolean deleteVillageMasterById(Long villageId) {
		String Status = "";

		VillageEntity villObj = entityManager.createNamedQuery("deleteByVillageId", VillageEntity.class)
				.setParameter("villageId", villageId).getSingleResult();

		Status = villObj.getActiveStatus();
		if (Status.matches("N")) {
			villObj.setActiveStatus("Y");
		} else {
			villObj.setActiveStatus(("N"));
		}
		entityManager.persist(villObj);
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public void updateVillageMasterData(VillageBean villageBean) throws ParseException {
		// TODO Auto-generated method stub

		System.out.println("Request is coming ...");
		VillageEntity villageEntity = new VillageEntity();
//		String str_date = villageBean.getNotificationDate();
//		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = formatter.parse(str_date);
		villageEntity = entityManager.createNamedQuery("updateByVillageId", VillageEntity.class)
				.setParameter("villageId", villageBean.getVillageId()).getSingleResult();
		villageEntity.setVillageName(villageBean.getVillageName());
		villageEntity.setLgdCode(villageBean.getLgdCode());
		villageEntity.setResolutionNumber(villageBean.getResolutionNumber());
		villageEntity.setDistrictId(villageBean.getDistrictId());
		villageEntity.setSubDivisionId(villageBean.getSubdivisionId());
		villageEntity.setBlocktId(villageBean.getBlocktId());
		villageEntity.setTehsiltId(villageBean.getTehsilId());
		villageEntity.setRiCircleId(villageBean.getRiCircleId());
		villageEntity.setGpId(villageBean.getGpId());

		// villageEntity.setNotificationDate(villageBean.getNotificationDate());

		Session ses = entityManager.unwrap(Session.class);
		ses.update(villageEntity);
		// return "Data updated successfully";
	}

	public List<GpBean> getGpByBlockId(Long blockId) {
		List<GpEntity> gPEntity = new ArrayList<>();

		List<GpBean> gpBean = new ArrayList<>();
		javax.persistence.Query que = entityManager
				.createQuery("select gpId from GPMappingEntity where blockId = :blockId");

		que.setParameter("blockId", blockId);

		List<Long> gpIds = que.getResultList();

		gPEntity = entityManager.createNamedQuery("gpDetails", GpEntity.class).setParameter("gpIds", gpIds)
				.getResultList();

		for (GpEntity gp : gPEntity) {
			GpBean blkb = new GpBean();
			blkb.setGpId(gp.getGpId());
			blkb.setGpName(gp.getGpName());
			gpBean.add(blkb);
		}

		return gpBean;

	}

	@Override
	public List<RICircleBeans> getRICircleByTehsilId(Long tehsil) {
		// TODO Auto-generated method stub

		List<RICircleMasterEntity> rICircleMasterEntity = new ArrayList<RICircleMasterEntity>();
		List<RICircleBeans> rICircleBeans = new ArrayList<RICircleBeans>();

		try {
			System.out.println("aman123");
			rICircleMasterEntity = entityManager.createNamedQuery("circleDetails", RICircleMasterEntity.class)
					.setParameter("riCircleId", tehsil).getResultList();

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

		// return null;
	}

	@Transactional
	@Override
	public VillageBean getVillageMasterDataById(Long villageId) {
		// TODO Auto-generated method stub

		VillageEntity villageEntity = new VillageEntity();

		VillageBean villageBean = new VillageBean();

		villageEntity = entityManager.createNamedQuery("updateByVillageId", VillageEntity.class)
				.setParameter("villageId", villageId).getSingleResult();

		villageBean.setVillageId(villageEntity.getVillageId());
		villageBean.setVillageName(villageEntity.getVillageName());
		villageBean.setResolutionNumber(villageEntity.getResolutionNumber());
		villageBean.setDistrictId(villageEntity.getDistrictId());
		villageBean.setSubdivisionId(villageEntity.getSubDivisionId());
		villageBean.setBlocktId(villageEntity.getBlocktId());
		villageBean.setTehsilId(villageEntity.getTehsiltId());
		villageBean.setGpId(villageEntity.getGpId());
		villageBean.setRiCircleId(villageEntity.getRiCircleId());
		villageBean.setLgdCode(villageEntity.getLgdCode());
		Format formatter = new SimpleDateFormat("dd-MM-yyyy");
		String s = formatter.format(villageEntity.getNotificationDate());
		villageBean.setNotificationDate(s);

		return villageBean;
	}

}
