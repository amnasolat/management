package com.springboot.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseAssessmentDto {
    private int studentCourseAssessmentId;
    private int obtainedMarks;
}
