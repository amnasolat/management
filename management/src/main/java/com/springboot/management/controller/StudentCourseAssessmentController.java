package com.springboot.management.controller;

import com.springboot.management.dto.ReportDto;
import com.springboot.management.dto.StudentCourseAssessmentDto;
import com.springboot.management.entity.StudentCourseAssessment;
import com.springboot.management.service.StudentCourseAssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/updateMarks")
    public StudentCourseAssessmentDto updateAssessmentMarks(@RequestBody StudentCourseAssessmentDto studentCourseAssessmentDto){
        return studentCourseAssessmentService.updateObtainedMarks(studentCourseAssessmentDto);
    }
    @PostMapping("/fetch")
    public List<ReportDto> fetchData(@RequestBody ReportDto reportDto){
        return  studentCourseAssessmentService.getAllData(reportDto);
    }
    @PostMapping("/assignAllStudentsAssessments")
    public List<StudentCourseAssessmentDto> assignAllStudentsMultipleAssessments(){
        return studentCourseAssessmentService.saveAllStudentCoursesAssessments();
    }
}
