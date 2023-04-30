package com.springboot.management.repository;

import com.springboot.management.entity.Course;
import com.springboot.management.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {

    List<Student> findByStudentName(String name);
  @Query("select a from Student a where (a.isDeleted =false or a.isDeleted is null) and a.studentId=:studentId")
  Student findByIdNotDeleted(@Param("studentId") Integer studentId );
  @Query("select a from Student a where a.isDeleted = false or a.isDeleted is null")
  List<Student> findAllNotDeleted();


}
