package com.iktpreobuka.grade_book.entities;

import java.util.HashSet;


import java.util.List;
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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.grade_book.security.Views;

@Entity
@Table(name="Student")
public class Student extends User {

	
	@NotBlank(message="First name must be provided")
	//@Size(min = 2, max = 30, message= "First name must be between {min} and {max} caracters long")
	@Column(nullable = false)
	private String firstName;
	

	@NotBlank(message="Last name must be provided")
	//@Size(min = 2, max = 30, message= "Last name must be between {min} and {max} caracters long")
	@Column(nullable = false)
	private String lastName;
	
	
	@NotBlank(message="JMBG must be provided")
	@Column(nullable = false)
	private String JMBG;
	
	//@NotBlank(message="Gender must be provided")
	//@Column(nullable = false)
	private Gender gender; 
	
	@NotBlank(message="Department must be provided")
	@Column(nullable = false)
	private String department; 
	
	
	@JsonBackReference
	@OneToMany(mappedBy = "student", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
	private List<Grade> grades;
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Student_Subject", joinColumns = {@JoinColumn(name = "Student_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "Subject_id", nullable = false, updatable = false)})
	private Set<Subject> subjects = new HashSet<Subject>();

	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Student_Teacher", joinColumns = {@JoinColumn(name = "Student_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "Teacher_id", nullable = false, updatable = false)})
	private Set<Teacher> teachers = new HashSet<Teacher>();
	
	@JsonIgnore
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Student_Parent", joinColumns = {@JoinColumn(name = "Student_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "Parent_id", nullable = false, updatable = false)})
	private Set<Parent> parents = new HashSet<Parent>();
	
	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public String getDepartment() {
		return department;
	}



	public void setDepartment(String department) {
		this.department = department;
	}



	public List<Grade> getGrades() {
		return grades;
	}

	public void setGrades(List<Grade> grades) {
		this.grades = grades;
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
	public String getJMBG() {
		return JMBG;
	}
	public void setJMBG(String jMBG) {
		JMBG = jMBG;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Set<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Set<Subject> subjects) {
		this.subjects = subjects;
	}
	public Set<Subject> addSubject(Subject subject) {
		setSubjects(subjects);
		subjects.add(subject);
		return subjects;
	}
	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	public Set<Teacher> addTeacher(Teacher teacher) {
		setTeachers(teachers);
		teachers.add(teacher);
		return teachers;
	}

	public Set<Parent> getParents() {
		return parents;
	}

	public void setParents(Set<Parent> parents) {
		this.parents = parents;
	}
	public Set<Parent> addParent(Parent parent) {
		setParents(parents);
		parents.add(parent);
		return parents;
	}
	
	

	
	
}
