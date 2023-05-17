package com.springboot.management.mapper;


import com.springboot.management.dto.SemesterDto;
import com.springboot.management.entity.Semester;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SemesterMapper {
    public SemesterDto entityToDto(Semester semester) {
        SemesterDto semesterDto=new SemesterDto();
        semesterDto.setSemesterId(semester.getSemesterId());
        semesterDto.setSemesterName(semester.getSemesterName());
        semesterDto.setStatus(semester.isStatus());
        return semesterDto;
    }
    public Semester dtoToEntity(SemesterDto semesterDto){
        Semester semester=new Semester();
        semester.setSemesterId(semesterDto.getSemesterId());
        semester.setSemesterName(semesterDto.getSemesterName());
        semester.setStatus(semesterDto.isStatus());
        return semester;
    }
    public List<SemesterDto> entityToDto(List<Semester> semesters){
        return semesters.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }
    public List<Semester> dtoToEntity(List<SemesterDto> semesterDtos){
        return semesterDtos.stream().map(x-> dtoToEntity(x)).collect(Collectors.toList());
    }
}
