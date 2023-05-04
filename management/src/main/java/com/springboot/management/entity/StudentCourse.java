package com.springboot.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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
    @ManyToOne
    @JsonIgnoreProperties("studentCourses")
    @JoinColumn(name = "fk_student_id",referencedColumnName = "studentId")
    private Student student;
    @ManyToOne
    @JsonIgnoreProperties("studentCourses")
    @JoinColumn(name = "fk_course_id",referencedColumnName = "courseId")
    private Course course;
    @OneToMany(mappedBy = "studentCourse")
    @JsonIgnoreProperties("studentCourse")
    private List<StudentCourseAssessment> studentCourseAssessments;
}
