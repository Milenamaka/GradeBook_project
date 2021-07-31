package com.iktpreobuka.grade_book.entities;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.grade_book.security.Views;

@Entity
@Table(name="Teacher")

public class Teacher extends User {

	
	
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
	
	@JsonView(Views.AdminView.class)
	@Column(nullable = true)
	private String phone;
	
	@JsonManagedReference
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "subject")
	private Subject subject;
	
	@JsonView(Views.AdminView.class)
	@JsonManagedReference
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Student_Teacher", joinColumns = {@JoinColumn(name = "Teacher_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "Student_id", nullable = false, updatable = false)})
	private Set<Student> students = new HashSet<Student>();
	
	public Teacher() {
			super();
			super.setRole(new UserRole("ROLE_TEACHER", 2));
			// TODO Auto-generated constructor stub
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

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	
}
