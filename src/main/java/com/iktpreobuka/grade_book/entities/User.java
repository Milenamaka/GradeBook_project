package com.iktpreobuka.grade_book.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.grade_book.entities.DTO.RoleDTO;
import com.iktpreobuka.grade_book.security.Views;

@Entity
@Table(name="user")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@NotBlank(message="Username must be provided")
	//@Size(min = 2, max = 15, message= "Username must be between {min} and {max} caracters long")
	@Column(nullable = false)
	private String username;
	
	
	@JsonIgnore
	//@NotBlank(message="Password must be provided")
	//@Size(min = 2, max = 10, message= "Password must be between {min} and {max} caracters long")
	@Column(nullable = false)
	private String password;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name="role")
	private UserRole role;
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}
	public void setRole(RoleDTO role) {
		this.role.setId(role.getId());
		this.role.setName(role.getName());
	}
	

	
}
