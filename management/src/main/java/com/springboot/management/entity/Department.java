package com.springboot.management.entity;


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
//    private String deleteStatus;




}
