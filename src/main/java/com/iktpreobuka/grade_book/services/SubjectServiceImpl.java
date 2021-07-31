package com.iktpreobuka.grade_book.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.grade_book.entities.Subject;
import com.iktpreobuka.grade_book.entities.DTO.StudentDTO;
import com.iktpreobuka.grade_book.entities.DTO.SubjectDTO;
import com.iktpreobuka.grade_book.repositories.StudentRepository;
import com.iktpreobuka.grade_book.repositories.SubjectRepository;

@Service
public class SubjectServiceImpl implements SubjectService {
	

	@Autowired
	private SubjectRepository subjectRepository;


	@Autowired
	private StudentRepository studentRepository;

/*	@Override
	public SubjectDTO FindSubjectByTeachersId(Integer Id) {
		List<Tuple> listSu = subjectRepository.findSubjectByTeacherId(teacher_id);
		List<SubjectDTO> listStudents = listSu.stream()
				.map(t->new SubjectDTO(
						t.get(0,Integer.class),
						t.get(1,String.class),
						t.get(2,String.class)
						))
				.collect(Collectors.toList());
			
		return SubjectDTO;
	}*/

}
