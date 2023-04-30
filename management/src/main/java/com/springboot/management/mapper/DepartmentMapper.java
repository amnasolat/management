package com.springboot.management.mapper;

import com.springboot.management.dto.DepartmentDto;
import com.springboot.management.entity.Department;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class DepartmentMapper {
    public DepartmentDto entityToDto(Department department){
        DepartmentDto departmentDto=new DepartmentDto();
        departmentDto.setDepartmentId(department.getDepartmentId());
        departmentDto.setDepartmentName(department.getDepartmentName());


        return departmentDto;
    }
    public Department dtoToEntity(DepartmentDto departmentDto){
        Department department=new Department();
        department.setDepartmentId(departmentDto.getDepartmentId());
        department.setDepartmentName(departmentDto.getDepartmentName());



        return department;
    }
    public List<DepartmentDto> entityToDto(List<Department> departments){
        return departments.stream().map(x-> entityToDto(x)).collect(Collectors.toList());
    }
    public List<Department> dtoToEntity(List<DepartmentDto> departmentDtos){
        return departmentDtos.stream().map(x-> dtoToEntity(x)).collect(Collectors.toList());
    }
}
