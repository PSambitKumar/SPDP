package com.spdp.master.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.Uri;
import org.json.JSONObject;

import com.spdp.bean.DistBean;
import com.spdp.bean.DistSubdivisionBean;
//import com.spdp.bean.SubdivisionBean;
import com.spdp.bean.SubdivisiondropdownBean;
import com.spdp.entity.DistrictEntity;
import com.spdp.entity.SubDivisionEntity;
import com.spdp.service.DistrictMasterService;




/**
 * 
 * @Author : Akash Deep
 * 
 **/


@Path("/api/d1")
@RequestScoped

public class DistrictMasterController {
	
	@Inject
	private DistrictMasterService masterService;
	
	@Path("/getDistrictDetailsForView")
	@GET
	public List<DistBean> getDistrictDetails() {
    //	DistrictEntity obj = new DistrictEntity();
			return masterService.getDetailsByDistrictId();
	}
	
	
	@Path("/getSubdivisionDetails")
	@GET
	public List<SubDivisionEntity> getSubdivisionDetails() {
		return masterService.getSubdivisionDetails();
	}
	
//	@Path("/save")	
//	@Consumes(MediaType.APPLICATION_JSON)
//	@POST
//	public void save( String a) {
//		System.out.println(a);
//	}
	
	@Path("/saveDistrictDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveDistrictDetails(DistBean rdcObj,@QueryParam("status") String status) throws ParseException  {

		System.out.println("<<<<<<<<<<<>>>>>>>>>"+status);
		String response ="";
		
		try {
			

		
		response =masterService.saveDistrictDetails(rdcObj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(response+"-----------------in controller");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("response", response);
		return Response.status(Response.Status.OK).entity(map).build();
	}
	
	@Path("/deleteDistByNotiNo/{notiNo}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteDistByNotiNo(@PathParam(value = "notiNo") String notiNo) {
		masterService.deleteDistByNotiNo(notiNo);
		System.out.println("Delete Controller ----- "+notiNo);
	}
	
	@Path("/getDistrictAndSubdivisionDetails")
	@GET
	public List<DistSubdivisionBean> getDistrictAndSubdivisionDetails() {
		return masterService.getDistrictAndSubdivisionDetails();
	}
	
	@Path("/getDistrictDetailsForUpdate/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public DistBean getDistrictDetailsForUpdate(@PathParam(value = "id") Long id) {
		
		return masterService.getDistrictDetailsForUpdate(id);
	}
	

	@Path("/updateDistrictDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateDistrictDetails(DistBean rdcObj) throws ParseException {
		
		SimpleDateFormat sfdate = new SimpleDateFormat("dd-MMM-yy hh.mm.ss.SSSSSSSSS a");
		
		//System.out.println(" <<<<<<<<<>>>>>>> "+(sfdate.format(new Date(rdcObj.getNotiDate()))));
		
		
		masterService.updateDistrictDetails(rdcObj);
	}
	
}