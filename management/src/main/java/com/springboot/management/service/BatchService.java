package com.springboot.management.service;

import com.springboot.management.dto.BatchDto;
import com.springboot.management.entity.Batch;
import com.springboot.management.mapper.BatchMapper;
import com.springboot.management.repository.BatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
    @Autowired
    private BatchRepository batchRepository;
    @Autowired
    private BatchMapper batchMapper;
    public BatchDto saveBatch(BatchDto batchDto){
        Batch batch=batchMapper.dtoToEntity(batchDto);
        batch=batchRepository.save(batch);
        return batchMapper.entityToDto(batch);
    }
    public BatchDto getBatchById(BatchDto batchDto){
        int batchId=batchDto.getBatchId();
        Batch batch=batchRepository.findById(batchId).orElse(null);
        return batchMapper.entityToDto(batch);
    }
}
