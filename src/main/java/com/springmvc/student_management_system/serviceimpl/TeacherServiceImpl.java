package com.springmvc.student_management_system.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.student_management_system.model.Teacher;
import com.springmvc.student_management_system.repository.TeacherRepository;
import com.springmvc.student_management_system.service.TeacherService;
@Service
public class TeacherServiceImpl implements TeacherService  {
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public void saveTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void updateTeacher(Teacher teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
    
}
