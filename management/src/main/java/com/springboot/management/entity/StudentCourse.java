package com.springboot.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString(exclude = "student")
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "StudentCourse_TBL")
public class StudentCourse extends BaseEntity{
    @Id
    @GeneratedValue
    private Integer studentCourseId;
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
    @ManyToOne
    @JsonIgnoreProperties("studentCourses")
    @JoinColumn(name = "fk_semester_id",referencedColumnName = "semesterId")
    private Semester semester;
}
