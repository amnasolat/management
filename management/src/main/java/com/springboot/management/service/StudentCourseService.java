package com.springboot.management.service;

import com.springboot.management.dto.*;
import com.springboot.management.entity.*;
import com.springboot.management.mapper.*;
import com.springboot.management.repository.CourseRepository;
import com.springboot.management.repository.StudentCourseRepository;
import com.springboot.management.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentCourseService {
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private DepartmentMapper departmentMapper;

    public StudentCourseDto saveStudentCourse(StudentCourseDto studentCourseDto) {
        if (studentCourseDto.getStudentDto() == null && studentCourseDto.getCourseDto() == null) {
            throw new RuntimeException("Please Provide Student and Course you want to assign");
        } else {
            StudentCourse studentCourse = studentCourseMapper.dtoToEntity(studentCourseDto);
            int StdId = studentCourseDto.getStudentDto().getStudentId();
            Student student = studentRepository.findByIdNotDeleted(StdId);
            int courId = studentCourseDto.getCourseDto().getCourseId();
            Course course = courseRepository.findByIdNotDeleted(courId);
            if (student != null && course != null) {
                Address address = student.getAddress();
                Department department = student.getDepartment();
                Department department1 = course.getDepartment();
                studentCourse.setStudent(student);
                studentCourse.setCourse(course);
                studentCourse = studentCourseRepository.save(studentCourse);
                studentCourseDto = studentCourseMapper.entityToDto(studentCourse);
                StudentDto studentDto = studentMapper.entityToDto(student);
                AddressDto addressDto = addressMapper.entityToDto(address);
                DepartmentDto departmentDto = departmentMapper.entityToDto(department);
                DepartmentDto departmentDto1 = departmentMapper.entityToDto(department1);
                CourseDto courseDto = courseMapper.entityToDto(course);
                courseDto.setDepartmentDto(departmentDto1);
                studentDto.setAddressDto(addressDto);
                studentDto.setDepartmentDto(departmentDto);
                studentCourseDto.setStudentDto(studentDto);
                studentCourseDto.setCourseDto(courseDto);
                return studentCourseDto;
            } else {
                throw new RuntimeException("Student or Course you have chosen isn't exist");
            }
        }
    }

    public List<StudentCourseDto> saveStudentCourses(StudentCourseDto studentCourseDto) {
        if (studentCourseDto.getStudentDto() == null) {
            throw new RuntimeException("Please Provide Student and Course you want to assign");
        } else {

            Student student = studentRepository.findByIdNotDeleted(studentCourseDto.getStudentDto().getStudentId());
            if (student != null) {
                List<StudentCourseDto> studentCourseDtoList = new ArrayList<>();


                for (Integer courseId : studentCourseDto.getCourseId()) {
                    Course course1 = courseRepository.findByIdNotDeleted(courseId);
                    List<Integer> id=new ArrayList<>();
                    StudentCourse studentCourse1=new StudentCourse();
                    studentCourse1.setStudent(student);
                    studentCourse1.setCourse(course1);
                    studentCourse1 = studentCourseRepository.save(studentCourse1);
                    studentCourseDto = studentCourseMapper.entityToDto(studentCourse1);
                    StudentDto studentDto = studentMapper.entityToDto(student);
                    CourseDto courseDto = courseMapper.entityToDto(course1);
                    studentCourseDto.setStudentDto(studentDto);
                    studentCourseDto.setCourseDto(courseDto);
                    id.add(courseId);
                    studentCourseDto.setCourseId(id);
                    studentCourseDtoList.add(studentCourseDto);

                }
                return studentCourseDtoList;
            }else {
                throw new RuntimeException("Student or Courses you have chosen aren't exist");
        }

    }

}

    public List<StudentCourseDto> getStudentCourses() {
        List<StudentCourseDto> studentCourseDtos = new ArrayList<>();
        List<StudentCourse> studentCourseList = studentCourseRepository.findAllNotDeleted();
        for (StudentCourse studentCourse : studentCourseList) {
            StudentCourseDto studentCourseDto = studentCourseMapper.entityToDto(studentCourse);
            studentCourseDtos.add(studentCourseDto);
        }
        return studentCourseDtos;
    }



}
