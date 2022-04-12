package com.spdp.serviceImpl;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.ws.rs.core.Response;

import com.spdp.bean.AuthRequest;
import com.spdp.bean.AuthResponse;
import com.spdp.bean.UserVO;
import com.spdp.entity.User;
import com.spdp.service.LoginService;
@ApplicationScoped
public class LoginServiceImpl implements LoginService{

	@PersistenceContext(unitName = "test")
	private EntityManager entityManager;

	@Override
	public Response preLogin(AuthRequest authRequest) {
		
		AuthResponse response=login(authRequest.getUsername(),authRequest.getPassword());
		 if(response.getValid().equals("true")  && response.getMsg().equalsIgnoreCase("success") )
	        {
			    UserVO userVo=new UserVO();
	    		userVo.setUsername(authRequest.getUsername());
	    		userVo.setPassWord(authRequest.getPassword());
	    		 return Response.status(Response.Status.OK).entity(userVo).build();
	        }
	        else
	        {
	           //return Response.status(Response.Status.UNAUTHORIZED).entity(userVo).build();
	        	throw new RuntimeException("Invalid credentials");
	        	//return Response.status(Response.Status.OK).entity("Invalid credentials.").build();
	        }
		
	}
	
	
	public AuthResponse login(String username, String password) {
		System.out.println(username);
		System.out.println(password);
		System.out.println("Login process Started...");
		AuthResponse Auth=null;
		try {
		Auth=new AuthResponse();
		User UserLogin=entityManager.createNamedQuery("UserDtls", User.class).setParameter("username", username).setParameter("password", password).getSingleResult();
		System.out.println("User Name Form Database:-"+UserLogin.getUserName());
		if(UserLogin!=null) {
			  if(username.equals(UserLogin.getUserName()) && password.equals(UserLogin.getPassWord())) { 
				   Auth.setMsg("success");
				   Auth.setValid("true");
			   }else {
				   Auth.setMsg("Invalid UserName or Password.");
				   Auth.setValid("false");
			   } 
			  
		     }
		}catch(NoResultException e) {
			 e.printStackTrace();		
			 }
		 System.out.println("Login Process End....");
		return Auth;
	}

	
	/*
	 * public static void main(String[] args) { LoginServiceImpl service=new
	 * LoginServiceImpl();
	 * 
	 * AuthResponse response=service.login("kamalcsm", "csm@123");
	 * System.out.println(response.getMsg());
	 * System.out.println(response.getValid());
	 * 
	 * 
	 * }
	 */
	
}
