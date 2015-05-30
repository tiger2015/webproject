package com.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class ChatClass implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5643721883216442436L;
	private String classId;
	private String className;
	private Timestamp createDate;
	private Integer capacity;
	private Integer total;
	private String classDescribe;
	private Teacher teacher;// 多对一的关系
	private Set<StudentClass> studentClass = new HashSet<>();// 一对多的关系
	private Set<Message> messages = new HashSet<>();// 一对多的关系
	private Set<JoinRequest> joinrequests = new HashSet<>();// 一对多的关系
	private Set<CourseFile> coursefile=new HashSet<>();//课件
	//在查询时，标记是否已申请或加入了
    private int state=-1;
	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public Timestamp getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Timestamp createDate) {
		this.createDate = createDate;
	}

	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public String getClassDescribe() {
		return classDescribe;
	}

	public void setClassDescribe(String classDescribe) {
		this.classDescribe = classDescribe;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Set getStudentClass() {
		return studentClass;
	}

	public void setStudentClass(Set studentClass) {
		this.studentClass = studentClass;
	}

	public Set getMessages() {
		return messages;
	}

	public void setMessages(Set messages) {
		this.messages = messages;
	}

	public Set getJoinrequests() {
		return joinrequests;
	}

	public void setJoinrequests(Set joinrequests) {
		this.joinrequests = joinrequests;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public Set getCoursefile() {
		return coursefile;
	}

	public void setCoursefile(Set coursefile) {
		this.coursefile = coursefile;
	}

}
