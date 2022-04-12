package com.spdp.master.controller;

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

import com.spdp.bean.DeprtBean;
import com.spdp.bean.DistrictBean;
import com.spdp.bean.SchemeBean;
import com.spdp.bean.UrbanWardBean;
import com.spdp.service.SchemeService;

@Path("/api")
@RequestScoped
public class SchemeController {

	@Inject
	private SchemeService schemeService;

	@Path("/saveSchemeMasterData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveSchemeMaster(SchemeBean schemeBean) {

		String response = " ";

		try {
			response = schemeService.saveSchemeMasterData(schemeBean);
		} catch (Exception e) {
			e.printStackTrace();
		}

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("response", response);
		return Response.status(Response.Status.OK).entity(map).build();
	}

	@Path("/getSchemeDetails")
	@GET
	public List<SchemeBean> getAllSchemesData() {
		return schemeService.getAllSchemesDatas();

	}

	@Path("/getDepartmentDetails")
	@Consumes(MediaType.APPLICATION_JSON)
	@GET
	public List<DeprtBean> getDepartmentDetails() {
		System.out.println("test");
		return schemeService.getDepartment();
	}

	@Path("/deleteByschemeId/{schemeId}")
	@GET
	public void deleteSchemeById(@PathParam(value = "schemeId") Long schemeId) {
		schemeService.deleteSchemeMasterById(schemeId);

	}

	@Path("/getScheme/{schemeId}")
	@GET
	public SchemeBean getSchemeById(@PathParam(value = "schemeId") Long schemeId) {
		return schemeService.getSchemeDetailsById(schemeId);

	}

	@Path("/updateSchemeData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateUrbanMasterData(SchemeBean schemeBean) {
		System.out.println("Request is coming or notSS");
		schemeService.updateSchemeData(schemeBean);
		// return "Data updated successfully";
	}
}
