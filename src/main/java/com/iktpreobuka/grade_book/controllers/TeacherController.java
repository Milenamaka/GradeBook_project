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
import com.iktpreobuka.grade_book.entities.UserRole;
import com.iktpreobuka.grade_book.entities.Subject;
import com.iktpreobuka.grade_book.entities.Teacher;
import com.iktpreobuka.grade_book.repositories.TeacherRepository;
import com.iktpreobuka.grade_book.security.Views;


@RestController
@RequestMapping(value = "/grade_book/teachers")
public class TeacherController {

	@Autowired
	private TeacherRepository teacherRepository;
	
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {

		if (teacherRepository.findAll() == null) {
			return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<>(((List<Teacher>) teacherRepository.findAll()), HttpStatus.OK);
	}
	
	@JsonView(Views.TeacherView.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> getById(@PathVariable Integer id) {
		Teacher teacher = teacherRepository.findById(id).get();
		if (teacher != null) {
			return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);
	}

	@JsonView(Views.AdminView.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> createTeacher(@Valid @RequestParam String firstName,@Valid @RequestParam String lastName,
			@Valid @RequestParam String username,@Valid @RequestParam String password, @Valid @RequestParam String phone,
			@Valid @RequestParam Subject subject) {
		Teacher teacher = new Teacher();
		teacher.setFirstName(firstName);
		teacher.setPassword(password);
		teacher.setLastName(lastName);
		teacher.setUsername(username);
		teacher.setPhone(phone);
		teacher.setRole(new UserRole("ROLE_TEACHER", 2));
		teacher.setSubject(subject);
		if (teacher != null) {
			teacherRepository.save(teacher);
		}
		return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
	
	}
	
	@JsonView(Views.AdminView.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateTeacher(@Valid @RequestBody Teacher changedTeacher,@Valid @PathVariable Integer id) {
		Teacher teacher = teacherRepository.findById(id).get();
		if (teacher != null) {
			teacher.setFirstName(changedTeacher.getFirstName());
			teacher.setLastName(changedTeacher.getLastName());
			teacher.setUsername(changedTeacher.getUsername());
			teacher.setPassword(changedTeacher.getPassword());
			teacher.setPhone(changedTeacher.getPhone());
			teacher.setRole(new UserRole("ROLE_TEACHER",2));
			teacherRepository.save(teacher);
			return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
	@JsonView(Views.AdminView.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteTeacher(@Valid @PathVariable Integer id) {
		Teacher teacher = teacherRepository.findById(id).get();
		if (teacher != null) {
			teacherRepository.delete(teacher);
			return new ResponseEntity<Teacher>(teacher, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}

}
