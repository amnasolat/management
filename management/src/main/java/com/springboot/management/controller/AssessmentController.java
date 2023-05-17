package com.springboot.management.controller;

import com.springboot.management.dto.AssessmentDto;
import com.springboot.management.dto.CourseDto;
import com.springboot.management.service.AssessmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AssessmentController {
    @Autowired
    private AssessmentService assessmentService;
    @PostMapping("/addAssessment")
    public AssessmentDto addAssessment(@RequestBody AssessmentDto assessmentDto){
        return assessmentService.saveAssessment(assessmentDto);
    }
    @PostMapping("/addAssessments")
    public List<AssessmentDto> addAssessments(@RequestBody List<AssessmentDto> assessmentDtos){
        return assessmentService.saveAssessments(assessmentDtos);
    }
    @GetMapping("/assessments")
    public List<AssessmentDto> findAllAssessments(){
        return assessmentService.getAssessments();
    }
    @PostMapping("/assessment")
    public AssessmentDto findAssessmentById(@RequestBody AssessmentDto assessmentDto){
        return assessmentService.getAssessmentById(assessmentDto);
    }
    @PostMapping("/assessmentName")
    public List<AssessmentDto> findAssessmentByName(@RequestBody AssessmentDto assessmentDto){
        return assessmentService.getAssessmentByName(assessmentDto);
    }
    @PostMapping("/deleteAssessment")
    public AssessmentDto deleteAssessment(@RequestBody AssessmentDto assessmentDto){
        return assessmentService.deleteAssessment(assessmentDto);
    }
    @PostMapping("/updateAssessment")
    public AssessmentDto updateAssessment(@RequestBody AssessmentDto assessmentDto){
        return assessmentService.updateAssessment(assessmentDto);
    }
    @PostMapping("/assignAllAssessments")
    public List<AssessmentDto> assignAllCoursesAssessments(){
        return assessmentService.saveAllCoursesAssessments();
    }

}