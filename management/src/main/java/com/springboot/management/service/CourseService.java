package com.springboot.management.service;

import com.springboot.management.dto.CourseDto;
import com.springboot.management.dto.DepartmentDto;
import com.springboot.management.entity.Course;
import com.springboot.management.entity.Department;
import com.springboot.management.mapper.CourseMapper;
import com.springboot.management.repository.CourseRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CourseMapper courseMapper;
    public CourseDto saveCourse(CourseDto courseDto){
        Course course=courseMapper.dtoToEntity(courseDto);
        course=courseRepository.save(course);
        return courseMapper.entityToDto(course);
    }
    public List<CourseDto> saveCourses(List<CourseDto> courseDtos){
        List<Course> courses=courseMapper.dtoToEntity(courseDtos);
        courses=courseRepository.saveAll(courses);
        return courseMapper.entityToDto(courses);
    }
    public List<CourseDto> getCourses(){
        List<CourseDto> courseDtos=new ArrayList<>();
        List<Course> courseList=courseRepository.findAllNotDeleted();
        for(Course course:courseList){
         CourseDto courseDto=  courseMapper.entityToDto(course);
         courseDtos.add(courseDto);
        }
        return courseDtos;
    }
    public CourseDto getCourseById(CourseDto courseDto){
        int id=courseDto.getCourseId();
        Course course=courseRepository.findByIdNotDeleted(id);
        return courseMapper.entityToDto(course);
    }
    public List<CourseDto> getCourseByName(CourseDto courseDto){
        String name=courseDto.getCourseName();
        List<Course> courses=courseRepository.findByCourseName(name);
        return courseMapper.entityToDto(courses);
    }
    public CourseDto deleteCourse(CourseDto courseDto){
       if(courseDto!=null&& courseDto.getCourseId()>0){
           int id=courseDto.getCourseId();
           Course course=courseRepository.findByIdNotDeleted(id);
           if(course!=null){
               course.setDeleted(true);
               courseRepository.save(course);
               courseDto.setDeleteStatus("Record Deleted");
           }else{
               courseDto.setDeleteStatus("Record not Found");
           }
       }else {
           courseDto=new CourseDto();
           courseDto.setDeleteStatus("Irrelevant Record");

       }
       return courseDto;
    }
  public CourseDto updateCourse(CourseDto courseDto){
        Course existingCourse=courseRepository.findByIdNotDeleted(courseDto.getCourseId());
        existingCourse.setCourseName(courseDto.getCourseName());
        courseRepository.save(existingCourse);
        return courseMapper.entityToDto(existingCourse);
  }

}
