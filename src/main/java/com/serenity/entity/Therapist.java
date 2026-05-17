package com.serenity.entity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "therapists")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Therapist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long therapistId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "specialization", nullable = false , length = 100)
    private String specialization;

    @Column(name = "phone", nullable = false, length = 20)
    private String phone;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "availability_status", nullable = false)
    private boolean availabilityStatus = true;

    @OneToMany(mappedBy = "therapist" , cascade = {CascadeType.PERSIST, CascadeType.MERGE} , fetch = FetchType.LAZY)
    private List<TherapySession> sessions = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST , CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinTable(
            name = "therapist_programs",
            joinColumns = @JoinColumn(name = "therapist_id"),
            inverseJoinColumns = @JoinColumn(name = "program_id")
    )
    private List<TherapyProgram> assignedPrograms = new ArrayList<>();


    public void addProgram(TherapyProgram program) {
        assignedPrograms.add(program);
        program.getAssignedTherapists().add(this);
    }

    public void removeProgram(TherapyProgram program) {
        assignedPrograms.remove(program);
        program.getAssignedTherapists().remove(this);
    }





}
