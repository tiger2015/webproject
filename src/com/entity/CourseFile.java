package com.entity;

import java.io.Serializable;
import java.sql.Blob;
import java.sql.Timestamp;

public class CourseFile implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6999966318892737981L;
	private String courseFileId;
	private String courseName;
	//private Blob course;
	private Timestamp uploadDate;
    private Teacher teacher;
    private ChatClass chatClass;
	public String getCourseFileId() {
		return courseFileId;
	}
	public void setCourseFileId(String courseFileId) {
		this.courseFileId = courseFileId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
/*
	public Blob getCourse() {
		return course;
	}

	public void setCourse(Blob course) {
		this.course = course;
	}*/
	public Timestamp getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Timestamp uploadDate) {
		this.uploadDate = uploadDate;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public ChatClass getChatClass() {
		return chatClass;
	}
	public void setChatClass(ChatClass chatClass) {
		this.chatClass = chatClass;
	}
    
    
}
