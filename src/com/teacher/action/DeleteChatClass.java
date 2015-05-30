package com.teacher.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ChatClassDAO;
import com.entity.ChatClass;
import com.opensymphony.xwork2.ActionSupport;

public class DeleteChatClass extends ActionSupport implements ServletRequestAware{
    /**
	 * 
	 */
	private static final long serialVersionUID = 8130066780797991066L;
	private HttpServletRequest request;
    
    public String deleteChatClass()
    {
    	String chatClassId=request.getParameter("chatclassid"); 	
    	new ChatClassDAO().delete(chatClassId);    	
    	return SUCCESS;
    }
    
    @Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	

}
