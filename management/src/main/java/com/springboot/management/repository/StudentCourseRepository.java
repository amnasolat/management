package com.springboot.management.repository;

import com.springboot.management.entity.Department;
import com.springboot.management.entity.StudentCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseRepository extends JpaRepository<StudentCourse,Integer> {
    @Query("select a from StudentCourse a where a.isDeleted = false or a.isDeleted is null")
    List<StudentCourse> findAllNotDeleted();
    @Query("select a from StudentCourse a where (a.isDeleted =false or a.isDeleted is null) and a.studentCourseId = :studentCourseId")
    StudentCourse findByIdNotDeleted(@Param("studentCourseId") Integer studentCourseId);
}
