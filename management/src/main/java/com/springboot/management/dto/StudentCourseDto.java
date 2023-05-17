package com.springboot.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentCourseDto {
    private int studentCourseId;
    private String grade;
    StudentDto studentDto;
    CourseDto courseDto;
    List<Integer> courseId;
    SemesterDto semesterDto;
}
