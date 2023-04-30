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
@Table(name = "StudentCourse_TBL")
public class StudentCourse extends BaseEntity{
    @Id
    @GeneratedValue
    private int studentCourseId;
    private String grade;
}
