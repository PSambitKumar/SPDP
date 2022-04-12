package com.spdp.master.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.spdp.bean.PoliceDistrictBean;
import com.spdp.bean.PoliceStationBean;
import com.spdp.bean.policeSubDivisionBean;
import com.spdp.service.PoliceSubDivisionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @author kamalakanta.g
 *
 */
@Path("/api/psd")
@RequestScoped
public class PoliceSubDivisionController {
	
	@Inject
	private PoliceSubDivisionService policeSubDivisionService;
	
	private static final Logger Logger = LoggerFactory.getLogger(PoliceSubDivisionController.class);
	

	@Path("/getPoliceStation")
	@GET
	public List<PoliceStationBean> getPoliceStationDetails() {
		System.out.println("Processing Police station Data...");
		return policeSubDivisionService.getPoliceStationDetails();
	}
	
	
	@Path("/getPoliceDistrict")
	@GET
	public List<PoliceDistrictBean> getPoliceDistrict() {
		
		return policeSubDivisionService.getPoliceDistrict();
	}
	
	@Path("/savePoliceSubdivisionDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response savePolicesubdivision(policeSubDivisionBean policesubdivisionObj,@QueryParam("status") String status)throws ParseException {
		 
		System.out.println("SPDO Name - " + policesubdivisionObj.getSpdoName() + " Notification No - "
				+ policesubdivisionObj.getNotiNo() + " Notification Date - " + policesubdivisionObj.getNotiDate()
				+ " Police District - " + policesubdivisionObj.getPoliceDistrict() + " Police Station - "
				+ policesubdivisionObj.getPoliceStationList() + " ---------- ");
		String response =" ";
		try {
			response=policeSubDivisionService.savePolicesubdivision(policesubdivisionObj);
		}catch(Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("response", response);
		return Response.status(Response.Status.OK).entity(map).build();
	 	

	}
	
@Path("/getPoliceSubDivisionView")
	@GET
	public List<policeSubDivisionBean> getPoliceSubDivisionView(){
		return policeSubDivisionService.getPoliceSubdivisionView();
	}
	
	@Path("/SubDivStatusByNotiNo/{notificationNo}")
	@GET
	public void SubDivStatusByNotiNo(@PathParam(value = "notificationNo") String notificationNo) {
		policeSubDivisionService.SubDivStatusByNotiNo(notificationNo);
		System.out.println("Successful Updated.. ----- "+notificationNo);
	}

	
	@Path("/updateSubdivisionDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	 public void updatePoliceSubdivision(policeSubDivisionBean policeSubDivisionBeanobj) throws ParseException {
		Long id=policeSubDivisionBeanobj.getSpdoId();
		System.out.println("SPDO Id coming in Controller :"+id);
		
		String msg=policeSubDivisionService.updatePoliceSubdivision(policeSubDivisionBeanobj);
		System.out.println(msg);
	}
	
	
	@Path("/getPoliceSubDivisionForUpdate/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public policeSubDivisionBean getPoliceSubDivisionForUpdate(@PathParam(value = "id") Long id) {
		
		return policeSubDivisionService.getPoliceSubDivisionForUpdate(id);
	}

	

}
