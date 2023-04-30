package com.springboot.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssessmentDto {
    private int assessmentId;
    private String assessmentName;
    private int totalMarks;
    private String deleteStatus;
}
