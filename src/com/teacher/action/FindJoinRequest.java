package com.teacher.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import oracle.net.aso.s;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ChatClassDAO;
import com.dao.JoinRequestDAO;
import com.entity.ChatClass;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class FindJoinRequest extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest request;

	public String findJoinRequest() {
		String classid = request.getParameter("classid");
		String teacherId = request.getSession().getAttribute("userid")
				.toString();
		String current = request.getParameter("currentpage");
		//在处理申请时
		if (current == null || current.trim().length() == 0)
			if (request.getAttribute("currentpage") != null)
				current = request.getAttribute("currentpage").toString();
		
		MySplitePage splitePage = null;
		if (request.getSession().getAttribute("splitepage") == null) {
			splitePage = new MySplitePage();
		} else {
			splitePage = (MySplitePage) request.getSession().getAttribute(
					"splitepage");
		}
		List list = new ChatClassDAO().findByProperty("teacherid", teacherId,
				0, null);
		request.setAttribute("result", list);
		if (classid == null && list.size() > 0)
			classid = ((ChatClass) list.get(0)).getClassId();

		splitePage.setTotalRecords((new JoinRequestDAO()).getTotalJoinRequest(
				"classId", classid));
		if (current == null || current.trim().length() == 0)
			splitePage.setPage(1);
		else
			splitePage.setPage(Integer.parseInt(current));
		List list1 = new JoinRequestDAO().findAllByProperty("classId", classid,
				splitePage);
		request.setAttribute("requests", list1);
		request.setAttribute("classid", classid);
		request.getSession().setAttribute("splitepage", splitePage);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
