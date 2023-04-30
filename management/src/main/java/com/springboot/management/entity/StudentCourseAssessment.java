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
@Table(name = "StudentCourseAssessment_TBL")
public class StudentCourseAssessment extends BaseEntity {
    @Id
    @GeneratedValue
    private int studentCourseAssessmentId;
    private int obtainedMarks;
}
