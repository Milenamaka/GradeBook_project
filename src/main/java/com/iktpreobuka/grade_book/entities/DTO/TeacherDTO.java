package com.iktpreobuka.grade_book.entities.DTO;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.grade_book.entities.Subject;
import com.iktpreobuka.grade_book.security.Views;

public class TeacherDTO {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@JsonView(Views.Public.class)
	@NotBlank(message="First name must be provided")
	@Size(min = 2, max = 30, message= "First name must be between {min} and {max} caracters long")
	@Column(nullable = false)
	private String firstName;
	
	@JsonView(Views.Public.class)
	@NotBlank(message="Last name must be provided")
	@Size(min = 2, max = 30, message= "Last name must be between {min} and {max} caracters long")
	@Column(nullable = false)
	private String lastName;
	
	@JsonView(Views.TeacherView.class)
	@Column(nullable = true)
	private String phone;
	
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "subject")
	private Subject subject;

	public TeacherDTO() {
		super();
		// TODO Auto-generated constructor stub
	}



	public TeacherDTO(Integer id,
			@NotBlank(message = "First name must be provided") @Size(min = 2, max = 30, message = "First name must be between {min} and {max} caracters long") String firstName,
			@NotBlank(message = "Last name must be provided") @Size(min = 2, max = 30, message = "Last name must be between {min} and {max} caracters long") String lastName,
			String phone, Subject subject) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phone = phone;
		this.subject = subject;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}
