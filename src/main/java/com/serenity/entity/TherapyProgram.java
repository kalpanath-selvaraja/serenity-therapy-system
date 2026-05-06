package com.serenity.entity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

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

    @Lob // to store large text in database
    @Column(name = "description")
    private String description;



}
