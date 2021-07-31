package com.iktpreobuka.grade_book.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.grade_book.controllers.utils.RESTError;
import com.iktpreobuka.grade_book.entities.Gender;
import com.iktpreobuka.grade_book.entities.Grade;
import com.iktpreobuka.grade_book.entities.Parent;
import com.iktpreobuka.grade_book.entities.Student;
import com.iktpreobuka.grade_book.entities.Teacher;
import com.iktpreobuka.grade_book.entities.UserRole;
import com.iktpreobuka.grade_book.entities.DTO.StudentDTO;
import com.iktpreobuka.grade_book.repositories.ParentRepository;
import com.iktpreobuka.grade_book.repositories.StudentRepository;
import com.iktpreobuka.grade_book.repositories.SubjectRepository;
import com.iktpreobuka.grade_book.repositories.TeacherRepository;
import com.iktpreobuka.grade_book.repositories.UserRoleRepository;
import com.iktpreobuka.grade_book.security.Views;
import com.iktpreobuka.grade_book.services.RoleService;
import com.iktpreobuka.grade_book.services.StudentService;


@RestController
@RequestMapping(value = "/grade_book/students")
public class StudentController {
	
	@Autowired
	private StudentRepository studentRepository;
	@Autowired
	private TeacherRepository teacherRepository;
	@Autowired
	private UserRoleRepository userRoleRepository;
	@Autowired
	private StudentService studentService;
	@Autowired
	private SubjectRepository subjectRepository;
	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private RoleService roleService;
	//radi
	@Secured("ROLE_PARENT")
	@RequestMapping(value = "/parent/{parent_id}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllParentView(@PathVariable Integer parent_id) {
		
		if(studentService.findAllStudentsByParent(parent_id)== null){
		return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);}
	
		return new ResponseEntity<>(studentService.findAllStudentsByParent(parent_id), HttpStatus.OK);
	}
	
	// radi
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ResponseEntity<?> findAll() {
		List<StudentDTO> list = studentService.findAllStudents();
		if(list == null){
		return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);}
	
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	//radi
	@Secured("ROLE_TEACHER")
	@RequestMapping(value = "/allTeacher/{teacherId}/{studentId}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllByTeacherIdStudentId (@PathVariable Integer teacherId, @PathVariable Integer studentId) {
		
		if (studentService.findAllByTeacherStudentId(studentId, teacherId) != null)	{
			return new  ResponseEntity<>(studentService.findAllByTeacherStudentId(studentId,teacherId), HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);
	}
	//radi
	@Secured("ROLE_TEACHER")
	@RequestMapping(value = "/teacher/{teacherId}/", method = RequestMethod.GET)
	public ResponseEntity<?> getAllTeacherStudentsByID(@PathVariable Integer teacherId) {
		
		if (studentService.findAllByTeacherStudents(teacherId) != null)	{
			return new ResponseEntity<>(studentService.findAllByTeacherStudents(teacherId), HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);
	}
	@Secured("ROLE_TEACHER")
	@RequestMapping(value = "/teacher/{teacherId}/{department}", method = RequestMethod.GET)
	public ResponseEntity<?> getAllTeacherClassStudentsByID(@PathVariable Integer teacherId, @PathVariable String department) {
		
		if (studentService.findAStudentsInClassByTeacher(teacherId, department) != null)	{
			return new ResponseEntity<>(studentService.findAStudentsInClassByTeacher(teacherId, department), HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);
	}
	
	
	
	

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> createStudent(@Valid @RequestParam String firstName, @Valid @RequestParam String lastName,
			@Valid @RequestParam String username, @Valid @RequestParam String password, @Valid @RequestParam String department) {
		Student student = new Student();
		student.setFirstName(firstName);
		student.setPassword(password);
		student.setLastName(lastName);
		student.setUsername(username);
		student.setRole(new UserRole("ROLE_STUDENT", 3));
		student.setDepartment(department);
		if(student!=null) {
		studentRepository.save(student);}
		return new ResponseEntity<Student>(student, HttpStatus.OK);


	}
	@Secured("ROLE_ADMIN")
	@JsonView(Views.AdminView.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateStudent(@Valid @RequestBody Student changedStudent, @Valid @PathVariable Integer id) {
		Student student = studentRepository.findById(id).get();
			if(student != null) {
				student.setFirstName(changedStudent.getFirstName());
				student.setLastName(changedStudent.getLastName());
				student.setUsername(changedStudent.getUsername());
				student.setPassword(changedStudent.getPassword());
				studentRepository.save(student);
				return new ResponseEntity<Student>(student, HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@JsonView(Views.AdminView.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteStudent(@Valid @PathVariable Integer id) {
		Student student = studentRepository.findById(id).get();
		if (student!= null) {
			studentRepository.delete(student);
			return new ResponseEntity<Student>(student, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);

	}
}
