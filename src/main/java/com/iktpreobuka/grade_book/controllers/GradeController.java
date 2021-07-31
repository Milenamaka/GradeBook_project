package com.iktpreobuka.grade_book.controllers;


import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.grade_book.controllers.utils.RESTError;
import com.iktpreobuka.grade_book.entities.Grade;
import com.iktpreobuka.grade_book.entities.DTO.GradesReportDTO;
import com.iktpreobuka.grade_book.repositories.GradeRepository;
import com.iktpreobuka.grade_book.repositories.StudentRepository;
import com.iktpreobuka.grade_book.repositories.SubjectRepository;
import com.iktpreobuka.grade_book.repositories.TeacherRepository;
import com.iktpreobuka.grade_book.security.Views;
import com.iktpreobuka.grade_book.services.GradeService;
import com.iktpreobuka.grade_book.services.TeacherService;


@RestController
@RequestMapping(value = "/grade_book/grades")
public class GradeController {

	@Autowired
	private GradeRepository gradeRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired 
	private TeacherRepository teacherRepository;
	
	@Autowired 
	private SubjectRepository subjectRepository;
	
	@Autowired 
	private TeacherService teacherService;
	
	@Autowired 
	private GradeService gradeService;
	
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/student/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllStudentView(@PathVariable Integer id) {
		
		if(gradeRepository.findAllByStudentId(id)== null){
		return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);}
	
		return new ResponseEntity<>((List<Grade>) gradeRepository.findAllByStudentId(id), HttpStatus.OK);
	}
	@Secured("ROLE_THEACHER")
	@RequestMapping(value = "/student/{student_id}/{teacher_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getStudentGradesFromTeacher(@PathVariable Integer student_id, @PathVariable Integer teacher_id) {
		
		if(gradeService.GetGradesForStudent(student_id, teacher_id)== null){
		return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);}
	
		return new ResponseEntity<>((List<GradesReportDTO>) gradeService.GetGradesForStudent(student_id, teacher_id), HttpStatus.OK);
	}
	@Secured("ROLE_THEACHER")
	@RequestMapping(value = "/teacher/{teacher_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getStudentsGradesFromTeacher(@PathVariable Integer teacher_id) {
		
		if(gradeService.GetGradesForTeacher(teacher_id)== null){
		return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);}
	
		return new ResponseEntity<>((List<GradesReportDTO>) gradeService.GetGradesForTeacher(teacher_id), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/parent/{parent_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getStudentsGradesForParent(@PathVariable Integer parent_id) {
		
		if(gradeService.GetGradesForParent(parent_id)== null){
		return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);}
	
		return new ResponseEntity<>((List<GradesReportDTO>) gradeService.GetGradesForParent(parent_id), HttpStatus.OK);
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> createGrade(@Valid @RequestParam Integer teacherId, @Valid @RequestParam Integer studentId, @RequestParam Integer semestar, @Valid @RequestParam Integer subjectId, @Valid @RequestParam Integer gradeVal ) {
		Grade grade = new Grade();
		grade.setGradeVal(gradeVal);
		grade.setTeacher(teacherRepository.findById(teacherId).get());
		grade.setStudent(studentRepository.findById(studentId).get());
		grade.setSubject(subjectRepository.findById(subjectId).get());
		grade.setSemestar(semestar);
		if(grade!=null) {
			gradeRepository.save(grade);}
		return new ResponseEntity<Grade>(grade, HttpStatus.OK);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> changeGrade(@Valid @RequestBody Grade changedGrade, @Valid @PathVariable Integer id) {
		Grade grade = gradeRepository.findById(id).get();
			if(grade != null) {
				grade.setGradeVal(changedGrade.getGradeVal());
				grade.setStudent(changedGrade.getStudent());
				grade.setSubject(changedGrade.getSubject());
				grade.setTeacher(changedGrade.getTeacher());
				gradeRepository.save(grade);
				return new ResponseEntity<Grade>(grade, HttpStatus.OK);
			}
			return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteGrade(@Valid @PathVariable Integer id) {
		Grade grade = gradeRepository.findById(id).get();
		if (grade!= null) {
			gradeRepository.delete(grade);
			return new ResponseEntity<Grade>(grade, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
}
