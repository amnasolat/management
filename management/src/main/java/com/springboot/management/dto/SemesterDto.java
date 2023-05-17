package com.springboot.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SemesterDto {
    private Integer semesterId;
    private String semesterName;
    private boolean status;
    BatchDto batchDto;
}
