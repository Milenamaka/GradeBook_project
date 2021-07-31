package com.iktpreobuka.grade_book.entities.DTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class SubjectDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer subject_id;
	
	protected String name;
	
	protected String last_name;
	
	private Integer classesPerWeek;

	public SubjectDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getSubject_id() {
		return subject_id;
	}

	public void setSubject_id(Integer subject_id) {
		this.subject_id = subject_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public Integer getClassesPerWeek() {
		return classesPerWeek;
	}

	public void setClassesPerWeek(Integer classesPerWeek) {
		this.classesPerWeek = classesPerWeek;
	}
	
	

}
