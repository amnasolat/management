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
@Table(name = "ASSESSMENT_TBL")
public class Assessment extends BaseEntity{
    @Id
    @GeneratedValue
    private int assessmentId;
    private String assessmentName;
    private int totalMarks;
    @ManyToOne
    @JsonIgnoreProperties("assessments")
    @JoinColumn(name = "fk_course_id",referencedColumnName = "courseId")
    private Course course;
    @OneToMany(mappedBy = "assessment")
    @JsonIgnoreProperties("assessment")
    private List<StudentCourseAssessment> studentCourseAssessments;


}
