package com.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.UserInfoDAO;
import com.entity.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class LogoutAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	public String logout()
	{
		String userId=request.getSession().getAttribute("userid").toString();
		UserInfo info=new UserInfo();
		info.setIsOnline(0);
		info.setUserId(userId);
		if(userId!=null&&userId.length()>0)
		new UserInfoDAO().updateIsOnline(info);
		request.getSession().removeAttribute("userid");
		request.getSession().removeAttribute("userrole");
		request.getSession().invalidate();
		return SUCCESS;
	}
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	

}
