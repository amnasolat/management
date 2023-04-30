package com.springboot.management.mapper;

import com.springboot.management.dto.CourseDto;
import com.springboot.management.entity.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourseMapper {
    public CourseDto entityToDto(Course course){
        CourseDto courseDto=new CourseDto();
        courseDto.setCourseId(course.getCourseId());
        courseDto.setCourseName(course.getCourseName());
        return courseDto;
    }
    public Course dtoToEntity(CourseDto courseDto){
        Course course=new Course();
        course.setCourseId(courseDto.getCourseId());
        course.setCourseName(courseDto.getCourseName());

        return course;
    }
    public List<CourseDto> entityToDto(List<Course> courses){
        return courses.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }
    public List<Course> dtoToEntity(List<CourseDto> courseDtos){
        return courseDtos.stream().map(x-> dtoToEntity(x)).collect(Collectors.toList());
    }
}
