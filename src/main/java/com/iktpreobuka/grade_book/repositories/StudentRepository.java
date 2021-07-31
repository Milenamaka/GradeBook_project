package com.iktpreobuka.grade_book.repositories;

import java.util.List;


import javax.persistence.Tuple;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;

import com.iktpreobuka.grade_book.entities.Student;
import com.iktpreobuka.grade_book.entities.DTO.StudentDTO;



public interface StudentRepository extends CrudRepository<Student, Integer>  {
	@Query(value ="SELECT s.user_id, s.first_name, s.last_name, s.department FROM student s inner join student_teacher st ON s.user_id = st.student_id WHERE st.student_id =?1 AND st.teacher_id =?2", nativeQuery = true)
	public List<Tuple> findAllByTeacherIdStudentId(Integer student_id, Integer teacher_id);
	
	@Query(value="SELECT st.student_id from student_teacher where student_teacher.teacher_id = :user_id", nativeQuery = true )
	public List<Integer> findAllTeacherStudents(Integer user_id);
	
	@Query(value ="SELECT s.user_id, s.first_name, s.last_name, s.department FROM student s WHERE s.user_id IN (SELECT st.student_id from student_teacher st where st.teacher_id = ?1) ORDER BY s.department",  nativeQuery = true)
	public List<Tuple> findAllStudentsByTeacher (Integer teacher_id);
	
	@Query(value ="SELECT s.user_id, s.first_name, s.last_name, s.department FROM student s WHERE s.user_id IN (SELECT st.student_id from student_teacher st where st.teacher_id = ?1) AND s.department= ?2",  nativeQuery = true)
	public List<Tuple> findAllStudentsInClassByTeacher (Integer teacher_id, String department);
	
	@Query(value ="SELECT s.user_id, s.first_name, s.last_name, s.department FROM student s inner join student_parent sp ON s.user_id = sp.student_id WHERE sp.parent_id =?1", nativeQuery = true)
	public List<Tuple> findAllStByParentId(Integer parent_id);
	
	@Query(value ="SELECT s.user_id, s.first_name, s.last_name, s.department FROM student s ", nativeQuery = true)
	public List<Tuple> findAllStud();
	
	//@Query("SELECT * FROM student s join student_teacher st WHERE s.user_id = st.student_id AND s.user_id= :user_id AND st.teacher_id LIKE :teacher_id")
	/*public List<Student> findAllByTeacherId(Integer teacherId);*/
//@Query("select sw from SkuWarehouse sw left join fetch Warehouse w where sw.warehouse = w.warehouseId AND w.warehouseId=:warehouseId AND sw.skuMaster=:skuMasterId")
//	SkuWarehouse findOneWithEagerRelationships(@Param("warehouseId") String warehouseId, @Param("skuMasterId") Long skuMasterId);	
}
