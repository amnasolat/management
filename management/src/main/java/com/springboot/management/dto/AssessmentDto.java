package com.springboot.management.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentDto {
    private int assessmentId;
    private String assessmentName;
    private int totalMarks;
    private String deleteStatus;
    CourseDto courseDto;
}
