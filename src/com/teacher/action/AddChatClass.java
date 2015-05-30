package com.teacher.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ChatClassDAO;
import com.entity.ChatClass;
import com.entity.Teacher;
import com.opensymphony.xwork2.ActionSupport;

public class AddChatClass extends ActionSupport implements ServletRequestAware {
	private ChatClass chatClass;
	private HttpServletRequest request;

	public String addChatClass() {
		String teacherId = request.getSession().getAttribute("userid")
				.toString();
		// System.out.println(teacherId);
		Teacher teacher = new Teacher();
		teacher.setTeacherId(teacherId);
		chatClass.setTeacher(teacher);
		new ChatClassDAO().save(chatClass);
		return SUCCESS;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {

		// TODO Auto-generated method stub
		request = arg0;
	}

	public ChatClass getChatClass() {
		return chatClass;
	}

	public void setChatClass(ChatClass chatClass) {
		this.chatClass = chatClass;
	}
}
