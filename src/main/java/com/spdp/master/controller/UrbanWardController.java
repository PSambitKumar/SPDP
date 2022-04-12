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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.spdp.bean.TehsilMasterBean;
import com.spdp.bean.UlbBean;
import com.spdp.bean.UrbanWardBean;
import com.spdp.bean.VillageBean;
import com.spdp.service.UrbanWardService;

@Path("/api")
@RequestScoped
public class UrbanWardController {

	@Inject
	private UrbanWardService urbanWardService;

	@Path("/getUlbNames/{districtId}")
	@GET
	public List<UlbBean> getUlbDetails(@PathParam(value = "districtId") Long districtId) {
		return urbanWardService.getUlbDetailsByDistrictId(districtId);

	}

	@Path("/saveUrbanWardData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveUrbanMaster(UrbanWardBean urbanWardBean) {

		String response = " ";

		try {
			response = urbanWardService.saveUrbanMasterData(urbanWardBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response", response);
		return Response.status(Response.Status.OK).entity(map).build();
	}

	@Path("/getAllDatas")
	@GET
	public List<UrbanWardBean> getUrbanDetails() {
		System.out.println("Request is coming");
		return urbanWardService.getAllUrbanDatas();

	}

//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteByUrbanId/{urbanId}")
	@GET
	public void deleteUrbanById(@PathParam(value = "urbanId") Long urbanId) {
		urbanWardService.deleteUrbanMasterById(urbanId);

	}

	@Path("/getUrban/{urbanId}")
	@GET
	public UrbanWardBean getUrbanById(@PathParam(value = "urbanId") Long urbanId) {
		return urbanWardService.getUrbanDetailsById(urbanId);

	}

	@Path("/updateUrbanData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateUrbanMasterData(UrbanWardBean urbanWardBean) {
		System.out.println("Request is coming or notSS");
		urbanWardService.updateUrbanData(urbanWardBean);
		// return "Data updated successfully";
	}

}
