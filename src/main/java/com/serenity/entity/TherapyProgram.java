package com.serenity.entity;
import jakarta.persistence.*;
import lombok.*;
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



}
