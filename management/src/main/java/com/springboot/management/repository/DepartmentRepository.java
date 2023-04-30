package com.springboot.management.repository;

import com.springboot.management.dto.DepartmentDto;
import com.springboot.management.entity.Department;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department,Integer> {


    List<Department> findByDepartmentName(String name);
    @Query("select a from Department a where a.isDeleted = false or a.isDeleted is null")
    List<Department> findAllNotDeleted();
    @Query("select a from Department a where (a.isDeleted =false or a.isDeleted is null) and a.departmentId = :departmentId")
    Department findByIdNotDeleted(@Param("departmentId") Integer departmentId);
}
