package com.springboot.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(exclude = "studentCourses")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COURSE_TBL")
public class Course extends BaseEntity{
    @Id
    @GeneratedValue
    private Integer courseId;
    private String courseName;
    @ManyToOne
    @JsonIgnoreProperties("courses")
    @JoinColumn(name = "fk_department_id",referencedColumnName ="departmentId")
    private Department department;
    @OneToMany(mappedBy = "course")
    @JsonIgnoreProperties("course")
    private List<Assessment> assessments;
    @OneToMany(mappedBy = "course")
    @JsonIgnoreProperties("course")
    private List<StudentCourse> studentCourses;


}
