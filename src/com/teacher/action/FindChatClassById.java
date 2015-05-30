package com.teacher.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;
import com.dao.ChatClassDAO;
import com.entity.ChatClass;
import com.opensymphony.xwork2.ActionSupport;

public class FindChatClassById extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3820352846656147571L;
	private HttpServletRequest request;

	public String findChatClassById() {
		String classId = request.getParameter("chatclassid");
		ChatClass chatClass = (ChatClass) (new ChatClassDAO().findByProperty(
				"classId", classId, 0, null)).get(0);
		request.setAttribute("chatClass", chatClass);
		// request.getSession().setAttribute("chatclassid", classId);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
