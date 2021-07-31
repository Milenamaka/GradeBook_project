package com.iktpreobuka.grade_book.entities.DTO;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class RoleDTO {
	@Id
	@Column(name="role_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	
	@NotBlank(message="name must be provided")
	@Column(name = "role_name")
	private String name;


	public RoleDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public RoleDTO(Integer id, @NotBlank(message = "name must be provided") String name) {
		super();
		this.id = id;
		this.name = name;
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
	

}
