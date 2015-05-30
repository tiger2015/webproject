package com.teacher.action;

import com.dao.ChatClassDAO;
import com.entity.ChatClass;
import com.opensymphony.xwork2.ActionSupport;

public class UpdateChatClass extends ActionSupport {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4267479575748583435L;
	private ChatClass chatClass;

	public String updateChatClass() {
		if (chatClass.getCapacity() < chatClass.getTotal()) {
			addFieldError("chatClass.capacity", "班级容量不能小于已添加人数，请重新输入！");
			return INPUT;
		}
		new ChatClassDAO().updateChatClass(chatClass);
		return SUCCESS;
	}

	public ChatClass getChatClass() {
		return this.chatClass;
	}

	public void setChatClass(ChatClass chatClass) {
		this.chatClass = chatClass;
	}

}
