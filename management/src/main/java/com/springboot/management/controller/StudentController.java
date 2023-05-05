package com.springboot.management.controller;


import com.springboot.management.dto.StudentDto;

import com.springboot.management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService service;


    @PostMapping("/addStudent")
    public StudentDto addStudent(@RequestBody StudentDto studentDto){
        return service.saveStudent(studentDto);
    }
    @PostMapping("/addStudents")
    public List<StudentDto> addStudents(@RequestBody List<StudentDto> studentDtos){
        return service.saveStudents(studentDtos);
    }
    @GetMapping("/students")
     public List<StudentDto> findAllStudents(){
        return service.getStudents();
    }

    @PostMapping("/student")
    public StudentDto findStudentById(@RequestBody StudentDto studentDto){
        return service.getStudentById(studentDto);
    }
    @PostMapping("/studentName")
    public List<StudentDto> findStudentByName(@RequestBody StudentDto studentDto){
        return service.getStudentByName(studentDto);
    }
    @PutMapping("/updateStudent")
    public StudentDto updateStudent(@RequestBody StudentDto studentDto){
        return service.updateStudent(studentDto);
    }
    @PostMapping("/deleteStudent")
    public StudentDto deleteStudent(@RequestBody StudentDto studentDto){
        return service.deleteStudent(studentDto);
    }


}
