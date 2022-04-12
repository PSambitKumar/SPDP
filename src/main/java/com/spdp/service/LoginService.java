package com.spdp.service;

import javax.ws.rs.core.Response;

import com.spdp.bean.AuthRequest;




public interface LoginService {
	Response preLogin(AuthRequest authRequest);
	
}
