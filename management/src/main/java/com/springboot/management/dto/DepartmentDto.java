package com.springboot.management.dto;

import com.springboot.management.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {
    private Integer departmentId;
    private String departmentName;
    private String deleteStatus;



}
