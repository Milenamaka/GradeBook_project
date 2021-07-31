package com.iktpreobuka.grade_book.entities.DTO;

import javax.persistence.Column;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class GradesReportDTO {
	@NotBlank(message="Grade must be provided")
	@Max(1) 
	@Min(5)
	@Column(nullable = false, name="grade_val")
	private Integer gradeVal;
	
	
	@NotBlank(message="Semestar must be provided")
	@Max(1) 
	@Min(2)
	@Column(nullable = false, name="semestar")
	private Integer semestar;
	private Integer student_id;
	private String first_name;
	private String last_name;
	private String subject_name;
	private String department;

	public GradesReportDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public GradesReportDTO(@NotBlank(message = "Grade must be provided") @Max(1) @Min(5) Integer gradeVal,
			@NotBlank(message = "Semestar must be provided") @Max(1) @Min(2) Integer semestar, Integer student_id,
			String first_name, String last_name, String subject_name, String department) {
		super();
		this.gradeVal = gradeVal;
		this.semestar = semestar;
		this.student_id = student_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.subject_name = subject_name;
		this.department = department;
	}

	public Integer getGradeVal() {
		return gradeVal;
	}

	public void setGradeVal(Integer gradeVal) {
		this.gradeVal = gradeVal;
	}

	public Integer getSemestar() {
		return semestar;
	}

	public void setSemestar(Integer semestar) {
		this.semestar = semestar;
	}

	public Integer getStudent_id() {
		return student_id;
	}

	public void setStudent_id(Integer student_id) {
		this.student_id = student_id;
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

	public String getSubject_name() {
		return subject_name;
	}

	public void setSubject_name(String subject_name) {
		this.subject_name = subject_name;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


	
}
