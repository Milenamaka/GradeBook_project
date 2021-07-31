package com.iktpreobuka.grade_book.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table (name="role")
public class UserRole {
	
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@NotBlank(message="name must be provided")
	@Column(name = "role_name")
	private String name;
	
	@JsonIgnore
	@OneToMany(mappedBy = "role", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH )
	private List<User> users = new ArrayList<>();

	public UserRole() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	




	public UserRole(String name, Integer id) {
		this.name = name;
		this.id = id;
	}



	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}






	public List<User> getUsers() {
		return users;
	}






	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	

}
