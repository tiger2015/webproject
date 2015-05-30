package com.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.UserInfoDAO;
import com.entity.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class UpdatePassword extends ActionSupport implements
		ServletRequestAware {
	private static final long serialVersionUID = 2519559638405122985L;
	private HttpServletRequest request;
	private String newPassword;
	private String oldPassword;
	private String confirmPassword;
	private String userId;

	public String updatePassword() {
		UserInfo userInfo = new UserInfo();
		userId = request.getSession().getAttribute("userid").toString();
		int userRole = Integer.parseInt(request.getSession()
				.getAttribute("userrole").toString());
		userInfo.setUserId(userId);
		userInfo.setUserPassword(newPassword);		
		new UserInfoDAO().updatePassword(userInfo);
		if (userRole == 1)
			return "teacher";
		else if (userRole == 0)
			return "student";
		return INPUT;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	@Override
	public void validate() {
		// TODO Auto-generated method stub
		userId = request.getSession().getAttribute("userid").toString();
		UserInfo userInfo = new UserInfoDAO().findByUserId(userId);
		Pattern pattern=Pattern.compile("^[A-Za-z0-9_]+$");
	     Matcher matcher=pattern.matcher(newPassword);
				
		if (oldPassword.length()>0&&!userInfo.getUserPassword().equals(oldPassword)) {
			this.addFieldError("oldPassword", "原密码错误");
		} else if (newPassword.length()>0&&!matcher.matches()) {
			this.addFieldError("newPassword", "密码为数字、字母或下划线");
		} else if (newPassword.length()>0&&confirmPassword.length()>0&&!newPassword.equals(confirmPassword)) {
			this.addFieldError("confirmPassword", "密码不一致");
		}
		

	}

}
