package com.iktpreobuka.grade_book.repositories;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.grade_book.entities.Parent;

public interface ParentRepository extends CrudRepository<Parent, Integer> {
	
	@Query(value ="SELECT p.user_id, p.first_name, p.last_name, p.email FROM parent p inner join student_parent sp ON p.user_id = sp.parent_id WHERE sp.student_id = ?1", nativeQuery = true)
	public List<Tuple> findAllStByParent(Integer student_id);
	
	@Query(value ="SELECT sp.student_id FROM student_parent sp WHERE sp.parent_id = ?1", nativeQuery = true)
	public List<Integer> findStudentByParentId(Integer parent_id);

}
