package com.springboot.management.mapper;

import com.springboot.management.dto.StudentDto;
import com.springboot.management.entity.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StudentMapper {


    public Student dtoToEntity(StudentDto studentDto){
        Student student=new Student();
        student.setStudentId(studentDto.getStudentId());
        student.setStudentName(studentDto.getStudentName());
        student.setDob(studentDto.getDob());
        student.setGender(studentDto.getGender());
        return student;
    }
    public StudentDto entityToDto(Student student){
        StudentDto studentDto=new StudentDto();
        studentDto.setStudentId(student.getStudentId());
        studentDto.setStudentName(student.getStudentName());
        studentDto.setDob(student.getDob());
        studentDto.setGender(student.getGender());
        return studentDto;
    }
    public List<Student> dtoToEntity(List<StudentDto> studentDtos){
        return studentDtos.stream().map(x -> dtoToEntity(x)).collect(Collectors.toList());
    }
    public List<StudentDto> entityToDto(List<Student> students){
      return students.stream().map(x -> entityToDto(x)).collect(Collectors.toList());
    }
}