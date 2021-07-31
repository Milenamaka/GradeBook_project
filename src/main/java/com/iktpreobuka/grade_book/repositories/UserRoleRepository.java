package com.iktpreobuka.grade_book.repositories;

import java.util.List;

import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.iktpreobuka.grade_book.entities.UserRole;

public interface UserRoleRepository extends CrudRepository<UserRole, Integer> {
	
	@Query(value="SELECT r.id, r.name from user_role where r_id = :?1", nativeQuery = true )
	public List<Tuple> findRoleById(Integer role_id);
	
}
