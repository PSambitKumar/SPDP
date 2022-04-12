package com.spdp.master.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.spdp.bean.DepartmentBean;
import com.spdp.bean.DistLGDBean;
import com.spdp.bean.DistrictBean;
import com.spdp.bean.EducationBean;
import com.spdp.bean.GpBean;
import com.spdp.bean.PoliceStationBean;
import com.spdp.bean.RICircleBean;
import com.spdp.bean.RdcBean;
import com.spdp.bean.RdcDistrictBean;
import com.spdp.bean.casteMasterBean;
import com.spdp.bean.dropdownBean;
import com.spdp.bean.finalBucketListBean;
import com.spdp.entity.RdcEntity;
import com.spdp.service.MasterService;


/**
 * 
 * @Author : Akash Deep
 * 
 **/


@Path("/api")
@RequestScoped
public class MasterController {
	
	@Inject
	private MasterService masterService;
	
	//RDC Master By Akash Deep
	
	@Path("/getRdcDetailsForView")
	@GET
	public List<RdcBean> getRdcDetails() {
		RdcEntity obj = new RdcEntity();
			return masterService.getDetailsByRdcId();
	}
	
	
	@Path("/getDistrictDetails")
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	public List<DistrictBean> getDistrictDetails() {
		System.out.println("test");
		return masterService.getDistrictDetails();
	}
	
//	@Path("/save")	
//	@Consumes(MediaType.APPLICATION_JSON)
//	@POST
//	public void save( String a) {
//		System.out.println(a);
//	}
	
	@Path("/saveRdcDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveRdcDetail(RdcBean rdcObj,@QueryParam("status") String status) throws ParseException  {
		
//		System.out.println("Rdc Name - "+rdcObj.getRdcName() +" Notification No - "+rdcObj.getNotiNo()
//		+" Notification Date - "+rdcObj.getNotiDate() +" Dist - "+rdcObj.getDistObj() +" ---------- ");
		System.out.println("<<<<<<<<<<<>>>>>>>>>"+status);
		String response =" ";
		
		try {
			
		
		
		
		//This Gives Exact format of our DB required date But problem it returns n string.
		//SimpleDateFormat formatterDDMMMYYYY = new SimpleDateFormat("dd-MMM-yyyy"); 
//		SimpleDateFormat sfdate = new SimpleDateFormat("dd-MMM-yy hh.mm.ss.SSSSSSSSS a");
//		sfdate.format(new Date("09-Jan-2021")).toUpperCase();
		 //It changes the date format.
	
		//System.out.println("---------- "+"---"+(sfdate.format(new Date("09-Jan-2021")).toUpperCase()));
//SimpleDateFormat sfdate = new SimpleDateFormat("dd-MMM-yy hh.mm.ss.SSSSSSSSS a");
//		
//		System.out.println(" <<<<<<<<<>>>>>>> "+(sfdate.format(rdcObj.getNotiDate().toLocaleDateString())));


		
		response =masterService.saveRdcDetails(rdcObj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("response", response);
		return Response.status(Response.Status.OK).entity(map).build();
	}
	
	@Path("/deleteByNotiNoRDC/{notiNo}")
	@GET
	public void deleteByNotiNo(@PathParam(value = "notiNo") String notiNo) {
		masterService.deleteByNotiNo(notiNo);
		System.out.println("Delete Controller ----- "+notiNo);
	}
	
	@Path("/getRdcAndDistrictDetails")
	@GET
	public List<RdcDistrictBean> getRdcAndDistrictDetails() {
		return masterService.getRdcAndDistrictDetails();
	}
	
	@Path("/getRdcDetailsForUpdate/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public RdcBean getRdcDetailsForUpdate(@PathParam(value = "id") Long id) {
		
		return masterService.getRdcDetailsForUpdate(id);
	}
	

	@Path("/updateRdcDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateRdcDetails(RdcBean rdcObj) throws ParseException {
		System.out.println("abc hii");
		SimpleDateFormat sfdate = new SimpleDateFormat("dd-MMM-yy hh.mm.ss.SSSSSSSSS a");
		
		//System.out.println(" <<<<<<<<<>>>>>>> "+(sfdate.format(new Date(rdcObj.getNotiDate()))));
		
		
		masterService.updateRdcDetails(rdcObj);
	}
	
	// RI Circle By Akash Deep
	
	@Path("/saveRICircleDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveRICircleDetails(RICircleBean obj) {
		String restResponse ="";
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			restResponse = masterService.saveRICircleDetails(obj);
			map.put("restResponse", restResponse);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.OK).entity(map).build();
	}
	
	@Path("/getRiCircleDropdownLists")
	@GET
	public List<DistrictBean> getRiCircleDropdownLists() {
		return masterService.getRiCircleDropdownLists();
	}
	
	@Path("/getOnClickLists")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<dropdownBean> getOnClickLists(RICircleBean obj) {
		return masterService.getOnClickLists(obj);
	}
	
	@Path("/getRiCircleListView")
	@GET
	public List<RICircleBean> getRiCircleListView() {
		return masterService.getRiCircleListView();
	}
	
	@Path("/changeRIstatus/{id}")
	@GET
	public void changeRIstatus(@PathParam(value = "id") Long id) {
		masterService.changeRIstatus(id);
	}
	
	@Path("/getListForRiEditById/{id}")
	@GET
	public RICircleBean getListForRiEditById(@PathParam(value = "id") Long id) {
		return masterService.getListForRiEditById(id);
	}
	@Path("/updateRiCircle")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateRiCircle(RICircleBean obj) {
			masterService.updateRiCircle(obj);
	}
	
	// Police Station By Akash Deep
	
	@Path("/getGPandVillListsBucket/{id}")
	@GET
	public List<Object> getGPandVillListsBucket(@PathParam(value = "id") Long id) {
		return masterService.getGPandVillListsBucket(id);
	}
	
	@Path("/savePoliceStation")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePoliceStation(PoliceStationBean obj) {
		
		String restResponse ="";
		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			restResponse = masterService.savePoliceStation(obj);
			map.put("restResponse", restResponse);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.OK).entity(map).build();
			
	}
	
