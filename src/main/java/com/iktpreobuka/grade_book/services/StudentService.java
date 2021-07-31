package com.iktpreobuka.grade_book.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.iktpreobuka.grade_book.entities.DTO.StudentDTO;

@Service
public interface StudentService {
	public List<StudentDTO> findAllByTeacherStudentId(Integer student_id, Integer teacher_id );
	
	public List<StudentDTO> findAllByTeacherStudents(Integer teacher_id );
	
	public List<StudentDTO> findAStudentsInClassByTeacher(Integer teacher_id, String department );
	
	public List<StudentDTO> findAllStudentsByParent(Integer parent_id );
	
	public List<StudentDTO> findAllStudents();
	
}
