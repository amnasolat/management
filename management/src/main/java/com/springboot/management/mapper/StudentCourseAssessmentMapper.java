package com.springboot.management.mapper;

import com.springboot.management.dto.StudentCourseAssessmentDto;
import com.springboot.management.entity.StudentCourseAssessment;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudentCourseAssessmentMapper {
    public StudentCourseAssessmentDto entityToDto(StudentCourseAssessment studentCourseAssessment){
        StudentCourseAssessmentDto studentCourseAssessmentDto=new StudentCourseAssessmentDto();
        studentCourseAssessmentDto.setStudentCourseAssessmentId(studentCourseAssessment.getStudentCourseAssessmentId());
        studentCourseAssessmentDto.setObtainedMarks(studentCourseAssessment.getObtainedMarks());
        return studentCourseAssessmentDto;
    }
    public StudentCourseAssessment dtoToEntity(StudentCourseAssessmentDto studentCourseAssessmentDto){
        StudentCourseAssessment studentCourseAssessment=new StudentCourseAssessment();
        studentCourseAssessment.setStudentCourseAssessmentId(studentCourseAssessmentDto.getStudentCourseAssessmentId());
        studentCourseAssessment.setObtainedMarks(studentCourseAssessmentDto.getObtainedMarks());
        return studentCourseAssessment;
    }
    public List<StudentCourseAssessmentDto> entityToDto(List<StudentCourseAssessment> studentCourseAssessments){
        return   studentCourseAssessments.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }
    public List<StudentCourseAssessment> dtoToEntity(List<StudentCourseAssessmentDto> studentCourseAssessmentDtos){
       return studentCourseAssessmentDtos.stream().map(x-> dtoToEntity(x)).collect(Collectors.toList());
    }
}
