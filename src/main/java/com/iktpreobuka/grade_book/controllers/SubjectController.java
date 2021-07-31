package com.iktpreobuka.grade_book.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.iktpreobuka.grade_book.controllers.utils.RESTError;
import com.iktpreobuka.grade_book.entities.Subject;
import com.iktpreobuka.grade_book.repositories.SubjectRepository;
import com.iktpreobuka.grade_book.security.Views;

@RestController
@RequestMapping(value = "/grade_book/subjects")
public class SubjectController {

	@Autowired
	private SubjectRepository subjectRepository;
	
	@JsonView(Views.TeacherView.class)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Subject> getAll() {
		
		if( subjectRepository.findAll()== null){
			return null;
		};
		return (List<Subject>) subjectRepository.findAll();
	}
	
	@JsonView(Views.TeacherView.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public  ResponseEntity<?> getById(@Valid @PathVariable Integer id) {
		Subject subject = subjectRepository.findById(id).get();
		if (subject != null)	{
			return new ResponseEntity<Subject>(subject, HttpStatus.OK);
		}
		return new ResponseEntity<RESTError>(new RESTError (1, "User not found"), HttpStatus.NOT_FOUND);

	}
	

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public ResponseEntity<?> createSubject(@Valid @RequestParam String name,@Valid @RequestParam Integer classesPerWeek) {
		Subject subject = new Subject();
		subject.setName(name);
		subject.setClassesPerWeek(classesPerWeek);
		if(subject!=null) {
		subjectRepository.save(subject);}
		return new ResponseEntity<Subject>(subject, HttpStatus.OK);

	}
	
	@JsonView(Views.AdminView.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<?> updateSubject(@Valid @RequestBody Subject changedSubject,@Valid @PathVariable Integer id) {
		Subject subject = subjectRepository.findById(id).get();
			if(subject != null) {
				subject.setName(changedSubject.getName());
				subject.setClassesPerWeek(changedSubject.getClassesPerWeek());
				subjectRepository.save(subject);
				return new ResponseEntity<Subject>(subject, HttpStatus.OK);
			}
			return new ResponseEntity<>(null, HttpStatus.OK);

	}

	@JsonView(Views.AdminView.class)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteParent(@Valid @PathVariable Integer id) {
		Subject subject = subjectRepository.findById(id).get();
		if (subject!= null) {
			subjectRepository.delete(subject);
			return new ResponseEntity<>(subject, HttpStatus.OK);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);

	}
}
