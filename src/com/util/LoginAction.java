package com.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.UserInfoDAO;
import com.entity.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport implements ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private HttpServletRequest request;
	private UserInfo userinfo;

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public String login() {
		UserInfo info = new UserInfoDAO().findByUserId(userinfo.getUserId());
		if (info != null) {
			// 密码正确
			if (userinfo.getUserPassword().equals(info.getUserPassword())) {
				request.getSession().setAttribute("userid",
						info.getUserId());
				request.getSession().setAttribute("userrole",
						info.getUserRole());
				info.setIsOnline(1);
				new UserInfoDAO().updateIsOnline(info);
				if (info.getUserRole() == 1)
					return "teacher";
				else if (info.getUserRole() == 0)
					return "student";
			} else {
				addFieldError("userinfo.userPassword", "密码不正确");
				return INPUT;
			}
		} else {
			addFieldError("userinfo.userId", "用户名不存在");
			
		}
		return INPUT;
	}

	public UserInfo getUserinfo() {
		return userinfo;
	}

	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}

}
