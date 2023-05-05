package com.springboot.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseAssessmentDto {
    private int studentCourseAssessmentId;
    private int obtainedMarks;
    StudentCourseDto studentCourseDto;
    AssessmentDto assessmentDto;
    List<Integer> assessmentId;
}
