package com.springboot.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;


@Getter
@Setter
//@ToString(exclude = {"studentCourseDto","assessmentDto"})

@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentCourseAssessmentDto {
    private  Integer studentCourseAssessmentId;
    private Integer obtainedMarks;
    StudentCourseDto studentCourseDto;
    AssessmentDto assessmentDto;
    List<Integer> assessmentId;
    private Integer studentId;
    private Integer courseId;
}
