package com.springmvc.student_management_system.service;


import java.util.List;

import com.springmvc.student_management_system.model.Student;

public interface StudentService {
    List<Student> getAllStudents();

    Student saveStudent(Student student);

    Student getStudentById(Long id);

    Student updateStudent(Student student);

    void deleteStudentById(Long id);
}

