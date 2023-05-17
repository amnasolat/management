package com.springboot.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.management.entity.Course;
import com.springboot.management.entity.Department;
import com.springboot.management.entity.Student;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ReportDto {
    List<Integer> courseIdList;
    private Integer courseId;
    private String courseName;
    List<Integer> studentIdList;
    private Integer studentId;
    private String studentName;
    List<Integer> departmentIdList;
    private String departmentName;
    private String semesterName;
    private String batchName;
    private String AssessmentName;
    private Integer totalMarks;
    private Integer obtainedMarks;
    private String grade;
}
