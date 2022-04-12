package com.spdp.master.controller;


import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.spdp.bean.BlockBean;
import com.spdp.bean.SubDivisionBean;
import com.spdp.bean.SubdivisionBlockBean;
import com.spdp.entity.SubDivisionEntity;
import com.spdp.service.SubDivisionService;


/**
 * 
 * @author sanghamitra.beura
 *
 */

@Path("/api/v2")
@RequestScoped
public class SubDivisionController {
	@Inject
	private SubDivisionService subdivisionService;
	
	@Path("/getSubDivisionDetailsForView")
	@GET
	public List<SubDivisionBean> getSubdivisionDetails() {
	SubDivisionEntity obj = new SubDivisionEntity();
			return subdivisionService.getDetailsBySubDivisionId();
	}
	
	
	@Path("/getBlockDetails")
	@GET
	public List<BlockBean> getBlockDetails() {
		
		return subdivisionService.getBlockDetails();
	}
	
//	@Path("/save")	
//	@Consumes(MediaType.APPLICATION_JSON)
//	@POST
//	public void save( String a) {
//		System.out.println(a);
//	}
	
	@Path("/saveSubdivisionDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveSubDivisionDetail(SubDivisionBean subdivisionObj,@QueryParam("status") String status) throws ParseException {
		System.out.println("Sub ID"+subdivisionObj.getDistrictId());

	String response =" ";
		
		try {
					
		response =subdivisionService.saveSubDivisionDetails(subdivisionObj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("response", response);
		return Response.status(Response.Status.OK).entity(map).build();
		
	}
	
	//To check Status
	
	@Path("/deleteByNotiNo/{notiNo}")
	@GET
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
	public void deleteByNotiNo(@PathParam(value = "notiNo") String notiNo) {
		subdivisionService.deleteByNotiNo(notiNo);
		System.out.println("Delete Controller ----- "+notiNo);
	}
	
	@Path("/getSubDivisionAndBlockDetails")
	@GET
	public List<SubdivisionBlockBean> getSubDivisionAndBlockDetails() {
		return subdivisionService.getSubdivisionAndBlockDetails();
	}
	

	@Path("/getSubDivisionDetailsForUpdate/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public SubDivisionBean getSubdivisionDetailsForUpdate(@PathParam(value = "id") Long id) {
		
		return subdivisionService.getSubdivisionDetailsForUpdate(id);
	}
	
	

	@Path("/updateSubdivisionDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateSubdivisionDetails(SubDivisionBean subdivisionObj) throws ParseException {
		
		subdivisionService.updateSubDivisionDetails(subdivisionObj);
	}
	
	
	
	@Path("/getBlockDetaildByDistId/{id}")
	@GET
	public List<SubDivisionBean> getBlockDetaildByDistId(@PathParam(value = "id") Long id)
	{
		return subdivisionService.getBlockDetaildByDistId(id);
	}
	
	
	
	@Path("/getSubdivisionDetaildByDistId/{id}")
	@GET
	public List<SubDivisionBean> getSubdivisionDetaildByDistId(@PathParam(value = "id") Long id)
	{
		return subdivisionService.getSubdivisionDetaildByDistId(id);
	}
	
	////Search Pipe Use Here
	
	@Path("/getSubDivisionDetailsFilteredByDistrict/{districtId}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByDistrict(@PathParam(value = "districtId") Long did) {               //,@PathParam(value = "date") Object dt
			return subdivisionService.getSubDivisionDetailsFilteredByDistrict(did);
	}
	
	
	
	@Path("/getSubDivisionDetailsFilteredBySubDivision/{subDivisionId}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubDivisionBean> getSubDivisionDetailsFilteredBySubDivision(@PathParam(value = "subDivisionId") Long sid) {               //,@PathParam(value = "date") Object dt
			return subdivisionService.getSubDivisionDetailsFilteredBySubDivision(sid);
	}
	
	
	
	
	@Path("/getSubDivisionDetailsFilteredByStatus/{status}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByStatus(@PathParam(value = "status") String status) {               //,@PathParam(value = "date") Object dt
			return subdivisionService.getSubDivisionDetailsFilteredByStatus(status);
	}
	
	
	
	@Path("/getSubDivisionDetailsFilteredByDate/{date}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByDate(@PathParam(value = "date") String date) {               //,@PathParam(value = "date") Object dt
			return subdivisionService.getSubDivisionDetailsFilteredByDate(date);
	}
	
	

	@Path("/getSubDivisionDetailsFilteredByDIdAndDate")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByDIdAndDate(@QueryParam(value = "dId") Long dId,@QueryParam(value = "date") String date) {               //,@PathParam(value = "date") Object dt
			return subdivisionService.getSubDivisionDetailsFilteredByDIdAndDate(dId,date);
	}
	
	@Path("/getSubDivisionDetailsFilteredBySIdAndDate")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubDivisionBean> getSubDivisionDetailsFilteredBySIdAndDate(@QueryParam(value = "sId") Long sId,@QueryParam(value = "date") String date) {               //,@PathParam(value = "date") Object dt
			return subdivisionService.getSubDivisionDetailsFilteredBySIdAndDate(sId,date);
	}
	
	
	@Path("/getSubDivisionDetailsFilteredByStatusAndDate")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByStatusAndDate(@QueryParam(value = "status") String status,@QueryParam(value = "date") String date) {               //,@PathParam(value = "date") Object dt
			return subdivisionService.getSubDivisionDetailsFilteredByStatusAndDate(status,date);
	}
	
	
	@Path("/getSubDivisionDetailsFilteredByStatusAndDist")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByStatusAndDist(@QueryParam(value = "did") Long did,@QueryParam(value = "status") String status) {               //,@PathParam(value = "date") Object dt
			return subdivisionService.getSubDivisionDetailsFilteredByStatusAndDist(did,status);
	}
	
	@Path("/getSubDivisionDetailsFilteredByStatusAndSubdiv")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByStatusAndSubdiv(@QueryParam(value = "sid") Long sid,@QueryParam(value = "status") String status) {               //,@PathParam(value = "date") Object dt
			return subdivisionService.getSubDivisionDetailsFilteredByStatusAndSubdiv(sid,status);
	}
	
	@Path("/getSubDivisionDetailsFilteredByStatusAndSubdivAndDate")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<SubDivisionBean> getSubDivisionDetailsFilteredByStatusAndSubdivAndDate(@QueryParam(value = "sid") Long sid,@QueryParam(value = "status") String status,@QueryParam(value = "date") String date) {               //,@PathParam(value = "date") Object dt
			return subdivisionService.getSubDivisionDetailsFilteredByStatusAndSubdivAndDate(sid,status,date);
	}
	
	
}
