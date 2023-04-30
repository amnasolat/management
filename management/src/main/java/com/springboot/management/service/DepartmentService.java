package com.springboot.management.service;

import com.springboot.management.dto.DepartmentDto;
import com.springboot.management.entity.Department;
import com.springboot.management.mapper.DepartmentMapper;
import com.springboot.management.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private DepartmentMapper departmentMapper;

    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        Department department = departmentMapper.dtoToEntity(departmentDto);
        department = departmentRepository.save(department);
        return departmentMapper.entityToDto(department);
    }

    public List<DepartmentDto> saveDepartments(List<DepartmentDto> departmentDtos) {
        List<Department> departments = departmentMapper.dtoToEntity(departmentDtos);
        departments = departmentRepository.saveAll(departments);
        return departmentMapper.entityToDto(departments);
    }

    public List<DepartmentDto> getDepartments() {
        List<DepartmentDto> departmentDtos = new ArrayList<>();
        List<Department> departmentList = departmentRepository.findAllNotDeleted();
        for (Department department : departmentList) {
            DepartmentDto departmentDtos1 = departmentMapper.entityToDto(department);
            departmentDtos.add(departmentDtos1);
        }
        return departmentDtos;
    }

    public DepartmentDto getDepartmentById(DepartmentDto departmentDto) {
        Department department = departmentMapper.dtoToEntity(departmentDto);
        int id = department.getDepartmentId();
        Department department1 = departmentRepository.findByIdNotDeleted(id);
        return departmentMapper.entityToDto(department1);
    }

    public List<DepartmentDto> getDepartmentByName(DepartmentDto departmentDto) {
        Department department = departmentMapper.dtoToEntity(departmentDto);
        String name = department.getDepartmentName();
        List<Department> departments = departmentRepository.findByDepartmentName(name);
        return departmentMapper.entityToDto(departments);
    }

    public DepartmentDto deleteDepartment(DepartmentDto departmentDto) {
        if (departmentDto != null && departmentDto.getDepartmentId() > 0) {
            int id = departmentDto.getDepartmentId();
            Department department = departmentRepository.findByIdNotDeleted(id);
            if (department != null) {
                department.setDeleted(true);
                departmentRepository.save(department);
                departmentDto.setDeleteStatus("Record Deleted");
            }else{
                departmentDto.setDeleteStatus("Record Not Found");
            }
        }else {
            departmentDto=new DepartmentDto();
            departmentDto.setDeleteStatus("Data irrelevant");
        }
        return departmentDto;
//        int id=departmentDto.getDepartmentId();
//       // Department department=departmentMapper.dtoToEntity(departmentDto);
//       // int id=department.getDepartmentId();
//        if(departmentRepository.existsById(id)==true){
//            departmentRepository.deleteById(id);
//            departmentDto.setDeleteStatus("deleted successfully");
//        }else{
//
//            departmentDto.setDeleteStatus("Not found");
//        }
//        return departmentDto;
//        departmentRepository.deleteById(id);
//        if(departmentRepository.findById(id).isEmpty()){
//            department.setDeleteStatus("deleted successfully");
//        }else{
//            department.setDeleteStatus("not deleted");
//        }
        //return departmentMapper.entityToDto(department);
//        Department department1=department.
//        //return "Deleted Department: || "+id;
    }


    public DepartmentDto updateDepartment(DepartmentDto departmentDto){

        Department department=departmentMapper.dtoToEntity(departmentDto);
        Department existingDepartment=departmentRepository.findById(department.getDepartmentId()).orElse(null);
        existingDepartment.setDepartmentName(department.getDepartmentName());
        departmentRepository.save(existingDepartment);
        return departmentMapper.entityToDto(existingDepartment);
    }
}
