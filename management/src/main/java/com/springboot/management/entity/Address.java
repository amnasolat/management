package com.springboot.management.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Transactional
@Entity
@Table(name = "ADDRESS_TBL")
public class Address extends BaseEntity{
    @Id
    @GeneratedValue
    private int addressId;
    private String addressType;
    private String city;
    private String country;

}
