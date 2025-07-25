package com.example.SchoolManager.service;

import com.example.SchoolManager.dto.StudentCreateDTO;
import com.example.SchoolManager.dto.StudentResponseDTO;
import com.example.SchoolManager.entity.SchoolClass;
import com.example.SchoolManager.entity.Student;
import com.example.SchoolManager.exception.EmailAlreadyExistsException;
import com.example.SchoolManager.exception.ResourceNotFoundException;
import com.example.SchoolManager.repository.SchoolClassRepository;
import com.example.SchoolManager.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final SchoolClassRepository schoolClassRepository;

    public StudentService(StudentRepository studentRepository, SchoolClassRepository schoolClassRepository) {
        this.studentRepository = studentRepository;
        this.schoolClassRepository = schoolClassRepository;
    }

    public StudentResponseDTO createStudent(StudentCreateDTO dto){
        if (studentRepository.existsByEmail(dto.getEmail())) {
            throw new EmailAlreadyExistsException("Email já cadastrado!");
        }
        Student student = mapToEntity(dto);
        student = studentRepository.save(student);
        return  mapToDTO(student);
    }


    public List<StudentResponseDTO> getAllStudents(){
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public StudentResponseDTO getStudentByid(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
        return mapToDTO(student);

    }

    public void deleteStudentByid(Long id){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Id de aluno não encontrado"));
        studentRepository.delete(student);
    }

    public StudentResponseDTO updateStudent(Long id, StudentCreateDTO dto){
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword());
        student.setBirthDate(dto.getBirthdate());
        student.setGrade(dto.getGrade());

        SchoolClass schoolClass = schoolClassRepository.findById(dto.getSchoolClassId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada"));

        student.setSchoolClass(schoolClass);
        student = studentRepository.save(student);

        return mapToDTO(student);

    }

    private Student mapToEntity(StudentCreateDTO dto) {
        Student student = new Student();
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword());
        student.setGrade(dto.getGrade());

        student.setBirthDate(dto.getBirthdate());

        SchoolClass schoolClass = schoolClassRepository.findById(dto.getSchoolClassId())
                .orElseThrow(() -> new ResourceNotFoundException("Turma não encontrada"));
        student.setSchoolClass(schoolClass);

        return student;
    }

    private StudentResponseDTO mapToDTO(Student student) {

        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        dto.setSchoolClassId(student.getSchoolClass().getId());
        dto.setBirthdate(student.getBirthDate());
        dto.setGrade(student.getGrade());
        dto.setId(student.getId());

        return dto;
    }
}
