package com.student.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.StudentClassDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class FindJoinedClass extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest request;

	public String findJoinedClass() {
		String userId = request.getSession().getAttribute("userid").toString();
		String flag = request.getParameter("flag");
		//分页查找加入的班级
		if (flag == null || flag.trim().length() == 0) {
			String current = request.getParameter("currentpage");
			MySplitePage splitePage = null;
			if (request.getSession().getAttribute("splitepage") == null) {
				splitePage = new MySplitePage();
			} else {
				splitePage = (MySplitePage) request.getSession().getAttribute(
						"splitepage");
			}
			splitePage.setTotalRecords((new StudentClassDAO())
					.getTotalStudentClass("studentId", userId));
            if(current==null||current.trim().length()==0)
            	splitePage.setPage(1);
            else
            	splitePage.setPage(Integer.parseInt(current));
			List list = new StudentClassDAO().findByProperty("studentId",
					userId, splitePage);
			request.setAttribute("result", list);
			request.getSession().setAttribute("splitepage", splitePage);
			
		} else {
			//非分页查找
			List list = new StudentClassDAO().findByProperty("studentId",
					userId, null);
			request.setAttribute("result", list);
		}
		if ("1".equals(flag))
			return "message";
		else if ("2".equals(flag))
			return "findmessage";
		else if ("3".equals(flag))
			return "downloadfile";
		else
			return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
