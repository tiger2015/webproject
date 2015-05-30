package com.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Message implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3897170113980506332L;
	private String messageId;
	private Timestamp messageDate;
	private String messageContent;
	private String title;
	private Set responses=new HashSet();
	private UserInfo userinfo;
	private ChatClass chatClass;
	private int responseCount=0;
	public String getMessageId() {
		return messageId;
	}
	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}
	public Timestamp getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(Timestamp messageDate) {
		this.messageDate = messageDate;
	}
	public String getMessageContent() {
		return messageContent;
	}
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	public Set getResponses() {
		
		return responses;
	}
	public void setResponses(Set responses) {
		this.responses = responses;
	}
	public UserInfo getUserinfo() {
		return userinfo;
	}
	public void setUserinfo(UserInfo userinfo) {
		this.userinfo = userinfo;
	}
	public ChatClass getChatClass() {
		return chatClass;
	}
	public void setChatClass(ChatClass chatClass) {
		this.chatClass = chatClass;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getResponseCount() {
		return responseCount;
	}
	public void setResponseCount(int responseCount) {
		this.responseCount = responseCount;
	}
	
}
