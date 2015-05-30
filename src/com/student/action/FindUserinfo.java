package com.student.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.StudentDAO;
import com.dao.TeacherDAO;
import com.dao.UserInfoDAO;
import com.entity.Student;
import com.entity.Teacher;
import com.entity.UserInfo;
import com.opensymphony.xwork2.ActionSupport;

public class FindUserinfo extends ActionSupport implements ServletRequestAware{
    /**
	 * 
	 */
	private static final long serialVersionUID = -621065067054440252L;
	private HttpServletRequest request;
    private String userId="";
    
    public String findUserinfo(){
    	UserInfo userInfo=new UserInfoDAO().findByUserId(userId);
    	if(userInfo.getUserRole()==1)
    	{
    		Teacher teacher=(Teacher) new TeacherDAO().findByProperty("teacherId", userId, 0).get(0);
    		request.setAttribute("teacher", teacher);
    	}else if(userInfo.getUserRole()==0){
    		
    		Student student=(Student) new StudentDAO().findByProperty("studentId", userId, 0).get(0);
    		request.setAttribute("student", student);
    	}  	
    	return SUCCESS;
    }
    
    
    
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request=arg0;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
