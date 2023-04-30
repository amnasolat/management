package com.springboot.management.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "STUDENT_TBL")
public class Student extends BaseEntity{
    @Id
    @GeneratedValue
    private Integer studentId;
    private String studentName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date dob;
    private String gender;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIncludeProperties("address")
    @JoinColumn(name = "fk_address_id",referencedColumnName = "addressId")
    private Address address;



//    @ManyToOne(cascade =CascadeType.ALL )
//    @JoinColumn(name = "fk_department_id",referencedColumnName ="deptId")
//    @JsonManagedReference
//    private Department department;
}
