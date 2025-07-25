package com.example.SchoolManager.controller;

import com.example.SchoolManager.dto.StudentCreateDTO;
import com.example.SchoolManager.dto.StudentResponseDTO;
import com.example.SchoolManager.entity.Student;
import com.example.SchoolManager.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }


    @Operation(summary = "Cria um novo aluno", description = "Cria um novo aluno e retorna os dados criados.")
    @ApiResponse(responseCode = "201", description = "Aluno criado com sucesso")
    @PostMapping
    public ResponseEntity<StudentResponseDTO> createStudent(@RequestBody StudentCreateDTO dto){
        StudentResponseDTO creteStudent = studentService.createStudent(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(creteStudent);

        }

    @Operation(summary = "Busca alunos registrados", description = "Busca todos os alunos cadastrados e retorna todos eles.")
    @ApiResponse(responseCode = "201", description = "Alunos encontrados com sucesso")
    @GetMapping
    public ResponseEntity<List<StudentResponseDTO>> getAAllStudent(){
        return ResponseEntity.ok(studentService.getAllStudents());
    }

    @Operation(summary = "Busca um aluno pelo id", description = "Busca o aluno no banco através do ID informado.")
    @ApiResponse(responseCode = "201", description = "Aluno encontrado com sucesso")
    @GetMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(@PathVariable Long id){
        return ResponseEntity.ok(studentService.getStudentByid(id));
    }

    @Operation(summary = "Deleta cadastro do aluno", description = "Deleta um aluno através do ID informado.")
    @ApiResponse(responseCode = "201", description = "Aluno deletado com sucesso")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudentById(@PathVariable Long id){
        studentService.deleteStudentByid(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualiza dados do aluno", description = "Atualiza os dados do aluno e retorna os dados criados.")
    @ApiResponse(responseCode = "201", description = "Dados do aluno atualizados com sucesso")
    @PutMapping("/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(@PathVariable long id, @RequestBody StudentCreateDTO dto){
        StudentResponseDTO updatedStudent = studentService.updateStudent(id, dto);
        return ResponseEntity.ok(updatedStudent);
    }


}
