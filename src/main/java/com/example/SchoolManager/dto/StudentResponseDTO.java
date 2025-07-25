package com.example.SchoolManager.dto;

import lombok.Data;

import java.time.LocalDate;
@Data
public class StudentResponseDTO {
    private Long id;
    private String name;
    private String email;
    private LocalDate birthdate;
    private int grade;
    private Long schoolClassId;
}
