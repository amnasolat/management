package com.springboot.management.controller;

import com.springboot.management.dto.BatchDto;
import com.springboot.management.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BatchController {
    @Autowired
    private BatchService batchService;
    @PostMapping("/addBatch")
    public BatchDto addBatch(@RequestBody BatchDto batchDto){
        return batchService.saveBatch(batchDto);
    }
    @PostMapping("/batch")
    public BatchDto findBatchById(@RequestBody BatchDto batchDto){
        return batchService.getBatchById(batchDto);
    }
}
