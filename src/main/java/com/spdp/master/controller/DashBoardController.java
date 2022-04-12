package com.spdp.master.controller;

import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.spdp.bean.UlbBean;
import com.spdp.bean.UrbanWardBean;
import com.spdp.service.DashBoardService;





@Path("/api/dashboard")
@RequestScoped

public class DashBoardController {
	
	@Inject
	private DashBoardService dashboardService;
	
	@Path("/getCountforDistrict")
	@GET
	public Map<String, Long> getCountforDistrict() {
		return dashboardService.getCountforDistrict();
	}

	@Path("/getDashBoardUlbWardData")
	@GET
	public List<UrbanWardBean> getAllUlbDatas()
	{
		return dashboardService.getDashBoardUlbWardData();
		
	}



	@Path("/downloadExcel")
	@GET
	public String downloadExcel() {
		
		return null;
	}
	
	@Path("/getDashBoardUlbData/{ulbTypeId}")
	@GET
	public List<UlbBean> getAllUlbDatas(@PathParam(value = "ulbTypeId")Long ulbTypeId)
	{
		System.out.println("-----------------------------------"+ulbTypeId);
		return dashboardService.getDashBoardUlbData(ulbTypeId);
		
	}
	
}
