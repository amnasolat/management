package com.springboot.management.mapper;


import com.springboot.management.dto.AssessmentDto;
import com.springboot.management.entity.Assessment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AssessmentMapper {
    public AssessmentDto entityToDto(Assessment assessment){
        AssessmentDto assessmentDto=new AssessmentDto();
        assessmentDto.setAssessmentId(assessment.getAssessmentId());
        assessmentDto.setAssessmentName(assessment.getAssessmentName());
        assessmentDto.setTotalMarks(assessment.getTotalMarks());
        return assessmentDto;
    }
    public Assessment dtoToEntity(AssessmentDto assessmentDto){
        Assessment assessment=new Assessment();
        assessment.setAssessmentId(assessmentDto.getAssessmentId());
        assessment.setAssessmentName(assessmentDto.getAssessmentName());
        assessment.setTotalMarks(assessmentDto.getTotalMarks());
        return assessment;
    }
    public List<AssessmentDto> entityToDto(List<Assessment> assessments){
        return assessments.stream().map(x->entityToDto(x)).collect(Collectors.toList());
    }
    public List<Assessment> dtoToEntity(List<AssessmentDto> assessmentDtos){
        return assessmentDtos.stream().map(x-> dtoToEntity(x)).collect(Collectors.toList());
    }
}
