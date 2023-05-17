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
@Table(name = "SEMESTER_TBL")
public class Semester extends BaseEntity{
    @Id
    @GeneratedValue
    private Integer semesterId;
    private String semesterName;
    private boolean status;
    @ManyToOne
    @JsonIgnoreProperties("semesters")
    @JoinColumn(name = "fk_batch_id",referencedColumnName = "batchId")
    private Batch batch;
    @OneToMany(mappedBy = "semester")
    @JsonIgnoreProperties("semester")
    private List<StudentCourse> studentCourses;

}
