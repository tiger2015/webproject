package com.teacher.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.MessageDAO;
import com.entity.Message;
import com.entity.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class AddMessage extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;
	private Message message;

	public String addMessage()
	{
		UserInfo userinfo=new UserInfo();
		String userId=request.getSession().getAttribute("userid").toString();
		userinfo.setUserId(userId);
		message.setUserinfo(userinfo);	
		new MessageDAO().save(message);
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

}
