package com.springboot.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
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
    @ManyToOne
    @JsonIgnoreProperties("studentCourseAssessments")
    @JoinColumn(name = "fk_student_course_id",referencedColumnName = "studentCourseId")
    private StudentCourse studentCourse;
    @ManyToOne
    @JsonIgnoreProperties("studentCourseAssessments")
    @JoinColumn(name = "fk_assessment_id",referencedColumnName = "assessmentId")
    private Assessment assessment;
}
