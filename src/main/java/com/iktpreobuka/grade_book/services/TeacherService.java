package com.iktpreobuka.grade_book.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.iktpreobuka.grade_book.entities.DTO.TeacherDTO;

@Service
public interface TeacherService {
	public List<TeacherDTO> GetTeacherById(Integer teacher_id);

}
