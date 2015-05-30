package com.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Teacher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2204886151991974614L;
	private String teacherId;
	private String teacherName;
	private String teacherSex;
	private String position;
	private String courseDescribe;
	private Department department;
	private Set chatClass = new HashSet();

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getTeacherSex() {
		return teacherSex;
	}

	public void setTeacherSex(String teacherSex) {
		this.teacherSex = teacherSex;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCourseDescribe() {
		return courseDescribe;
	}

	public void setCourseDescribe(String courseDescribe) {
		this.courseDescribe = courseDescribe;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Set getChatClass() {
		return chatClass;
	}

	public void setChatClass(Set chatClass) {
		this.chatClass = chatClass;
	}

}
