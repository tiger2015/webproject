package com.teacher.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ResponseDAO;
import com.entity.Response;
import com.opensymphony.xwork2.ActionSupport;

public class AddResponse extends ActionSupport implements ServletRequestAware{
   private HttpServletRequest request;
   private Response response;
   public String addResponse()
   {
	   request.setAttribute("messageid", response.getMessage().getMessageId());
	   new ResponseDAO().save(response);
	  
	   return SUCCESS;
   }
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}

}
