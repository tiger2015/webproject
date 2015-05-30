package com.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Response implements Serializable{
/**
	 * 
	 */
private static final long serialVersionUID = -3070382272610284324L;
private String responseId;
private Timestamp responseDate;
private String responseContent;
private UserInfo userinfo;
private Message message;
public String getResponseId() {
	return responseId;
}
public void setResponseId(String responseId) {
	this.responseId = responseId;
}
public Timestamp getResponseDate() {
	return responseDate;
}
public void setResponseDate(Timestamp responseDate) {
	this.responseDate = responseDate;
}
public String getResponseContent() {
	return responseContent;
}
public void setResponseContent(String responseContent) {
	this.responseContent = responseContent;
}
public UserInfo getUserinfo() {
	return userinfo;
}
public void setUserinfo(UserInfo userinfo) {
	this.userinfo = userinfo;
}
public Message getMessage() {
	return message;
}
public void setMessage(Message message) {
	this.message = message;
}


}
