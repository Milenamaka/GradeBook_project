package com.iktpreobuka.grade_book.services;

import java.util.List;


import org.springframework.stereotype.Service;

import com.iktpreobuka.grade_book.entities.DTO.GradesReportDTO;

@Service
public interface GradeService {
	public List<GradesReportDTO> GetGradesForStudent (Integer teacher_id, Integer user_id);
	
	public List<GradesReportDTO> GetGradesForTeacher (Integer teacher_id);
	
	public List<GradesReportDTO> GetGradesForParent (Integer parent_id);

}
