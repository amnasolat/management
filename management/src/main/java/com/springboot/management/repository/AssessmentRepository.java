package com.springboot.management.repository;

import com.springboot.management.entity.Assessment;
import com.springboot.management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssessmentRepository extends JpaRepository<Assessment,Integer> {
    @Query("select a from Assessment a where (a.isDeleted =false or a.isDeleted is null) and a.assessmentId=:assessmentId")
    Assessment findByIdNotDeleted(@Param("assessmentId") Integer assessmentId);
    @Query("select a from Assessment a where a.isDeleted=false or a.isDeleted is null ")
    List<Assessment> findAllNotDeleted();
    List<Assessment> findByAssessmentName(String name);
}
