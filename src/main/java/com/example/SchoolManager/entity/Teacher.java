package com.example.SchoolManager.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Teacher")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    @Email
    @NotBlank
    private String email;
    @Column(nullable = false)
    private String password;

    private LocalDate bithDate;

    @Min(1)
    @Max(12)
    private int grade;

    @ManyToMany
    @JoinTable(
            name = "teacher_schoolclass",
            joinColumns =  @JoinColumn(name = "teacher_id"),
            inverseJoinColumns = @JoinColumn(name = "school_class_id")
    )
    private List<SchoolClass> schoolClasses;

}
