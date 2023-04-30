package com.springboot.management.mapper;

import com.springboot.management.dto.StudentCourseDto;
import com.springboot.management.entity.StudentCourse;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class StudentCourseMapper {
    public StudentCourseDto entityToDto(StudentCourse studentCourse){
        StudentCourseDto studentCourseDto=new StudentCourseDto();
        studentCourseDto.setStudentCourseId(studentCourse.getStudentCourseId());
        studentCourseDto.setGrade(studentCourse.getGrade());
        return studentCourseDto;
    }
    public StudentCourse dtoToEntity(StudentCourseDto studentCourseDto){
        StudentCourse studentCourse=new StudentCourse();
        studentCourse.setStudentCourseId(studentCourseDto.getStudentCourseId());
        studentCourse.setGrade(studentCourseDto.getGrade());
        return studentCourse;
    }
    public List<StudentCourseDto> entityToDto(List<StudentCourse> studentCourses ){
        return studentCourses.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }
    public List<StudentCourse> dtoToEntity(List<StudentCourseDto> studentCourseDtos){
        return studentCourseDtos.stream().map(x-> dtoToEntity(x)).collect(Collectors.toList());
    }
}
