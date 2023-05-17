package com.springboot.management.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BatchDto {
    private Integer batchId;
    private String batchName;
    private Date startingDate;
    private Date endingDate;
    private boolean status;

}
