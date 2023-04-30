package com.springboot.management.repository;

import com.springboot.management.dto.CourseDto;
import com.springboot.management.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findByCourseName(String name);
    @Query("select a from Course a where (a.isDeleted =false or a.isDeleted is null) and a.courseId=:courseId")
    Course findByIdNotDeleted(@Param("courseId") Integer courseId);
    @Query("select a from Course a where a.isDeleted=false or a.isDeleted is null ")
    List<Course> findAllNotDeleted();
}
