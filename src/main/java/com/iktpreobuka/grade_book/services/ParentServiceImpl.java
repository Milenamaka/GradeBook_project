package com.iktpreobuka.grade_book.services;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.grade_book.entities.DTO.ParentDTO;

import com.iktpreobuka.grade_book.repositories.ParentRepository;

@Service
public class ParentServiceImpl implements ParentService {

	
	@Autowired
	private ParentRepository parentRepository;

	
	@Override
	public List<ParentDTO> FindParentFromStudent(Integer student_id) {
		List<Tuple> listPr = parentRepository.findAllStByParent(student_id);
		List<ParentDTO> listParents = listPr.stream()
				.map(t->new ParentDTO(
						t.get(0,Integer.class),
						t.get(1,String.class),
						t.get(2,String.class),
						t.get(3,String.class)
						))
				.collect(Collectors.toList());
			
		return listParents;
	}
	
	

}
