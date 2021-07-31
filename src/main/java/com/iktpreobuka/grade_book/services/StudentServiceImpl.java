package com.iktpreobuka.grade_book.services;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iktpreobuka.grade_book.entities.DTO.StudentDTO;
import com.iktpreobuka.grade_book.repositories.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public List<StudentDTO> findAllByTeacherStudentId(Integer student_id, Integer teacher_id) {
		
		List<Tuple> listSt = studentRepository.findAllByTeacherIdStudentId(student_id, teacher_id);
		List<StudentDTO> listStudents = listSt.stream()
				.map(t->new StudentDTO(
						t.get(0,Integer.class),
						t.get(1,String.class),
						t.get(2,String.class),
						t.get(3,String.class)
						))
				.collect(Collectors.toList());
			
		return listStudents;
	}

	@Override
	public List<StudentDTO> findAllByTeacherStudents(Integer teacher_id) {
		List<Tuple> listSt = studentRepository.findAllStudentsByTeacher(teacher_id);
		List<StudentDTO> listStudents = new ArrayList <StudentDTO>();
				listSt.stream().forEach((record) ->
				{listStudents.add (new StudentDTO(
						record.get(0,Integer.class),
						record.get(1,String.class),
						record.get(2,String.class),
						record.get(3,String.class)
						));});
			
		return listStudents;
	}

	@Override
	public List<StudentDTO> findAllStudentsByParent(Integer parent_id) {
	List<Tuple> listSt = studentRepository.findAllStByParentId(parent_id);
	List<StudentDTO> listStudents = new ArrayList <StudentDTO>();
				listSt.stream().forEach((record) ->
				{listStudents.add (new StudentDTO(
						record.get(0,Integer.class),
						record.get(1,String.class),
						record.get(2,String.class),
						record.get(3,String.class)
						));});
			
		return listStudents;
	}

	@Override
	public List<StudentDTO> findAStudentsInClassByTeacher(Integer teacher_id, String department) {
		
		List<Tuple> listSt = studentRepository.findAllStudentsInClassByTeacher(teacher_id, department);
		List<StudentDTO> listStudents = new ArrayList <StudentDTO>();
		listSt.stream().forEach((record) ->
		{listStudents.add (new StudentDTO(
				record.get(0,Integer.class),
				record.get(1,String.class),
				record.get(2,String.class),
				record.get(3,String.class)
				));});
	
return listStudents;
	}

	@Override
	public List<StudentDTO> findAllStudents() {
		List<Tuple> listSt = studentRepository.findAllStud();
		List<StudentDTO> listStudents = new ArrayList <StudentDTO>();
		listSt.stream().forEach((record) ->
		{listStudents.add (new StudentDTO(
				record.get(0,Integer.class),
				record.get(1,String.class),
				record.get(2,String.class),
				record.get(3,String.class)
				));});
	
return listStudents;
	}
	


}