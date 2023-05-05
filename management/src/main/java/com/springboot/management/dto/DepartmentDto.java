package com.springboot.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.springboot.management.entity.Department;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DepartmentDto {
    private Integer departmentId;
    private String departmentName;
    private String deleteStatus;



}
