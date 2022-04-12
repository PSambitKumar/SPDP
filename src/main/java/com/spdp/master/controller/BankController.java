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

import com.spdp.bean.BankBean;
import com.spdp.bean.BankMasterBean;
import com.spdp.service.BankService;





/**
 * 
 * @Author : Akash Deep
 * 
 **/


@Path("/api/r1")
@RequestScoped

public class BankController {
	
	@Inject
	private BankService masterService;
	
	@Path("/getBankDetailsForView")
	@GET
	public List<BankBean> getBankDetails() {
   
	return masterService.getDetailsByBankId();
	}
	
	@Path("/getDetailsOfBank")
	@GET
	public List<BankMasterBean> getDetailsOfBank() {
   
	return masterService.getDetailsOfBank();
	}
	

//	@Path("/getSubdivisionDetails")
//	@GET
//	public List<SubdivisiondropdownBean> getSubdivisionDetails() {
//		return masterService.getSubdivisionDetails();
//	}
	
	
	
	
	
	

	
	@POST
	@Path("/saveBankDetails")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response saveBankDetails(BankBean bkBean) throws ParseException  {
		System.out.println(bkBean);		

		System.out.println("<<<<<<<<<<<>>>>>>>>>");		
		String response ="";
		
		try {
		response =masterService.saveBankDetails(bkBean);
		}catch (Exception e) {
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("response", response);
		return Response.status(Response.Status.OK).entity(map).build();
	}
	
	
	
	
	
	
	@Path("/deleteBankByBranchName/{bankbranchName}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void deleteBankByBranchName(@PathParam(value = "bankbranchName") String bankbranchName) {
		masterService.deleteBankByBranchName(bankbranchName);
		System.out.println("Delete Controller ----- "+bankbranchName);
	}
	
//	
//	@Path("/getDistrictAndSubdivisionDetails")
//	@GET
//	public List<DistSubdivisionBean> getDistrictAndSubdivisionDetails() {
//		return masterService.getDistrictAndSubdivisionDetails();
//	}
//	
	@Path("/getBankDetailsForUpdate/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public BankBean getBankDetailsForUpdate(@PathParam(value = "id") Long id) {
		
		return masterService.getBankDetailsForUpdate(id);
	}
	

	@Path("/updateBankDetails")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void updateBankDetails(BankBean rdcObj) throws ParseException {
		
//		SimpleDateFormat sfdate = new SimpleDateFormat("dd-MMM-yy hh.mm.ss.SSSSSSSSS a");
System.out.println("Inside Update Bank Controller >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+rdcObj.getBankbranchName());		
		
		
		masterService.updateBankDetails(rdcObj);
	}
	
	@Path("/getBankBranchDetailsBybankId/{id}")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BankBean> getBankBranchDetailsBybankId(@PathParam(value = "id") Long id) {
		
		return masterService.getBankBranchDetailsBybankId(id);
	}
	
	@Path("/getBankMasterDetailsFilteredByDistrictNames/{districtNames}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BankBean> getBankMasterDetailsFilteredByDistrictNames(@PathParam(value = "districtNames") Long did) {               //,@PathParam(value = "date") Object dt
			return masterService.getBankMasterDetailsFilteredByDistrictNames(did);
	}
	
	@Path("/getBankMasterDetailsFilteredByBankNames/{bankNames}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BankBean> getBankMasterDetailsFilteredByBankNames(@PathParam(value = "bankNames") Long bid) {               //,@PathParam(value = "date") Object dt
			return masterService.getBankMasterDetailsFilteredByBankNames(bid);
	}
	
	@Path("/getBankMasterDetailsFilteredByBankBranchNames/{bankBranchNames}")///{date}
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BankBean> getBankMasterDetailsFilteredByBankBranchNames(@PathParam(value = "bankBranchNames") Long bbid) {               //,@PathParam(value = "date") Object dt
			return masterService.getBankMasterDetailsFilteredByBankBranchNames(bbid);
	}
	
	@Path("/bankMasterDetailsFilterByAllData")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BankBean> bankMasterDetailsFilterByAllData(@QueryParam(value = "distId") Long did,@QueryParam(value = "bankId") Long bid,@QueryParam(value = "branchId") Long bbid) {      
			return masterService.bankMasterDetailsFilterByAllData(did,bid,bbid);
	}
	
	@Path("/bankMasterDetailsFilterByDistAndBank")
	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public List<BankBean> bankMasterDetailsFilterByDistAndBank(@QueryParam(value = "distId") Long did,@QueryParam(value = "bankId") Long bid) {      
			return masterService.bankMasterDetailsFilterByDistAndBank(did,bid);
	}
}