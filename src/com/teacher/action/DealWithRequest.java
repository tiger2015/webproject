package com.teacher.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.StudentClassDAO;
import com.entity.ChatClass;
import com.entity.JoinRequest;
import com.entity.JoinRequestId;
import com.entity.Student;
import com.entity.StudentClass;
import com.entity.StudentClassId;
import com.opensymphony.xwork2.ActionSupport;

public class DealWithRequest extends ActionSupport implements
		ServletRequestAware {
	private HttpServletRequest request;

	public String dealWithRequest() {
		String classid = request.getParameter("classid");
		String userid = request.getParameter("userid");
		String teacherId = request.getSession().getAttribute("userid")
				.toString();
		String current = request.getParameter("currentpage");
		ChatClass chatClass = new ChatClass();
		chatClass.setClassId(classid);

		Student student = new Student();
		student.setStudentId(userid);

		StudentClassId studentClassId = new StudentClassId();
		studentClassId.setChatClass(chatClass);
		studentClassId.setStudent(student);

		StudentClass studentClass = new StudentClass();
		studentClass.setStudentClassId(studentClassId);

		JoinRequest joinRequest = new JoinRequest();
		joinRequest.setJoinRequestId(new JoinRequestId(student, chatClass));
		joinRequest.setIsAllow(new Integer(1));
		// 加入到studentclass中
		new StudentClassDAO().save(studentClass);
		// 更改申请是否通过
		// new JoinRequestDAO().updateIsAllow(joinRequest);
		// 更改总人数
		// new ChatClassDAO().updateTotal("classId", classid);
		// 查询结果
		request.setAttribute("classid", classid);
        request.setAttribute("currentpage", current);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
