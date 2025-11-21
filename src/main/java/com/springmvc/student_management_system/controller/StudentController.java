package com.springmvc.student_management_system.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.springmvc.student_management_system.dto.StudentDto;
import com.springmvc.student_management_system.service.StudentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public String listStudents(Model model) {
        List<StudentDto> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students"; 
    }

    @GetMapping("/students/new")
    public String createStudentForm(Model model) {
        StudentDto student = new StudentDto();
        model.addAttribute("student", student);
        return "create-student";
    }

    @PostMapping("/students")
    public String saveStudent(@Valid @ModelAttribute("student") StudentDto student, BindingResult result) {
        if (result.hasErrors()) {
            log.info("Validation errors while submitting form: {}", result.getAllErrors());
            return "create-student";
        }
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    @GetMapping("/students/edit/{id}")
    public String editStudentForm(@PathVariable Long id, Model model) {
        StudentDto student = studentService.getStudentById(id);
        if (student == null) {
            return "redirect:/students";
        }
        model.addAttribute("student", student);
        return "edit-student";
    }

    @PostMapping("/students/{id}")
    public String updateStudent(@PathVariable Long id, 
                               @Valid @ModelAttribute("student") StudentDto studentDto, 
                               BindingResult result) {
        if (result.hasErrors()) {
            log.info("Validation errors while updating student: {}", result.getAllErrors());
            studentDto.setId(id);
            return "edit-student";
        }
        
        studentService.updateStudent(id, studentDto);
        return "redirect:/students";
    }

    @GetMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return "redirect:/students";
    }
}

