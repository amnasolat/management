package com.springboot.management.controller;

import com.springboot.management.dto.StudentCourseAssessmentDto;
import com.springboot.management.entity.StudentCourseAssessment;
import com.springboot.management.service.StudentCourseAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentCourseAssessmentController {
    @Autowired
    private StudentCourseAssessmentService studentCourseAssessmentService;
    @PostMapping("/assignAssessment")
    public StudentCourseAssessmentDto assignStudentAssessment(@RequestBody StudentCourseAssessmentDto studentCourseAssessmentDto){
        return studentCourseAssessmentService.saveStudentCourseAssessment(studentCourseAssessmentDto);
    }
    @PostMapping("/assignMultipleAssessment")
    public List<StudentCourseAssessmentDto>  assignStudentMultipleAssessments(@RequestBody StudentCourseAssessmentDto studentCourseAssessmentDto){
        return studentCourseAssessmentService.saveStudentCourseAssessments(studentCourseAssessmentDto);

    }
}
