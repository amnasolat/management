package com.springboot.management.service;

import com.springboot.management.dto.AssessmentDto;
import com.springboot.management.dto.CourseDto;
import com.springboot.management.dto.DepartmentDto;
import com.springboot.management.entity.Assessment;
import com.springboot.management.entity.Course;
import com.springboot.management.entity.Department;
import com.springboot.management.mapper.AssessmentMapper;
import com.springboot.management.mapper.CourseMapper;
import com.springboot.management.mapper.DepartmentMapper;
import com.springboot.management.repository.AssessmentRepository;
import com.springboot.management.repository.CourseRepository;
import com.springboot.management.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AssessmentService {
    @Autowired
    private AssessmentRepository assessmentRepository;
    @Autowired
    private AssessmentMapper assessmentMapper;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentMapper departmentMapper;

    public AssessmentDto saveAssessment(AssessmentDto assessmentDto) {
        if (assessmentDto.getCourseDto() != null && assessmentDto.getCourseDto().getCourseId() > 0) {
            Course course = courseRepository.findByIdNotDeleted(assessmentDto.getCourseDto().getCourseId());
            int id=course.getDepartment().getDepartmentId();
            Department department=departmentRepository.findByIdNotDeleted(id);
            Assessment assessment = assessmentMapper.dtoToEntity(assessmentDto);
            course.setDepartment(department);
            assessment.setCourse(course);

            assessment = assessmentRepository.save(assessment);
            AssessmentDto assessmentDto1 = assessmentMapper.entityToDto(assessment);
            CourseDto courseDto = courseMapper.entityToDto(course);
            DepartmentDto departmentDto=departmentMapper.entityToDto(department);
            courseDto.setDepartmentDto(departmentDto);
            assessmentDto1.setCourseDto(courseDto);
            return assessmentDto1;
        }else {
           AssessmentDto assessmentDto1=new AssessmentDto();
           return assessmentDto1;

        }
    }
    public List<AssessmentDto> saveAssessments(List<AssessmentDto> assessmentDtos){
        List<Assessment> assessments=assessmentMapper.dtoToEntity(assessmentDtos);
        assessments=assessmentRepository.saveAll(assessments);
        return assessmentMapper.entityToDto(assessments);
    }
   public List<AssessmentDto> getAssessments(){
        List<AssessmentDto> assessmentDtos=new ArrayList<>();
        List<Assessment> assessmentList=assessmentRepository.findAllNotDeleted();
        for(Assessment assessment: assessmentList){
            AssessmentDto assessmentDto=assessmentMapper.entityToDto(assessment);
            assessmentDtos.add(assessmentDto);
        }
        return assessmentDtos;
   }
   public AssessmentDto getAssessmentById(AssessmentDto assessmentDto){
        int id=assessmentDto.getAssessmentId();
        Assessment assessment=assessmentRepository.findByIdNotDeleted(id);
        return assessmentMapper.entityToDto(assessment);
   }
   public List<AssessmentDto> getAssessmentByName(AssessmentDto assessmentDto){
        String name=assessmentDto.getAssessmentName();
        List<Assessment> assessments=assessmentRepository.findByAssessmentName(name);
        return assessmentMapper.entityToDto(assessments);
   }
   public AssessmentDto deleteAssessment(AssessmentDto assessmentDto){
        if(assessmentDto!=null && assessmentDto.getAssessmentId()>0){
            int id=assessmentDto.getAssessmentId();
            Assessment assessment=assessmentRepository.findByIdNotDeleted(id);
            if(assessment!=null){
                assessment.setDeleted(true);
                assessmentRepository.save(assessment);
                assessmentDto.setDeleteStatus("Record Deleted");
            }else{
                assessmentDto.setDeleteStatus("Record Not Found");
            }
        }else {
            assessmentDto=new AssessmentDto();
            assessmentDto.setDeleteStatus("Data Irrelevant");
        }
        return assessmentDto;
   }

   public AssessmentDto updateAssessment(AssessmentDto assessmentDto) {
       Assessment existingAssessment = assessmentRepository.findByIdNotDeleted(assessmentDto.getAssessmentId());
       existingAssessment.setAssessmentName(assessmentDto.getAssessmentName());
       existingAssessment.setTotalMarks(assessmentDto.getTotalMarks());
       assessmentRepository.save(existingAssessment);
       return assessmentMapper.entityToDto(existingAssessment);

   }
   public List<AssessmentDto> saveAllCoursesAssessments(){
        List<AssessmentDto> assessmentDtoList=new ArrayList<>();
        List<Course> courseList=courseRepository.findAllNotDeleted();
       Map<String, Integer> assessmentType = new LinkedHashMap<>();
       assessmentType.put("Quiz 1", 10);
       assessmentType.put("Quiz 2",10);
       assessmentType.put("Quiz 3",10);
       assessmentType.put("Quiz 4",10);
       assessmentType.put("Assignment 1",10);
       assessmentType.put("Assignment 2",10);
       assessmentType.put("Assignment 3",10);
       assessmentType.put("Mid-Term Exam",25);
       assessmentType.put("Final Exam",50);
        for(Course course:courseList){
//            Integer corId=course.getCourseId();
            for(Map.Entry<String,Integer> entry: assessmentType.entrySet()){
                String assessmentName = entry.getKey();
                int totalMarks = entry.getValue();
                Assessment assessment=new Assessment();
                assessment.setAssessmentName(assessmentName);
                assessment.setTotalMarks(totalMarks);
                assessment.setCourse(course);
                assessment=assessmentRepository.save(assessment);
                CourseDto courseDto=courseMapper.entityToDto(course);
                AssessmentDto assessmentDto=assessmentMapper.entityToDto(assessment);
                assessmentDto.setCourseDto(courseDto);
                assessmentDtoList.add(assessmentDto);
            }


        }
        return assessmentDtoList;
   }


}
