package com.serenity.entity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "patients")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor


public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer patientId;

    @Column(name = "name" , nullable = false, length = 100)
    private String name;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "email", nullable = false , unique = true, length = 100)
    private String email;

    @Column(name = "registration_date", nullable = false)
    private LocalDate registrationDate;


    @Column(name = "medical_history")
    private String medicalHistory;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TherapySession> sessions = new ArrayList<>();

    // One patient has many payments
    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE}, fetch =  FetchType.LAZY)
    @JoinTable(
            name = "patient_programs",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private List<TherapyProgram> enrolledPrograms = new ArrayList<>();

    // Methods


    // add Programs
    public void addProgram(TherapyProgram program){
        enrolledPrograms.add(program);
        program.getEnrolledPatients().add(this);
    }

    // Remove programs
    public void removeProgram(TherapyProgram program){
        enrolledPrograms.remove(program);
        program.getEnrolledPatients().remove(this);
    }












}
