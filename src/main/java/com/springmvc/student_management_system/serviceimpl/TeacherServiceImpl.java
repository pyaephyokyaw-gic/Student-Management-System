package com.springmvc.student_management_system.serviceimpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.springmvc.student_management_system.entity.TeacherEntity;
import com.springmvc.student_management_system.repository.TeacherRepository;
import com.springmvc.student_management_system.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;

    public TeacherServiceImpl(TeacherRepository teacherRepository) {
        this.teacherRepository = teacherRepository;
    }

    @Override
    public TeacherEntity getTeacherById(Long id) {
        return teacherRepository.findById(id).orElse(null);
    }

    @Override
    public void saveTeacher(TeacherEntity teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void updateTeacher(TeacherEntity teacher) {
        teacherRepository.save(teacher);
    }

    @Override
    public void deleteTeacherById(Long id) {
        teacherRepository.deleteById(id);
    }

    @Override
    public List<TeacherEntity> getAllTeachers() {
        return teacherRepository.findAll();
    }
}
