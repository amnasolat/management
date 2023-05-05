package com.springboot.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentCourseDto {
    private int studentCourseId;
    private String grade;
    StudentDto studentDto;
    CourseDto courseDto;
    List<Integer> courseId;
}
