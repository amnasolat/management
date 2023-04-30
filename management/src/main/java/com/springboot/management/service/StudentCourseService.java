package com.springboot.management.service;

import com.springboot.management.dto.DepartmentDto;
import com.springboot.management.dto.StudentCourseDto;
import com.springboot.management.entity.Department;
import com.springboot.management.entity.StudentCourse;
import com.springboot.management.mapper.StudentCourseMapper;
import com.springboot.management.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCourseService {
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    public StudentCourseDto saveStudentCourse(StudentCourseDto studentCourseDto) {
        StudentCourse studentCourse=studentCourseMapper.dtoToEntity(studentCourseDto);
        studentCourse=studentCourseRepository.save(studentCourse);
        return studentCourseMapper.entityToDto(studentCourse);
    }

    public List<StudentCourseDto> saveStudentCourses(List<StudentCourseDto> studentCourseDtos) {
        List<StudentCourse> studentCourses = studentCourseMapper.dtoToEntity(studentCourseDtos);
        studentCourses = studentCourseRepository.saveAll(studentCourses);
        return studentCourseMapper.entityToDto(studentCourses);
    }

    public List<StudentCourseDto> getStudentCourses() {
        List<StudentCourseDto> studentCourseDtos = new ArrayList<>();
        List<StudentCourse> studentCourseList = studentCourseRepository.findAllNotDeleted();
        for (StudentCourse studentCourse : studentCourseList) {
            StudentCourseDto studentCourseDto = studentCourseMapper.entityToDto(studentCourse);
            studentCourseDtos.add(studentCourseDto);
        }
        return studentCourseDtos;
    }



}
