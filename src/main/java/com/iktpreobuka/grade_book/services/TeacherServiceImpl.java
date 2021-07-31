package com.iktpreobuka.grade_book.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.grade_book.entities.Subject;
import com.iktpreobuka.grade_book.entities.DTO.TeacherDTO;
import com.iktpreobuka.grade_book.repositories.TeacherRepository;

@Service
public class TeacherServiceImpl implements TeacherService {

		@Autowired TeacherRepository teacherRepository;
	@Override
	public List<TeacherDTO> GetTeacherById(Integer teacher_id) {
		List<Tuple> listSu = teacherRepository.findTeacherById(teacher_id);
		List<TeacherDTO> listTeachers = listSu.stream()
				.map(t->new TeacherDTO(
						t.get(0,Integer.class),
						t.get(1,String.class),
						t.get(2,String.class),
						t.get(3,String.class),
						t.get(4,Subject.class)
						))
				.collect(Collectors.toList());
			
		return listTeachers;
	}

}
