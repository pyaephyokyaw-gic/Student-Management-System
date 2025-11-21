package com.springmvc.student_management_system.service;

import java.util.List;

import com.springmvc.student_management_system.dto.StudentDto;

public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto saveStudent(StudentDto studentDto);

    StudentDto getStudentById(Long id);

    StudentDto updateStudent(Long id, StudentDto studentDto);

    void deleteStudentById(Long id);
}

