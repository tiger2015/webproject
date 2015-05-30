package com.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class StudentClass implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -1607614332466083880L;
	private Timestamp joinDate;
	private StudentClassId studentClassId;

	public Timestamp getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Timestamp joinDate) {
		this.joinDate = joinDate;
	}

	public StudentClassId getStudentClassId() {
		return studentClassId;
	}

	public void setStudentClassId(StudentClassId studentClassId) {
		this.studentClassId = studentClassId;
	}
}
