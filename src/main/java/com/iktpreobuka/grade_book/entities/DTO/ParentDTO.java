package com.iktpreobuka.grade_book.entities.DTO;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class ParentDTO {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Integer user_id;
	
	protected String first_name;
	
	protected String last_name;
	
	protected String email;

	public ParentDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ParentDTO(Integer user_id, String first_name, String last_name, String email) {
		super();
		this.user_id = user_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
