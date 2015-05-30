package com.teacher.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import oracle.net.aso.s;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ChatClassDAO;
import com.dao.CourseFileDAO;
import com.entity.ChatClass;
import com.opensymphony.xwork2.ActionSupport;
import com.util.MySplitePage;

public class FindCourseFile extends ActionSupport implements
		ServletRequestAware {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2640461200958046673L;
	private HttpServletRequest request;
	private String classId="";

	public String findCourseFile() {
		String userId = request.getSession().getAttribute("userid").toString();
		//查找班级
		List classList = new ChatClassDAO().findByProperty("teacherid", userId,0,null);
		request.setAttribute("chatclass", classList);
		if(request.getParameter("classid")!=null)
		classId=request.getAttribute("classid").toString();
        //第一次查询
		if((classId==null||classId.trim().length()==0)&&classList.size()>0){
		  classId=((ChatClass)classList.get(0)).getClassId();
		}
		//如果创建了班级
		if(classList!=null&&classList.size()>0){
		String current=request.getParameter("currentpage");
		MySplitePage splitePage=null;
		if(request.getSession().getAttribute("splitepage")==null)
			splitePage=new MySplitePage();
		else
			splitePage=(MySplitePage) request.getSession().getAttribute("splitepage");
		 splitePage.setTotalRecords((new CourseFileDAO()).getTotalCourseFile("classId", classId));
		
		 if(current==null||current.trim().length()==0)
			 splitePage.setPage(1);
		 else
			 splitePage.setPage(Integer.parseInt(current));
		 
		 List list = new CourseFileDAO().findByPropertyName("classId", classId,splitePage);
		 request.setAttribute("coursefile", list);
		 request.setAttribute("classid", classId);
		 request.getSession().setAttribute("splitepage", splitePage);
		 }
		 
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

}
