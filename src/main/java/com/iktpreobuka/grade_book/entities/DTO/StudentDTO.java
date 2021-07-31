package com.iktpreobuka.grade_book.entities.DTO;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class StudentDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer user_id;
	
	protected String first_name;
	
	protected String last_name;
	
	protected String department;

	public StudentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public StudentDTO(Integer user_id, String first_name, String last_name, String department) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.department = department;
	}



	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	
	
	
}
