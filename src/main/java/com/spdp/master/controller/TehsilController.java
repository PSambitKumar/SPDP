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

import com.spdp.bean.RICircleBeans;
import com.spdp.bean.TehsilMasterBean;
import com.spdp.service.TehsilService;

@Path("/api")
@RequestScoped
public class TehsilController {

	@Inject
	private TehsilService tehsilService;

	@Path("/saveTehsilMasterData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveTehsilDetails(TehsilMasterBean tehsilMasterBean) {
		String response = " ";

		try {
			response = tehsilService.saveTehsilDetails(tehsilMasterBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response", response);
		return Response.status(Response.Status.OK).entity(map).build();
	}

	@Path("/getTehsilDetails")
	@GET
	public List<TehsilMasterBean> getVillageDetails() {
		return tehsilService.getAllTehsilData();

	}

//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteBytehsiltId/{tehsiltId}")
	@GET
	public void deleteTehsilById(@PathParam(value = "tehsiltId") Long tehsiltId) {
		tehsilService.deleteTehsilMasterById(tehsiltId);

	}

	@Path("/getTehsil/{tehsiltId}")
	@GET
	public TehsilMasterBean getTehsilById(@PathParam(value = "tehsiltId") Long tehsiltId) {
		return tehsilService.getTehsilDetailsById(tehsiltId);

	}

	@Path("/updateTehsilData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateTehsilMasterData(TehsilMasterBean tehsilMasterBean) throws ParseException {
		System.out.println("Request is coming or notSS");
		tehsilService.updateTehsilMasterData(tehsilMasterBean);
		// return "Data updated successfully";
	}

	@Path("/getRICircle")
	@GET
	public List<RICircleBeans> getRICircles() {
		return tehsilService.getRICircleByTehsilId();

	}
}
