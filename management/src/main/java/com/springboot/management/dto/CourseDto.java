package com.springboot.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CourseDto {
    private Integer courseId;
    private String courseName;
    private String deleteStatus;
    DepartmentDto departmentDto;
}
