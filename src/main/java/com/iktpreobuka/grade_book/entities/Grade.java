package com.iktpreobuka.grade_book.entities;


import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.grade_book.entities.DTO.TeacherDTO;
import com.iktpreobuka.grade_book.security.Views;

@Entity
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	@Max(5) 
	@Min(1)
	@Column(nullable = false, name="grade_val")
	private Integer gradeVal;
	
	@Max(2) 
	@Min(1)
	@Column(nullable = false, name="semestar")
	private Integer semestar;
	
	@Column(nullable = true, name="final_grade")
	private Integer finalGrade;

	@JsonManagedReference
	@ManyToOne(cascade =CascadeType.REFRESH, fetch = FetchType.EAGER)
	@JoinColumn(name = "student")
	private Student student;


	@JsonManagedReference
	@ManyToOne(cascade =CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "teacher")
	private Teacher teacher;
	

	@JsonManagedReference
	@ManyToOne(cascade =CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "subject")
	private Subject subject;
	
	public Grade() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	public Integer getSemestar() {
		return semestar;
	}
	public void setSemestar(Integer semestar) {
		this.semestar = semestar;
	}
	public Integer getFinalGrade() {
		return finalGrade;
	}
	public void setFinalGrade(Integer finalGrade) {
		this.finalGrade = finalGrade;
	}
	public Integer getGradeVal() {
		return gradeVal;
	}
	public void setGradeVal(Integer gradeVal) {
		this.gradeVal = gradeVal;
	}
	public Student getStudent() {
		return student;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public void setTeacher(TeacherDTO teacher) {
		this.teacher.setFirstName(teacher.getFirstName());
		this.teacher.setLastName(teacher.getLastName());
		this.teacher.setPhone(teacher.getPhone());
		this.teacher.setId(teacher.getId());
		
	}
	public Subject getSubject() {
		return subject;
	}
	public void setSubject(Subject subject) {
		this.subject = subject;
	}
	
}
