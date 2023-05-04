package com.springboot.management.service;

import com.springboot.management.dto.AssessmentDto;
import com.springboot.management.dto.StudentCourseAssessmentDto;
import com.springboot.management.dto.StudentCourseDto;
import com.springboot.management.entity.Assessment;
import com.springboot.management.entity.StudentCourse;
import com.springboot.management.entity.StudentCourseAssessment;
import com.springboot.management.mapper.AssessmentMapper;
import com.springboot.management.mapper.StudentCourseAssessmentMapper;
import com.springboot.management.mapper.StudentCourseMapper;
import com.springboot.management.repository.AssessmentRepository;
import com.springboot.management.repository.StudentCourseAssessmentRepository;
import com.springboot.management.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCourseAssessmentService {
    private final StudentCourseRepository studentCourseRepository;
    private final AssessmentRepository assessmentRepository;
    @Autowired
    private StudentCourseAssessmentMapper studentCourseAssessmentMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private AssessmentMapper assessmentMapper;
    private final StudentCourseAssessmentRepository studentCourseAssessmentRepository;

    public StudentCourseAssessmentService(StudentCourseRepository studentCourseRepository,
                                          AssessmentRepository assessmentRepository,
                                          StudentCourseAssessmentRepository studentCourseAssessmentRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.assessmentRepository = assessmentRepository;
        this.studentCourseAssessmentRepository = studentCourseAssessmentRepository;
    }

    public StudentCourseAssessmentDto saveStudentCourseAssessment(StudentCourseAssessmentDto studentCourseAssessmentDto){
        if(studentCourseAssessmentDto.getAssessmentDto()==null && studentCourseAssessmentDto.getStudentCourseDto()==null){
            throw new RuntimeException("Please provide Student and the Course which you want to assign the obtained marks");
        }else{
            int id=studentCourseAssessmentDto.getStudentCourseDto().getStudentCourseId();
            StudentCourse studentCourse=studentCourseRepository.findByIdNotDeleted(id);
            int id2=studentCourseAssessmentDto.getAssessmentDto().getAssessmentId();
            Assessment assessment=assessmentRepository.findByIdNotDeleted(id2);
            StudentCourseAssessment studentCourseAssessment=studentCourseAssessmentMapper.dtoToEntity(studentCourseAssessmentDto);
            if(studentCourse!=null && assessment!=null) {
                studentCourseAssessment.setStudentCourse(studentCourse);
                studentCourseAssessment.setAssessment(assessment);
                studentCourseAssessment = studentCourseAssessmentRepository.save(studentCourseAssessment);
                StudentCourseAssessmentDto studentCourseAssessmentDto1 = studentCourseAssessmentMapper.entityToDto(studentCourseAssessment);
                StudentCourseDto studentCourseDto = studentCourseMapper.entityToDto(studentCourse);
                AssessmentDto assessmentDto = assessmentMapper.entityToDto(assessment);
                studentCourseAssessmentDto1.setStudentCourseDto(studentCourseDto);
                studentCourseAssessmentDto1.setAssessmentDto(assessmentDto);
                return studentCourseAssessmentDto1;
            }else {
                throw new RuntimeException("Irrelevant Data");

            }
        }
    }

}
