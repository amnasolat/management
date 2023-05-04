package com.springboot.management.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;



@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DEPARTMENT_TBL")
public class Department extends BaseEntity{
    @Id
    @GeneratedValue
    private Integer departmentId;
    private String departmentName;
    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties("department")
    private List<Student> students;
    @OneToMany(mappedBy = "department")
    @JsonIgnoreProperties("department")
    private List<Course> courses;




}
