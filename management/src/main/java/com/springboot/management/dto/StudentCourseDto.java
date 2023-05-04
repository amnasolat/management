package com.springboot.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentCourseDto {
    private int studentCourseId;
    private String grade;
    StudentDto studentDto;
    CourseDto courseDto;
    List<Integer> courseId;
}
