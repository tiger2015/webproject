package com.util;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.StudentDAO;
import com.dao.TeacherDAO;
import com.entity.Student;
import com.entity.Teacher;
import com.opensymphony.xwork2.ActionSupport;

public class FindPersonalInfo extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2157964234265081983L;
	private HttpServletRequest request;
	
	public String findPersonalInfo()
	{
		String userId=request.getSession().getAttribute("userid").toString();
		int role=Integer.parseInt(request.getSession().getAttribute("userrole").toString());
		if(role==0)
		{
			Student student=(Student) new StudentDAO().findByProperty("studentId", userId, 0).get(0);
		    request.setAttribute("user",student);
		    return "student";
		
		}else if(role==1){
			Teacher teacher=(Teacher) new TeacherDAO().findByProperty("teacherId", userId, 0).get(0);
			request.setAttribute("user", teacher);
			return "teacher";
		}
		return INPUT;
	}


	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}

}
