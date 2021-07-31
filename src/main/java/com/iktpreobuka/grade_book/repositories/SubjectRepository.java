package com.iktpreobuka.grade_book.repositories;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import com.iktpreobuka.grade_book.entities.Subject;

public interface SubjectRepository extends CrudRepository<Subject, Integer> {
	
	
}