	@Path("/getPoliceStationView")
	@GET
	public List<PoliceStationBean> getPoliceStationView() {
		return masterService.getPoliceStationView();
	}
	
	@Path("/getTypesOfPoliceStation")
	@GET
	public List<Object> getTypesOfPoliceStation() {
		return masterService.getTypesOfPoliceStation();
	}
	
	@Path("/changePsStatus/{id}")
	@GET
	public void changePsStatus(@PathParam(value = "id") Long id) {
		masterService.changePsStatus(id);
	}
	
	@Path("/getBucketVillageListById/{id}")
	@GET
	public List<finalBucketListBean> getBucketVillageListById(@PathParam(value = "id") Long id) {
		return masterService.getBucketVillageListById(id);
	}
	@Path("/updatePoliceStation")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updatePoliceStation(PoliceStationBean obj) {
		masterService.updatePoliceStation(obj);
	}
	
	//GP Master Added By Akash Deep
	
	@Path("/saveGPMaster")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveGPMaster(GpBean obj) {
		
		String response = "";
		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			
			response = masterService.saveGPMaster(obj);
			map.put("restResponse", response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.OK).entity(map).build();
		
	}
	
	@Path("/getGPListView")
	@GET
	public List<GpBean> getGPListView() {
		return masterService.getGPListView();
	}
	
	@Path("/changeGPStatus/{id}")
	@GET
	public void changeGPStatus(@PathParam(value = "id") Long id) {
		masterService.changeGPStatus(id);
	}
	
	@Path("/getGpVillageById/{id}")
	@GET
	public List<dropdownBean> getGpVillageById(@PathParam(value = "id") Long id) {
		return masterService.getGpVillageById(id);
	}
	
	@Path("/updateGPList")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateGPList(GpBean obj) {
		masterService.updateGPList(obj);
	}
	
	// Caste Master By Akash Deep
	
	@Path("/getCasteCategory")
	@GET
	public List<dropdownBean> getCasteCategory() {
		return masterService.getCasteCategory();
	}
	
	@Path("/saveCasteByCategory")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveCasteByCategory(casteMasterBean obj) {
		
		String response = "";
		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			
			response = masterService.saveCasteByCategory(obj);
			map.put("restResponse", response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.OK).entity(map).build();
	}
	
	@Path("/getCasteMasterView")
	@GET
	public List<casteMasterBean> getCasteMasterView() {
		return masterService.getCasteMasterView();
	}
	
	// Educational Institution Added By Akash Deep
	
	@Path("/saveEducationalInstitution")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveEducationalInstitution(EducationBean obj) {
		
		String response = "";
		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			
			response = masterService.saveEducationalInstitution(obj);
			map.put("restResponse", response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.OK).entity(map).build();
	}
	
	@Path("/getEducationDropdownList")
	@GET
	public List<Object> getEducationDropdownList() {
		return masterService.getEducationDropdownList();
	}
	
	@Path("/getEducationView")
	@GET
	public List<EducationBean> getEducationView() {
		return masterService.getEducationView();
	}
	
	@Path("/changeEducationViewStatus/{id}")
	@GET
	public void changeEducationViewStatus(@PathParam(value = "id") Long id) {
		masterService.changeEducationViewStatus(id);
	}
	
	@Path("/updateEducationForm")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateEducationForm(EducationBean obj) {
		masterService.updateEducationForm(obj);
	}
	
	// Added Department Master By Akash Deep
	
	@Path("/saveDepartment")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveDepartment(DepartmentBean obj) {
		
		String response = "";
		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			
			response = masterService.saveDepartment(obj);
			map.put("restResponse", response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.OK).entity(map).build();
	}
	
	@Path("/getDepartmentView")
	@GET
	public List<DepartmentBean> getDepartmentView() {
		return masterService.getDepartmentView();
	}
	
	@Path("/changeDepartmentStatus/{id}")
	@GET
	public void changeDepartmentStatus(@PathParam(value = "id") Long id) {
		masterService.changeDepartmentStatus(id);
	}
	
	@Path("/updateDepartment")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateDepartment(DepartmentBean obj) {
		masterService.updateDepartment(obj);
	}
	
	// Added LGD Mapping By Akash Deep
	
	@Path("/saveDistLGDMapping")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveDistLGDMapping(DistLGDBean obj) {
		
		String response = "";
		Map<String,Object> map = new HashMap<String, Object>();
		
		try {
			
			response = masterService.saveDistLGDMapping(obj);
			map.put("restResponse", response);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return Response.status(Response.Status.OK).entity(map).build();
	}
	
	@Path("/getDepartmentList")
	@GET
	public List<dropdownBean> getDepartmentList() {
		return masterService.getDepartmentList();
	}
}
