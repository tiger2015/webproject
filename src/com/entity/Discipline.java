package com.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Discipline implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -581881842367623234L;
	private Long disciplineId;
	private String disciplineName;	
	private Department department;
	private Set students=new HashSet();
	public Long getDisciplineId() {
		return disciplineId;
	}
	public void setDisciplineId(Long disciplineId) {
		this.disciplineId = disciplineId;
	}
	public String getDisciplineName() {
		return disciplineName;
	}
	public void setDisciplineName(String disciplineName) {
		this.disciplineName = disciplineName;
	}
	public Department getDepartment() {
		return department;
	}
	public void setDepartment(Department department) {
		this.department = department;
	}
	public Set getStudents() {
		return students;
	}
	public void setStudents(Set students) {
		this.students = students;
	}

}
