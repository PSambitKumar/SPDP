package com.spdp.master.controller;

import java.text.ParseException;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.spdp.bean.DistUlbBean;
import com.spdp.bean.UlbBean;
import com.spdp.service.ULBMasterService;

@Path("/api")
@RequestScoped
public class ULBMasterController

{
	@Inject
	private ULBMasterService uLBMasterService;
	
	@Path("/saveUlbMasterData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String saveUlbMasterData(UlbBean ulbBean) throws ParseException{
		
		return uLBMasterService.saveUlbData(ulbBean);
	}
	
	@Path("/getAllData")
	@GET
	public List<UlbBean> getAllUlbDatas()
	{
		System.out.println("Request is coming");
		return uLBMasterService.getAllUlbMasterData();
		
	}
	
	@Path("/deleteByid/{notificationNumber}")
	@GET
	public Boolean deleteUlbMasterById(@PathParam(value = "notificationNumber")String notificationNumber)
	{
		return uLBMasterService.deleteUlbMasterById(notificationNumber);
		
	}
	
	@Path("/getDistsAndUlbName")
	@GET
	public List<DistUlbBean> getDistAndUlbName()
	{
		return uLBMasterService.getAllDistAndUlbName();
		
	}
	
	@GET
	@Path("/updateUlb/{ulbId}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public UlbBean getUlbDataById(@PathParam(value = "ulbId") Long ulbId)
	{
		return uLBMasterService.getUlbMasterDataById(ulbId) ;
		
	}
	
	@Path("/updateUlbData")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public String updateUlbMasterData(UlbBean ulbBean) throws ParseException 
	{
		System.out.println("Request is coming or notSS");
		return uLBMasterService.updateUlbData(ulbBean);
		
		
	}
	
}
