package com.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Student implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7935890943721404585L;
	private String studentId;
	private String studentName;
	private String studentSex;
	private Date studentBirthday;
	private Department department;
	private Discipline discipline;
	private Set joinRequest = new HashSet();
	private Set studentClass = new HashSet();

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentSex() {
		return studentSex;
	}

	public void setStudentSex(String studentSex) {
		this.studentSex = studentSex;
	}

	public Date getStudentBirthday() {
		return studentBirthday;
	}

	public void setStudentBirthday(Date studentBirthday) {
		this.studentBirthday = studentBirthday;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Discipline getDiscipline() {
		return discipline;
	}

	public void setDiscipline(Discipline discipline) {
		this.discipline = discipline;
	}

	public Set getJoinRequest() {
		return joinRequest;
	}

	public void setJoinRequest(Set joinRequest) {
		this.joinRequest = joinRequest;
	}

	public Set getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(Set studentClass) {
		this.studentClass = studentClass;
	}
}
