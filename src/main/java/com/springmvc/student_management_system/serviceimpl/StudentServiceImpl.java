package com.springmvc.student_management_system.serviceimpl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.springmvc.student_management_system.dto.StudentDto;
import com.springmvc.student_management_system.entity.StudentEntity;
import com.springmvc.student_management_system.repository.StudentRepository;
import com.springmvc.student_management_system.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto saveStudent(StudentDto studentDto) {
        StudentEntity entity = toEntity(studentDto);
        @SuppressWarnings("null")
        StudentEntity savedEntity = studentRepository.save(entity);
        return toDto(savedEntity);
    }

    @Override
    public StudentDto getStudentById(Long id) {
        return studentRepository.findById(Objects.requireNonNull(id))
                .map(this::toDto)
                .orElse(null);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        StudentEntity existingEntity = studentRepository.findById(Objects.requireNonNull(id))
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        
        existingEntity.setFirstName(studentDto.getFirstName());
        existingEntity.setLastName(studentDto.getLastName());
        existingEntity.setEmail(studentDto.getEmail());
        existingEntity.setCourse(studentDto.getCourse());
        
        @SuppressWarnings("null")
        StudentEntity updatedEntity = studentRepository.save(existingEntity);
        return toDto(updatedEntity);
    }

    @Override
    public void deleteStudentById(Long id) {
        studentRepository.deleteById(Objects.requireNonNull(id));
    }

    // Helper methods for conversion
    private StudentDto toDto(@NonNull StudentEntity entity) {
        StudentDto dto = new StudentDto();
        dto.setId(entity.getId());
        dto.setFirstName(entity.getFirstName());
        dto.setLastName(entity.getLastName());
        dto.setEmail(entity.getEmail());
        dto.setCourse(entity.getCourse());
        return dto;
    }

    private StudentEntity toEntity(StudentDto dto) {
        StudentEntity entity = new StudentEntity();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setCourse(dto.getCourse());
        return entity;
    }
}

