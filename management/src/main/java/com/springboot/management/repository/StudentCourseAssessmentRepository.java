package com.springboot.management.repository;

import com.springboot.management.entity.Course;
import com.springboot.management.entity.StudentCourseAssessment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentCourseAssessmentRepository extends JpaRepository<StudentCourseAssessment,Integer> {
    @Query("select a from StudentCourseAssessment a where (a.isDeleted =false or a.isDeleted is null) and a.studentCourseAssessmentId=:studentCourseAssessmentId")
    StudentCourseAssessment findByIdNotDeleted(@Param("studentCourseAssessmentId") Integer studentCourseAssessmentId);
    @Query("select a from Course a where a.isDeleted=false or a.isDeleted is null ")
    List<StudentCourseAssessment> findAllNotDeleted();
}
