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
import com.spdp.bean.BlockGpBean;
import com.spdp.bean.GpBean;
import com.spdp.bean.SubDivisionBean;
import com.spdp.entity.BlockEntity;
import com.spdp.service.BlockService;

/**
 * 
 * @author jyotiranjan.behera
 *
 */
@Path("/api/v3")
@RequestScoped
public class BlockController {
	
	@Inject
	private BlockService blockService;
	
	@Path("/getBlockDetailsForView")
	@GET
	public List<BlockBean> getblockDetails() {
			return blockService.getDetailsByBlockId();
	}
	
	@Path("/getGpDetails")
	@GET
	public List<GpBean> getGpDetails() {
		return blockService.getGpDetails();
	}
	
	@Path("/getSubDivisionDetails")
	@GET
	public List<SubDivisionBean> getSubDivisionDetails() {
		return blockService.getSubDivisionDetails();
	}
	
	@Path("/saveBlockDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON) 
	@Produces(MediaType.APPLICATION_JSON) 
	public Response saveRdcDetail(BlockBean blockObj) throws ParseException  {
		String response =" ";
		
		try {
		response =blockService.saveBlockDetails(blockObj);
		}catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("response", response);
		return Response.status(Response.Status.OK).entity(map).build();
	}
	
	@Path("/deleteBlockByNotiNo/{notiNo}")
	@GET
	public void deleteBlockByNotiNo(@PathParam(value = "notiNo") String notiNo) {
		blockService.deleteBlockByNotiNo(notiNo);
		System.out.println("Delete Controller ----- "+notiNo);
	}
	
	@Path("/getBlockAndGpDetails")
	@GET
	public List<BlockGpBean> getBlockAndGpDetails() {
		return blockService.getBlockAndGpDetails();
	}
	
	@Path("/getBlockDetailsForUpdate/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BlockBean getBlockDetailsForUpdate(@PathParam(value = "id") Long id) {
		
		return blockService.getBlockDetailsForUpdate(id);
	}
	
	
	@Path("/updateBlockDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateBlockDetails(BlockBean blockObj) throws ParseException {
		
		
		blockService.updateBlockDetails(blockObj);
	}
	
	@Path("/getSubDivDetailsByDistId/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BlockBean> getSubDivDetailsByDistId(@PathParam(value = "id") Long id) {
		
		return blockService.getSubDivDetailsByDistId(id);
	}
	
	@Path("/getGpDetailsBySubDivId/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BlockBean> getGpDetailsBySubDivId(@PathParam(value = "id") Long id) {
		
		return blockService.getGpDetailsBySubDivId(id);
	}
	
	@Path("/getBlockDetailsBySubDivId/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BlockBean> getBlockDetailsBySubDivId(@PathParam(value = "id") Long id) {
		
		return blockService.getBlockDetailsBySubDivId(id);
	}
	
	//Filter
	
	
	@Path("/blockDetailsFilterForDist/{distId}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BlockBean> blockDetailsFilterForDist(@PathParam(value = "distId") Long did) {     
			return blockService.blockDetailsFilterForDist(did);
	}
	
	@Path("/blockDetailsFilterForSubDivision/{subDivID}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BlockBean> blockDetailsFilterForSubDivision(@PathParam(value = "subDivID") Long sid) {     
			return blockService.blockDetailsFilterForSubDivision(sid);
	}
	
	@Path("/getBlockDetailsAfterFiltered/{blockId}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BlockBean> getBlockDetailsAfterFiltered(@PathParam(value = "blockId") Long bid) {              
			return blockService.getBlockDetailsAfterFiltered(bid);
	}
	
	@Path("/blockDetailsFilterByAllData")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BlockBean> blockDetailsFilterByAllData(@QueryParam(value = "bId") Long bid,@QueryParam(value = "date") String date) {    
			return blockService.blockDetailsFilterByAllData(bid,date);
	}
	
	@Path("/blockDetailsFilterByDate")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BlockBean> blockDetailsFilterByDate(@QueryParam(value = "date") String date) {      
			return blockService.blockDetailsFilterByDate(date);
	}
	
	@Path("/blockDetailsFilterByDistAndDate")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BlockBean> blockDetailsFilterByDistAndDate(@QueryParam(value = "dId") Long did,@QueryParam(value = "date") String date) {      
			return blockService.blockDetailsFilterByDistAndDate(did,date);
	}
	@Path("/blockDetailsFilterBySubDivAndDate")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BlockBean> blockDetailsFilterBySubDivAndDate(@QueryParam(value = "sId") Long sid,@QueryParam(value = "date") String date) {      
			return blockService.blockDetailsFilterBySubDivAndDate(sid,date);
	}
	
	
}
