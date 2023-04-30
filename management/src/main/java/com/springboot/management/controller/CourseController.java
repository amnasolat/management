package com.springboot.management.controller;

import com.springboot.management.dto.CourseDto;
import com.springboot.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;
    @PostMapping("/addCourse")
    public CourseDto addCourse(@RequestBody CourseDto courseDto){
       return courseService.saveCourse(courseDto);
    }
    @PostMapping("/addCourses")
    public List<CourseDto> addCourses(@RequestBody List<CourseDto> courseDtos){
        return courseService.saveCourses(courseDtos);
    }
    @GetMapping("/courses")
    public List<CourseDto> findAllCourses(){
        return courseService.getCourses();
    }
    @PostMapping("/course")
    public CourseDto findCourseById(@RequestBody CourseDto courseDto){
        return courseService.getCourseById(courseDto);
    }
    @PostMapping("/courseName")
    public List<CourseDto> findCourseByName(@RequestBody CourseDto courseDto){
        return courseService.getCourseByName(courseDto);
    }
    @PostMapping("/deleteCourse")
    public CourseDto deleteCourse(@RequestBody CourseDto courseDto){
        return courseService.deleteCourse(courseDto);
    }
    @PostMapping("/updateCourse")
    public CourseDto updateCourse(@RequestBody CourseDto courseDto){
        return courseService.updateCourse(courseDto);
    }

}
