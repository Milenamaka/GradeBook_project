package com.iktpreobuka.grade_book.controllers;

import java.util.HashSet;

import java.util.List;
import java.util.Set;

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
import com.iktpreobuka.grade_book.entities.DTO.StudentDTO;
import com.iktpreobuka.grade_book.entities.Parent;
import com.iktpreobuka.grade_book.entities.Student;
import com.iktpreobuka.grade_book.repositories.ParentRepository;
import com.iktpreobuka.grade_book.repositories.StudentRepository;
import com.iktpreobuka.grade_book.security.Views;
import com.iktpreobuka.grade_book.services.ParentService;
import com.iktpreobuka.grade_book.services.StudentService;

@RestController
@RequestMapping(value = "/grade_book/parents")
public class ParentController {
	
	@Autowired
	private ParentRepository parentRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private ParentService parentService;
	
	@Autowired
	private StudentService studentService;
	
	//radi
	@Secured("ROLE_ADMIN")
	@JsonView(Views.TeacherView.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ResponseEntity<?> getAll() {
		
		if( parentRepository.findAll()== null){
			return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);}
	
		return new ResponseEntity<>((List<Parent>) parentRepository.findAll(), HttpStatus.OK);
	}
	//ne radi
	@Secured("ROLE_TEACHER")
	@RequestMapping(value = "/myStudent/{teache_id}/{parent_id}", method = RequestMethod.GET)
	public  ResponseEntity<?> getById(@PathVariable Integer teache_id,@PathVariable Integer parent_id) {
		List<Integer> listSt = studentRepository.findAllTeacherStudents(teache_id);
		List<Integer> list = parentRepository.findStudentByParentId(parent_id);
		
		 Parent parent = parentRepository.findById(parent_id).get();
		if (parent != null)	{
			if(listSt.contains(list)) { 
				return new ResponseEntity<>(parent, HttpStatus.OK);}	
			return new ResponseEntity<RESTError>(new RESTError (1, "Parent is not Parent from your Student"), HttpStatus.OK);}
		return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);
	}
	//radi
	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/student/{student_id}", method = RequestMethod.GET)
	public  ResponseEntity<?> GetParentFromStudentId(@PathVariable Integer student_id) {
		if (parentService.FindParentFromStudent(student_id) != null)	{
			return new ResponseEntity<> (parentService.FindParentFromStudent(student_id), HttpStatus.OK);}
			return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);
		
	}
	
	@JsonView(Views.AdminView.class)
	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> createParent(@Valid @RequestParam String firstName,@Valid @RequestParam String lastName,
			@Valid @RequestParam String username, @Valid @RequestParam String email, @Valid @RequestParam String password, @Valid @RequestParam Student student) {
		Parent parent = new Parent();
		Set<Student> students = new HashSet<Student> ();
		students.add(student);
		parent.setEmail(email);
		parent.setFirstName(firstName);
		parent.setPassword(password);
		parent.setLastName(lastName);
		parent.setUsername(username);
		parent.setStudents(students);
		parent.setRole(new UserRole("ROLE_PARENT", 4));
		if(parent!=null) {
		parentRepository.save(parent);}
		return new ResponseEntity<Parent>(parent, HttpStatus.OK);


	}
	
	@JsonView(Views.AdminView.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateParent(@Valid @RequestBody Parent changedParent, @Valid @PathVariable Integer id) {
		Parent parent = parentRepository.findById(id).get();
			if(parent != null) {
				parent.setFirstName(changedParent.getFirstName());
				parent.setLastName(changedParent.getLastName());
				parent.setUsername(changedParent.getUsername());
				parent.setPassword(changedParent.getPassword());
				parent.setEmail(changedParent.getEmail());
				parentRepository.save(parent);
				return new ResponseEntity<Parent>(parent, HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.OK);
	}

	@JsonView(Views.AdminView.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteParent(@Valid @PathVariable Integer id) {
		Parent parent = parentRepository.findById(id).get();
		if (parent!= null) {
			parentRepository.delete(parent);
			return new ResponseEntity<Parent>(parent, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);

	}
}
