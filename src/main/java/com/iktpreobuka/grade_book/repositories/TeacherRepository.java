package com.iktpreobuka.grade_book.repositories;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.grade_book.entities.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {
	@Query(value="SELECT t.subject_id from teacher t where t.user_id = ?1", nativeQuery = true )
	public List<Tuple> findSubjectIdByTeacherId(Integer teacher_id);
	
	@Query(value="SELECT t.user_id, t.phone, t.first_name, t.last_name, t.subject from teacher t where t.user_id = ?1", nativeQuery = true )
	public List<Tuple> findTeacherById(Integer teacher_id);
}
