package com.serenity.entity;
import jakarta.persistence.*;
import lombok.*;
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

}
