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
    @Autowired
    private SemesterMapper semesterMapper;

    public StudentCourseDto saveStudentCourse(StudentCourseDto studentCourseDto) {
        if (studentCourseDto.getStudentDto() == null && studentCourseDto.getCourseDto() == null) {
            throw new RuntimeException("Please Provide Student and Course you want to assign");
        } else {
            StudentCourse studentCourse = studentCourseMapper.dtoToEntity(studentCourseDto);
            int stdId = studentCourseDto.getStudentDto().getStudentId();
            Student student = studentRepository.findByIdNotDeleted(stdId);
            int courId = studentCourseDto.getCourseDto().getCourseId();
            Course course = courseRepository.findByIdNotDeleted(courId);
            if (student != null && course != null) {
                StudentCourse check = studentCourseRepository.findDuplicateRecords(stdId, courId);
                if (check == null) {
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
                    throw new RuntimeException("Student already Registered in this course");

                }
            }else
            {
                throw new RuntimeException("Student or Course you have chosen isn't exist");
            }
        }
    }

    public List<StudentCourseDto> saveStudentCourses(StudentCourseDto studentCourseDto) {
        if (studentCourseDto.getStudentDto() == null) {
            throw new RuntimeException("Please Provide Student and Course you want to assign");
        } else {
            Integer stdId = studentCourseDto.getStudentDto().getStudentId();

            Student student = studentRepository.findByIdNotDeleted(stdId);
            if (student != null) {
                List<StudentCourseDto> studentCourseDtoList = new ArrayList<>();

                for (Integer courseId : studentCourseDto.getCourseId()) {
                    StudentCourse check = studentCourseRepository.findDuplicateRecords(stdId, courseId);
                    if (check == null) {
                        Course course1 = courseRepository.findByIdNotDeleted(courseId);
                        List<Integer> id = new ArrayList<>();
                        StudentCourse studentCourse1 = new StudentCourse();
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

                    } else  {
                        throw new RuntimeException("Student already Registered in this course");
                    }

                }
                return studentCourseDtoList;
            } else {
                throw new RuntimeException("Student or Courses you have chosen aren't exist");
            }

        }
    }


    public List<StudentCourseDto> getCoursesbyStudentId(StudentDto studentDto) {
        int id1 = studentDto.getStudentId();
        List<StudentCourse> studentCourseList = studentCourseRepository.findAllNotDeleted();
        List<StudentCourseDto> studentCourseDtos = new ArrayList<>();
        for (StudentCourse studentCourse : studentCourseList) {
            int id2 = studentCourse.getStudent().getStudentId();
            if (id1 == id2) {
               Student student= studentCourse.getStudent();
               Course course=studentCourse.getCourse();
               Department department=course.getDepartment();
               Semester semester=studentCourse.getSemester();
               StudentDto studentDto1=studentMapper.entityToDto(student);
               CourseDto courseDto=courseMapper.entityToDto(course);
               SemesterDto semesterDto=semesterMapper.entityToDto(semester);
               DepartmentDto departmentDto=departmentMapper.entityToDto(department);
                StudentCourseDto studentCourseDto = studentCourseMapper.entityToDto(studentCourse);

                courseDto.setDepartmentDto(departmentDto);
                studentCourseDto.setCourseDto(courseDto);
                studentCourseDto.setStudentDto(studentDto1);
                studentCourseDto.setSemesterDto(semesterDto);
                studentCourseDtos.add(studentCourseDto);


            }
        }
        return studentCourseDtos;
    }

//        List<StudentCourseDto> studentCourseDtos = new ArrayList<>();
//        List<StudentCourse> studentCourseList = studentCourseRepository.findAllNotDeleted();
//        for (StudentCourse studentCourse : studentCourseList) {
//            StudentCourseDto studentCourseDto = studentCourseMapper.entityToDto(studentCourse);
//            studentCourseDtos.add(studentCourseDto);
//        }
//        return studentCourseDtos;
//    }



}
