package com.springmvc.student_management_system.serviceimpl;


import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.student_management_system.model.Student;
import com.springmvc.student_management_system.service.StudentService;
import com.springmvc.student_management_system.repository.StudentRepository;
@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    // No need for @Autowired from Spring 4.3+ if there's only one constructor
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student saveStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student updateStudent(Student student) {
        return studentRepository.save(student);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}

