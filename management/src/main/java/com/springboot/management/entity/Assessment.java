package com.springboot.management.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ASSESSMENT_TBL")
public class Assessment extends BaseEntity{
    @Id
    @GeneratedValue
    private int assessmentId;
    private String assessmentName;
    private int totalMarks;
    private String deleteStatus;

}
