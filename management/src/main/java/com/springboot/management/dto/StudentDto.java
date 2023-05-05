package com.springboot.management.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.springboot.management.entity.Student;
import lombok.*;


import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentDto {

    private Integer studentId;

    private String studentName;

    private Date dob;

    private String gender;
    private String deleteStatus;

    AddressDto addressDto;
    DepartmentDto departmentDto;


    }


