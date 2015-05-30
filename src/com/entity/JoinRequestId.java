package com.entity;

import java.io.Serializable;

public class JoinRequestId implements Serializable{
	private static final long serialVersionUID = 1L;
	private Student student;
	private ChatClass chatClass;

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public ChatClass getChatClass() {
		return chatClass;
	}

	public void setChatClass(ChatClass chatClass) {
		this.chatClass = chatClass;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chatClass == null) ? 0 : chatClass.hashCode());
		result = prime * result + ((student == null) ? 0 : student.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		JoinRequestId  other = (JoinRequestId) obj;
		if (chatClass == null) {
			if (other.chatClass != null)
				return false;
		} else if (!chatClass.equals(other.chatClass))
			return false;
		if (student == null) {
			if (other.student != null)
				return false;
		} else if (!student.equals(other.student))
			return false;
		return true;
	}

	public JoinRequestId(Student student, ChatClass chatClass) {
		this.student = student;
		this.chatClass = chatClass;
	}

	public JoinRequestId() {
	
	}
}
