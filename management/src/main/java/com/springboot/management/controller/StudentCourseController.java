package com.springboot.management.controller;

import com.springboot.management.dto.StudentCourseDto;
import com.springboot.management.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentCourseController {
    @Autowired
    private StudentCourseService studentCourseService;

    @PostMapping("/studentEnrollment")
    public StudentCourseDto studentEnrollmentInCourse(@RequestBody StudentCourseDto studentCourseDto){
        return studentCourseService.saveStudentCourse(studentCourseDto);
    }
    @PostMapping("/EnrollmentInMultipleCourses")
    public List<StudentCourseDto> studentEnrollmentInMultipleCourses(@RequestBody StudentCourseDto studentCourseDto){
        return studentCourseService.saveStudentCourses(studentCourseDto);
    }
}
