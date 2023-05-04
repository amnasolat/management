package com.springboot.management.controller;

import com.springboot.management.dto.StudentCourseAssessmentDto;
import com.springboot.management.entity.StudentCourseAssessment;
import com.springboot.management.service.StudentCourseAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentCourseAssessmentController {
    @Autowired
    private StudentCourseAssessmentService studentCourseAssessmentService;
    @PostMapping("/studentMarks")
    public StudentCourseAssessmentDto StudentAssessmentCourseMarks(@RequestBody StudentCourseAssessmentDto studentCourseAssessmentDto){
        return studentCourseAssessmentService.saveStudentCourseAssessment(studentCourseAssessmentDto);
    }
}
