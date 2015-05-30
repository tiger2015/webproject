package com.student.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.dao.ChatClassDAO;
import com.entity.ChatClass;
import com.opensymphony.xwork2.ActionSupport;

public class AddChatClass extends ActionSupport implements ServletRequestAware {
	private HttpServletRequest request;
	private ChatClass chatClass;

	public String addChatClass() {
		new ChatClassDAO().save(chatClass);
		return SUCCESS;
	}

	public ChatClass getChatClass() {
		return chatClass;
	}

	public void setChatClass(ChatClass chatClass) {
		this.chatClass = chatClass;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		request = arg0;
	}

}
