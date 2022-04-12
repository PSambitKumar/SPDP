package com.spdp.master.controller;

import java.text.ParseException;
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

import com.spdp.bean.BlockBean;
import com.spdp.bean.PoliceDistSdpoMappingBean;
import com.spdp.bean.PoliceDistrictBean;
import com.spdp.bean.PoliceDistrictCatagoryBean;
import com.spdp.bean.SubDivisionBean;
import com.spdp.bean.SubdivisionBlockBean;
import com.spdp.entity.PoliceDistrictEntity;
import com.spdp.service.PoliceDistrictService;

/**
 * 
 * @author sanghamitra.beura
 *
 */

@Path("/api/v4")
@RequestScoped
public class PoliceDistrictController {
	
    @Inject
	private PoliceDistrictService policedistrictService;
		
	@Path("/getPoliceDistrictDetailsForView")
	@GET
	public List<PoliceDistrictBean> getPoliceDistrictDetails() {
		PoliceDistrictEntity obj = new PoliceDistrictEntity();
			return policedistrictService.getDetailsByPoliceDistrict();
	}
	
	@Path("/activeAndInactiveByNotiNo/{notiNo}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteByNotiNo(@PathParam(value = "notiNo") String notiNo) {
		policedistrictService.deleteByNotiNo(notiNo);
		System.out.println("Delete Controller ----- "+notiNo);
	}
	
	
//	@Path("/savePoliceDistrictDetails")
//	@POST
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response savePoliceDistrictDetail(List<PoliceDistrictBean> policedistrictObj) throws ParseException {//,@QueryParam("status") String status
//		System.out.println("PoliceDistrict ID"+policedistrictObj.getPdistrictId());
//		String response =" ";
//		try {		
//			response =policedistrictService.savePoliceDistrictDetails(policedistrictObj);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		Map<String,Object> map = new HashMap<String, Object>();
//		map.put("response", response);
//		return Response.status(Response.Status.OK).entity(map).build();
//		
//	}
	
	
	@Path("/savePoliceDistrictDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePoliceDistrictDetail(PoliceDistrictBean policedistrictObj) throws ParseException {//,@QueryParam("status") String status
		System.out.println("PoliceDistrict ID --"+ policedistrictObj.getPdistrictName());
		String response =" ";
		try {		
			response =policedistrictService.savePoliceDistrictDetails(policedistrictObj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("response", response);
		return Response.status(Response.Status.OK).entity(map).build();
		
	}
	
	
	@Path("/getPoliceDistrictAndSdpoDetails")
	@GET
	public List<PoliceDistSdpoMappingBean> getPoliceDistrictAndSdpoDetails() {
		return policedistrictService.getPoliceDistrictAndSdpoDetails();
	}
	
	
	@Path("/getTypesofpoliceDistDetails")
	@GET
	public List<PoliceDistrictCatagoryBean> getTypeOfPoliceDistDetails() {
		
		return policedistrictService. getTypeOfPoliceDistDetails();
	}
	
	@Path("/getPoliceDistrictDetailsForUpdate/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PoliceDistrictBean getPoliceDistrictDetailsForUpdate(@PathParam(value = "id") Long id) {
		
		return policedistrictService.getPoliceDistrictDetailsForUpdate(id);
	}
	
	@Path("/updatePoliceDistrictDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updatePoliceDistrictDetails(PoliceDistrictBean policedistrictObj) throws ParseException {
		System.out.println("PoliceDistrict ID ----------------------------------------check"+ policedistrictObj.getNotiDate());
		System.out.println("PoliceDistrict ID --"+ policedistrictObj.getPdistrictName());
		policedistrictService.updatePoliceDistrictDetails(policedistrictObj);
		
	}
	
	@Path("/getPoliceDistDetailsByTypeId/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceDistrictBean> getPoliceDistDetailsByTypeId(@PathParam(value = "id") Long id) {
		
		return policedistrictService.getPoliceDistDetailsByTypeId(id);
	}
	
	@Path("/getSdpoByDistrictId/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceDistrictBean> getSdpoByDistrictId(@PathParam(value = "id") Long id) {
		
		return policedistrictService.getSdpoByDistrictId(id);
	}
	
	///search pipe use here
	
	@Path("/getPoliceDistrictDetailsFilteredByPDistrict/{PdistrictId}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredByPDistrict(@PathParam(value = "PdistrictId") Long pdid) {               //,@PathParam(value = "date") Object dt
			return policedistrictService.getPoliceDistrictDetailsFilteredByPDistrict(pdid);
	}
	
	@Path("/getPoliceDistrictDetailsFilteredByStatus/{status}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredByStatus(@PathParam(value = "status") String status) {               //,@PathParam(value = "date") Object dt
			return policedistrictService.getPoliceDistrictDetailsFilteredByStatus(status);
	}
	

	@Path("/getPoliceDistrictDetailsFilteredByDate/{date}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredByDate(@PathParam(value = "date") String date) {               //,@PathParam(value = "date") Object dt
			return policedistrictService.getPoliceDistrictDetailsFilteredByDate(date);
	}
	
	@Path("/getPoliceDistrictDetailsFilteredPdIdAndStatus")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredPdIdAndStatus(@QueryParam(value = "pdId") Long pdid,@QueryParam(value = "status") String status) {  
		System.out.println("abcd");
			return policedistrictService.getPoliceDistrictDetailsFilteredPdIdAndStatus(pdid,status);
	}
	
	@Path("/getPoliceDistrictDetailsFilteredPDateAndStatus")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredPDateAndStatus(@QueryParam(value = "date") String date,@QueryParam(value = "status") String status) {  
		System.out.println("abcd");
			return policedistrictService.getPoliceDistrictDetailsFilteredPDateAndStatus(date,status);
	}
	
	
	@Path("/getPoliceDistrictDetailsFilteredPDateAndStatusAndPdId")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceDistrictBean> getPoliceDistrictDetailsFilteredPDateAndStatusAndPdId(@QueryParam(value = "date") String date,@QueryParam(value = "status") String status,@QueryParam(value = "pdId") Long pdId) {  
		System.out.println("abcd");
			return policedistrictService.getPoliceDistrictDetailsFilteredPDateAndStatusAndPdId(date,status,pdId);
	}
	
}
