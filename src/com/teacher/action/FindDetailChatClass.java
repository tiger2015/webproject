package com.teacher.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.StudentClassDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class FindDetailChatClass extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3446118221476743876L;
	private HttpServletRequest request;

	public String findDetailChatClass() {
		String classId = request.getParameter("classid");
		if (classId == null || classId.trim().length() == 0)
			classId = request.getAttribute("classid").toString();
		// 分页
		MySplitePage splitePage = null;
		if (request.getSession().getAttribute("splitepage") == null) {
			splitePage = new MySplitePage();
		} else {
			splitePage = (MySplitePage) request.getSession().getAttribute(
					"splitepage");
		}
		// 设置总记录数
		splitePage.setTotalRecords((new StudentClassDAO()).getTotalStudentClass("classId", classId));
		String current = request.getParameter("currentpage");
		// 翻页,第一次
		if (current == null)
			splitePage.setPage(1);
		else
			splitePage.setPage(Integer.parseInt(current));
		//得到班级中所有学生的信息
		List list=new StudentClassDAO().findByProperty("classId", classId, splitePage);
	    request.setAttribute("students", list);	    	    
		request.setAttribute("classid", classId);
		request.getSession().setAttribute("splitepage", splitePage);

		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
