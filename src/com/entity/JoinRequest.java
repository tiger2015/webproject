package com.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class JoinRequest implements Serializable{
	private static final long serialVersionUID = -8377583111386512407L;
	private Timestamp requestDate;
	private Integer isAllow=0;
    private JoinRequestId joinRequestId;
    
	public Timestamp getRequestDate() {
		return requestDate;
	}
	public void setRequestDate(Timestamp requestDate) {
		this.requestDate = requestDate;
	}
	public Integer getIsAllow() {
		return isAllow;
	}
	public void setIsAllow(Integer isAllow) {
		this.isAllow = isAllow;
	}
	public JoinRequestId getJoinRequestId() {
		return joinRequestId;
	}
	public void setJoinRequestId(JoinRequestId joinRequestId) {
		this.joinRequestId = joinRequestId;
	}

}
