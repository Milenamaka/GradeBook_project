package com.iktpreobuka.grade_book.repositories;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.iktpreobuka.grade_book.entities.Grade;



public interface GradeRepository extends CrudRepository<Grade, Integer> {
	
	public List<Grade> findAllByStudentId(Integer student_Id);
	
	@Query(value ="SELECT g.grade_val, g.semestar, s.user_id, s.first_name, s.last_name, sub.name, s.department FROM student s left outer join grade g ON s.user_id = g.student left outer join subject sub ON sub.subject_id = g.subject WHERE s.user_id =?1 AND s.user_id IN (SELECT st.student_id from student_teacher st WHERE st.teacher_id =?2)ORDER BY s.user_id ", nativeQuery = true)
	public List<Tuple> findAllGradesByTeacherIdStudentId(Integer student_id, Integer teacher_id);
	
	@Query(value ="SELECT g.grade_val, g.semestar, s.user_id, s.first_name, s.last_name, sub.name, s.department FROM student s left outer join grade g ON s.user_id = g.student left outer join subject sub ON sub.subject_id = g.subject WHERE s.user_id IN (SELECT st.student_id from student_teacher st WHERE st.teacher_id =?1)ORDER BY s.user_id ", nativeQuery = true)
	public List<Tuple> findAllStGradesByTeacherId(Integer teacher_id);
	
	
	@Query(value ="SELECT g.grade_val, g.semestar, s.user_id, s.first_name, s.last_name, sub.name, s.department FROM student s left outer join grade g ON s.user_id = g.student left outer join subject sub ON sub.subject_id = g.subject WHERE s.user_id IN (SELECT sp.student_id from student_parent sp WHERE sp.parent_id =?1)ORDER BY s.user_id ", nativeQuery = true)
	public List<Tuple> findAllStByParentId(Integer parent_id);
}
