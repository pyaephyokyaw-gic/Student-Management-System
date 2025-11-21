package com.springmvc.student_management_system.service;

import java.util.List;

import com.springmvc.student_management_system.entity.TeacherEntity;

public interface TeacherService {
    List<TeacherEntity> getAllTeachers();
    TeacherEntity getTeacherById(Long id);
    void saveTeacher(TeacherEntity teacher);
    void updateTeacher(TeacherEntity teacher);
    void deleteTeacherById(Long id);
}
