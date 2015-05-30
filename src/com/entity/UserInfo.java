package com.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class UserInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7249858415834746163L;
	private String userId;
	private String userPassword;
	private Integer userRole;
	private Integer isOnline;
	private Set responses = new HashSet();
	private Set messages = new HashSet();

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public Set getResponses() {
		return responses;
	}

	public void setResponses(Set responses) {
		this.responses = responses;
	}

	public Set getMessages() {
		return messages;
	}

	public void setMessages(Set messages) {
		this.messages = messages;
	}

}
