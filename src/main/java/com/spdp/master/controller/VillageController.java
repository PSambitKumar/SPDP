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

import com.spdp.bean.BlockBean;
import com.spdp.bean.GpBean;
import com.spdp.bean.RICircleBeans;
import com.spdp.bean.SubDivisionBean;
import com.spdp.bean.TehsilBean;
import com.spdp.bean.VillageBean;
import com.spdp.service.VillageService;

@Path("/api")
@RequestScoped
public class VillageController {

	@Inject
	private VillageService villageService;

	@Path("/saveVillageMasterData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveVillageMaster(VillageBean villageBean) throws ParseException {
		
		String response =" ";
		
		try {
			response=villageService.saveVillageData(villageBean);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("response", response);
		return Response.status(Response.Status.OK).entity(map).build();
	}

	@Path("/getSubDivisionDetails/{districtId}")
	@GET
	public List<SubDivisionBean> getSubDivisionDetails(@PathParam(value = "districtId") Long districtId) {
		return villageService.getAllSubDivisionDetails(districtId);
	}

	@Path("/getBlockBySubDivisionId/{subdivisionId}")
	@GET
	public List<BlockBean> getBlockBySubDivisionId(@PathParam(value = "subdivisionId") Long subdivisionId) {
		return villageService.getBlockDetailsBySubDivisionId(subdivisionId);
	}

	@Path("/getVillageDetails")
	@GET
	public List<VillageBean> getVillageDetails() {
		return villageService.getAllVillageData();

	}

	@Path("/getTehsilBySubDivisionId/{subdivisionId}")
	@GET
	public List<TehsilBean> getTehsilBySubDivisionById(@PathParam(value = "subdivisionId") Long subdivisionId) {
		return villageService.getTehsilBySubDivisionById(subdivisionId);

	}

	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/deleteByid/{villageId}")
	@GET
	public Boolean deleteVillageMasterById(@PathParam(value = "villageId") Long villageId) {
		return villageService.deleteVillageMasterById(villageId);

	}

	@Path("/updateVillageData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateVillageMasterData(VillageBean villageBean) throws ParseException {
		System.out.println("Request is coming or notSS");
		villageService.updateVillageMasterData(villageBean);
		// return "Data updated successfully";
	}

	@Path("/getGpByBlockId/{blockId}")
	@GET
	public List<GpBean> getGpByBlockId(@PathParam(value = "blockId") Long blockId) {
		return villageService.getGpByBlockId(blockId);

	}

	@Path("/getRICircleBytehsil/{tehsil}")
	@GET
	public List<RICircleBeans> getRICircleByTehsilId(@PathParam(value = "tehsil") Long tehsil) {
		return villageService.getRICircleByTehsilId(tehsil);

	}
	@Path("/getVillageDetails/{villageId}")
	@GET
   public VillageBean getVillageDataById(@PathParam(value = "villageId") Long villageId)
   {
	return villageService.getVillageMasterDataById(villageId);
	   
   }
}
