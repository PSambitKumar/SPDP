package com.spdp.master.controller;

import java.text.ParseException;
import java.util.Date;
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

import com.spdp.bean.PRangePDistrictBean;
import com.spdp.bean.PoliceRangeBean;
import com.spdp.service.PoliceRangeService;

/**
 * 
 * @author jyotiranjan.behera
 *
 */
@Path("/api/v5")
@RequestScoped
public class PoliceRangeController {
	
	@Inject
	private PoliceRangeService policeRangeService;
	
	@Path("/getPoliceRangeDetailsForView")
	@GET
	public List<PoliceRangeBean> getblockDetails() {
			return policeRangeService.getPoliceRangeDetailsById();
	}
	
	@Path("/deletePoliceRangeByNotiNo/{notificationNo}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteBlockByNotiNo(@PathParam(value = "notificationNo") String notificationNo) {
		policeRangeService.deletePoliceRangeByNotiNo(notificationNo);
		System.out.println("Delete Controller ----- "+notificationNo);
	}
	
	@Path("/getPRangeAndPDistDetails")
	@GET
	public List<PRangePDistrictBean> getPRangeAndPDistDetails() {
		return policeRangeService.getPRangeAndPDistDetails();
	}
	
	@Path("/getPoliceRangeDetailsForUpdate/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public PoliceRangeBean getPoliceRangeDetailsForUpdate(@PathParam(value = "id") Long id) {
		System.out.println(" Controller ----- "+id);
		return policeRangeService.getPoliceRangeDetailsForUpdate(id);
		
	}
	@Path("/savePoliceRangeDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON) //Check Here by Jyoti
	@Produces(MediaType.APPLICATION_JSON) //Check Here by Jyoti
	public Response savePoliceRangeDetail(PoliceRangeBean policeRBean) throws ParseException  {
		String response =" ";
		
		try {
		response =policeRangeService.savePoliceRangeDetails(policeRBean);
		}catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("response", response);
		return Response.status(Response.Status.OK).entity(map).build();
	}
	
	@Path("/updatePoliceRangeDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updatePoliceRangeDetails(PoliceRangeBean policeRBean) throws ParseException {
//	public void updatePoliceRangeDetails(@QueryParam(value = "object")PoliceRangeBean policeRBean,@PathParam(value = "date") Date date) throws ParseException {
		
		policeRangeService.updatePoliceRangeDetails(policeRBean);
	}
	
	@Path("/getPoliceRangeDetailsFilteredByPoliceRange/{prId}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceRangeBean> getPoliceRangeDetailsFilteredByPoliceRange(@PathParam(value = "prId") Long pid) {               
			return policeRangeService.getPoliceRangeDetailsFilteredByPoliceRange(pid);
	}
	
	@Path("/getPoliceRangeDetailsFilteredByStatus/{status}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceRangeBean> getPoliceRangeDetailsFilteredByStatus(@PathParam(value = "status") String status) {               
			return policeRangeService.getPoliceRangeDetailsFilteredByStatus(status);
	}
	
	@Path("/getPoliceRangeDetailsFilteredByDate/{date}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceRangeBean> getPoliceRangeDetailsFilteredByDate(@PathParam(value = "date") String date) {  
			return policeRangeService.getPoliceRangeDetailsFilteredByDate(date);
	}
	
	@Path("/getPoliceRangeDetailsFilteredByPrIdAndStatus")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceRangeBean> getPoliceRangeDetailsFilteredByPrIdAndStatus(@QueryParam(value = "prId") Long prid,@QueryParam(value = "status") String status) {  
		System.out.println("abcd");
			return policeRangeService.getPoliceRangeDetailsFilteredByPrIdAndStatus(prid,status);
	}
	
	@Path("/getPoliceRangeDetailsFilteredByDateAndStatus")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceRangeBean> getPoliceRangeDetailsFilteredByDateAndStatus(@QueryParam(value = "date") String date,@QueryParam(value = "status") String status) {  
			return policeRangeService.getPoliceRangeDetailsFilteredByDateAndStatus(date,status);
	}
	
	@Path("/getPoliceRangeDetailsFilteredByAllData")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<PoliceRangeBean> getPoliceRangeDetailsFilteredByAllData(@QueryParam(value = "prId") Long prid,@QueryParam(value = "status") String status,@QueryParam(value = "date") String date) {  
			return policeRangeService.getPoliceRangeDetailsFilteredByAllData(prid,status,date);
	}


}
