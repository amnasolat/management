package com.springboot.management.controller;

import com.springboot.management.dto.SemesterDto;
import com.springboot.management.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SemesterController {
    @Autowired
    private SemesterService semesterService;
    @PostMapping("/addSemester")
    public SemesterDto addSemester(@RequestBody SemesterDto semesterDto){
        return semesterService.saveSemester(semesterDto);
    }
    @PostMapping("/semester")
    public SemesterDto findSemesterById(@RequestBody SemesterDto semesterDto){
        return semesterService.getSemesterById(semesterDto);
    }
    @GetMapping("/semesters")
    public List<SemesterDto> findAllSemesters(){
        return semesterService.getSemesters();
    }
}
