package com.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class Department implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -3640058433707940672L;
	private Long departementId;
	private String departmentName;
	private Set disciplines = new HashSet();
	private Set students = new HashSet();
	private Set teachers = new HashSet();

	public Long getDepartementId() {
		return departementId;
	}

	public void setDepartementId(Long departementId) {
		this.departementId = departementId;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set getDisciplines() {
		return disciplines;
	}

	public void setDisciplines(Set disciplines) {
		this.disciplines = disciplines;
	}

	public Set getStudents() {
		return students;
	}

	public void setStudents(Set students) {
		this.students = students;
	}

	public Set getTeachers() {
		return teachers;
	}

	public void setTeachers(Set teachers) {
		this.teachers = teachers;
	}

}
