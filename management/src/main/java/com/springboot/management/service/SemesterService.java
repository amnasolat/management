package com.springboot.management.service;

import com.springboot.management.dto.BatchDto;
import com.springboot.management.dto.SemesterDto;
import com.springboot.management.entity.Batch;
import com.springboot.management.entity.Semester;
import com.springboot.management.mapper.BatchMapper;
import com.springboot.management.mapper.SemesterMapper;
import com.springboot.management.repository.BatchRepository;
import com.springboot.management.repository.SemesterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SemesterService {
    @Autowired
    private SemesterMapper semesterMapper;
    @Autowired
    private SemesterRepository semesterRepository;
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private BatchMapper batchMapper;

    public SemesterDto saveSemester(SemesterDto semesterDto){
        Semester semester=semesterMapper.dtoToEntity(semesterDto);
        int id=semesterDto.getBatchDto().getBatchId();
        Batch batch=batchRepository.findById(id).orElse(null);
        if(batch.isStatus()==true){
            semester.setStatus(true);
        }else {
            semester.setStatus(false);
        }
        semester.setBatch(batch);
        semester=semesterRepository.save(semester);
        BatchDto batchDto=batchMapper.entityToDto(batch);
        SemesterDto semesterDto1=semesterMapper.entityToDto(semester);
        semesterDto1.setBatchDto(batchDto);
        return semesterDto1;
    }
    public SemesterDto getSemesterById(SemesterDto semesterDto){
        int semId=semesterDto.getSemesterId();
        Semester semester=semesterRepository.findById(semId).orElse(null);
        return semesterMapper.entityToDto(semester);
    }
    public List<SemesterDto> getSemesters(){
        List<Semester> semesters=semesterRepository.findAll();
        List<SemesterDto> semesterDtos=new ArrayList<>();
        for(Semester semester:semesters){
            SemesterDto semesterDto=semesterMapper.entityToDto(semester);
            semesterDtos.add(semesterDto);
        }
        return semesterDtos;
    }

}
