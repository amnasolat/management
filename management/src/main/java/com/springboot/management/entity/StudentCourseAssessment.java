package com.springboot.management.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;

@Getter
@Setter
@ToString(exclude = {"studentCourse","assessment"})
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "StudentCourseAssessment_TBL")
public class StudentCourseAssessment extends BaseEntity {
    @Id
    @GeneratedValue
    private Integer studentCourseAssessmentId;
    private Integer obtainedMarks;
    @ManyToOne
    @JsonIgnoreProperties("studentCourseAssessments")
    @JoinColumn(name = "fk_student_course_id",referencedColumnName = "studentCourseId")
    private StudentCourse studentCourse;
    @ManyToOne
    @JsonIgnoreProperties("studentCourseAssessments")
    @JoinColumn(name = "fk_assessment_id",referencedColumnName = "assessmentId")
    private Assessment assessment;
}
