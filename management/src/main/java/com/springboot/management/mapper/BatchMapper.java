package com.springboot.management.mapper;

import com.springboot.management.dto.BatchDto;
import com.springboot.management.dto.CourseDto;
import com.springboot.management.entity.Batch;
import com.springboot.management.entity.Course;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BatchMapper {
    public BatchDto entityToDto(Batch batch) {
        BatchDto batchDto=new BatchDto();
        batchDto.setBatchId(batch.getBatchId());
        batchDto.setBatchName(batch.getBatchName());
        batchDto.setStartingDate(batch.getStartingDate());
        batchDto.setEndingDate(batch.getEndingDate());
        batchDto.setStatus(batch.isStatus());
        return batchDto;
    }
    public Batch dtoToEntity(BatchDto batchDto){
        Batch batch=new Batch();
        batch.setBatchId(batchDto.getBatchId());
        batch.setBatchName(batchDto.getBatchName());
        batch.setStartingDate(batchDto.getStartingDate());
        batch.setEndingDate(batchDto.getEndingDate());
        batch.setStatus(batchDto.isStatus());
        return batch;
    }
    public List<BatchDto> entityToDto(List<Batch> batches){
        return batches.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }
    public List<Batch> dtoToEntity(List<BatchDto> batchDtos){
        return batchDtos.stream().map(x-> dtoToEntity(x)).collect(Collectors.toList());
    }
}
