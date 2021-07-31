package com.iktpreobuka.grade_book.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Tuple;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iktpreobuka.grade_book.entities.DTO.GradesReportDTO;
import com.iktpreobuka.grade_book.repositories.GradeRepository;

@Service
public class GradeServiceImpl implements GradeService {

	@Autowired
	private GradeRepository gradeRepository;
	
	@Override
	public List<GradesReportDTO> GetGradesForStudent(Integer teacher_id, Integer user_id) {
		List<Tuple> listSt = gradeRepository.findAllGradesByTeacherIdStudentId(teacher_id, user_id);
		List<GradesReportDTO> listGrades = new ArrayList <GradesReportDTO>();
				listSt.stream().forEach((record) ->
				{listGrades.add (new GradesReportDTO(
						record.get(0,Integer.class),
						record.get(1,Integer.class),
						record.get(2,Integer.class),
						record.get(3,String.class),
						record.get(4,String.class),
						record.get(5,String.class),
						record.get(6,String.class)
						));});
			
		return listGrades;
	}

	@Override
	public List<GradesReportDTO> GetGradesForTeacher(Integer teacher_id) {
		List<Tuple> listSt = gradeRepository.findAllStGradesByTeacherId(teacher_id);
		List<GradesReportDTO> listGrades = new ArrayList <GradesReportDTO>();
				listSt.stream().forEach((record) ->
				{listGrades.add (new GradesReportDTO(
						record.get(0,Integer.class),
						record.get(1,Integer.class),
						record.get(2,Integer.class),
						record.get(3,String.class),
						record.get(4,String.class),
						record.get(5,String.class),
						record.get(6,String.class)
						));});
			
		return listGrades;
	}

	@Override
	public List<GradesReportDTO> GetGradesForParent(Integer parent_id) {
		List<Tuple> listSt = gradeRepository.findAllStByParentId(parent_id);
		List<GradesReportDTO> listGrades = new ArrayList <GradesReportDTO>();
				listSt.stream().forEach((record) ->
				{listGrades.add (new GradesReportDTO(
						record.get(0,Integer.class),
						record.get(1,Integer.class),
						record.get(2,Integer.class),
						record.get(3,String.class),
						record.get(4,String.class),
						record.get(5,String.class),
						record.get(6,String.class)
						));});
			
		return listGrades;
	}

}
