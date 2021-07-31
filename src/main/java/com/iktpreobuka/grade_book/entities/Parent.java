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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.grade_book.security.Views;



@Entity
@Table(name="Parent")
public class Parent extends User {

	
	@JsonView(Views.Public.class)
	@NotBlank(message="First name must be provided")
	@Size(min = 2, max = 30, message= "First name must be between {min} and {max} caracters long")
	@Column(nullable = false)
	private String firstName;
	
	@JsonView(Views.Public.class)
	@NotBlank(message="Last name must be provided")
	@Size(min = 2, max = 30, message= "Last name must be between {min} and {max} caracters long")
	@Column(nullable = false)
	private String LastName;
	
	@JsonView(Views.TeacherView.class)
	@NotBlank(message="Email must be provided")
	@Column(nullable = false)
	private String email;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Student_Parent", joinColumns = {@JoinColumn(name = "Parent_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "Student_id", nullable = false, updatable = false)})
	private Set<Student> students = new HashSet<Student>();
	
	
	public Parent() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return LastName;
	}
	public void setLastName(String lastName) {
		LastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	
	
	
}
