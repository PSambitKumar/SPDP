package com.spdp.master.controller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.spdp.bean.AuthRequest;
import com.spdp.service.LoginService;

@Path("/spdp")
@RequestScoped
public class LoginController {
	
	@Inject
	private LoginService loginService;
	
	@POST
	@Path("/getLogin")
	public Response loginDetails(AuthRequest authRequest) {//Need to add AuthRequest authRequest
		/*
		 * String name="kamalcsm"; String pwd="csm123"; AuthRequest auth=new
		 * AuthRequest(); auth.setUsername(name); auth.setPassword(pwd);
		 */
		System.out.println("Username and password :- "+authRequest.getUsername()+" "+authRequest.getPassword());
		return loginService.preLogin(authRequest);
	}
	
	
	
	
	
	/*
	 * @POST
	 * 
	 * @Path("/getLogin") public Response loginDetails() {//Need to add AuthRequest
	 * authRequest
	 * 
	 * String name="kamalcsm"; String pwd="csm123"; AuthRequest auth=new
	 * AuthRequest(); auth.setUsername(name); auth.setPassword(pwd);
	 * 
	 * System.out.println("Username and password :- "+auth.getUsername()+" "+auth.
	 * getPassword()); return loginService.preLogin(auth); }
	 */

}
