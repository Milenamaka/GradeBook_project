package com.iktpreobuka.grade_book.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iktpreobuka.grade_book.entities.DTO.ParentDTO;

@Service
public interface ParentService {

	public List<ParentDTO> FindParentFromStudent(Integer student_id);
	

}
