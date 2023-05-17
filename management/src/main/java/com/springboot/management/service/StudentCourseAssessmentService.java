package com.springboot.management.service;

import com.springboot.management.dto.AssessmentDto;
import com.springboot.management.dto.ReportDto;
import com.springboot.management.dto.StudentCourseAssessmentDto;
import com.springboot.management.dto.StudentCourseDto;
import com.springboot.management.entity.*;
import com.springboot.management.mapper.AssessmentMapper;
import com.springboot.management.mapper.StudentCourseAssessmentMapper;
import com.springboot.management.mapper.StudentCourseMapper;
import com.springboot.management.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class StudentCourseAssessmentService {
    private final StudentCourseRepository studentCourseRepository;
    private final AssessmentRepository assessmentRepository;
    @Autowired
    private StudentCourseAssessmentMapper studentCourseAssessmentMapper;
    @Autowired
    private StudentCourseMapper studentCourseMapper;
    @Autowired
    private AssessmentMapper assessmentMapper;
    private final StudentCourseAssessmentRepository studentCourseAssessmentRepository;
    private final DepartmentRepository departmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public StudentCourseAssessmentService(StudentCourseRepository studentCourseRepository,
                                          AssessmentRepository assessmentRepository,
                                          StudentCourseAssessmentRepository studentCourseAssessmentRepository,
                                          DepartmentRepository departmentRepository,
                                          CourseRepository courseRepository,
                                          StudentRepository studentRepository) {
        this.studentCourseRepository = studentCourseRepository;
        this.assessmentRepository = assessmentRepository;
        this.studentCourseAssessmentRepository = studentCourseAssessmentRepository;
        this.departmentRepository = departmentRepository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    public StudentCourseAssessmentDto saveStudentCourseAssessment(StudentCourseAssessmentDto studentCourseAssessmentDto) {
        if (studentCourseAssessmentDto.getAssessmentDto() == null && studentCourseAssessmentDto.getStudentCourseDto() == null) {
            throw new RuntimeException("Please provide Student and the Course which you want to assign Assessment");
        } else {
            int id = studentCourseAssessmentDto.getStudentCourseDto().getStudentCourseId();
            StudentCourse studentCourse = studentCourseRepository.findByIdNotDeleted(id);
            int id2 = studentCourseAssessmentDto.getAssessmentDto().getAssessmentId();
            Assessment assessment = assessmentRepository.findByIdNotDeleted(id2);
            StudentCourseAssessment studentCourseAssessment = studentCourseAssessmentMapper.dtoToEntity(studentCourseAssessmentDto);
            if (studentCourse != null && assessment != null) {
                studentCourseAssessment.setStudentCourse(studentCourse);
                studentCourseAssessment.setAssessment(assessment);
                studentCourseAssessment = studentCourseAssessmentRepository.save(studentCourseAssessment);
                StudentCourseAssessmentDto studentCourseAssessmentDto1 = studentCourseAssessmentMapper.entityToDto(studentCourseAssessment);
                StudentCourseDto studentCourseDto = studentCourseMapper.entityToDto(studentCourse);
                AssessmentDto assessmentDto = assessmentMapper.entityToDto(assessment);
                studentCourseAssessmentDto1.setStudentCourseDto(studentCourseDto);
                studentCourseAssessmentDto1.setAssessmentDto(assessmentDto);
                return studentCourseAssessmentDto1;
            } else {
                throw new RuntimeException("Irrelevant Data");

            }
        }
    }

    public List<StudentCourseAssessmentDto> saveStudentCourseAssessments(StudentCourseAssessmentDto studentCourseAssessmentDto) {
        if (studentCourseAssessmentDto.getStudentCourseDto() == null) {
            throw new RuntimeException("Please provide Student and the Course which you want to assign Assessments");
        } else {
            int id = studentCourseAssessmentDto.getStudentCourseDto().getStudentCourseId();
            StudentCourse studentCourse = studentCourseRepository.findByIdNotDeleted(id);
            if (studentCourse != null) {
                List<StudentCourseAssessmentDto> studentCourseAssessmentDtosList = new ArrayList<>();
                for (Integer assessmentId : studentCourseAssessmentDto.getAssessmentId()) {
                    Assessment assessment = assessmentRepository.findByIdNotDeleted(assessmentId);
                    StudentCourseAssessment studentCourseAssessment = new StudentCourseAssessment();
                    studentCourseAssessment.setAssessment(assessment);
                    studentCourseAssessment.setStudentCourse(studentCourse);
                    studentCourseAssessment = studentCourseAssessmentRepository.save(studentCourseAssessment);
                    studentCourseAssessmentDto = studentCourseAssessmentMapper.entityToDto(studentCourseAssessment);
                    StudentCourseDto studentCourseDto = studentCourseMapper.entityToDto(studentCourse);
                    AssessmentDto assessmentDto = assessmentMapper.entityToDto(assessment);
                    studentCourseAssessmentDto.setStudentCourseDto(studentCourseDto);
                    studentCourseAssessmentDto.setAssessmentDto(assessmentDto);
                    studentCourseAssessmentDtosList.add(studentCourseAssessmentDto);
                }
                return studentCourseAssessmentDtosList;
            } else {
                throw new RuntimeException("Irrelevant Data");
            }


        }

    }

    public StudentCourseAssessmentDto updateObtainedMarks(StudentCourseAssessmentDto studentCourseAssessmentDto) {
        if (studentCourseAssessmentDto.getStudentId() != null && studentCourseAssessmentDto.getCourseId() != null) {
            StudentCourseAssessmentDto studentCourseAssessmentDto1 = new StudentCourseAssessmentDto();
            int stdId1 = studentCourseAssessmentDto.getStudentId();
            int corId1 = studentCourseAssessmentDto.getCourseId();
            int assessmentId1 = studentCourseAssessmentDto.getAssessmentDto().getAssessmentId();
            List<StudentCourse> studentCourseList = studentCourseRepository.findAllNotDeleted();
            List<StudentCourseAssessment> studentCourseAssessmentList = studentCourseAssessmentRepository.findAllNotDeleted();
            for (StudentCourse studentCourse : studentCourseList) {
                int stdId2 = studentCourse.getStudent().getStudentId();
                int corId2 = studentCourse.getCourse().getCourseId();
                if (stdId1 == stdId2 && corId1 == corId2) {
                    StudentCourse studentCourse1 = studentCourse;
                    int enrollmentId1 = studentCourse1.getStudentCourseId();
                    for (StudentCourseAssessment studentCourseAssessment : studentCourseAssessmentList) {
                        int assessmentId2 = studentCourseAssessment.getAssessment().getAssessmentId();
                        int enrollmentId2 = studentCourseAssessment.getStudentCourse().getStudentCourseId();
                        if (enrollmentId1 == enrollmentId2 && assessmentId1 == assessmentId2) {
                            StudentCourseAssessment studentCourseAssessment1 = studentCourseAssessment;
                            studentCourseAssessment1.setObtainedMarks(studentCourseAssessmentDto.getObtainedMarks());
                            studentCourseAssessment = studentCourseAssessmentRepository.save(studentCourseAssessment1);
                            studentCourseAssessmentDto1 = studentCourseAssessmentMapper.entityToDto(studentCourseAssessment);

                        }


                    }

                }


            }

            return studentCourseAssessmentDto1;
        } else {
            throw new RuntimeException("Please provide student and course id");
        }

    }

    public List<ReportDto> getAllData(ReportDto reportDto) {
        if (reportDto.getDepartmentIdList() == null && reportDto.getCourseIdList() == null && reportDto.getStudentIdList() == null) {
            List<Department> departments = departmentRepository.findAllNotDeleted();
            List<StudentCourse> studentCourses = studentCourseRepository.findAllNotDeleted();
            List<StudentCourseAssessment> studentCourseAssessments = studentCourseAssessmentRepository.findAllNotDeleted();
            List<ReportDto> reportDtoList = new ArrayList<>();
            for (Department department : departments) {
                List<Student> students = department.getStudents();
                for (Student student : students) {
                    int stdId = student.getStudentId();
                    String stdName = student.getStudentName();
                    String deptName = student.getDepartment().getDepartmentName();
                    for (StudentCourse studentCourse : studentCourses) {
                        if (stdId == studentCourse.getStudent().getStudentId()) {
                            String semesterName= studentCourse.getSemester().getSemesterName();
                            String batchName=studentCourse.getSemester().getBatch().getBatchName();
                            Course course = studentCourse.getCourse();
                            int corId = course.getCourseId();
                            String corName = course.getCourseName();
                            for (StudentCourseAssessment studentCourseAssessment : studentCourseAssessments) {
                                if (studentCourseAssessment.getStudentCourse().getStudentCourseId() == studentCourse.getStudentCourseId()) {
                                    String assessmentName = studentCourseAssessment.getAssessment().getAssessmentName();
                                    int total = studentCourseAssessment.getAssessment().getTotalMarks();
                                    int obtained = studentCourseAssessment.getObtainedMarks();
                                    ReportDto reportDto1 = new ReportDto();
                                    reportDto1.setCourseId(corId);
                                    reportDto1.setCourseName(corName);
                                    reportDto1.setStudentId(stdId);
                                    reportDto1.setStudentName(stdName);
                                    reportDto1.setDepartmentName(deptName);
                                    reportDto1.setSemesterName(semesterName);
                                    reportDto1.setBatchName(batchName);
                                    reportDto1.setAssessmentName(assessmentName);
                                    reportDto1.setTotalMarks(total);
                                    reportDto1.setObtainedMarks(obtained);
                                    reportDtoList.add(reportDto1);
                                }
                            }
                            ReportDto reportDto1 = new ReportDto();
                            reportDto1.setCourseId(corId);
                            reportDto1.setCourseName(corName);
                            reportDto1.setStudentId(stdId);
                            reportDto1.setStudentName(stdName);
                            reportDto1.setDepartmentName(deptName);
                            reportDto1.setSemesterName(semesterName);
                            reportDto1.setBatchName(batchName);
                            reportDtoList.add(reportDto1);
                        }
                    }
//                ReportDto reportDto1=new ReportDto();
//                reportDto1.setStudentId(stdId);
//                reportDto1.setStudentName(stdName);
//                reportDto1.setDepartmentName(deptName);


                }
//                System.out.println(students);


            }


            return reportDtoList;
        } else if (reportDto.getDepartmentIdList() != null && reportDto.getCourseIdList() == null && reportDto.getStudentIdList() == null) {
            List<StudentCourse> studentCourses = studentCourseRepository.findAllNotDeleted();
            List<StudentCourseAssessment> studentCourseAssessments = studentCourseAssessmentRepository.findAllNotDeleted();
            List<ReportDto> reportDtoList = new ArrayList<>();
            for (Integer deptId : reportDto.getDepartmentIdList()) {
                Department department = departmentRepository.findByIdNotDeleted(deptId);
                List<Student> students = department.getStudents();
                for (Student student : students) {
                    int stdId = student.getStudentId();
                    String stdName = student.getStudentName();
                    String deptName = student.getDepartment().getDepartmentName();
                    for (StudentCourse studentCourse : studentCourses) {
                        if (stdId == studentCourse.getStudent().getStudentId()) {
                            Course course = studentCourse.getCourse();
//                            String semesterName= studentCourse.getSemester().getSemesterName();
                            int corId = course.getCourseId();
                            String corName = course.getCourseName();
                            for (StudentCourseAssessment studentCourseAssessment : studentCourseAssessments) {
                                if (studentCourseAssessment.getStudentCourse().getStudentCourseId() == studentCourse.getStudentCourseId()) {
                                    String assessmentName = studentCourseAssessment.getAssessment().getAssessmentName();
                                    int total = studentCourseAssessment.getAssessment().getTotalMarks();
                                    int obtained = studentCourseAssessment.getObtainedMarks();
                                    ReportDto reportDto1 = new ReportDto();
                                    reportDto1.setCourseId(corId);
                                    reportDto1.setCourseName(corName);
                                    reportDto1.setStudentId(stdId);
                                    reportDto1.setStudentName(stdName);
                                    reportDto1.setDepartmentName(deptName);
//                                    reportDto1.setSemesterName(semesterName);
                                    reportDto1.setAssessmentName(assessmentName);
                                    reportDto1.setTotalMarks(total);
                                    reportDto1.setObtainedMarks(obtained);
                                    reportDtoList.add(reportDto1);
                                }

                            }
                        }
                    }


                }
            }
            return reportDtoList;
        }else if(reportDto.getDepartmentIdList() != null && reportDto.getCourseIdList() != null && reportDto.getStudentIdList() == null){
            List<ReportDto> reportDtoList = new ArrayList<>();
            List<StudentCourse> studentCourses=studentCourseRepository.findAllNotDeleted();
            List<StudentCourseAssessment> studentCourseAssessments=studentCourseAssessmentRepository.findAllNotDeleted();
            for (Integer deptId : reportDto.getDepartmentIdList()){
                Department department=departmentRepository.findByIdNotDeleted(deptId);
                for(Integer corId: reportDto.getCourseIdList()){
                    Course course=courseRepository.findByIdNotDeleted(corId);
                    for(StudentCourse studentCourse: studentCourses){
                        if(corId==studentCourse.getCourse().getCourseId() && studentCourse.getCourse().getDepartment().getDepartmentId()==deptId){
                            Student student=studentCourse.getStudent();
                            int stdId = student.getStudentId();
                            String stdName = student.getStudentName();
                            String deptName = department.getDepartmentName();
                            String corName = course.getCourseName();
                            for(StudentCourseAssessment studentCourseAssessment:studentCourseAssessments){
                                if(studentCourse.getStudentCourseId()==studentCourseAssessment.getStudentCourse().getStudentCourseId()){
                                    String assessmentName = studentCourseAssessment.getAssessment().getAssessmentName();
                                    int total = studentCourseAssessment.getAssessment().getTotalMarks();
                                    int obtained = studentCourseAssessment.getObtainedMarks();
                                    ReportDto reportDto1 = new ReportDto();
                                    reportDto1.setCourseId(corId);
                                    reportDto1.setCourseName(corName);
                                    reportDto1.setStudentId(stdId);
                                    reportDto1.setStudentName(stdName);
                                    reportDto1.setDepartmentName(deptName);
                                    reportDto1.setAssessmentName(assessmentName);
                                    reportDto1.setTotalMarks(total);
                                    reportDto1.setObtainedMarks(obtained);
                                    reportDtoList.add(reportDto1);
                                }
                            }


                        }
                    }

                }
            }

            return reportDtoList;

        }else {
            throw new RuntimeException("testing phase");
        }
    }
    public List<StudentCourseAssessmentDto> saveAllStudentCoursesAssessments(){
       List<StudentCourseAssessmentDto> studentCourseAssessmentDtoList=new ArrayList<>();
        List<Student> studentList=studentRepository.findAllNotDeleted();
        List<StudentCourse> studentCourseList=studentCourseRepository.findAllNotDeleted();
        List<Assessment> assessmentList=assessmentRepository.findAllNotDeleted();
        for(Student student:studentList){
            int stdId1=student.getStudentId();
            List<Integer> courseIdList=new ArrayList<>();
            List<Integer> studentCourseIdList=new ArrayList<>();
            for(StudentCourse studentCourse:studentCourseList){
                int stdId2=studentCourse.getStudent().getStudentId();
                if(stdId1==stdId2){
                    Integer corId1=studentCourse.getCourse().getCourseId();
                    courseIdList.add(corId1);
                    Integer stdCorId1=studentCourse.getStudentCourseId();
                    studentCourseIdList.add(stdCorId1);
//                    List<Assessment> assessments=new ArrayList<>();
                    for(Assessment assessment:assessmentList){
                        Integer corId2=assessment.getCourse().getCourseId();
                        if(corId1==corId2){
//                            assessments.add(assessment);
                            int totalMarks=assessment.getTotalMarks();

                            StudentCourseAssessment studentCourseAssessment=new StudentCourseAssessment();
                            Integer obtainedMarks=generateRandomMarks(totalMarks);
                            studentCourseAssessment.setObtainedMarks(obtainedMarks);
                            studentCourseAssessment.setAssessment(assessment);
                            studentCourseAssessment.setStudentCourse(studentCourse);
                            studentCourseAssessment = studentCourseAssessmentRepository.save(studentCourseAssessment);
                            StudentCourseAssessmentDto studentCourseAssessmentDto1=studentCourseAssessmentMapper.entityToDto(studentCourseAssessment);
//                          studentCourseAssessmentDto1 = studentCourseAssessmentMapper.entityToDto(studentCourseAssessment);
                            StudentCourseDto studentCourseDto = studentCourseMapper.entityToDto(studentCourse);
                            AssessmentDto assessmentDto = assessmentMapper.entityToDto(assessment);
                            studentCourseAssessmentDto1.setStudentCourseDto(studentCourseDto);
                            studentCourseAssessmentDto1.setAssessmentDto(assessmentDto);
                            studentCourseAssessmentDtoList.add(studentCourseAssessmentDto1);
                        }

                    }

                }
            }
        }
        return studentCourseAssessmentDtoList;
    }
    public Integer generateRandomMarks(int max){
        Random random = new Random();
        int maxValue = max+1;
        Integer randomNumber = random.nextInt(maxValue);
        return randomNumber;
    }
}



