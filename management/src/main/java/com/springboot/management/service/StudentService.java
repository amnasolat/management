package com.springboot.management.service;

import com.springboot.management.dto.AddressDto;
import com.springboot.management.dto.DepartmentDto;
import com.springboot.management.dto.StudentDto;
import com.springboot.management.entity.Address;
import com.springboot.management.entity.Department;
import com.springboot.management.entity.Student;
import com.springboot.management.mapper.AddressMapper;
import com.springboot.management.mapper.DepartmentMapper;
import com.springboot.management.mapper.StudentMapper;

import com.springboot.management.repository.AddressRepository;
import com.springboot.management.repository.DepartmentRepository;
import com.springboot.management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentRepository departmentRepository;


    public StudentDto saveStudent(StudentDto studentDto){
        Student student=studentMapper.dtoToEntity(studentDto);
        Address address=new Address();
        Department department=new Department();
        if(studentDto.getAddressDto()!=null && studentDto.getDepartmentDto().getDepartmentId()!=null) {
            AddressDto addressDto = studentDto.getAddressDto();
            address = addressMapper.dtoToEntity(addressDto);
            address=addressRepository.save(address);
            DepartmentDto departmentDto=studentDto.getDepartmentDto();
            department= departmentMapper.dtoToEntity(departmentDto) ;
            department=departmentRepository.findByIdNotDeleted(department.getDepartmentId());
            student.setDepartment(department);
            student.setAddress(address);
        }
            DepartmentDto departmentDto =departmentMapper.entityToDto(department);
            student=studentRepository.save(student);
            AddressDto addressDto=addressMapper.entityToDto(address);
            studentDto=studentMapper.entityToDto(student);
            studentDto.setAddressDto(addressDto);
            studentDto.setDepartmentDto(departmentDto);
            return studentDto;


        }

    public List<StudentDto> saveStudents(List<StudentDto> studentDtos){
        List<Student> students=studentMapper.dtoToEntity(studentDtos);
        students=studentRepository.saveAll(students);
        return studentMapper.entityToDto(students);
    }
    public List<StudentDto> getStudents(){
        List<StudentDto> studentDtos=new ArrayList<>();
        List<Student> studentList=studentRepository.findAllNotDeleted();
        for(Student student: studentList){
            StudentDto studentDto=studentMapper.entityToDto(student);
            if(student.getAddress()!=null){
                Address address=student.getAddress();
                AddressDto addressDto=addressMapper.entityToDto(address);
                studentDto.setAddressDto(addressDto);
            }
            studentDtos.add(studentDto);
        }
        return studentDtos;
    }
    public StudentDto getStudentById(StudentDto studentDto) {
        int id = studentDto.getStudentId();
        Student student = studentRepository.findByIdNotDeleted(id);
        StudentDto studentDto1 = studentMapper.entityToDto(student);
            Address address = student.getAddress();
            AddressDto addressDto=addressMapper.entityToDto(address);
            Department department=student.getDepartment();
            DepartmentDto departmentDto=departmentMapper.entityToDto(department);
            studentDto1.setAddressDto(addressDto);
            studentDto1.setDepartmentDto(departmentDto);
            return studentDto1;
    }
    public List<StudentDto> getStudentByName(StudentDto studentDto){
        String name=studentDto.getStudentName();
        List<Student> students=studentRepository.findByStudentName(name);
        return studentMapper.entityToDto(students);
    }
    public StudentDto deleteStudent(StudentDto studentDto){
        if(studentDto!=null && studentDto.getStudentId()>0){
            int id=studentDto.getStudentId();
            Student student=studentRepository.findByIdNotDeleted(id);
            if(student!=null){
                student.setDeleted(true);
                studentRepository.save(student);
                studentDto.setDeleteStatus("Record Deleted");
            }else {
                studentDto.setDeleteStatus("Record Not Found");
            }
        }else {
            studentDto=new StudentDto();
            studentDto.setDeleteStatus("Irrelevant Data");
        }
        return studentDto;

    }
      public StudentDto updateStudent(StudentDto studentDto){

        if(studentDto.getAddressDto()!=null && studentDto.getDepartmentDto()!=null){
            int id=studentDto.getStudentId();
            Student existingStudent=studentRepository.findByIdNotDeleted(id);
            existingStudent.setStudentName(studentDto.getStudentName());
            existingStudent.setDob(studentDto.getDob());
            existingStudent.setGender(studentDto.getGender());
            Address address= existingStudent.getAddress();
            address.setAddressType(studentDto.getAddressDto().getAddressType());
            address.setCity(studentDto.getAddressDto().getCity());
            address.setCountry(studentDto.getAddressDto().getCountry());
            addressRepository.save(address);
            int deptId=studentDto.getDepartmentDto().getDepartmentId();
            Department department=departmentRepository.findByIdNotDeleted(deptId);
            existingStudent.setDepartment(department);
//            Department department=existingStudent.getDepartment();
            //department.setDepartmentName(studentDto.getDepartmentDto().getDepartmentName());
           // departmentRepository.save(department);
            studentRepository.save(existingStudent);
           StudentDto studentDto1= studentMapper.entityToDto(existingStudent);
            AddressDto addressDto=addressMapper.entityToDto(address);
            DepartmentDto departmentDto=departmentMapper.entityToDto(department);
            studentDto1.setAddressDto(addressDto);
            studentDto1.setDepartmentDto(departmentDto);
            return studentDto1;


        }else {
            int id=studentDto.getStudentId();
            Student existingStudent=studentRepository.findByIdNotDeleted(id);
            existingStudent.setStudentName(studentDto.getStudentName());
            existingStudent.setDob(studentDto.getDob());
            existingStudent.setGender(studentDto.getGender());
            studentRepository.save(existingStudent);
            return studentMapper.entityToDto(existingStudent);

        }

      }
}
