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
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.grade_book.security.Views;

@Entity
public class Subject {
	@JsonView(Views.Public.class)
	@Id
	@Column(name="Subject_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@JsonView(Views.Public.class)
	@NotBlank(message="Subject name must be provided")
	@Column(nullable = false)
	private String name;
	
	@JsonView(Views.Public.class)
	@Column(nullable = false)
	private Integer classesPerWeek;
	
	
	@JsonBackReference
	@OneToMany(mappedBy = "subject", cascade =CascadeType.REFRESH, fetch = FetchType.EAGER)
	private List<Teacher> teachers;
	
	@JsonBackReference
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
	@JoinTable(name = "Student_Subject", joinColumns = {@JoinColumn(name = "Subject_id", nullable = false, updatable = false)},
	inverseJoinColumns = {@JoinColumn(name = "Student_id", nullable = false, updatable = false)})
	private Set<Student> students = new HashSet<Student>();
	
	public Subject() {
		super();
	
		// TODO Auto-generated constructor stub
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
	public Integer getClassesPerWeek() {
		return classesPerWeek;
	}
	public void setClassesPerWeek(Integer classesPerWeek) {
		this.classesPerWeek = classesPerWeek;
	}

	public List<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(List<Teacher> teachers) {
		this.teachers = teachers;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	
	

}
