package com.springboot.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BATCH_TBL")
public class Batch extends BaseEntity{
    @Id
    @GeneratedValue
    private Integer batchId;
    private String batchName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date startingDate;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date endingDate;
    private boolean status;
    @OneToMany(mappedBy = "batch")
    @JsonIgnoreProperties("batch")
    private List<Student> students;
    @OneToMany(mappedBy = "batch")
    @JsonIgnoreProperties("batch")
    private List<Semester> semesters;


}
