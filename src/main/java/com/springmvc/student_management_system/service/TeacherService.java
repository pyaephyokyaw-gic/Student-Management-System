package com.springmvc.student_management_system.service;

import java.util.List;

import com.springmvc.student_management_system.model.Teacher;

public interface TeacherService {
    List<Teacher> getAllTeachers();
    Teacher getTeacherById(Long id);
    void saveTeacher(Teacher teacher);
    void updateTeacher(Teacher teacher);
    void deleteTeacherById(Long id);
}
