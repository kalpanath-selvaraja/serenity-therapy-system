package com.serenity.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "therapy_programs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TherapyProgram {

    @Id
    @Column(name = "program_id", length = 10)
    private String program_id;

    @Column(name = "name" , nullable = false, length = 100)
    private String name;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "fee", nullable = false, precision = 10, scale = 2) // to reduce rounding Error and more precise calculation
    private BigDecimal fee;


    @Column(name = "description")
    private String description;


    // Inverse side of Patient-Program relationship
    @ManyToMany(mappedBy = "enrolledPrograms", fetch = FetchType.LAZY)
    private List<Patient> enrolledPatients = new ArrayList<>();


    // Inverse side of Therapist-Program relationship
    @ManyToMany(mappedBy = "assignedPrograms" , fetch = FetchType.LAZY)
    private List<Therapist> assignedTherapists = new ArrayList<>();







}
