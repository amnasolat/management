package com.springboot.management.controller;

import com.springboot.management.dto.DepartmentDto;
import com.springboot.management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/addDepartment")
    public DepartmentDto addDepartment(@RequestBody DepartmentDto departmentDto){
        return departmentService.saveDepartment(departmentDto);
    }
    @PostMapping("/addDepartments")
    public List<DepartmentDto> addDepartments(@RequestBody List<DepartmentDto> departmentDtos){
        return departmentService.saveDepartments(departmentDtos);
    }
    @GetMapping("/departments")
    public List<DepartmentDto> findAllDepartments(){
        return departmentService.getDepartments();
    }

    @PostMapping("/findDepartmentById")
    public DepartmentDto findDepartmentById(@RequestBody DepartmentDto departmentDto){
        return departmentService.getDepartmentById(departmentDto);
    }

    @PostMapping("/findDepartmentByName")
    public List<DepartmentDto> findDepartmentByName(@RequestBody DepartmentDto departmentDto){
        return  departmentService.getDepartmentByName(departmentDto);
    }
    @PutMapping("/updateDepartment")
    public DepartmentDto updateDepartment(@RequestBody DepartmentDto departmentDto){
        return departmentService.updateDepartment(departmentDto);
    }


    @PostMapping("/deleteDepartment")
    public DepartmentDto deleteDepartment(@RequestBody DepartmentDto departmentDto){
        return departmentService.deleteDepartment(departmentDto);
    }
}
